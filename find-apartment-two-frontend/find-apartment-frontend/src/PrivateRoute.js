import React from "react";
import {Redirect} from 'react-router-dom';
import {Route} from "react-router-dom";

const PrivateRoute = ({component: Component, authenticated, ...rest}) => {
    console.log(authenticated)
    return (
        <Route
            {...rest}
            render={props => (
                authenticated === true ?
                 <Component {...props} />
                :  <Redirect to="/login" />
                )}/>
    );
};
export default PrivateRoute;