import React from 'react';
import styles from './ProductViewBody.scss';
import classNames from 'classnames/bind';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const ProductViewBody = ({product}) => {
    const description = product.get('description');
    const productImageGroups = product.get('productImageGroups');
    let productImageList;

    if(productImageGroups.size > 0){
        for(let imageGroup of productImageGroups){
            if(imageGroup.get('name') === 'body'){
                const productImages = imageGroup.get('productImages');
                productImageList = productImages.map(
                    (productImage, index) => {
                        const savedUri = productImage.get('savedUri');
                        const imagePathUri = constants.IMAGE_GET_API + "?fileName=" + encodeURI(savedUri);
                        return(
                            <img key={index} src={imagePathUri}/>
                        )
                    }
                );
            }
        }
    }

    return(
        <div className={cx('product-body')}>

            <div className={cx('intro')}>
                <h1>DETAIL VIEW</h1>
                <div className={cx('description')}>
                    {constants.TEXT_BODY_DESCRIPTION}
                </div>
            </div>
            {productImageList}
            <div className={cx('content')}>

                {description}
            </div>
        </div>
    )
}

export default ProductViewBody;