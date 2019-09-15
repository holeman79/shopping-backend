import React, {Component} from 'react';
import styles from './PostCodeModal.scss';
import classNames from 'classnames/bind';
import ModalWrapper from 'components/modal/ModalWrapper';
import Button from "../../common/Button/Button";
import DaumPostcode from 'react-daum-postcode';

const cx = classNames.bind(styles);

const PostCodeModal = ({visible, onCancel, onAddress}) => (
    <ModalWrapper visible={visible}>
        <DaumPostcode onComplete={onAddress} autoClose/>
        <div className={cx('options')}>
            <Button theme="gray" onClick={onCancel}>닫기</Button>
        </div>
    </ModalWrapper>
);

export default PostCodeModal;