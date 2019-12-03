import { createAction, handleActions } from 'redux-actions';

import { Map, List, fromJS } from 'immutable';
import { pender } from 'redux-pender';

import * as api from 'lib/api';

// action types
const INITIALIZE = 'productView/INITIALIZE';
const GET_PRODUCT = 'productView/GET_PRODUCT';
const CHANGE_INPUT = 'productView/CHANGE_INPUT';
const ADD_ORDER_OPTION = 'productView/ADD_ORDER_OPTION';
const DELETE_ORDER_OPTION = 'productView/DELETE_ORDER_OPTION';
const CHANGE_SELECTBOX = 'productView/CHANGE_SELECTBOX';
const CHANGE_ORDER_PRODUCT_NUMBER = 'productView/CHANGE_ORDER_PRODUCT_NUMBER';

// action creators
export const initialize = createAction(INITIALIZE);
export const getProduct = createAction(GET_PRODUCT, api.getProduct);
export const changeInput = createAction(CHANGE_INPUT);
export const changeSelectbox = createAction(CHANGE_SELECTBOX);
export const addOrderOption = createAction(ADD_ORDER_OPTION);
export const deleteOrderOption = createAction(DELETE_ORDER_OPTION);
export const changeOrderProductNumber = createAction(CHANGE_ORDER_PRODUCT_NUMBER);

// initial state
const initialState = Map({
    product: fromJS({
        id: null,
        name: '',
        category: '',
        price: 0,
        savedMoneyRate: 0,
        description: '',
        userId: '',
        createdDate: null,

        // optionGroupSpecs: fromJS([
        //     {
        //         name: '',
        //         exclusive: false,
        //         basic: true,
        //         optionSpecs: fromJS([
        //             {
        //                 color: '',
        //                 colorValue: '',
        //                 size: '',
        //                 sizeValue: '',
        //                 price: 0,
        //                 totalCount: 0
        //             }])
        //     }
        // ]),
        // productImageGroups: fromJS([
        //     {
        //         name: '',
        //         productImages: fromJS([
        //             {
        //                 id: null,
        //                 name: '',
        //                 size: '',
        //                 savedURI: ''
        //
        //             }])
        //     }
        // ])

        optionGroupSpecs: fromJS([]),
        productImageGroups: fromJS([]),

    }),
    selectedColor: Map({
        key: '',
        value: ''
    }),
    selectedSize: Map({
        key: '',
        value: ''
    }),
    count: '',

    order: Map({
        options: fromJS([]),
    })
});

// reducer
export default handleActions({
    [INITIALIZE]: (state, action) => initialState,
    ...pender({
        type: GET_PRODUCT,
        onSuccess: (state, action) => {
            const { data } = action.payload;
            return state.set('product', fromJS(data));
        },
        onError: (state, action) => {

        }
    }),
    [CHANGE_INPUT]: (state, action) => {
        const { name, value } = action.payload;
        return state.set(name, value);
    },
    [CHANGE_SELECTBOX]: (state, action) => {
        const name = action.payload.name;
        let value = action.payload.value;
        if(value === "") return state.setIn([name, 'key'], '')
            .setIn([name, 'value'], '');
        value = eval("("+value+")");
        return state.setIn([name, 'key'], value.key)
            .setIn([name, 'value'], value.value);
    },
    [ADD_ORDER_OPTION]: (state, action) => {
        const { selectedColor, selectedSize } = action.payload;
        const option = Map({color: selectedColor, size: selectedSize, number: 1});
        return state.setIn(['order', 'options'], state.get('order').get('options').push(option))
            .set('selectedColor', Map({key: '', value: ''}))
            .set('selectedSize', Map({key: '', value: ''}))
    },
    [DELETE_ORDER_OPTION]: (state, action) => {
        const { payload: index } = action;
        return state.setIn(['order', 'options'], state.get('order').get('options').delete(index))
    },
    [CHANGE_ORDER_PRODUCT_NUMBER]: (state, action) => {
        const { name, value, index } = action.payload;
        return state.setIn(['order', 'options', index, name], value);
    },
}, initialState)