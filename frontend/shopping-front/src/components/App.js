import React from 'react';
import { Switch, Route } from 'react-router-dom';
import { ListPage, ProductViewPage, ProductRegistrationPage, LoginPage, SignupPage, OrderPage, OrderCompletePage, NotFoundPage } from 'pages';
import OAuth2RedirectHandler from "./oauth2";

const App = () => {
    return (
        <div>
            <Switch>
                <Route exact path="/" component={ListPage}/>
                <Route path="/page/:page" component={ListPage}/>
                <Route path="/category/:category/:page?" component={ListPage}/>
                <Route exact path="/product/registration" component={ProductRegistrationPage}/>
                <Route path="/product/:id" component={ProductViewPage}/>
                <Route path="/login" component={LoginPage}/>
                <Route path="/signup" component={SignupPage}/>
                <Route exact path="/order" component={OrderPage}/>
                <Route path="/order/complete" component={OrderCompletePage}/>
                <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}/>
                <Route component={NotFoundPage}/>
            </Switch>
        </div>
    );
};

export default App;