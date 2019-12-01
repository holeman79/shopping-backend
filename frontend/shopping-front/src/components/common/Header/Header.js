import React from 'react';
import styles from './Header.scss';
import classNames from 'classnames/bind';
import { Link } from 'react-router-dom';
import Button from "components/common/Button";
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);

const Header = ({categories, logged, onLoginClick}) => {
    const role = sessionStorage.getItem('role');
    const categoryList = categories.map((category, index) => {
        return(<Link className={cx('item')} key={index} value={JSON.stringify(category)} to={`/category/${category.get('key')}`}>{category.get('value')}</Link>)
    });

    return(
        <header className={cx('header')}>
            <div className={cx('header-content')}>
                <div className={cx('left')}>
                    { (role === 'ADMIN')  && <Button theme="outline" to="/product/registration">상품등록</Button>}
                </div>
                <div className={cx('brand')}>
                    <Link to="/">The Shopping Mall</Link>
                </div>
                <div className={cx('right')}>

                    <Button theme="outline" onClick={onLoginClick}>{logged ? '로그아웃' : '로그인'}</Button>
                    {!logged && <Button theme="outline" to="/signup">회원가입</Button>}
                    {/*<Button theme="outline" to="">{constants.TEXT_CART}</Button>*/}
                    {/*<Button theme="outline" to="">{constants.TEXT_ORDER}</Button>*/}
                </div>
            </div>
            <div className={cx('header-content')}>
                <div className={cx('category')}>
                    {categoryList}
                </div>
            </div>
        </header>
    )
};

export default Header;