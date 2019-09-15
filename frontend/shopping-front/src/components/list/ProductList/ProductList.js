import React from 'react';
import styles from './ProductList.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

const ProductItem = ({id, title, price, productFile}) => {
    const { directory, savedFileName }  = productFile;
    const imagePathUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI(directory + savedFileName);
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
                const {id, title, price, productFile} = product.toJS();
                return (
                    <ProductItem
                        title={title}
                        price={price}
                        productFile={productFile}
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