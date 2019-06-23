CREATE TABLE ROLE (
    ID INT NOT NULL AUTO_INCREMENT,
    CODE VARCHAR(25) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    UNIQUE UNQ_CODE (CODE),
    PRIMARY KEY (ID)
);

CREATE TABLE USER (
    ID INT NOT NULL AUTO_INCREMENT,
    USERNAME VARCHAR(25) NOT NULL,
    PASSWORD VARCHAR(25) NOT NULL,
    ID_ROLE INT NOT NULL,
    UNIQUE UNQ_USERNAME (USERNAME),
    PRIMARY KEY (ID),
    CONSTRAINT FK_USER_ROLE FOREIGN KEY (ID_ROLE) REFERENCES ROLE(ID)
);

CREATE TABLE PRODUCT (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(25) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    PRICE DECIMAL NOT NULL,
    POPULARITY INT DEFAULT 0,
    STOCK INT DEFAULT 0,
    UNIQUE UNQ_NAME (NAME),
    PRIMARY KEY (ID)
);

CREATE TABLE BUY_PRODUCT (
    ID INT NOT NULL AUTO_INCREMENT,
    ID_USER INT NOT NULL,
    ID_PRODUCT INT NOT NULL,
    QUANTITY INT NOT NULL,
    BUY_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_BUYPROD_USER FOREIGN KEY (ID_USER) REFERENCES USER(ID),
    CONSTRAINT FK_BUY_PROD_PRODUCT FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT(ID),
    PRIMARY KEY (ID)
)

CREATE TABLE LOG_PRICE(
    ID INT NOT NULL AUTO_INCREMENT,
    ID_PRODUCT NOT NULL,
    OLD_PRICE DECIMAL,
    NEW_PRICE DECIMAL,
    USERNAME VARCHAR2(25),
    PRIMARY KEY (ID)
)