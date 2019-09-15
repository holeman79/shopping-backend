import React from 'react';
import PageTemplate from 'components/common/PageTemplate';
import Wrapper from "components/common/Wrapper/Wrapper";
import ProductRegistrationContainer from 'containers/product/ProductRegistrationContainer';

const ProductRegistrationPage = () => {
    return (
        <PageTemplate>
            <Wrapper>
                <ProductRegistrationContainer/>
            </Wrapper>
        </PageTemplate>
    );
};

export default ProductRegistrationPage;