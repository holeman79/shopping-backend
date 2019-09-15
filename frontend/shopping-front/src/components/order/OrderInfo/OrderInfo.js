import React from 'react';
import styles from './OrderInfo.scss';
import classNames from 'classnames/bind';
import Button from "components/common/Button";
import { Link } from 'react-router-dom';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);

const OrderInfo = ({ zipcode, address, phoneFirstNumberTypes, onPostCode, onChangeInput}) => {
    const phoneFirstNumberTypeList = phoneFirstNumberTypes.map(
        (phoneFirstNumberType, index) => {
            return (<option key={index} value={phoneFirstNumberType.get('code')}>{phoneFirstNumberType.get('code')}</option>)
        }
    );
    return (
        <div>
            <div className={cx('order-info-header')}>받는분 정보</div>
            <div className={cx('order-info-content')}>
                <div className={cx('order-info-list')}>
                    <div className={cx('term')}>주문자 이름</div>
                    <input type="text" name="ordererName" className={cx('input')} placeholder="이름" onChange={onChangeInput}/>
                </div>
                <div className={cx('order-info-list')}>
                    <div className={cx('term')}>주소</div>
                    <div className={cx('order-info-sub')}>
                        <div className={cx('order-info-postcode')}>
                            <input type="text" name="zipcode" value={zipcode} className={cx('input')} placeholder="우편번호" disabled/>
                            <Button onClick={onPostCode} theme="default">우편번호 찾기</Button>
                        </div>
                        <input type="text" name="address" value={address} className={cx('input')} placeholder="주소" disabled/>
                        <input type="text" name="detailAddress" className={cx('input')} placeholder="상세주소" onChange={onChangeInput}/>
                    </div>
                </div>
                <div className={cx('order-info-list')}>
                    <div className={cx('term')}>휴대전화</div>
                    <select name="mobileNo1" className={cx('input')} onChange={onChangeInput}>
                        {phoneFirstNumberTypeList}
                    </select>
                    <input type="text" name="mobileNo2" className={cx('input')} onChange={onChangeInput}/>
                    <input type="text" name="mobileNo3" className={cx('input')} onChange={onChangeInput}/>
                </div>
            </div>
        </div>
    );
};

export default OrderInfo;