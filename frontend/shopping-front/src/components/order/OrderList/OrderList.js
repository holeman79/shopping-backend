import React from 'react';
import styles from './OrderList.scss';
import classNames from 'classnames/bind';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

const OrderItem = ({productImage = "", name, price, color, size, count}) => {
    let thumbnailUrl = "";
    if(productImage != ""){
        const savedUri = productImage.savedUri;
        thumbnailUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI(savedUri );
    }
    return (
        <div className={cx('order-item')}>
            <img src={thumbnailUrl}/>
            <div className={cx('order-content')}>{name}</div>
            <div className={cx('order-option')}>{color} / {size}</div>
            <div className={cx('order-amount')}>수량 {count}개</div>
            <div className={cx('order-price')}>{numberFormat.format(price)}원</div>
        </div>
    )
}

const OrderList = ({orderItems}) => {
    let orderItemList = "";
    if(orderItems.size > 0){
        orderItemList = orderItems.map(
            (orderItem, index) => {
                const orderOption = orderItem.get('orderOptionGroups').get(0).get('orderOptions').get(0);
                const { productImage, name, price, count } = orderItem.toJS();
                const color = orderOption.get('color').get('value');
                const size = orderOption.get('size').get('value');
                // const { product, color, size, number } = order.toJS();
                // const { productFile, title, price } = product;
                return (
                    <OrderItem
                        key={index}
                        productImage={productImage}
                        name={name}
                        price={price}
                        color={color}
                        size={size}
                        count={count}
                    />
                )
            }
        )
    }

    return (
        <div>
            <div className={cx('order-list-header')}>주문상품: {orderItemList.size}개</div>
            <div className={cx('order-list-content')}>
                {orderItemList}
            </div>
        </div>
    );
};

export default OrderList;