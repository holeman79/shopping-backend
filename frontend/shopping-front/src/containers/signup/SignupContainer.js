import React, {Component} from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as baseActions from 'store/modules/base';
import SignupBody from 'components/signup/SignupBody';
import { withRouter } from 'react-router-dom';

class SignupContainer extends Component {
    handleChangeInput = (e) =>{
        const { BaseActions } = this.props;
        const { value, name } = e.target;
        BaseActions.changeInputSignup({name, value});
    };

    handleSignup = async() => {
        const { userId, name, password, history, BaseActions } = this.props;
        try{
            await BaseActions.signup({userId, password, name});
            history.push('/');
        }catch(e){
            console.log(e);
        }
    };

    render() {
        const { userId, password, name } = this.props;
        const { handleSignup, handleChangeInput } = this;
        return (
            <div>
                <SignupBody userId={userId} name={name} password={password} onSignup={handleSignup} onChangeInput={handleChangeInput}/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        userId: state.base.getIn(['signup', 'userId']),
        name: state.base.getIn(['signup', 'name']),
        password: state.base.getIn(['signup', 'password']),
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch)
    })
)(withRouter(SignupContainer));
