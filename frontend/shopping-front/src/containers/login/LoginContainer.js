import React, {Component} from 'react';
import Login from 'components/login/Login';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as baseActions from 'store/modules/base';
import { withRouter } from 'react-router-dom';
import * as constants from "constants/Constants";

class LoginContainer extends Component {
    handleLogin = async (e) => {
        const { BaseActions, userId, password, history } = this.props;
        const { name } = e.target;
        try{
            if(name === "classic"){
                await BaseActions.login({userId, password});
                history.push('/');
            }else{
                window.location.href = "/oauth2/authorization/" + name;// + '?redirect_uri=' + constants.OAUTH2_REDIRECT_URI;
                BaseActions.hideModal('login');
            }
        }catch(e){
            console.log(e);
        }
    };

    handleChange = ({name, value}) => {
        const { BaseActions } = this.props;
        BaseActions.changeInputLogin({name, value});
    };
    handleKeyPress = (e) => {
        if(e.key === 'Enter'){
            this.handleLogin();
        }
    };
    render() {
        const {
            handleLogin, handleChange, handleKeyPress
        } = this;
        const { error, loginId, password } = this.props;

        return (
            <Login
                onLogin={handleLogin}
                onChange={handleChange} onKeyPress={handleKeyPress}
                error={error} loginId= {loginId} password={password}
            />
        );
    }
}

export default connect(
    (state) => ({
        userId: state.base.getIn(['login', 'userId']),
        password: state.base.getIn(['login', 'password']),
        error: state.base.getIn(['login', 'error'])
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch)
    })
)(withRouter(LoginContainer));