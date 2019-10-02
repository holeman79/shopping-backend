INSERT INTO tb_color(code, name) VALUES('red', '레드');
INSERT INTO tb_color(code, name) VALUES('green', '그린');
INSERT INTO tb_color(code, name) VALUES('blue', '블루');
INSERT INTO tb_color(code, name) VALUES('black', '블랙');
INSERT INTO tb_color(code, name) VALUES('white', '화이트');
INSERT INTO tb_color(code, name) VALUES('ivory', '아이보리');
INSERT INTO tb_color(code, name) VALUES('khaki', '카키');
INSERT INTO tb_color(code, name) VALUES('camel', '카멜');
INSERT INTO tb_color(code, name) VALUES('navy', '네이비');
INSERT INTO tb_color(code, name) VALUES('yellow', '옐로우');

INSERT INTO tb_size(code, name, size_order) VALUES('small', 'S', 1);
INSERT INTO tb_size(code, name, size_order) VALUES('medium', 'M', 2);
INSERT INTO tb_size(code, name, size_order) VALUES('large', 'L', 3);

INSERT INTO tb_category(code, name) VALUES('outer', '아우터');
INSERT INTO tb_category(code, name) VALUES('knit', '니트');
INSERT INTO tb_category(code, name) VALUES('shirt', '셔츠');
INSERT INTO tb_category(code, name) VALUES('t_shirt', '티셔츠');
INSERT INTO tb_category(code, name) VALUES('pants', '팬츠');
INSERT INTO tb_category(code, name) VALUES('denim', '데님');
INSERT INTO tb_category(code, name) VALUES('accessory', '악세사리');
INSERT INTO tb_category(code, name) VALUES('etc', '기타잡화');

insert into tb_payment_way(code, name) values('no_bankbook', '무통장입금');
insert into tb_payment_way(code, name) values('kakaopay', '카카오페이');
insert into tb_payment_way(code, name) values('bank_transfer', '계좌이체');
insert into tb_payment_way(code, name) values('phone', '핸드폰 소액결제');

insert into tb_bank_book(code, bank_name, bank_account, account_holder) values('hana01', '하나은행', '444-999999-333444', '(주)쇼핑몰');
insert into tb_bank_book(code, bank_name, bank_account, account_holder) values('shinhan01', '신한은행', '110-222-888888', '(주)쇼핑몰');
insert into tb_bank_book(code, bank_name, bank_account, account_holder) values('woori01', '우리은행', '1111-101-999999', '(주)쇼핑몰');

insert into tb_phone_first_number_type(code, number_order) values('010', 1);
insert into tb_phone_first_number_type(code, number_order) values('011', 2);
insert into tb_phone_first_number_type(code, number_order) values('016', 3);
insert into tb_phone_first_number_type(code, number_order) values('017', 4);
insert into tb_phone_first_number_type(code, number_order) values('019', 5);

INSERT INTO tb_role(id, name) VALUES(1, 'USER');
INSERT INTO tb_role(id, name) VALUES(2, 'ADMIN');
insert into tb_user(id, user_id, name, email, password, created_date, updated_date, role_id) values(1, 'holeman90', '조원진', 'holeman79@nate.com', '$2a$10$o.q9d5/Oq0b2oYCk09X56uux8RQ6pAEA5OzUpOyKO.WvOe8Ybvsa6', now(), now(), 2);

--insert into USER_ROLES(user_id, role_id) values(1, 1);
--insert into USER_ROLES(user_id, role_id) values(1, 2);

