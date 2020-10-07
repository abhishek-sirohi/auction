INSERT INTO AUCTION.AUCTION_HOUSE (AUCTION_HOUSE_NAME, ADDRESS)
VALUE ("Sotheby's Auction House", "1334 York Ave, New York, NY 10021, United States");

INSERT INTO AUCTION.USER_INFO (FIRST_NAME, LAST_NAME, EMAIL, COUNTRY_CODE, CONTACT_NO, USER_STATUS, REGISTRATION_DATE)
VALUES ('John', 'Doe', 'john.doe@gmail.com', '+91', '8840021742', 'ACTIVE', now()),
('Buyer1', 'Bidder', 'buyer1@gmail.com', '+91', '8736257398', 'ACTIVE', now()),
('Buyer2', 'Bidder', 'buyer2@gmail.com', '+91', '8763475892', 'ACTIVE', now());

INSERT INTO AUCTION.AUCTION_ITEM (ITEM_NAME, DESCRIPTION, OWNER_ID)
VALUES ('Excalibur','Legendary sword of King Arthur', 1),
('Wallace Sword', 'Sword of William Wallace', 1);

INSERT INTO AUCTION.AUCTION_DATA
(AUCTION_HOUSE_ID, AUCTION_ITEM_ID, AUCTION_STATUS, STEP_RATE, MIN_BID_PRICE, UPDATED_ON, START_DATE, END_DATE, CREATED_ON, COMMISSION)
VALUES (1, 1, "RUNNING", 1000, 100000, now(), now(), DATE_ADD(now() , INTERVAL 12 DAY), now(), 5000.0),
(1, 2, "RUNNING", 20000, 150000, now(), now(), DATE_ADD(now() , INTERVAL 15 DAY), now(), 15000.0);