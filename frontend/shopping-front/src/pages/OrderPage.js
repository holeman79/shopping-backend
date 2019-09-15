import React from 'react';
import PageTemplate from 'components/common/PageTemplate';
import Wrapper from "components/common/Wrapper/Wrapper";
import OrderContainer from 'containers/order/OrderContainer';

const OrderPage = () => {
    return (
        <PageTemplate>
            <Wrapper title="결제하기">
                <OrderContainer/>
            </Wrapper>
        </PageTemplate>
    );
};

export default OrderPage;