import { createAction, handleActions } from 'redux-actions';

import { Map, List, fromJS } from 'immutable';
import { pender } from 'redux-pender';

import * as api from 'lib/api';

// action types
const GET_PRODUCT_LIST = 'list/GET_PRODUCT_LIST';

// action creators
export const getProductList = createAction(GET_PRODUCT_LIST, api.getProductList, meta=>meta);

// initial state
const initialState = Map({
    products: List(),
    lastPage: null
});

// reducer
export default handleActions({
    ...pender({
        type: GET_PRODUCT_LIST,
        onSuccess: (state, action) => {
            const { content: products } = action.payload.data;
            if (products == undefined) return initialState;
            const lastPage = action.payload.data.totalPages;
            return state.set('products', fromJS(products))
                .set('lastPage', parseInt(lastPage, 10));
        }
    })
}, initialState)