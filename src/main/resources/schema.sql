DROP TABLE IF EXISTS PRODUCTS;
CREATE TABLE PRODUCTS(
    PRODUCT_ID INT PRIMARY KEY AUTO_INCREMENT,
    PRODUCT_NAME VARCHAR(150)           NOT NULL           COMMENT '상품명',
    CATEGORY     VARCHAR(50)            NOT NULL           COMMENT '카테고리명',
    PRICE INT                           NOT NULL           COMMENT '상품 가격',
    SAVED_MONEY_RATE FLOAT              NOT NULL           COMMENT '적립율',
    PRODUCT_DESCRIPTION VARCHAR(1000)   NOT NULL           COMMENT '상품 설명',
    CREATED_DATE TIMESTAMP              NOT NULL           COMMENT '상품 등록일자',
    CREATOR_ID VARCHAR(100)             NOT NULL           COMMENT '등록자 ID'
);

DROP TABLE IF EXISTS OPTION_GROUP_SPECS;
CREATE TABLE OPTION_GROUP_SPECS(
    OPTION_GROUP_SPEC_ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(150)       NOT NULL           COMMENT '옵션그룹스펙명',
    EXCLUSIVE BIT           NOT NULL           COMMENT '배타선택 여부',
    BASIC BIT               NOT NULL           COMMENT '기본 그룹 여부',
    PRODUCT_ID INT          NULL               COMMENT '상품 ID'
);

DROP TABLE IF EXISTS OPTION_SPECS;
CREATE TABLE OPTION_SPECS(
    OPTION_SPEC_ID INT PRIMARY KEY AUTO_INCREMENT,
    COLOR VARCHAR(50)           NOT NULL           COMMENT '색상',
    SIZE  VARCHAR(50)           NOT NULL           COMMENT '사이즈',
    PRICE INT                   NOT NULL           COMMENT '가격',
    TOTAL_COUNT INT             NOT NULL           COMMENT '총 갯수',
    OPTION_GROUP_SPEC_ID INT    NULL               COMMENT '옵션그룹스펙ID'
);

DROP TABLE IF EXISTS PRODUCT_IMAGE_GROUPS;
CREATE TABLE PRODUCT_IMAGE_GROUPS(
    PRODUCT_IMAGE_GROUP_ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(150)           NOT NULL           COMMENT '상품이미지그룹명',
    PRODUCT_ID INT              NULL               COMMENT '상품 ID'
);

DROP TABLE IF EXISTS PRODUCT_IMAGES;
CREATE TABLE PRODUCT_IMAGES(
    PRODUCT_IMAGE_ID INT PRIMARY KEY AUTO_INCREMENT,
    IMAGE_NAME VARCHAR(150)           NOT NULL           COMMENT '상품이미지명',
    IMAGE_SIZE INT                    NOT NULL           COMMENT '이미지사이즈',
    PRODUCT_IMAGE_GROUP_ID INT        NULL               COMMENT '상품그룹ID',
);

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
    USER_ID         INT PRIMARY KEY AUTO_INCREMENT,
    USER_INPUT_ID   VARCHAR(50)                      COMMENT '유저입력아이디',
    PASSWORD        VARCHAR(60)                      COMMENT '패스워드',
    USER_NAME       VARCHAR(50)                      COMMENT '유저이름',
    EMAIL           VARCHAR(100)                     COMMENT '이메일',
    IMAGE_URL       VARCHAR(100)                     COMMENT '유저이미지경로',
    SOCIAL_ID       VARCHAR(50)                      COMMENT '소셜ID',
    SOCIAL_TYPE     VARCHAR(30)                      COMMENT '소셜타입',
    ROLE_TYPE       VARCHAR(30)       NOT NULL       COMMENT '권한타입',
    CREATED_DATE    TIMESTAMP         NOT NULL       COMMENT '유저 등록일자',
    UPDATED_DATE    TIMESTAMP                COMMENT '유저 업데이트일자'
);