import React from 'react';
import PageTemplate from 'components/common/PageTemplate';
import Wrapper from "components/common/Wrapper/Wrapper";
import ProductViewContainer from 'containers/product/ProductViewContainer';

const ProductViewPage = ({match}) => {
    const { id } = match.params;
    return (
        <PageTemplate>
            <Wrapper>
                <ProductViewContainer id={id}/>
            </Wrapper>
        </PageTemplate>
    );
};

export default ProductViewPage;