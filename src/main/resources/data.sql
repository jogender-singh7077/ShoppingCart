insert into products (PRODUCT_TYPE, PRODUCT_ID, PRICE, PRODUCT_NAME, AUTHOR, GENRE, PUBLICATIONS)
values ('book', 100, 403, 'Persepolis', 'Marjane Satrapi', 'Fiction', 'Pantheon Books');

insert into products (PRODUCT_TYPE, PRODUCT_ID, PRICE, PRODUCT_NAME, AUTHOR, GENRE, PUBLICATIONS)
values ('book', 101, 221, 'The Last Lecture', 'Randy Pausch and Jeffrey Zaslow', 'Non-fiction', 'Hachette Books');

insert into products (PRODUCT_TYPE, PRODUCT_ID, PRICE, PRODUCT_NAME, AUTHOR, GENRE, PUBLICATIONS)
values ('book', 102, 523, 'Long Walk To Freedom', 'Nelson Mandela', 'Autobiography', 'Little Brown & Co');

insert into products (PRODUCT_TYPE, PRODUCT_ID, PRICE, PRODUCT_NAME, BRAND, CLOTH_TYPE, DESIGN)
values ('apparal', 103, 75000, 'Sweatshirt', 'Fendi', 'Sweatshirt', 'Fendi');

insert into products (PRODUCT_TYPE, PRODUCT_ID, PRICE, PRODUCT_NAME, BRAND, CLOTH_TYPE, DESIGN)
values ('apparal', 104, 82500, 'Logo Technical Hoodie', 'Prada', 'Hoodie', 'Prada');

insert into users (USER_ID, NAME, EMAIL_ADDRESS, PASSWORD)
values (1, 'admin', 'jogender.singh@mindtree.com', 'admin');

insert into users (USER_ID, NAME, EMAIL_ADDRESS, PASSWORD)
values (2, 'Jogender', 'developer@mindtree.com', 'dev');

insert into cart (CART_ID, USER_ID)
values (20, 1);

insert into cart (CART_ID, USER_ID)
values (30, 2);