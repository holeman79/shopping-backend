import React from 'react';
import styles from './SignupBody.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const SignupBody = ({userId, name, password, onSignup, onChangeInput}) => {
    // const handleChange = (e) => {
    //     const { value, name } = e.target;
    //     onChangeInput({name, value});
    // }
    return(
        <div className={cx('signup-body')}>
            <div className={cx('content')}>
                <h1>Sign Up</h1>
                <div className={cx('input-box')}>
                    <div className={cx('input-title')}>아이디</div>
                    <input name = 'userId' autoFocus type="text" value={userId} placeholder="ID" onChange={onChangeInput}/>
                </div>
                <div className={cx('input-box')}>
                    <div className={cx('input-title')}>비밀번호</div>
                    <input name = 'password' type="password" value={password} placeholder="password" onChange={onChangeInput}/>
                </div>
                <div className={cx('input-box')}>
                    <div className={cx('input-title')}>이름</div>
                    <input name = 'name' type="text" value={name} placeholder="Name" onChange={onChangeInput}/>
                </div>
                {/*{error && <div className={cx('error')}>로그인 실패</div>} */}
                <button onClick={onSignup}>Sign up</button>
            </div>
        </div>

    )
};

export default SignupBody;