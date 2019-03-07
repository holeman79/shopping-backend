INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 1, 'product_category', 'outer', '아우터');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 2, 'product_category', 'knit', '니트');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 3, 'product_category', 'shirt', '셔츠');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 4, 'product_category', 't_shirt', '티셔츠');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 5, 'product_category', 'pants', '팬츠');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 6, 'product_category', 'denim', '데님');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 7, 'product_category', 'accessory', '악세사리');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD001', 8, 'product_category', 'etc', '기타잡화');

INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 1, 'product_color', 'black', '블랙');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 2, 'product_color', 'gray', '그레이');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 3, 'product_color', 'charcoal', '차콜');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 4, 'product_color', 'navy', '네이비');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 5, 'product_color', 'blue', '블루');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 6, 'product_color', 'green', '그린');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 7, 'product_color', 'khaki','카키');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 8, 'product_color', 'purple', '퍼플');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 9, 'product_color', 'yellow', '옐로우');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 10, 'product_color', 'red', '레드');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 11, 'product_color', 'wine', '와인');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD002', 12, 'product_color', 'brown', '브라운');

INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD003', 1, 'product_size', 'small', 'S');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD003', 2, 'product_size', 'medium', 'M');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD003', 3, 'product_size', 'large', 'L');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD003', 4, 'product_size', 'x_large', 'XL');
INSERT INTO COMMON_CODE(group_code, code, group_name, code_value, show_value) VALUES('PROD003', 5, 'product_size', 'xx_large', 'XXL');

INSERT INTO PRODUCT(id, category, description, price, saved_money_rate, title) values(1, 'outer', 'hhhhhhh', 10000, 4, '베이직 램스울 니트');

INSERT INTO OPTION(id, color, number, size, product_id) values(1, 'blue', 20, 'L', 1);
INSERT INTO OPTION(id, color, number, size, product_id) values(2, 'red', 10, 'M', 1);
INSERT INTO OPTION(id, color, number, size, product_id) values(3, 'black', 30, 'XL', 1);

INSERT INTO PRODUCT_FILE(id, created_at, directory, file_size, original_file_name, saved_file_name, thumbnail_saved_file_name, upload_path, product_id)
VALUES(1, sysdate, '\images\products\outer\1.헬로우\', 15583, 'default_image.PNG', 'f72f36d5-bb42-44fd-a99f-3dfaaa12869d_default_image.PNG', 'Thumbnail_f72f36d5-bb42-44fd-a99f-3dfaaa12869d_default_image.PNG', 'C:\\upload', 1);

INSERT INTO PRODUCT_DETAIL_FILE(id, created_at, directory, file_size, original_file_name, product_id, saved_file_name, upload_path)
VALUES(1, sysdate, '\images\products\outer\1.헬로우\', 772665, 'knit1_type2.PNG', 1, '8a38b83a-0d42-490c-a042-a4e23debfccc_knit1_type2.PNG', 'C:\\upload');
INSERT INTO PRODUCT_DETAIL_FILE(id, created_at, directory, file_size, original_file_name, product_id, saved_file_name,  upload_path)
VALUES(2, sysdate, '\images\products\outer\1.헬로우\', 634071, 'knit1_type3.PNG', 1, 'dc5d71bd-d1f3-4f12-ae03-9bf642db6419_knit1_type3.PNG', 'C:\\upload');
