import { createAction, handleActions } from 'redux-actions';
import { Map, List, fromJS } from 'immutable';
import { pender } from 'redux-pender';

import * as api from 'lib/api';

// action types
const INITIALIZE = 'productRegistration/INITIALIZE';
const GET_OPTION_LIST = 'productRegistration/GET_OPTION_LIST';
const ADD_OPTION_GROUP = 'productRegistration/ADD_OPTION_GROUP';
const ADD_OPTION = 'productRegistration/ADD_OPTION';
const DELETE_OPTION = 'productRegistration/DELETE_OPTION';
const CHANGE_INPUT = 'productRegistration/CHANGE_INPUT';
const CHANGE_SELECTBOX = 'productRegistration/CHANGE_SELECTBOX';
const ADD_SINGLE_IMAGE = 'productRegistration/ADD_SINGLE_IMAGE';
const ADD_MULTI_IMAGE = 'productRegistration/ADD_MULTI_IMAGE';
const ADD_PRODUCT = 'productRegistration/ADD_PRODUCT';

// action creators
export const initialize = createAction(INITIALIZE);
export const getOptionList = createAction(GET_OPTION_LIST, api.getProductOptions);
export const addOptionGroup = createAction(ADD_OPTION_GROUP);
export const addOption = createAction(ADD_OPTION);
export const deleteOption = createAction(DELETE_OPTION);
export const changeInput = createAction(CHANGE_INPUT);
export const changeSelectbox = createAction(CHANGE_SELECTBOX);
export const addSingleImage = createAction(ADD_SINGLE_IMAGE);
export const addMultiImage = createAction(ADD_MULTI_IMAGE);
export const addProduct = createAction(ADD_PRODUCT, api.addProduct);

// initial state
const initialState = Map({
    productId: null,
    product: fromJS({
        name: '',
        category: '',
        price: 0,
        savedMoneyRate: 0,
        description: '',
        userId: '',
        /*
        optionGroupSpecs: fromJS([
            {
                name: '',
                exclusive: false,
                basic: true,
                optionSpecs: fromJS([
                    {
                        color: '',
                        size: '',
                        price: 0,
                        totalCount: 0
                    }])
            }
        ])
        productImageGroups: fromJS([
            {
                name: '',
                productImages: fromJS([
                    {
                        color: '',
                        size: '',
                        price: 0,
                        totalCount: 0
                    }])
            }
        ])
        */
        optionGroupSpecs: fromJS([]),
        productImageGroups: fromJS([]),

        color: '',
        size: '',
        totalCount: ''
    }),
    productImages: Map({
        headerUrl: '',
        header: '',
        bodyUrl: fromJS([]),
        body: fromJS([])
    }),

    categories: fromJS([]),
    colors: fromJS([]),
    sizes: fromJS([]),

    error: Map({
        status: '',
        message: '',
    }),
});

// reducer
export default handleActions({
    [INITIALIZE]: (state, action) => initialState,
    [CHANGE_INPUT]: (state, action) => {
        const { name, value } = action.payload;
        return state.setIn(['product', name], value);
    },
    [CHANGE_SELECTBOX]: (state, action) => {
        const name = action.payload.name;
        const value = action.payload.value;
        return state.set(name, value)
            .setIn(['product', 'optionGroupSpecs', 'optionSpecs', name], value);
    },
    [ADD_OPTION_GROUP]: (state, action) => {
        const { payload: optionGroupSpec } = action;
        return state.setIn(['product', 'optionGroupSpecs'], state.get('product').get('optionGroupSpecs').push(fromJS(optionGroupSpec)))
            .setIn(['product', 'color'], '')
            .setIn(['product', 'size'], '')
            .setIn(['product', 'totalCount'], '')
    },
    [ADD_OPTION]: (state, action) => {
        const { payload: optionGroupSpecs } = action;
        return state.setIn(['product', 'optionGroupSpecs'], fromJS(optionGroupSpecs))
            .setIn(['product', 'color'], '')
            .setIn(['product', 'size'], '')
            .setIn(['product', 'totalCount'], '')
    },
    [DELETE_OPTION]: (state, action) => {
        const { payload: optionGroupSpecs } = action;
        return state.setIn(['product', 'optionGroupSpecs'], fromJS(optionGroupSpecs))
            .setIn(['product', 'color'], '')
            .setIn(['product', 'size'], '')
            .setIn(['product', 'totalCount'], '')
    },
    [ADD_SINGLE_IMAGE]: (state, action) => {
        const { name, fileUrl, file } = action.payload;
        return state.setIn(['productImages', name + 'Url'], fileUrl)
            .setIn(['productImages', name], file)
    },
    [ADD_MULTI_IMAGE]: (state, action) => {
        const { name, fileUrl, file } = action.payload;
        return state.setIn(['productImages', name + 'Url'], state.get('productImages').get(name + 'Url').push(fileUrl))
            .setIn(['productImages', name], state.get('productImages').get(name).push(file))
    },
    ...pender({
        type: ADD_PRODUCT,
        onSuccess: (state, action) => {
            const { data: productId } = action.payload;
            return state.set('productId', productId);
        },
        onError: (state, action) => {
            const { status, message } = action.payload.response.data;
            return state.setIn(['error', 'status'], status)
                .setIn(['error', 'message'], message);
        }
    }),
    ...pender({
        type: GET_OPTION_LIST,
        onSuccess: (state, action) => {
            const { categories, colors, sizes } = action.payload.data;
            return state.set('categories', fromJS(categories))
                .set('colors', fromJS(colors))
                .set('sizes', fromJS(sizes));

        },
        onError: (state, action) => {
            const { status, message } = action.payload.response.data;
            return state.setIn(['error', 'status'], status)
                .setIn(['error', 'message'], message);
        }
    }),
}, initialState)