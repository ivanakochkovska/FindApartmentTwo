import React from 'react'
import axios from 'axios'
import LoginFormStyle from './LoginFormStyle.module.css'
import {Redirect} from "react-router-dom";
import {withRouter} from'react-router'
import PropTypes from "prop-types";
class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
        }
    this.handleUsername=this.handleUsername.bind(this);
        this.handlePasswordChange=this.handlePasswordChange.bind(this);
        this.onFormSubmit=this.onFormSubmit.bind(this);
    }

    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    handleUsername(event) {
        this.setState({
            username: event.target.value
        });
    }

    handlePasswordChange(event) {
        this.setState({
            password: event.target.value
        });
    }

    onFormSubmit(event) {
        event.preventDefault();
        axios.post("/user/login",{
            username: this.state.username,
            password: this.state.password
        }).catch(error => {
            this.setState({
                message: error.response.data.error_message,
            });
        }).then(response=>{if(response!=undefined){if(response.status==200) {
            this.props.history.push('/profile');
        }}});
    }

    disableButton() {
        if(this.state.username==='' || this.state.password==='')
            return true;
    }
    render() {

        return (

            <div className={LoginFormStyle.divForm}>
                <form onSubmit={this.onFormSubmit}>

                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Внесете корисничко име: </label>
                        <input type="text" className="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp"
                               onChange={this.handleUsername}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputPassword1">Внесете лозинка: </label>
                        <input type="password" className="form-control" id="exampleInputPassword1"
                               minLength={6}
                               onChange={this.handlePasswordChange}/>
                    </div>
                    <div>{this.state.message}</div>
                    <button type="submit" className="btn btn-primary" onClick={this.onFormSubmit}
                    disabled={this.disableButton()}>Логирај се</button>
                </form>
            </div>

        );
    }

}
LoginForm = withRouter(LoginForm);
export default LoginForm;