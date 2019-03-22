--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value,) VALUES('PROD001', 1, 'product_category', '아우터');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 2, 'product_category', '니트');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 3, 'product_category', '셔츠');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 4, 'product_category', '티셔츠');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 5, 'product_category', '팬츠');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 6, 'product_category', '데님');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 7, 'product_category', '악세사리');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD001', 8, 'product_category', '기타잡화');
--
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 1, 'product_color', '블랙');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 2, 'product_color', '그레이');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 3, 'product_color', '차콜');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 4, 'product_color', '네이비');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 5, 'product_color', '블루');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 6, 'product_color', '그린');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 7, 'product_color','카키');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 8, 'product_color', '퍼플');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 9, 'product_color', '옐로우');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 10, 'product_color', '레드');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 11, 'product_color', '와인');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD002', 12, 'product_color', '브라운');
--
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD003', 1, 'product_size','S');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD003', 2, 'product_size','M');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD003', 3, 'product_size','L');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD003', 4, 'product_size','XL');
--INSERT INTO COMMON_CODE(group_code, code, group_name, code_value) VALUES('PROD003', 5, 'product_size','XXL');
--



INSERT INTO COLOR(code, name) VALUES('red', '레드');
INSERT INTO COLOR(code, name) VALUES('green', '그린');
INSERT INTO COLOR(code, name) VALUES('blue', '블루');
INSERT INTO COLOR(code, name) VALUES('black', '블랙');

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

INSERT INTO PRODUCT(id, category_code, description, price, saved_money_rate, title, created_id) values(1, 'outer', 'hhhhhhh', 10000, 4, '베이직 램스울 니트', 'holeman79');

INSERT INTO OPTION(id, color_code, number, size_code, product_id) values(1, 'blue', 20, 'large', 1);
INSERT INTO OPTION(id, color_code, number, size_code, product_id) values(2, 'red', 10, 'large', 1);
INSERT INTO OPTION(id, color_code, number, size_code, product_id) values(3, 'red', 10, 'medium', 1);
INSERT INTO OPTION(id, color_code, number, size_code, product_id) values(4, 'blue', 13, 'small', 1);
INSERT INTO OPTION(id, color_code, number, size_code, product_id) values(5, 'black', 30, 'large', 1);

INSERT INTO PRODUCT_FILE(id, created_at, directory, file_size, original_file_name, saved_file_name, thumbnail_saved_file_name, upload_path, product_id)
VALUES(1, sysdate, '\images\products\outer\1.헬로우\', 15583, 'default_image.PNG', 'f72f36d5-bb42-44fd-a99f-3dfaaa12869d_default_image.PNG', 'Thumbnail_f72f36d5-bb42-44fd-a99f-3dfaaa12869d_default_image.PNG', 'C:\\upload', 1);

INSERT INTO PRODUCT_DETAIL_FILE(id, created_at, directory, file_size, original_file_name, product_id, saved_file_name, upload_path)
VALUES(1, sysdate, '\images\products\outer\1.헬로우\', 772665, 'knit1_type2.PNG', 1, '8a38b83a-0d42-490c-a042-a4e23debfccc_knit1_type2.PNG', 'C:\\upload');
INSERT INTO PRODUCT_DETAIL_FILE(id, created_at, directory, file_size, original_file_name, product_id, saved_file_name,  upload_path)
VALUES(2, sysdate, '\images\products\outer\1.헬로우\', 634071, 'knit1_type3.PNG', 1, 'dc5d71bd-d1f3-4f12-ae03-9bf642db6419_knit1_type3.PNG', 'C:\\upload');

insert into USERS(id, username, email, password) values(1, 'holeman90', 'holeman79@nate.com', '$2a$10$o.q9d5/Oq0b2oYCk09X56uux8RQ6pAEA5OzUpOyKO.WvOe8Ybvsa6');
INSERT INTO ROLES(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO ROLES(id, name) VALUES(2, 'ROLE_ADMIN');

insert into USER_ROLES(user_id, role_id) values(1, 1);

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
