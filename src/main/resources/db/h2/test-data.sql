INSERT INTO ROLE (ID, CODE, DESCRIPTION) VALUES (1, 'ADMIN', 'ADMINISTRATOR ROLE');
INSERT INTO ROLE (ID, CODE, DESCRIPTION) VALUES (2, 'CUSTOMER', 'CUSTOMER ROLE');

INSERT INTO USER(USERNAME, PASSWORD, ID_ROLE) VALUES ('ADMIN', 'ADMIN', 1);
INSERT INTO USER(USERNAME, PASSWORD, ID_ROLE) VALUES ('EDWIN', 'EDWIN', 2);
INSERT INTO USER(USERNAME, PASSWORD, ID_ROLE) VALUES ('ALEXANDER', 'ALEXANDER', 2);

INSERT INTO PRODUCT(NAME, DESCRIPTION, PRICE, STOCK) VALUES ('Pop Corn', 'Pop Corn Bag', 3.5, 5);
INSERT INTO PRODUCT(NAME, DESCRIPTION, PRICE, STOCK) VALUES ('Oreo Cookies', 'Oreo Cookies', 1, 20);
INSERT INTO PRODUCT(NAME, DESCRIPTION, PRICE, STOCK) VALUES ('Tortillitas Jalapeno', 'Tortillitas Jalapeno', 0.25, 10);
INSERT INTO PRODUCT(NAME, DESCRIPTION, PRICE, STOCK) VALUES ('Snickers', 'Snickers', 1, 3);


