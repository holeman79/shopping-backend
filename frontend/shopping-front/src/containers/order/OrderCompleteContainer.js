import React, {Component} from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as orderActions from 'store/modules/order';
import * as constants from "constants/Constants";
import OrderComplete from "components/order/OrderComplete";

class OrderCompleteContainer extends Component {

    initialize = async () => {
        const { OrderActions } = this.props;
        try{

        }catch(e){
            console.log(e);
        }
    }

    componentDidMount() {
        this.initialize();
    }


    render() {
        const { depositorName, selectedPayment, selectedBankBook} = this.props;
        const name = sessionStorage.getItem('name');
        return (
            <div>
                <OrderComplete depositorName={depositorName} selectedPayment={selectedPayment} selectedBankBook={selectedBankBook} name={name}/>
            </div>
        );
    }
}

export default connect(
    (state) => ({
        depositorName: state.order.get('depositorName'),
        selectedPayment: state.order.get('selectedPayment'),
        selectedBankBook: state.order.get('selectedBankBook'),
    }),
    (dispatch) => ({

    })
)(OrderCompleteContainer);