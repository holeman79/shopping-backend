import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faImages, faImage } from '@fortawesome/free-solid-svg-icons'
import classNames from 'classnames/bind';
import styles from './FileUploadButton.scss';

const cx = classNames.bind(styles);

const FileUploadButton = ({type, onChange, text, name}) => {
    const button = (type === 'single') ? (<div className='button'>
            <label htmlFor='single'>
                <FontAwesomeIcon icon={faImage} color='#3B5998' size='4x' />
            </label>
            <input name={name} type='file' id='single' onChange={onChange} />
        </div>)
        :
        (<div className='button'>
            <label htmlFor='multi'>
                <FontAwesomeIcon icon={faImages} color='#6d84b4' size='4x' />
            </label>
            <input name={name} type='file' id='multi' onChange={onChange} multiple />
        </div>)

    return (
        <div className={cx('fileUpload-button')}>
            {button}
            <div className={cx('text')}>{text}</div>
        </div>
    )
}

export default FileUploadButton;
