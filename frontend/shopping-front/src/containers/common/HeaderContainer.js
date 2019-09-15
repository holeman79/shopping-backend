import React, {Component} from 'react';
import Header from 'components/common/Header';
import { withRouter } from 'react-router-dom';

import * as baseActions from 'store/modules/base';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

class HeaderContainer extends Component {
    handleLoginClick = () => {
        const logged = sessionStorage.logged;
        const { BaseActions, history } = this.props;
        if(logged){
            try{
                sessionStorage.removeItem("id");
                sessionStorage.removeItem("name");
                sessionStorage.removeItem("imageUrl");
                sessionStorage.removeItem("role");
                sessionStorage.removeItem("accessToken");
                sessionStorage.removeItem("tokenType");

                sessionStorage.logged = '';
                window.location.reload();
            }catch(e){
                console.log(e);
            }
            return;
        }

        history.push('/login');
        BaseActions.initializeLogin();
    };

    initialize = async () => {
        const { BaseActions } = this.props;
        try{
            await BaseActions.getCategoryList();
        }catch(e){
            console.log(e);
        }
    };

    componentDidMount() {
        this.initialize();
    };

    render() {
        const { handleLoginClick } = this;
        const { categories } = this.props;
        const logged = sessionStorage.logged;
        return (
            <Header
                logged={logged}
                categories={categories}
                onLoginClick={handleLoginClick}
            />
        );
    }
}

export default connect(
    (state) => ({
        categories: state.base.get('categories'),
    }),
    (dispatch) => ({
        BaseActions: bindActionCreators(baseActions, dispatch),
    })
)(withRouter(HeaderContainer));