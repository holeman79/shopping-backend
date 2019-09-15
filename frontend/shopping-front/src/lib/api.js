import axios from 'axios';
import queryString from 'query-string';

const request = () => {
    var token = '';
    if(sessionStorage.getItem("accessToken")) {
        token = sessionStorage.getItem("accessToken");
    }
    const headers = {
        headers: {
            //'Content-Type': 'application/json',
            Authorization: 'Bearer ' + token
        }
    };

    return headers;
};

export const getCategoryList = () => axios.get('/api/product/category');
export const addProduct = (product) => axios.post('/api/product', product, request());
export const getProductOptionList = () => axios.get('/api/product/option', request());
export const getProduct = (id) => axios.get(`/api/product/${id}`);
export const getProductList = ({category, page}) => axios.get(`/api/product/?${queryString.stringify({category, page})}`);

export const login = ({userId, password}) => axios.post('/api/user/login', {userId, password}, request());
export const signup = ({userId, password, name}) => axios.post('/api/user/signup', {userId, password, name});
export const getOauth2User = () => axios.get(`/api/user/oauth`);

export const getOrderOptionList = () => axios.get('/api/order/option', request());
export const addOrder = (order) => axios.post('/api/order', order, request());



