
INSERT INTO COLOR(code, name) VALUES('red', '레드');
INSERT INTO COLOR(code, name) VALUES('green', '그린');
INSERT INTO COLOR(code, name) VALUES('blue', '블루');
INSERT INTO COLOR(code, name) VALUES('black', '블랙');
INSERT INTO COLOR(code, name) VALUES('white', '화이트');
INSERT INTO COLOR(code, name) VALUES('ivory', '아이보리');
INSERT INTO COLOR(code, name) VALUES('khaki', '카키');
INSERT INTO COLOR(code, name) VALUES('camel', '카멜');
INSERT INTO COLOR(code, name) VALUES('navy', '네이비');
INSERT INTO COLOR(code, name) VALUES('yellow', '옐로우');

INSERT INTO SIZE(code, name, size_order) VALUES('small', 'S', 1);
INSERT INTO SIZE(code, name, size_order) VALUES('medium', 'M', 2);
INSERT INTO SIZE(code, name, size_order) VALUES('large', 'L', 3);

INSERT INTO CATEGORY(code, name) VALUES('outer', '아우터');
INSERT INTO CATEGORY(code, name) VALUES('knit', '니트');
INSERT INTO CATEGORY(code, name) VALUES('shirt', '셔츠');
INSERT INTO CATEGORY(code, name) VALUES('t_shirt', '티셔츠');
INSERT INTO CATEGORY(code, name) VALUES('pants', '팬츠');
INSERT INTO CATEGORY(code, name) VALUES('denim', '데님');
INSERT INTO CATEGORY(code, name) VALUES('accessory', '악세사리');
INSERT INTO CATEGORY(code, name) VALUES('etc', '기타잡화');

insert into PAYMENT_WAY(code, name) values('no_bankbook', '무통장입금');
insert into PAYMENT_WAY(code, name) values('credit_card', '신용카드');
insert into PAYMENT_WAY(code, name) values('bank_transfer', '계좌이체');
insert into PAYMENT_WAY(code, name) values('phone', '핸드폰 소액결제');

insert into BANK_BOOK(code, bank_name, bank_account, account_holder) values('hana01', '하나은행', '444-999999-333444', '(주)쇼핑몰');
insert into BANK_BOOK(code, bank_name, bank_account, account_holder) values('shinhan01', '신한은행', '110-222-888888', '(주)쇼핑몰');
insert into BANK_BOOK(code, bank_name, bank_account, account_holder) values('woori01', '우리은행', '1111-101-999999', '(주)쇼핑몰');

insert into PHONE_FIRST_NUMBER_TYPE(code, number_order) values('010', 1);
insert into PHONE_FIRST_NUMBER_TYPE(code, number_order) values('011', 2);
insert into PHONE_FIRST_NUMBER_TYPE(code, number_order) values('016', 3);
insert into PHONE_FIRST_NUMBER_TYPE(code, number_order) values('017', 4);
insert into PHONE_FIRST_NUMBER_TYPE(code, number_order) values('019', 5);

insert into USERS(id, username, email, password) values(1, 'holeman90', 'holeman79@nate.com', '$2a$10$o.q9d5/Oq0b2oYCk09X56uux8RQ6pAEA5OzUpOyKO.WvOe8Ybvsa6');
INSERT INTO ROLES(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO ROLES(id, name) VALUES(2, 'ROLE_ADMIN');

insert into USER_ROLES(user_id, role_id) values(1, 1);


