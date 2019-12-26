import React, {Component} from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as orderActions from 'store/modules/order';
import * as constants from "constants/Constants";
import { withRouter } from 'react-router-dom';
import Notifications, { notify } from 'react-notify-toast';
import OrderList from "components/order/OrderList";
import OrderInfo from "components/order/OrderInfo";
import PostCodeModal from "components/modal/PostCodeModal";
import PaymentInfo from "components/order/PaymentInfo";
import {Map} from "immutable";

const toastColor = {
    background: '#505050',
    text: '#fff'
};

class OrderContainer extends Component {
    toast = notify.createShowQueue();
    initialize = async () => {
        const { OrderActions } = this.props;
        try{
            await OrderActions.getOptionList();
            this.calculateTotalPrice();
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
    };
    calculateTotalPrice = () => {
        const { OrderActions, orderItems } = this.props;
        let totalPrice = 0;
        for(let i = 0; i < orderItems.size; i++){
            let price = orderItems.get(i).get('price');
            let count = orderItems.get(i).get('count');
            totalPrice += (price * count);
        }
        OrderActions.changeInput({name: 'totalPrice', value: totalPrice});
    };

    handlePostCode = () => {
        const { OrderActions } = this.props;
        OrderActions.showModal();
    };
    handleCancel = () => {
        const { OrderActions } = this.props;
        OrderActions.hideModal();
    };
    handleChangeInput = (e) =>{
        const { OrderActions } = this.props;
        const { value, name } = e.target;
        OrderActions.changeInput({name, value});
    }
    handleChangeObject = (e) => {
        const { OrderActions } = this.props;
        const { value, name } = e.target;
        OrderActions.changeObject({name, value});
        //obj[event.target.value] = event.target.checked // true

    };

    handleAddress = (data) => {
        const { OrderActions } = this.props;
        let addr = ''; // 주소 변수
        let extraAddr = ''; // 참고항목 변수

        //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
        } else { // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
        }

        // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
        if(data.userSelectedType === 'R'){
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraAddr !== ''){
                extraAddr = ' (' + extraAddr + ')';
            }
            // 조합된 참고항목을 해당 필드에 넣는다.

            //document.getElementById("sample6_extraAddress").value = extraAddr;

        } else {
            //document.getElementById("sample6_extraAddress").value = '';
        }

        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        let name = 'zipcode';
        let value = data.zonecode;
        OrderActions.changeInput({name, value});

        name = 'address';
        value = addr;
        OrderActions.changeInput({name, value});
        this.handleCancel();
    };

    handlePayment = async() => {
        let { orderItems, ordererName, zipcode, address, detailAddress, mobileNo1, mobileNo2, mobileNo3, selectedPayment, OrderActions, history  } = this.props;
        if(ordererName === '' || zipcode === '' || address === '' || detailAddress === '' || mobileNo2 === '' || mobileNo3 === ''){
            this.toast(constants.WARNING_ADD_ORDER_ORDERER_INFO, 'custom', 2000, toastColor);
            return;
        }
        if(selectedPayment.get('key') !== 'REMITTANCE') return this.toast(constants.WARNING_ADD_ORDER_PAYMENT_WAY, 'custom', 2000, toastColor);
        else{
            const { depositorName, selectedBankBook } = this.props;
            if(depositorName === '') return this.toast(constants.WARNING_ADD_ORDER_PAYMENT_DEPOSITORNAME, 'custom', 2000, toastColor);
            const userId = sessionStorage.getItem('id');

            for(let i = 0; i < orderItems.size; i++){
                orderItems = orderItems.update(i, orderItem =>
                    {
                        let orderOptionGroups = orderItem.get('orderOptionGroups');
                        for(let j = 0; j < orderOptionGroups.size; j++){
                            orderOptionGroups = orderOptionGroups.update(j, orderOptionGroup => {
                                let orderOptions = orderOptionGroup.get('orderOptions');
                                for(let k = 0; k < orderOptions.size; k++){
                                    orderOptions = orderOptions.update(k, orderOption => orderOption.set('color', orderOption.get('color').get('key'))
                                                                                                    .set('size', orderOption.get('size').get('key')));
                                }
                                orderOptionGroup = orderOptionGroup.set('orderOptions', orderOptions);
                                return orderOptionGroup;
                            });
                        }
                        return orderItem.set('orderOptionGroups', orderOptionGroups);
                    }
                );
            }

            let order = { ordererName, userId, zipcode, address, detailAddress, mobileNumber: mobileNo1 + mobileNo2 + mobileNo3,
                paymentType: selectedPayment.get('key'), bankBook: selectedBankBook.get('key'), depositorName, orderItems };
            try{
                await OrderActions.addOrder(order);
                history.push('/order/complete');
            }catch(e){
                console.log(e);
            }
        }
    };

    render() {
        const { handleAddress, handleChangeInput, handlePostCode, handleCancel, handleChangeObject, handlePayment } = this;
        const { orderItems, visible, zipcode, address, selectedPayment, depositorName, selectedBankBook, totalPrice, paymentTypes, bankBooks, phoneFirstNumbers } = this.props;
        return (
            <div>
                <Notifications />
                <OrderList orderItems={orderItems}/>
                <OrderInfo zipcode={zipcode} address={address} phoneFirstNumbers={phoneFirstNumbers}
                           onPostCode={ handlePostCode } onChangeInput={handleChangeInput} />
                <PaymentInfo paymentTypes={paymentTypes} bankBooks={bankBooks}  selectedPayment={selectedPayment} depositorName={depositorName} selectedBankBook={selectedBankBook} totalPrice={totalPrice}
                             onChangeObject={handleChangeObject} onChangeInput={handleChangeInput} onPayment={handlePayment}/>
                <PostCodeModal visible={ visible } onCancel={ handleCancel } onAddress={ handleAddress }/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        orderItems: state.order.get('orderItems'),
        visible: state.order.get('modalPostCode'),
        ordererName: state.order.get('ordererName'),
        zipcode: state.order.get('zipcode'),
        address: state.order.get('address'),
        detailAddress: state.order.get('detailAddress'),
        mobileNo1: state.order.get('mobileNo1'),
        mobileNo2: state.order.get('mobileNo2'),
        mobileNo3: state.order.get('mobileNo3'),

        selectedPayment: state.order.get('selectedPayment'),
        depositorName: state.order.get('depositorName'),
        selectedBankBook: state.order.get('selectedBankBook'),

        totalPrice: state.order.get('totalPrice'),

        paymentTypes: state.order.get('paymentTypes'),
        bankBooks: state.order.get('bankBooks'),
        phoneFirstNumbers: state.order.get('phoneFirstNumbers'),
        error: state.order.get('error'),
    }),
    (dispatch) => ({
        OrderActions: bindActionCreators(orderActions, dispatch),
    })
)(withRouter(OrderContainer));