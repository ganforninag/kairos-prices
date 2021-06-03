CREATE TABLE PRICE(
                      PRICE_ID             NUMBER GENERATED ALWAYS AS IDENTITY,
                      BRAND_ID             NUMBER NOT NULL,
                      PRODUCT_ID           NUMBER NOT NULL,
                      START_DATE           DATETIME NOT NULL,
                      END_DATE             DATETIME NOT NULL,
                      PRIORITY             NUMBER NOT NULL,
                      PRICE                NUMERIC(20,2) NOT NULL,
                      CURRENCY             VARCHAR2(3) NOT NULL,
                      LAST_UPDATE_DATE     DATETIME NOT NULL,
                      LAST_UPDATE_BY       VARCHAR2(255) NOT NULL,
                      PRIMARY KEY (PRICE_ID)
);

-- Create comments
COMMENT ON TABLE PRICE IS 'Table containing product prices';
COMMENT ON COLUMN PRICE.PRICE_ID IS 'Primary key';
COMMENT ON COLUMN PRICE.BRAND_ID IS 'Product brand identifier';
COMMENT ON COLUMN PRICE.PRODUCT_ID IS 'Product identifier';
COMMENT ON COLUMN PRICE.START_DATE IS 'Product price start date';
COMMENT ON COLUMN PRICE.END_DATE IS 'Product price end date';
COMMENT ON COLUMN PRICE.PRIORITY IS 'Product price priority';
COMMENT ON COLUMN PRICE.PRICE IS 'Product price';
COMMENT ON COLUMN PRICE.CURRENCY IS 'Product price currency';
COMMENT ON COLUMN PRICE.LAST_UPDATE_DATE IS 'Last product price update date';
COMMENT ON COLUMN PRICE.LAST_UPDATE_BY IS 'Last product price update user';