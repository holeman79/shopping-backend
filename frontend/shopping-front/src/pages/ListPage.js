import React from 'react';
import PageTemplate from 'components/common/PageTemplate';
import Wrapper from "components/common/Wrapper";
import ListContainer from "containers/list/ListContainer";

const ListPage = ({match}) => {
    const { page = 1, category } = match.params;
    return (
        <PageTemplate>
            <Wrapper>
                <ListContainer page={parseInt(page, 10)} category={category}/>
            </Wrapper>
        </PageTemplate>
    );
};

export default ListPage;