import React from 'react';
import SignupTemplate from "../components/signup/SignupTemplate/SignupTemplate";
import SignupContainer from 'containers/signup/SignupContainer';
import Wrapper from "components/common/Wrapper";
import HeaderContainer from 'containers/common/HeaderContainer'

const SignupPage = () => {
    return (
        <SignupTemplate>
            <HeaderContainer/>
            <Wrapper>
                <SignupContainer/>
            </Wrapper>
        </SignupTemplate>
    );
};

export default SignupPage;