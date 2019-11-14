import React, {Component} from 'react';
import styles from './ProductRegistrationHeader.scss';
import classNames from 'classnames/bind';
import FileUploadButton from "components/common/FileUploadButton";
import * as constants from "constants/Constants";
import xButton from "resources/images/xButton.PNG";
import { Map, fromJS } from 'immutable';

const cx = classNames.bind(styles);

class ProuductRegistrationInfo extends Component {
    render() {
        const { product, productFiles, categories, colors, sizes, onChangeProductInput, onAddOption, onDeleteOption, onImageUpload } = this.props;
        const productImagePreview = productFiles.get('productImagePreview');
        const categoryList = categories.map((category, index) => {
            const categoryValue = {code: category.get('code'), name: category.get('name')};
            return(<option key={index} value={JSON.stringify(categoryValue)}>{category.get('name')}</option>)
        });
        const colorList = colors.map((color, index) => {
            const colorValue = {code: color.get('code'), name: color.get('name')};
            return(<option key={index} value={JSON.stringify(colorValue)}>{color.get('name')}</option>)
        });
        const sizeList = sizes.map((size, index) => {
            const sizeValue = {code: size.get('code'), name: size.get('name')};
            return(<option key={index} value={JSON.stringify(sizeValue)}>{size.get('name')}</option>)
        });
        const optionList = product.get('options').map(
            (option, index) => (
                <div key={index} className={cx('option')}>
                    <div className={cx('option-factor')}>
                        <div className={cx('option-title')}>{constants.TEXT_COLOR}:</div>
                        <div className={cx('option-choice')}>{option.get('color').get('name')}</div>
                    </div>
                    <div className={cx('option-factor')}>
                        <div className={cx('option-title')}>{constants.TEXT_SIZE}:</div>
                        <div className={cx('option-choice')}>{option.get('size').get('name')}</div>
                    </div>
                    <div className={cx('option-factor')}>
                        <div className={cx('option-title')}>{constants.TEXT_NUMBER}:</div>
                        <div className={cx('option-choice')}>{option.get('number')}</div>
                    </div>
                    <div className={cx('option-factor')}>
                        <button onClick={() => onDeleteOption(index)}><img src="http://www.faso.store/xButton.png"/></button>
                    </div>
                </div>
            )
        );
        const noImageUrl = constants.BASE_URL + "common/no_image.jpg";

        return (
            <div className={cx('product-registration-info')}>
                <div className={cx('product-image')}>
                    { /* 조건에 따른 렌더링 */
                        productImagePreview ? <img src={productImagePreview} className={cx('preview-image')}/> : <img src={noImageUrl} className={cx('preview-image')}/>
                    }
                    <FileUploadButton name="productImage" type="single" text={constants.TEXT_IMAGE_UPLOAD} onChange={onImageUpload}/>
                </div>
                <div className={cx('info')}>
                    <input name='title' value={product.get('title')} placeholder={constants.INPUT_TEXT_PLACEHOLDER_TITLE} className={cx('input-title')} onChange={onChangeProductInput}/>
                    <div className={cx('text')}>{constants.TEXT_BASIC_INFO}</div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_CATEGORY}
                        </div>
                        <select name="category" className={cx('select-box')} onChange={onChangeProductInput}>
                            <option value="" defaultValue>{constants.SELECTBOX_DEFAULT}</option>
                            {categoryList}
                        </select>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_PRICE}
                        </div>
                        <input name='price' value={product.get('price')} placeholder={constants.INPUT_TEXT_PLACEHOLDER_PRICE} className={cx('input-text')} onChange={onChangeProductInput}/>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_SAVED_MONEY}
                        </div>
                        <input name='savedMoneyRate' value={product.get('savedMoneyRate')} placeholder={constants.INPUT_TEXT_PLACEHOLDER_SAVED_MONEY} className={cx('input-text')} onChange={onChangeProductInput}/>
                    </div>

                    <div className={cx('text')}>{constants.TEXT_OPTION}</div>

                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_COLOR}
                        </div>
                        <select name="color" className={cx('select-box')} onChange={onChangeProductInput}>
                            <option value="" selected={product.get('color').get('code') === ''}>{constants.SELECTBOX_DEFAULT}</option>
                            {colorList}
                        </select>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_SIZE}
                        </div>
                        <select name="size" className={cx('select-box')} onChange={onChangeProductInput}>
                            <option value="" selected={product.get('size').get('code') === ''}>{constants.SELECTBOX_DEFAULT}</option>
                            {sizeList}
                        </select>
                    </div>

                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_NUMBER}
                        </div>
                        <input name='number' value={product.get('number')} placeholder={constants.INPUT_TEXT_PLACEHOLDER_NUMBER} className={cx('input-text')} onChange={onChangeProductInput}/>
                    </div>
                    <div className={cx('options')}>
                        {optionList}
                    </div>
                    <div className={cx('buttons')}>
                        <button className={cx('button1')} onClick={onAddOption}>
                            {constants.TEXT_OPTION_ADD}
                        </button>
                    </div>
                </div>
            </div>
        )
    }
}


export default ProuductRegistrationInfo;