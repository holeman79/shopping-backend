import React from 'react';
import styles from './ProductViewBody.scss';
import classNames from 'classnames/bind';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const ProductViewBody = ({product}) => {
    const { productDetailFiles, description }  = product.toJS();
    const productDetailImageList = productDetailFiles.map(
        (productDetailFile, index) => {
            const { directory, savedFileName } = productDetailFile;
            const imagePathUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI(directory + savedFileName);
            return(
                <img key={index} src={imagePathUrl}/>
            )
        }
    )
    return(
        <div className={cx('product-body')}>

            <div className={cx('intro')}>
                <h1>DETAIL VIEW</h1>
                <div className={cx('description')}>
                    {constants.TEXT_BODY_DESCRIPTION}
                </div>
            </div>
            {productDetailImageList}
            <div className={cx('content')}>
                {description}
            </div>
        </div>
    )
}

export default ProductViewBody;