import React, {Component} from 'react';
import ProductRegistrationInfo from 'components/product/ProductRegistrationHeader';
import ProductRegistrationBody from 'components/product/ProductRegistrationBody';
import Notifications, { notify } from 'react-notify-toast';
import * as constants from "constants/Constants";
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { withRouter } from 'react-router-dom';
import * as productRegistrationActions from 'store/modules/productRegistration';

const toastColor = {
    background: '#505050',
    text: '#fff'
};

class ProductRegistrationContainer extends Component {
    toast = notify.createShowQueue();
    handleImageUpload = (e) => {
        const { ProductRegistrationActions } = this.props;
        const { name, id, files } = e.target;
        e.preventDefault();
        //const files = Array.from(e.target.files);
        if (files.length > 10 ) {
            const msg = 'Only 10 images can be uploaded at a time';
            return this.toast(msg, 'custom', 2000, toastColor)
        }

        if(id === 'single'){
            const file = files[0];
            const fileUrl = URL.createObjectURL(file);
            ProductRegistrationActions.addSingleImage({name, fileUrl, file});
        }else{
            for(let i =0; i<files.length;i++){
                const file = files[i];
                const fileUrl = URL.createObjectURL(file);
                ProductRegistrationActions.addMultiImage({name, fileUrl, file});
            }
        }
    };
    handleDeleteOption = (index) => {
        const { ProductRegistrationActions } = this.props;
        ProductRegistrationActions.deleteOption(index);
    };
    handleAddOption = () => {
        const { product, ProductRegistrationActions } = this.props;
        const color = product.get('color');
        const size = product.get('size');
        const number = product.get('number');
        if(color === '' || size === ''|| number === ''){
            alert('색상, 사이즈, 판매수량 모두 입력하세요.');
            return;
        }
        ProductRegistrationActions.addOption({color, size, number})
    };
    handleChangeProductInput = (e) => {
        const { ProductRegistrationActions } = this.props;
        const { value, name } = e.target;
        if(name === 'category' || name === 'color' || name === 'size'){
            ProductRegistrationActions.changeSelectbox({name, value});
        }
        else ProductRegistrationActions.changeInput({name, value});
    };
    handleSubmit = async() => {
        const {  productFiles, ProductRegistrationActions, history } = this.props;
        let { product } = this.props;

        const title = product.get('title');
        const category = product.get('category').get('code');
        const price = product.get('price');
        const savedMoneyRate = product.get('savedMoneyRate');
        const options = product.get('options');
        const productImage = productFiles.get('productImage');
        const productDetailImages = productFiles.get('productDetailImages');

        if(title === '' || category === '' || price === '' || savedMoneyRate === ''){
            this.toast(constants.WARNING_ADD_PRODUCT, 'custom', 2000, toastColor);
            return;
        }
        if(options.size === 0){
            this.toast(constants.WARNING_ADD_PRODUCT_OPTION, 'custom', 2000, toastColor);
            return;
        }
        if(productImage === '' || productDetailImages.size === 0){
            this.toast(constants.WARNING_ADD_PRODUCT_IMAGE, 'custom', 2000, toastColor);
            return;
        }

        product = product.set('userId', sessionStorage.getItem('id'));
        let formData = new FormData();
        formData.append('productFile', productImage);
        formData.append('product', new Blob([JSON.stringify(product)], {
            type: "application/json"
        }));

        for(let i = 0; i < productDetailImages.size; i++){
            formData.append('productDetailFiles', productDetailImages.get(i));
        }
        try{
            await ProductRegistrationActions.addProduct(formData);
            history.push(`/product/${this.props.productId}`);
        }catch(e){
            console.log(e);
        }
    };

    initialize = async () => {
        const { ProductRegistrationActions } = this.props;
        try{
            ProductRegistrationActions.initialize();
            await ProductRegistrationActions.getOptionList();
        }catch(e){
            console.log(e);
        }
    };

    componentDidMount() {
        this.initialize();
    }

    componentDidUpdate(prevProps, prevState) {
        const { error, history } = this.props;
        if(prevProps.error !== this.props.error){
            const errorStatus = error.get('status');
            if(errorStatus === 401) history.push('/login')
        }
    }

    render() {
        const { product, productFiles, categories, colors, sizes } = this.props;
        const { handleChangeProductInput, handleAddOption, handleDeleteOption, handleImageUpload, handleSubmit } = this;
        return (
            <div>
                <Notifications />
                <ProductRegistrationInfo product={product} productFiles={productFiles} categories={categories} colors={colors} sizes={sizes}
                             onChangeProductInput={handleChangeProductInput} onAddOption={handleAddOption} onDeleteOption={handleDeleteOption} onImageUpload={handleImageUpload}/>
                <ProductRegistrationBody productFiles={productFiles} onChangeProductInput={handleChangeProductInput} onImageUpload={handleImageUpload} onAddProduct={handleSubmit}/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        product: state.productRegistration.get('product'),
        productFiles: state.productRegistration.get('productFiles'),
        categories: state.productRegistration.get('categories'),
        colors: state.productRegistration.get('colors'),
        sizes: state.productRegistration.get('sizes'),
        productId: state.productRegistration.get('productId'),
        error: state.productRegistration.get('error'),
    }),
    (dispatch) => ({
        ProductRegistrationActions: bindActionCreators(productRegistrationActions, dispatch)
    })
)(withRouter(ProductRegistrationContainer));
