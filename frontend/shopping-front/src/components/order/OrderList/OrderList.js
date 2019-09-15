import React from 'react';
import styles from './OrderList.scss';
import classNames from 'classnames/bind';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

const OrderItem = ({productFile = "", title, price, color, size, number}) => {
    let thumbnailUrl = "";
    if(productFile != ""){
        const { directory, thumbnailSavedFileName } = productFile;
        thumbnailUrl = constants.IMAGE_GET_API + "?fileName=" + encodeURI(directory + thumbnailSavedFileName);
    }
    return (
        <div className={cx('order-item')}>
            <img src={thumbnailUrl}/>
            <div className={cx('order-content')}>{title}</div>
            <div className={cx('order-option')}>{color.name} / {size.name}</div>
            <div className={cx('order-amount')}>수량 {number}개</div>
            <div className={cx('order-price')}>{numberFormat.format(price)}원</div>
        </div>
    )
}

const OrderList = ({orderItems}) => {
    let orderItemList = "";
    if(orderItems.size > 0){
        orderItemList = orderItems.map(
            (order, index) => {
                const { product, color, size, number } = order.toJS();
                const { productFile, title, price } = product;
                return (
                    <OrderItem
                        key={index}
                        productFile={productFile}
                        title={title}
                        price={price}
                        color={color}
                        size={size}
                        number={number}
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