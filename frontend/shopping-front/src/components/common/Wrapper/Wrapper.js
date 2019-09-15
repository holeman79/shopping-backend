import React from 'react';
import styles from './Wrapper.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

const Wrapper = ({children, title}) => (
    <div className={cx('list-wrapper')}>
        {title && <div className={cx('title')}>{title}</div>}
        {children}
    </div>
);

export default Wrapper;