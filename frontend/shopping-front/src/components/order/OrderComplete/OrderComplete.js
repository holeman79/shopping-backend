import React from 'react';
import styles from './OrderComplete.scss';
import classNames from 'classnames/bind';
import Button from "components/common/Button";
import { Link } from 'react-router-dom';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);

const OrderComplete = ({ selectedPayment, selectedBankBook, name }) => {
    return (
        <div className={cx('order-complete-content')}>
            <div className={cx('info')}>
                <div className={cx('label-header')}>
                    {name
                    } 고객님, 주문이 완료되었습니다.<br/>
                    입금이 완료되면 배송이 진행됩니다.
                </div>
                <ul>
                    <li>결제수단:{selectedPayment.get('value')}</li>
                    <li>입금계좌:{selectedBankBook.get('value')}</li>
                </ul>
            </div>
        </div>
    );
};

export default OrderComplete;