import React from 'react';
import styles from './ProductRegistrationBody.scss';
import classNames from 'classnames/bind';
import FileUploadButton from "components/common/FileUploadButton";
import * as constants from "constants/Constants";
const cx = classNames.bind(styles);

const ProductRegistrationBody = ({productFiles, onImageUpload, onChangeProductInput, onAddProduct}) => {
    const productDetailImagesPreview = productFiles.get('productDetailImagesPreview').map(
        (productDetailImagePreview, index) => (
            <img src={productDetailImagePreview} />
        )
    );

    return (
        <div className={cx('product-body')}>
            <div className={cx('content-inner')}>
                <h3 >{constants.TEXT_DESCRIPTION_DETAIL_IMAGE}</h3>

                { /* 조건에 따른 렌더링 */
                    productDetailImagesPreview && productDetailImagesPreview
                }
                <div className={cx('fileUpload-button')}>
                <FileUploadButton name="productDetailImages" type="multi" text={constants.TEXT_IMAGE_UPLOAD} onChange={onImageUpload}/>
                </div>
            </div>
            <div className={cx('content-inner')}>
                <h3>{constants.TEXT_DESCRIPTION_DETAIL_TEXT}</h3>
                <textarea name="description" onChange={onChangeProductInput}/>
                <button onClick={onAddProduct}>
                    {constants.TEXT_PRODUCT_ADD}
                </button>
            </div>
        </div>
    )
}

export default ProductRegistrationBody;