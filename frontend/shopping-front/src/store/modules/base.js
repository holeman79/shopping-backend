import { createAction, handleActions } from 'redux-actions';
import * as api from 'lib/api';

import {fromJS, Map} from 'immutable';
import { pender } from 'redux-pender';

// action types
const INITIALIZE_LOGIN = 'base/INITIALIZE_LOGIN';
const LOGIN = 'base/LOGIN';
const CHANGE_INPUT_LOGIN = 'base/CHANGE_INPUT';
const CHANGE_INPUT_SIGNUP = 'base/CHANGE_INPUT_SIGNUP';
const SIGNUP = 'base/SIGNUP';
const GET_CATEGORY_LIST = 'base/GET_CATEGORY_LIST';
const GET_OAUTH2_USER = 'base/GET_OAUTH2_USER';

// action creators
export const initializeLogin = createAction(INITIALIZE_LOGIN);
export const login = createAction(LOGIN, api.login);
export const changeInputLogin = createAction(CHANGE_INPUT_LOGIN);
export const changeInputSignup = createAction(CHANGE_INPUT_SIGNUP);
export const signup = createAction(SIGNUP, api.signup);
export const getCategoryList = createAction(GET_CATEGORY_LIST, api.getCategoryList);
export const getOauth2User = createAction(GET_OAUTH2_USER, api.getOauth2User);

// initial state
const initialState = Map({
    categories: fromJS([]),
    login: Map({
        userId: '',
        password: '',
        name: '',
        authorities: '',
        error: false
    }),
    signup: Map({
        userId: '',
        name: '',
        password: '',
    }),
});

// reducer
export default handleActions({
    [INITIALIZE_LOGIN]: (state, action) => {
        return state.set('login', initialState.get('login'));
    },
    ...pender({
        type: LOGIN,
        onSuccess: (state, action) => {
            const { id, name, imageUrl, roleType, accessToken, tokenType } = action.payload.data;
            sessionStorage.setItem("id", id);
            sessionStorage.setItem("name", name);
            sessionStorage.setItem("imageUrl", imageUrl);
            sessionStorage.setItem("role", roleType);
            sessionStorage.setItem('accessToken', accessToken);
            sessionStorage.setItem('tokenType', tokenType);
            sessionStorage.logged = true;
        },
        onError: (state, action) => {
            return state.setIn(['login', 'error'], true)
                .setIn(['login', 'userId'], '')
                .setIn(['login', 'password'], '');
        }
    }),
    [CHANGE_INPUT_LOGIN]: (state, action) => {
        const { name, value } = action.payload;
        return state.setIn(['login', name], value);
    },
    [CHANGE_INPUT_SIGNUP]: (state, action) => {
        const { name, value } = action.payload;
        return state.setIn(['signup', name], value);
    },
    ...pender({
        type: SIGNUP,
        onSuccess: (state, action) =>{

        },
        onError: (state, action) => {
            return state.setIn(['signup', 'userId'], '')
                .setIn(['signup', 'name'], '')
                .setIn(['signup', 'password'], '');
        }
    }),
    ...pender({
        type: GET_OAUTH2_USER,
        onSuccess: (state, action) => {
            const { id, name, imageUrl, role, accessToken, tokenType } = action.payload.data;
            sessionStorage.setItem("id", id);
            sessionStorage.setItem("name", name);
            sessionStorage.setItem("imageUrl", imageUrl);
            sessionStorage.setItem("role", role.name);
            sessionStorage.setItem('accessToken', accessToken);
            sessionStorage.setItem('tokenType', tokenType);
            sessionStorage.logged = true;

            //const { data: userInfo } = action.payload;
            //sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
        },
        onError: (state, action) => {
            return state.setIn(['login', 'error'], true);
        }
    }),
    ...pender({
        type: GET_CATEGORY_LIST,
        onSuccess: (state, action) =>{
            const { data: categories } = action.payload;
            return state.set('categories', fromJS(categories))
        },
        onError: (state, action) => {
            return state.setIn(['signup', 'userId'], '')
                .setIn(['signup', 'name'], '')
                .setIn(['signup', 'password'], '');
        }
    }),
}, initialState)