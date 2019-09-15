import { createAction, handleActions } from 'redux-actions';
import { Map, List, fromJS } from 'immutable';
import { pender } from 'redux-pender';

import * as api from 'lib/api';

// action types
const INITIALIZE = 'productRegistration/INITIALIZE';
const GET_OPTION_LIST = 'productRegistration/GET_OPTION_LIST';
const ADD_OPTION = 'productRegistration/ADD_OPTION';
const DELETE_OPTION = 'productRegistration/DELETE_OPTION';
const CHANGE_INPUT = 'productRegistration/CHANGE_INPUT';
const CHANGE_SELECTBOX = 'productRegistration/CHANGE_SELECTBOX';
const ADD_SINGLE_IMAGE = 'productRegistration/ADD_SINGLE_IMAGE';
const ADD_MULTI_IMAGE = 'productRegistration/ADD_MULTI_IMAGE';
const ADD_PRODUCT = 'productRegistration/ADD_PRODUCT';

// action creators
export const initialize = createAction(INITIALIZE);
export const getOptionList = createAction(GET_OPTION_LIST, api.getProductOptionList);
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
    product: Map({
        title: '',
        category: Map({
            code: '',
            name: ''
        }),
        price: '',
        savedMoneyRate: '',
        options: fromJS([]),
        description: '',
        createdId: '',

        color: Map({
            code: '',
            name: ''
        }),
        size: Map({
            code: '',
            name: ''
        }),
        number: '',
    }),
    productFiles: Map({
        productImagePreview: '',
        productImage: '',
        productDetailImagesPreview: fromJS([]),
        productDetailImages: fromJS([])
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
        let value = action.payload.value;
        value = eval("("+value+")");
        return state.setIn(['product', name, 'code'], value.code)
            .setIn(['product', name, 'name'], value.name);
    },
    [ADD_OPTION]: (state, action) => {
        const { payload: option } = action;
        return state.setIn(['product', 'options'], state.get('product').get('options').push(Map(option)))
            .setIn(['product', 'color'], Map({code: '', name: ''}))
            .setIn(['product', 'size'], Map({code: '', name: ''}))
            .setIn(['product', 'number'], '')
    },
    [DELETE_OPTION]: (state, action) => {
        const { payload: index } = action;
        return state.setIn(['product', 'options'], state.get('product').get('options').delete(index))
    },
    [ADD_SINGLE_IMAGE]: (state, action) => {
        const { name, fileUrl, file } = action.payload;
        return state.setIn(['productFiles', name + 'Preview'], fileUrl)
            .setIn(['productFiles', name], file)
    },
    [ADD_MULTI_IMAGE]: (state, action) => {
        const { name, fileUrl, file } = action.payload;
        return state.setIn(['productFiles', name + 'Preview'], state.get('productFiles').get(name + 'Preview').push(fileUrl))
            .setIn(['productFiles', name], state.get('productFiles').get(name).push(file))
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