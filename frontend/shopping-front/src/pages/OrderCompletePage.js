import React from 'react';
import PageTemplate from 'components/common/PageTemplate';
import Wrapper from "components/common/Wrapper/Wrapper";
import OrderCompleteContainer from 'containers/order/OrderCompleteContainer';

const OrderCompletePage = () => {
    return (
        <PageTemplate>
            <Wrapper title="주문완료">
                <OrderCompleteContainer/>
            </Wrapper>
        </PageTemplate>
    );
};

export default OrderCompletePage;