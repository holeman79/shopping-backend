import React, {Component} from 'react';
import ProductViewInfo from 'components/product/ProductViewInfo';
import ProductViewBody from 'components/product/ProductViewBody';
import Notifications, { notify } from 'react-notify-toast';
import * as constants from "constants/Constants";
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { bindActionCreators } from 'redux';
import * as ProductViewActions from 'store/modules/productView';
import * as OrderActions from 'store/modules/order';
import {fromJS, List, Map } from "immutable";

const toastColor = {
    background: '#505050',
    text: '#fff'
}

class ProductViewContainer extends Component {
    toast = notify.createShowQueue();
    state = {
        colorSizeList: fromJS([])
    };
    componentDidUpdate(prevProps, prevState){
        const { productOptionGroupSpecs, selectedColor, selectedSize } = this.props;
        if(prevProps.productOptionGroupSpecs !== this.props.productOptionGroupSpecs) {
            let productOptionSpecs = productOptionGroupSpecs.get(0).get('optionSpecs');
            let colorSizeList = fromJS([]);
            for(let i = 0; i < productOptionSpecs.size; i++){
                let productOptionSpec = productOptionSpecs.get(i);
                let exist = false;

                for(let j = 0; j < colorSizeList.size; j++){
                    let item = colorSizeList.get(j);
                    if(productOptionSpec.get('color') === item.get('color').get('key')) {
                        //colorSizeList = colorSizeList.update(j, item => item.set('size', item.get('size').push(option.get('size'))));
                        colorSizeList = colorSizeList.update(j, item => item.set('size',
                            item.get('size').push(
                                Map({
                                    key: productOptionSpec.get('size'),
                                    value: productOptionSpec.get('sizeValue')
                                })
                            )));
                        exist = true;
                        break;
                    }
                }
                if(exist === false){
                    colorSizeList = colorSizeList.push(
                        Map(
                            {
                                color: Map(
                                    {
                                        key: productOptionSpec.get('color'),
                                        value: productOptionSpec.get('colorValue'),
                                    }),

                                size: List([
                                    Map({
                                        key: productOptionSpec.get('size'),
                                        value: productOptionSpec.get('sizeValue'),
                                    })
                                ])
                            }));
                }
            }
            this.setState({
                colorSizeList: colorSizeList
            });
        }
        // (prevProps.color !== this.props.color) || (prevProps.size !== this.props.size)
        if( selectedColor.get('code') != "" && selectedSize.get('code') != "") {
            this.handleAddOrderOption({selectedColor, selectedSize});
        }
    };

    initialize = async () => {
        const { ProductViewActions, id } = this.props;
        try{
            ProductViewActions.initialize();
            await ProductViewActions.getProduct(id);
        }catch(e){
            console.log(e);
        }
    };

    componentDidMount() {
        this.initialize();
        document.documentElement.scrollTop = 0;
    };

    handleChangeProductInput = (e) =>{
        const { ProductViewActions } = this.props;
        const { value, name } = e.target;
        if(name === 'selectedColor' || name === 'selectedSize'){
            ProductViewActions.changeSelectbox({name, value});
        }
        else ProductViewActions.changeInput({name, value});
    };

    handleChangeOrderProductNumber = ({e, index}) =>{
        const { ProductViewActions } = this.props;
        const { value, name } = e.target;
        ProductViewActions.changeOrderProductNumber({name, value, index});
    };

    handleAddOrderOption = ({selectedColor, selectedSize}) => {
        const { orderOptions, ProductViewActions } = this.props;
        for(let i = 0 ; i < orderOptions.size; i++){
            const color = orderOptions.get(i).get('color');
            const size = orderOptions.get(i).get('size');
            if(color.get('key') === selectedColor.get('key') && size.get('key') === selectedSize.get('key')){
                const msg = constants.WARNING_ADD_ORDER_OPTION;
                this.toast(msg, 'custom', 2000, toastColor);
                ProductViewActions.changeInput({name: 'selectedSize', value: Map({key: '', value: ''})});
                return;
            }
        }
        ProductViewActions.addOrderOption({selectedColor, selectedSize});
    };
    handleDeleteOption = (index) => {
        const { ProductViewActions } = this.props;
        ProductViewActions.deleteOrderOption(index);
    };
    handlePurchase = () => {
        const { OrderActions, product, orderOptions, history } = this.props;
        let orderItems = fromJS([]);
        for(let i = 0; i < orderOptions.size; i++){
            orderItems = orderItems.push(Map({product: product, color: orderOptions.get(i).get('color'), size: orderOptions.get(i).get('size'), number: orderOptions.get(i).get('number')}));
        }
        OrderActions.initializeOrder(orderItems);
        history.push('/order');
    };


    render() {
        const { product, selectedColor, orderOptions } = this.props;
        const { handleChangeProductInput, handleDeleteOption, handleChangeOrderProductNumber, handlePurchase } = this;
        return (
            <div>
                <Notifications />
                <ProductViewInfo product={product} selectedColor={selectedColor} colorSizeList={this.state.colorSizeList} orderOptions={orderOptions}
                                 onChangeProductInput={handleChangeProductInput} onDeleteOption={handleDeleteOption} onChangeOrderProductNumber={handleChangeOrderProductNumber}
                                 onPurchase={handlePurchase}/>
                <ProductViewBody product={product}/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        product: state.productView.get('product'),
        productOptionGroupSpecs: state.productView.get('product').get('optionGroupSpecs'),
        selectedColor: state.productView.get('selectedColor'),
        selectedSize: state.productView.get('selectedSize'),
        orderOptions: state.productView.get('order').get('options'),
    }),
    (dispatch) => ({
        ProductViewActions: bindActionCreators(ProductViewActions, dispatch),
        OrderActions: bindActionCreators(OrderActions, dispatch),
    })
)(withRouter(ProductViewContainer));
