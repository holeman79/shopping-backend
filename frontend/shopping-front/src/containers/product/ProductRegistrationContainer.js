import React, {Component} from 'react';
import ProductRegistrationHeader from 'components/product/ProductRegistrationHeader';
import ProductRegistrationBody from 'components/product/ProductRegistrationBody';
import Notifications, { notify } from 'react-notify-toast';
import * as constants from "constants/Constants";
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { withRouter } from 'react-router-dom';
import * as productRegistrationActions from 'store/modules/productRegistration';
import {fromJS} from "immutable";

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
    handleDeleteOption = (index1, index2) => {
        const { product, ProductRegistrationActions } = this.props;
        let optionGroupSpecs = product.get('optionGroupSpecs');
        let optionSpecs = optionGroupSpecs.get(index1).get('optionSpecs').delete(index2);
        optionGroupSpecs = optionGroupSpecs.setIn([index1, 'optionSpecs'], optionSpecs);

        ProductRegistrationActions.deleteOption(optionGroupSpecs);
    };
    handleAddOption = () => {
        const { product, ProductRegistrationActions } = this.props;
        const color = product.get('color');
        const size = product.get('size');
        const totalCount = product.get('totalCount');
        if(color === '' || size === ''|| totalCount === ''){
            alert('색상, 사이즈, 판매수량 모두 입력하세요.');
            return;
        }
        let optionGroupSpecs = product.get('optionGroupSpecs');
        let newOptionGroupSpec;
        let newOptionSpec = {
            color: '',
            size: '',
            price: 0,
            totalCount: 0
        };
        if(optionGroupSpecs.size === 0){
            newOptionGroupSpec = {
                name: 'color&size',
                exclusive: false,
                basic: true,
                optionSpecs: fromJS([])
            };

            newOptionSpec.color = color;
            newOptionSpec.size = size;
            newOptionSpec.totalCount = totalCount;
            newOptionGroupSpec.optionSpecs = fromJS(newOptionGroupSpec.optionSpecs.push(fromJS(newOptionSpec)));
            ProductRegistrationActions.addOptionGroup(newOptionGroupSpec);
        }else{
            newOptionSpec.color = color;
            newOptionSpec.size = size;
            newOptionSpec.totalCount = totalCount;
            for(let i = 0; i < optionGroupSpecs.size; i++){
                let optionSpecs = optionGroupSpecs.get(i).get('optionSpecs').push(fromJS(newOptionSpec));
                optionGroupSpecs = optionGroupSpecs.setIn([i, 'optionSpecs'], optionSpecs);
            }
            ProductRegistrationActions.addOption(optionGroupSpecs);
        }
    };


    handleChangeProductInput = (e) => {
        const { ProductRegistrationActions } = this.props;
        let { value, name } = e.target;
        ProductRegistrationActions.changeInput({name, value});
    };
    // handleSubmit = async() => {
    //     const {  productImages, ProductRegistrationActions, history } = this.props;
    //     let { product } = this.props;
    //
    //     const title = product.get('title');
    //     const category = product.get('category').get('code');
    //     const price = product.get('price');
    //     const savedMoneyRate = product.get('savedMoneyRate');
    //     const options = product.get('options');
    //     const headerImage = productImages.get('header');
    //     const bodyImages = productImages.get('body');
    //
    //     if(title === '' || category === '' || price === '' || savedMoneyRate === ''){
    //         this.toast(constants.WARNING_ADD_PRODUCT, 'custom', 2000, toastColor);
    //         return;
    //     }
    //     if(options.size === 0){
    //         this.toast(constants.WARNING_ADD_PRODUCT_OPTION, 'custom', 2000, toastColor);
    //         return;
    //     }
    //     if(headerImage === '' || bodyImages.size === 0){
    //         this.toast(constants.WARNING_ADD_PRODUCT_IMAGE, 'custom', 2000, toastColor);
    //         return;
    //     }
    //
    //     product = product.set('userId', sessionStorage.getItem('id'));
    //     let formData = new FormData();
    //
    //     formData.append('product', new Blob([JSON.stringify(product)], {
    //         type: "application/json"
    //     }));
    //     formData.append('productFile', productImage);
    //     for(let i = 0; i < productDetailImages.size; i++){
    //         formData.append('productDetailFiles', productDetailImages.get(i));
    //     }
    //     try{
    //         await ProductRegistrationActions.addProduct(formData);
    //         history.push(`/product/${this.props.productId}`);
    //     }catch(e){
    //         console.log(e);
    //     }
    // };
    handleSubmit = async() => {
        const {  product, productImages, ProductRegistrationActions, history } = this.props;
        const name = product.get('name');
        const category = product.get('category');
        const price = product.get('price');
        const savedMoneyRate = product.get('savedMoneyRate');
        const description = product.get('description');
        const userId = sessionStorage.getItem('id');
        const optionGroupSpecs = product.get('optionGroupSpecs');

        const headerImage = productImages.get('header');
        const bodyImages = productImages.get('body');

        // if(name === '' || category === '' || price === '' || savedMoneyRate === ''){
        //     this.toast(constants.WARNING_ADD_PRODUCT, 'custom', 2000, toastColor);
        //     return;
        // }
        // if(optionGroupSpecs.size === 0){
        //     this.toast(constants.WARNING_ADD_PRODUCT_OPTION, 'custom', 2000, toastColor);
        //     return;
        // }
        // if(headerImage === '' || bodyImages.size === 0){
        //     this.toast(constants.WARNING_ADD_PRODUCT_IMAGE, 'custom', 2000, toastColor);
        //     return;
        // }

        let formData = new FormData();
        formData.append('name', name);
        formData.append('category', category);
        formData.append('price', price);
        formData.append('savedMoneyRate', savedMoneyRate);
        formData.append('description', description);
        formData.append('userId', userId);

        for(let i = 0; i < optionGroupSpecs.size; i++){
            formData.append('optionGroupSpecs[' + i + '].name', optionGroupSpecs.get(i).get('name'));
            formData.append('optionGroupSpecs[' + i + '].exclusive', optionGroupSpecs.get(i).get('exclusive'));
            formData.append('optionGroupSpecs[' + i + '].basic', optionGroupSpecs.get(i).get('basic'));
            let optionSpecs = optionGroupSpecs.get(i).get('optionSpecs');
            for(let j = 0; j < optionSpecs.size; j++){
                formData.append('optionGroupSpecs[' + i + '].optionSpecs[' + j + '].color', optionSpecs.get(j).get('color'));
                formData.append('optionGroupSpecs[' + i + '].optionSpecs[' + j + '].size', optionSpecs.get(j).get('size'));
                formData.append('optionGroupSpecs[' + i + '].optionSpecs[' + j + '].price', optionSpecs.get(j).get('price'));
                formData.append('optionGroupSpecs[' + i + '].optionSpecs[' + j + '].totalCount', optionSpecs.get(j).get('totalCount'));
            }
        }

        formData.append('productImageGroups[0].name', 'header');
        formData.append('productImageGroups[0].productImages[0].name', headerImage.name);
        formData.append('productImageGroups[0].productImages[0].size', headerImage.size);
        formData.append('productImageGroups[0].productImages[0].image', headerImage);

        formData.append('productImageGroups[1].name', 'body');
        for(let j = 0; j < bodyImages.size; j++){
            formData.append('productImageGroups[1].productImages[' + j + '].name', bodyImages.get(j).name);
            formData.append('productImageGroups[1].productImages[' + j + '].size', bodyImages.get(j).size);
            formData.append('productImageGroups[1].productImages[' + j + '].image', bodyImages.get(j));
        }

        ProductRegistrationActions.addProduct(formData);
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
        const { product, productImages, categories, colors, sizes } = this.props;
        const { handleChangeProductInput, handleAddOption, handleDeleteOption, handleImageUpload, handleSubmit } = this;
        return (
            <div>
                <Notifications />
                <ProductRegistrationHeader product={product} productImages={productImages} categories={categories} colors={colors} sizes={sizes}
                             onChangeProductInput={handleChangeProductInput} onAddOption={handleAddOption} onDeleteOption={handleDeleteOption} onImageUpload={handleImageUpload}/>
                <ProductRegistrationBody productImages={productImages} onChangeProductInput={handleChangeProductInput} onImageUpload={handleImageUpload} onAddProduct={handleSubmit}/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        product: state.productRegistration.get('product'),
        productImages: state.productRegistration.get('productImages'),
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
