import React from 'react';
import styles from './Login.scss';
import classNames from 'classnames/bind';
import * as constants from "constants/Constants";

const cx = classNames.bind(styles);

const Login = ({visible, userId, password, error, onLogin, onChange, onKeyPress}) => {
    const handleChange = (e) => {
        const { value, name } = e.target;
        onChange({name, value});
    }
    return(
        <div className={cx('form')}>
            {/*<div onClick={onCancel} className={cx('close')}>&times;</div>*/}
            <div className={cx('title')}>로그인</div>
            <div className={cx('description')}>아이디, 비밀번호를 입력하세요</div>
            <input name = 'userId' autoFocus type="text" placeholder="아이디 입력" value={userId} onChange={handleChange} onKeyPress={onKeyPress}/>
            <input name = 'password' type="password" placeholder="비밀번호 입력" value={password} onChange={handleChange} onKeyPress={onKeyPress}/>
            {error && <div className={cx('error')}>로그인 실패</div>}
            <button name = "classic" className={cx('login')} onClick={onLogin}>로그인</button>
            <button name='google' onClick={onLogin} className={cx('login-btn', 'google')} >구글계정으로 로그인</button>
            <button name='facebook' onClick={onLogin} className={cx('login-btn', 'facebook')}>페이스북으로 로그인</button>
            <button name='kakao' onClick={onLogin} className={cx('login-btn', 'kakao')}>카카오톡으로 로그인</button>
        </div>
    )
};

export default Login;