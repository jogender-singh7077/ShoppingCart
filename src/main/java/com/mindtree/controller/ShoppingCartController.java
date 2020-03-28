package com.mindtree.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.datatype.ProductType;
import com.mindtree.dto.CartProductDto;
import com.mindtree.dto.ProductDisplayDto;
import com.mindtree.dto.ProductSearchDto;
import com.mindtree.dto.UpdateItemDto;
import com.mindtree.dto.UserDetails;
import com.mindtree.entities.Cart;
import com.mindtree.entities.CartProduct;
import com.mindtree.entities.Product;
import com.mindtree.entities.User;
import com.mindtree.service.CartService;
import com.mindtree.service.ProductService;
import com.mindtree.service.UserService;
import com.mindtree.util.ShoppingCartHelper;
import com.mindtree.validator.ProductSearchValidator;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	
	private static final Logger logger = 
            LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private ProductSearchValidator productSearchValidator;
	
	@Autowired
	private ProductService productServiceImpl;
	
	@Autowired
	private CartService cartServiceImpl;
	
	@Autowired
	private UserService userServiceImpl;
		
	@GetMapping("/login")
	public String loginPage(final HttpServletRequest request, final HttpServletResponse response, final Map<String, Object> model) {
		logger.debug("login page display");
		model.put("userDetails", new UserDetails());
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(final HttpServletRequest request, final HttpServletResponse response, final Map<String, Object> model) {
		logger.debug("logging out the user from application");
		HttpSession session = request.getSession(false);
		if(session!= null) {
			session.invalidate();
		}
		model.put("userDetails", new UserDetails());
		return "login";
	}
	
	@PostMapping(value="/userLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String login(final HttpServletRequest request, final HttpServletResponse response, final Map<String, Object> model, final UserDetails userDetails) {
		HttpSession session = request.getSession(false);
		User user = userServiceImpl.findByNameAndPassword(userDetails.getName(), userDetails.getPassword());
		if(user != null) {
			logger.info("currently logged in user - {}", user);
			session.setAttribute("user", user);
			return init(request, response, model);
		}else {
			logger.debug("no user found with the provided credentials username - {} password - {} ", userDetails.getName(), userDetails.getPassword() );
			model.put("userDetails", new UserDetails());
			model.put("message", "Invalid credentials");
			return "login";
		}
	}
	
	@GetMapping("/welcome")
	public String init(final HttpServletRequest request, final HttpServletResponse response, final Map<String, Object> model) {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			logger.debug("invalid user attempt to access the application");
			model.put("userDetails", new UserDetails());
			return "login";
		}
		List<Product> avialableProducts = productServiceImpl.getAllProducts();
		List<ProductDisplayDto> products = ShoppingCartHelper.transformProductToScreenDto(avialableProducts);
		model.put("products", products);
		logger.debug("directing the user to welcome screen");
		return "main";
	}
	
	@GetMapping("/search")
	public String searchInit(final HttpServletRequest request, final HttpServletResponse response, final Map<String, Object> model) {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			logger.debug("invalid user attempt to access the application");
			model.put("userDetails", new UserDetails());
			return "login";
		}
		model.put("productSearch", new ProductSearchDto());
		model.put("category", Stream.of(ProductType.values())
                .map(ProductType::getValue)
                .collect(Collectors.toList()));
		return "productSearch";
	}
	
	@GetMapping("/product/search")
	public ModelAndView productSearch(final HttpServletRequest request, final HttpServletResponse response, final @ModelAttribute("productSearch") ProductSearchDto productSearchCriteria, BindingResult result) throws Exception{
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			logger.debug("invalid user attempt to access the application");
			return new ModelAndView("login", "", null);
		}
		logger.debug("product search by criteria - {} ", productSearchCriteria);
		productSearchValidator.validate(productSearchCriteria, result);
		if(result.hasErrors()) {
			logger.info("invalid criteria for searching the products");
			return new ModelAndView("error", "errorMessage", result.getAllErrors().get(0).getDefaultMessage());
		}
		List<Product> products = productServiceImpl.searchProductsByCriteria(productSearchCriteria);
		List<ProductDisplayDto> productDtos = ShoppingCartHelper.transformProductToScreenDto(products);
		return new ModelAndView("productList", "products", productDtos);
	}
	
	@ResponseBody
	@GetMapping(value="/addToCart/{productId}")
	public String addToCart(final HttpServletRequest request, final HttpSession session, @PathVariable final int productId) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			logger.debug("invalid user attempt to access the application");
			return "User not authenticated";
		}
		cartServiceImpl.addToCart(user, productId);
		return "Product Successfully Added To Cart";
	}
	
	@GetMapping(value="/viewcart")
	public String getCartDetails(final Map<String, Object> model, final HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			logger.debug("invalid user attempt to access the application");
			model.put("userDetails", new UserDetails());
			model.put("message", "User not authenticated to view the cart");
			return "login";
		}
		logger.debug("displaying the cart contents for the user - {} ", user);
		List<CartProduct> cartProducts = cartServiceImpl.getCartDetailsByUser(user);
		List<Integer> productIds = cartProducts.stream()
									.map(CartProduct::getProduct)
									.collect(Collectors.toList()).stream()
									.map(Product::getProductId)
									.collect(Collectors.toList());
		
		List<Product> products = productServiceImpl.findProductByProductIds(productIds);
		List<CartProductDto> cartItems = ShoppingCartHelper.transfromProductsToDto(cartProducts, products);
		double cartTotal = ShoppingCartHelper.calculateCartTotalPrice(cartItems);
		model.put("cartItems", cartItems);
		model.put("cartTotal", cartTotal);
		return "viewCart";
	}
	
	@ResponseBody
	@PutMapping(value="/removeFromCart")
	public String removeProductFromCart(@RequestBody Integer productId, final HttpSession session, final Map<String, Object> model) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			logger.debug("invalid user attempt to access the application");
			return "User not authenticated";
		}
		logger.debug("deleting the product with id - {} ", productId);
		cartServiceImpl.deleteProductFromCart(user, productId);
		return "Product Removed From The Cart";
	}
	
	@ResponseBody
	@DeleteMapping(value="/removeAllItems")
	public String removeAllProductsFromCart(final HttpServletRequest request, final HttpSession session, final Map<String, Object> model) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			logger.debug("invalid user attempt to access the application");
			return "User not authenticated";
		}
		logger.debug("deleting all the cart items for the user - {}", user);
		cartServiceImpl.deleteAllProductsFromCart(user);
		return "Cart Does Not Have Any Items At The Movement";
	}
	
	@ResponseBody
	@PutMapping(value="/updateCart")
	public double updateCartProduct(@RequestBody UpdateItemDto updateItem, final HttpServletRequest request, final HttpSession session, final Map<String, Object> model) {
		User user = (User) session.getAttribute("user");
		logger.info("updating the cart details.");
		if(user == null) {
			logger.debug("invalid user attempt to access the application");
			return Double.MIN_VALUE;
		}
		
		Cart userCart = cartServiceImpl.getCartByUser(user);
		Optional<CartProduct> cartProduct = userCart.getProducts().stream().filter(product -> product.getProduct().getProductId().equals(updateItem.getProductId())).findFirst();
		if(updateItem.getQuantity() == 0) {
			logger.debug("deleting the cart item - {} for the user - {} ", cartProduct.get().getProduct(), user);
			cartServiceImpl.deleteProductFromCart(user, cartProduct.get().getProduct().getProductId());
		}else {
			cartServiceImpl.updateCart(updateItem.getQuantity(), userCart, cartProduct.get().getProduct());
		}
		
		double price = ShoppingCartHelper.calculateCartTotal(userCart.getProducts(), updateItem);
		return price;
	}
	
}
