import React, {Component} from 'react';
import ProductList from 'components/list/ProductList';
import Pagination from 'components/list/Pagination';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as listActions from 'store/modules/list';

class ListContainer extends Component {
    getProductList = () => {
        const { page, category, ListActions } = this.props;
        ListActions.getProductList({
            page,
            category
        });
    }

    componentDidMount() {
        this.getProductList();
    }

    componentDidUpdate(prevProps, prevState) {
        if(prevProps.page !== this.props.page || prevProps.category !== this.props.category){
            this.getProductList();
            document.documentElement.scrollTop = 0;
        }
    }

    render() {
        const { loading, products, page, lastPage, category } = this.props;
        if(loading) return null;
        return (
            <div>
                <ProductList products={products}/>
                <Pagination page={page} lastPage={lastPage} category={category}/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        lastPage: state.list.get('lastPage'),
        products: state.list.get('products'),
        loading: state.pender.pending['list/GET_PRODUCT_LIST']
    }),
    (dispatch) => ({
        ListActions: bindActionCreators(listActions, dispatch)
    })
)(ListContainer);
