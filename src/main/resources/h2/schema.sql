create table IF NOT EXISTS BRAND (
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  PRIMARY KEY ( ID )
);

create table IF NOT EXISTS PRICES (
  ID int not null AUTO_INCREMENT,
  BRAND_ID int not null,
  START_DATE TIMESTAMP not null,
  END_DATE TIMESTAMP not null,
  PRICE_LIST int not null,
  PRODUCT_ID varchar(100) not null,
  PRIORITY int not null,
  PRICE double not null,
  CURR varchar(100) not null,
  PRIMARY KEY ( ID ),
  FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID)
);
