import React, {Component} from 'react';
import styles from './ProductViewInfo.scss';
import classNames from 'classnames/bind';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

class ProductViewInfo extends Component {

    render() {
        const { product, selectedColor, orderOptions, colorSizeList, onChangeProductInput, onDeleteOption, onChangeOrderProductNumber, onPurchase } = this.props;
        const { id, name, price, savedMoneyRate } = product.toJS();
        let imagePathUrl = "";
        let productImageGroups = product.get('productImageGroups');
        if(productImageGroups != "" && productImageGroups.size > 0){
            let productImage;
            for(let imageGroup of productImageGroups){
                if(imageGroup.get('name') === 'header') productImage = imageGroup.get('productImages').get(0);
            }
            const savedUri = productImage.get('savedUri');
            imagePathUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI(savedUri);
        }
        const colorList = colorSizeList.map(
            (option, index) => {
                const colorValue = {key: option.get('color').get('key'), value: option.get('color').get('value')};
                return(<option key={index} value={JSON.stringify(colorValue)}>{option.get('color').get('value')}</option>);
            }
        );
        let sizeList = "";
        if(selectedColor.get('key') != ""){
            for(let i = 0; i < colorSizeList.size; i++){
                const colorCode = colorSizeList.get(i).get('color').get('key');
                if(colorCode === selectedColor.get('key')){
                    sizeList = colorSizeList.get(i).get('size').map(
                        (size, index) => {
                            const sizeValue = {key: size.get('key'), value: size.get('value')};
                            return(<option key={index} value={JSON.stringify(sizeValue)}>{size.get('value')}</option>)
                        }
                    )
                }
            }
        }
        const orderOptionList = orderOptions.map(
            (orderOption, index) => (
                <div key={index} className={cx('order-option', 'text')}>
                    <div className={cx('item')}>옵션: {orderOption.get('color').get('value')} / {orderOption.get('size').get('value')}</div>
                    <div className={cx('item')}>수량: <input name='count' value={orderOption.get('count')} defaultValue={1} className={cx('input-text')} onChange={(e) => onChangeOrderProductNumber({e, index})}/>개</div>
                    <div className={cx('item')}><button onClick={() => onDeleteOption(index)}><img src="http://www.faso.store/xButton.png"/></button></div>
                </div>
            )
        );
        return (
            <div className={cx('product-info')}>
                <img src={imagePathUrl} className={cx('product-image')}/>
                <div className={cx('info')}>
                    <div className={cx('product-title')}>{name}</div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title1')}>
                            {constants.TEXT_PRICE}
                        </div>
                        <div className={cx('title2')}>{numberFormat.format(price)}원</div>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title1')}>{constants.TEXT_SAVED_MONEY}</div>
                        <div className={cx('title1')}>
                            {numberFormat.format((price * savedMoneyRate)/100)}원
                        </div>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title1')}>{constants.TEXT_DELIVERY_MONEY}</div>
                        <div className={cx('title1')}>
                            { price >= constants.DELIVERY_FREE_PRICE ? '무료' : numberFormat.format(constants.DELEVIERY_PRICE) + '원 (50,000원 이상 구매 시 무료)' }
                        </div>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title1')}>
                            {constants.TEXT_COLOR}
                        </div>
                        <select name="selectedColor" className={cx('select-box')} onChange={onChangeProductInput}>
                            <option value="" selected={selectedColor.get('key') === ''}>{constants.SELECTBOX_DEFAULT}</option>
                            { colorList }
                        </select>
                    </div>
                    <div className={cx('info-sub')}>
                        <div className={cx('title1')}>
                            {constants.TEXT_SIZE}
                        </div>
                        <select name="selectedSize" className={cx('select-box')} onChange={onChangeProductInput}>
                            <option value="" defaultValue>{constants.SELECTBOX_DEFAULT}</option>
                            { selectedColor.get('key') && sizeList }
                        </select>
                    </div>
                    <div className={cx('order-options')}>
                        {orderOptionList}
                    </div>
                    <div className={cx('buttons')}>
                        <button className={cx('button1')}>
                            {constants.TEXT_CART}
                        </button>
                        <button className={cx('button2')} onClick={onPurchase}>
                            {constants.TEXT_PURCHASE}
                        </button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProductViewInfo;