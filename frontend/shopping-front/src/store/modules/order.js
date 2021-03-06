import { createAction, handleActions } from 'redux-actions';
import { Map, List, fromJS } from 'immutable';
import { pender } from 'redux-pender';
import * as api from 'lib/api';

// action types
const INITIALIZE_ORDER = 'order/INITIALIZE_ORDER';
const GET_OPTION_LIST = 'order/GET_OPTION_LIST';
const SHOW_MODAL = 'order/SHOW_MODAL';
const HIDE_MODAL = 'order/HIDE_MODAL';
const ADD_ORDER = 'order/ADD_ORDER';
const CHANGE_INPUT = 'order/CHANGE_INPUT';
const CHANGE_OBJECT = 'order/CHANGE_OBJECT';

// action creators
export const initializeOrder = createAction(INITIALIZE_ORDER);
export const getOptionList = createAction(GET_OPTION_LIST, api.getOrderOptionList);
export const showModal = createAction(SHOW_MODAL);
export const hideModal = createAction(HIDE_MODAL);
export const addOrder = createAction(ADD_ORDER, api.addOrder);
export const changeInput = createAction(CHANGE_INPUT);
export const changeObject = createAction(CHANGE_OBJECT);

// initial state
const initialState = Map({
    modalPostCode: false,

    orderItems: fromJS([]),

    ordererName: '',
    zipcode: '',
    address: '',
    detailAddress: '',
    extraAddress: '',
    mobileNo1: '',
    mobileNo2: '',
    mobileNo3: '',

    selectedPayment: Map({}),
    depositorName: '',
    selectedBankBook: Map({}),

    totalPrice: '',

    userId: '',

    paymentWays: fromJS([]),
    bankBooks: fromJS([]),
    phoneFirstNumberTypes: fromJS([]),
    error: Map({
        status: '',
        message: '',
    }),
});

// reducer
export default handleActions({
    [INITIALIZE_ORDER]: (state, action) => {
        const { payload: orderItems } = action;
        state = initialState;
        return state.set('orderItems', orderItems);
    },
    [SHOW_MODAL]: (state, action) => {
        return state.set('modalPostCode', true);
    },
    [HIDE_MODAL]: (state, action) => {
        return state.set('modalPostCode', false);
    },
    [CHANGE_INPUT]: (state, action) => {
        const { name, value } = action.payload;
        return state.set(name, value);
    },
    [CHANGE_OBJECT]: (state, action) => {
        const { name } = action.payload;
        let value = action.payload.value;
        value = eval("("+value+")");
        return state.set(name, Map(value));
    },
    ...pender({
        type: GET_OPTION_LIST,
        onSuccess: (state, action) => {
            const { paymentWays, bankBooks, phoneFirstNumberTypes } = action.payload.data;
            return state.set('paymentWays', fromJS(paymentWays))
                .set('bankBooks', fromJS(bankBooks))
                .set('phoneFirstNumberTypes', fromJS(phoneFirstNumberTypes))
                .set('selectedPayment', Map(paymentWays[0]))
                .set('selectedBankBook', Map(bankBooks[0]))
                .set('mobileNo1', phoneFirstNumberTypes[0].code)
        },
        onError: (state, action) => {
            const { status, message } = action.payload.response.data;
            return state.setIn(['error', 'status'], status)
                .setIn(['error', 'message'], message);
        }
    }),
    ...pender({
        type: ADD_ORDER,
        onError: (state, action) => {
            const { status, message } = action.payload.response.data;
            return state.setIn(['error', 'status'], status)
                .setIn(['error', 'message'], message);
        }
    }),
}, initialState)