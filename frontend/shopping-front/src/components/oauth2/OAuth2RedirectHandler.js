import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as baseActions from 'store/modules/base';


class OAuth2RedirectHandler extends Component {

    initialize = async () => {
        const { BaseActions } = this.props;
        try{
            BaseActions.getOauth2User();
        }catch(e){
            console.log(e);
        }
    };

    componentDidMount() {
        this.initialize();
    };

    render() {
        return <Redirect to={{
            pathname: "/",
            state: { from: this.props.location }
        }}/>;

    }
}
export default connect(
    (state) => ({

    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch)
    })
)(OAuth2RedirectHandler);