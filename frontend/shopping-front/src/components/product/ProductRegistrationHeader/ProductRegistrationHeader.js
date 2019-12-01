import React, {Component} from 'react';
import styles from './ProductRegistrationHeader.scss';
import classNames from 'classnames/bind';
import FileUploadButton from "components/common/FileUploadButton";
import * as constants from "constants/Constants";
import xButton from "resources/images/xButton.PNG";
import { Map, fromJS } from 'immutable';

const cx = classNames.bind(styles);

class ProductRegistrationHeader extends Component {
    render() {
        const { product, productImages, categories, colors, sizes, onChangeProductInput, onAddOption, onDeleteOption, onImageUpload } = this.props;
        const headerUrl = productImages.get('headerUrl');
        const categoryList = categories.map((category, index) => {
            const categoryValue = {key: category.get('key'), value: category.get('value')};
            return(<option key={index} value={categoryValue.key}>{categoryValue.value}</option>)
        });
        const colorList = colors.map((color, index) => {
            const colorValue = {key: color.get('key'), value: color.get('value')};
            return(<option key={index} value={colorValue.key}>{colorValue.value}</option>)
        });
        const sizeList = sizes.map((size, index) => {
            const sizeValue = {key: size.get('key'), value: size.get('value')};
            return(<option key={index} value={sizeValue.key}>{sizeValue.value}</option>)
        });
        const optionList = product.get('optionGroupSpecs').map(
            (optionGroupSpec, index1) => (
                optionGroupSpec.get('optionSpecs').map(
                    (optionSpec, index2) => (
                        <div key={index2} className={cx('option')}>
                            <div className={cx('option-factor')}>
                                <div className={cx('option-title')}>{constants.TEXT_COLOR_SIZE}:</div>
                                <div className={cx('option-choice')}>{optionSpec.get('color')} / {optionSpec.get('size')}</div>
                            </div>

                            <div className={cx('option-factor')}>
                                <div className={cx('option-title')}>{constants.TEXT_NUMBER}:</div>
                                <div className={cx('option-choice')}>{optionSpec.get('totalCount')}</div>
                            </div>
                            <div className={cx('option-factor')}>
                                <button onClick={() => onDeleteOption(index1, index2)}><img src="http://www.faso.store/xButton.png"/></button>
                            </div>
                        </div>
                    )
                )
            )
        );
        const noImageUrl = constants.BASE_URL + "common/no_image.jpg";

        return (
            <div className={cx('product-registration-info')}>
                <div className={cx('product-image')}>
                    { /* 조건에 따른 렌더링 */
                        headerUrl ? <img src={headerUrl} className={cx('preview-image')}/> : <img src={noImageUrl} className={cx('preview-image')}/>
                    }
                    <FileUploadButton name="header" type="single" text={constants.TEXT_IMAGE_UPLOAD} onChange={onImageUpload}/>
                </div>
                <div className={cx('info')}>
                    <input name='name' value={product.get('name')} placeholder={constants.INPUT_TEXT_PLACEHOLDER_TITLE} className={cx('input-title')} onChange={onChangeProductInput}/>
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
                            <option value="" selected>{constants.SELECTBOX_DEFAULT}</option>
                            {colorList}
                        </select>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_SIZE}
                        </div>
                        <select name="size" className={cx('select-box')} onChange={onChangeProductInput}>
                            <option value="" selected>{constants.SELECTBOX_DEFAULT}</option>
                            {sizeList}
                        </select>
                    </div>

                    <div className={cx('info-sub')}>
                        <div className={cx('title')}>
                            {constants.TEXT_NUMBER}
                        </div>
                        <input name='totalCount' value={product.get('totalCount')} placeholder={constants.INPUT_TEXT_PLACEHOLDER_NUMBER} className={cx('input-text')} onChange={onChangeProductInput}/>
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


export default ProductRegistrationHeader;