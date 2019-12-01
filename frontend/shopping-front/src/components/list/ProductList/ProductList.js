import React from 'react';
import styles from './ProductList.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

const ProductItem = ({id, title, price}) => {
    const imagePathUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI("");
    return (
        <Link className={cx('product-item')} to={`/product/${id}`}>
            <img src={imagePathUrl}/>
            <div className={cx('product-name')}>{title}</div>
            <div className={cx('product-price')}>{numberFormat.format(price)}원</div>
        </Link>
    )
}

const ProductList = ({products}) => {
    let productList = "";

    if(products.size > 0){
        productList = products.map(
            (product) => {
                const {id, title, price} = product.toJS();
                return (
                    <ProductItem
                        title={title}
                        price={price}
                        key={id}
                        id={id}
                    />
                )
            }
        )
    }

    return (
        <div className={cx('product-list')}>
            {productList}
        </div>
    );
};

export default ProductList;