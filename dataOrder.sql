-- H2 2.2.224;
;             
CREATE USER IF NOT EXISTS "SA" SALT '0b503cd7d2f6942c' HASH '5711aea192dd08cb3935ce7588d113d7c8a0dc4bf39941acc122e5c7d4c32b67' ADMIN;         
CREATE SEQUENCE "PUBLIC"."ORDERS_SEQ" START WITH 1 RESTART WITH 101 INCREMENT BY 50;          
CREATE MEMORY TABLE "PUBLIC"."ORDERS"(
    "ORDER_ID" BIGINT NOT NULL,
    "ASSET_NAME" CHARACTER VARYING(255),
    "CREATE_DATE" DATE,
    "CUSTOMER_ID" CHARACTER VARYING(255),
    "ORDER_SIDE" CHARACTER VARYING(255),
    "PRICE" FLOAT(53) NOT NULL,
    "SIZE" INTEGER NOT NULL,
    "STATUS" CHARACTER VARYING(255)
);       
ALTER TABLE "PUBLIC"."ORDERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("ORDER_ID"); 
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.ORDERS;  
INSERT INTO "PUBLIC"."ORDERS" VALUES
(1, '3Asset', DATE '2024-10-17', '1232334', 'SELL', 3.0, 23, 'MATCHED'),
(2, '3Asset', DATE '2024-10-17', '1232332', 'SELL', 3.0, 23, 'PENDING'),
(3, '3Asset', DATE '2024-10-17', '1232332', 'SELL', 3.0, 23, 'PENDING'),
(4, '3Asset', DATE '2024-10-17', '1232332', 'SELL', 3.0, 23, 'MATCHED');  
CREATE MEMORY TABLE "PUBLIC"."ASSET"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "ASSET_NAME" CHARACTER VARYING(255),
    "CUSTOMER_ID" CHARACTER VARYING(255),
    "SIZE" INTEGER NOT NULL,
    "USABLE_SIZE" INTEGER NOT NULL
);    
ALTER TABLE "PUBLIC"."ASSET" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3" PRIMARY KEY("ID");        
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.ASSET;   
INSERT INTO "PUBLIC"."ASSET" VALUES
(1, 'ASSET1', '4578551', 25, 100),
(2, 'TRY', '4578551', 25, 100),
(3, 'ASSET2', '4578552', 25, 100),
(4, 'ASSET3', '4578553', 20, 78),
(5, 'ASSET4', '4578554', 22, 89);            
