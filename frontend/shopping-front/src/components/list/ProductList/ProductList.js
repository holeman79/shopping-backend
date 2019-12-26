import React from 'react';
import styles from './ProductList.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

const ProductItem = ({id, name, price, savedUri}) => {
    const imagePathUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI(savedUri);
    return (
        <Link className={cx('product-item')} to={`/product/${id}`}>
            <img src={imagePathUrl}/>
            <div className={cx('product-name')}>{name}</div>
            <div className={cx('product-price')}>{numberFormat.format(price)}원</div>
        </Link>
    )
}

const ProductList = ({products}) => {
    let productList = "";

    if(products.size > 0){
        productList = products.map(
            (product) => {
                const {id, name, price} = product.toJS();
                const productImageGroups = product.get('productImageGroups');
                let savedUri;
                if(productImageGroups != "" && productImageGroups.size > 0){
                    let productImage;
                    for(let imageGroup of productImageGroups){
                        if(imageGroup.get('name') === 'header') productImage = imageGroup.get('productImages').get(0);
                    }
                    savedUri = productImage.get('savedUri');
                }
                return (
                    <ProductItem
                        savedUri={savedUri}
                        name={name}
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