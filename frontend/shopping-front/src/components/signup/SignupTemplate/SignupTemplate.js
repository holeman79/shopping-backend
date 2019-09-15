import React, {Component} from 'react';
import styles from './SignupTemplate.scss';
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

class SignupTemplate extends Component {

    render() {
        const { children } = this.props;
        return (
            <div className={cx('signup-template')}>
                <main>
                    {children}
                </main>
            </div>
        );
    }
}

export default SignupTemplate;