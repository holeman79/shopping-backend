INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(1, 'outer', '[겉 감] 나일론 2%, [겉 감] 폴리에스터 44%, [겉 감] 레이온 5%, [겉 감] 모 49%, [배 색] 폴리에스터 50%, [배 색] 모 50%, [안 감] 폴리에스터 100%', 209500, 1, '다크네이비 쓰리버튼 울 롱 코트', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(2, 'outer', '[AD14] 액세서리, [AD20] 헹거칩, [겉 감] 모 90%, [겉 감] 캐시미어 10%, [배 색] 나일론 11%, [배 색] 모 89%, [안 감] 폴리에스터 100%, [충전재] 폴리에스터 100%', 249500, 1, '네이비멜란지 체스터 캐시미어 코트', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(3, 'outer', '[AD8] (여우), [AD8] 천연모피, [AD8] FUR, [겉 감] 폴리에스터 100%, [배 색] 아크릴 64%, [배 색] 폴리에스터 9%, [배 색] 폴리우레탄 2%, [배 색] 모 25%, [안 감] 폴리에스터 100%, [충전재] 거위깃털 20%, [충전재] 거위솜털 80%', 329500, 1, '폭스퍼 구스다운 롱 패딩 점퍼', now(), 'holeman79');

INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(4, 'knit', '[겉 감] 나일론 22%, [겉 감] 아크릴 1%, [겉 감] 모 77%', 69500, 1, '울혼방 반목폴라 니트', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(5, 'knit', '[겉 감] 나일론 20%, [겉 감] 모 80%', 69500, 1, '그래픽 패턴 아이보리 라운드 니트', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(6, 'knit', '[겉 감] 아크릴 38%, [겉 감] 나일론 30%, [겉 감] 모 32%', 179400, 3, '카멜 브이넥 롱 포켓 가디건', now(), 'holeman79');

INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(7, 'shirt', '[겉 감] 마 100%', 107100, 5, '린넨 썸머 셔츠', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(8, 'shirt', '[겉 감] 폴리에스터 98%, [겉 감] 폴리우레탄 2%', 69300, 3, '오버핏 컬러 스트라이프 블루 셔츠', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(9, 'shirt', '[겉 감] 면 37%, [겉 감] 폴리에스터 57%, [겉 감] 폴리우레탄 6%', 79000, 5, '퓨어 화이트 컷어웨이 셔츠', now(), 'holeman79');

INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(10, 't_shirt', '[겉 감] 면 60%, [겉 감] 폴리에스터 40%, [배 색] 면 59%, [배 색] 폴리에스터 39%, [배 색] 폴리우레탄 2%', 79000, 5, '뮌헨 라운드 맨투맨', now(), 'holeman79');
INSERT INTO tb_product(id, category_code, description, price, saved_money_rate, title, created_at, created_id)
values(11, 't_shirt', '[겉 감] 면 100%', 71100, 5, '썸머 스포티 라운드 반팔 티셔츠', now(), 'holeman79');

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(1, 'blue', 20, 'medium', 1);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(2, 'blue', 30, 'large', 1);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(3, 'red', 20, 'large', 1);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(4, 'red', 10, 'medium', 1);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(5, 'blue', 13, 'small', 1);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(6, 'black', 30, 'large', 1);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(7, 'blue', 20, 'small', 2);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(8, 'blue', 30, 'medium', 2);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(9, 'blue', 30, 'large', 2);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(10, 'blue', 20, 'small', 3);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(11, 'blue', 30, 'medium', 3);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(12, 'blue', 30, 'large', 3);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(13, 'black', 20, 'small', 3);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(14, 'black', 30, 'medium', 3);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(15, 'black', 30, 'large', 3);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(16, 'blue', 20, 'small', 4);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(17, 'blue', 30, 'medium', 4);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(18, 'blue', 30, 'large', 4);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(19, 'white', 30, 'medium', 4);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(20, 'white', 30, 'large', 4);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(21, 'ivory', 20, 'small', 5);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(22, 'ivory', 30, 'medium', 5);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(23, 'ivory', 30, 'large', 5);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(24, 'red', 30, 'medium', 5);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(25, 'red', 30, 'large', 5);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(26, 'camel', 20, 'small', 6);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(27, 'camel', 30, 'medium', 6);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(28, 'camel', 30, 'large', 6);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(29, 'navy', 20, 'small', 7);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(30, 'navy', 30, 'medium', 7);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(31, 'navy', 30, 'large', 7);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(32, 'white', 30, 'small', 7);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(33, 'white', 30, 'medium', 7);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(34, 'blue', 20, 'small', 8);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(35, 'blue', 30, 'medium', 8);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(36, 'blue', 30, 'large', 8);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(37, 'white', 20, 'small', 9);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(38, 'white', 30, 'medium', 9);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(39, 'white', 30, 'large', 9);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(40, 'white', 20, 'small', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(41, 'white', 30, 'medium', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(42, 'white', 30, 'large', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(43, 'navy', 20, 'small', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(44, 'navy', 30, 'medium', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(45, 'navy', 30, 'large', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(46, 'green', 20, 'small', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(47, 'green', 30, 'medium', 10);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(48, 'green', 30, 'large', 10);

INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(49, 'navy', 20, 'small', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(50, 'navy', 30, 'medium', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(51, 'navy', 30, 'large', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(52, 'yellow', 20, 'small', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(53, 'yellow', 30, 'medium', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(54, 'yellow', 30, 'large', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(55, 'white', 20, 'small', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(56, 'white', 30, 'medium', 11);
INSERT INTO tb_option(id, color_code, number, size_code, product_id) values(57, 'white', 30, 'large', 11);