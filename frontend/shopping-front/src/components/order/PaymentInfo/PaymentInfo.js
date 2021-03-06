import React from 'react';
import styles from './PaymentInfo.scss';
import classNames from 'classnames/bind';
import Button from "components/common/Button";
import { Link } from 'react-router-dom';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);
const numberFormat = new Intl.NumberFormat();

const PaymentInfo = ({paymentWays, bankBooks, totalPrice, depositorName, selectedBankBook, selectedPayment, onChangeObject, onChangeInput, onPayment }) => {
    const paymentWayList = paymentWays.map(
        (paymentWay) => (
            <label className={cx('radio-box')}>
                <input name="selectedPayment" type="radio" checked={selectedPayment.get('code') === paymentWay.get('code')} name="selectedPayment" value={JSON.stringify(paymentWay)} onChange={onChangeObject}/>
                {paymentWay.get('name')}
            </label>
        )
    );
    const bankBookList = bankBooks.map(
        (bankBook, index) => {
            const showValue = bankBook.get('bankName') + ":" + bankBook.get('bankAccount') + " " + bankBook.get('accountHolder');
            return (<option key={index} value={JSON.stringify(bankBook)} selected={bankBook.get('code') === selectedBankBook}>{showValue}</option>)
        }
    );

    return (
        <div>
            <div className={cx('payment-header')}>결제수단</div>

            <div className={cx('payment-content')}>
                <div className={cx('payment-way')}>
                    <div className={cx('radio-group')}>
                        {paymentWayList}
                    </div>
                    {
                        (selectedPayment.get('code') === 'no_bankbook') &&
                        <div className={cx('no-bankbook')}>
                            <div className={cx('no-bankbook-sub')}>
                                <div className={cx('term')}>입금자명</div>
                                <input type="text" name='depositorName' value={depositorName} onChange={onChangeInput}/>
                            </div>
                            <div className={cx('no-bankbook-sub')}>
                                <div className={cx('term')}>입금은행</div>
                                <select name="selectedBankBook" className={cx('bank') } onChange={onChangeObject}>
                                    {bankBookList}
                                </select>
                            </div>
                        </div>
                    }
                    {
                        (selectedPayment.get('code') !== 'no_bankbook') && <div className={cx('message')}>추후 구현 예정입니다.</div>
                    }
                </div>
                <div className={cx('payment-info')}>
                    <div className={cx('term', 'payment-info-sub')}>최종결제 금액</div>
                    { totalPrice < 50000 && <div className={cx('term', 'payment-info-sub')}>배송비: 2500원</div> }
                    <div className={cx('term-price', 'payment-info-sub')}>{ totalPrice < 50000 ? numberFormat.format(totalPrice + 2500) : numberFormat.format(totalPrice)}원</div>
                    <button onClick={onPayment}>결제하기</button>
                </div>
            </div>
        </div>
    );
};

export default PaymentInfo;