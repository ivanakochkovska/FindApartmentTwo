import React from 'react'
import axios from 'axios'
import RegisterFormStyle from './RegisterForm.module.css'
import {Redirect} from 'react-router-dom'
import {withRouter} from "react-router";
import PropTypes from "prop-types";

class RegisterForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            surname: '',
            username: '',
            password: '',
            message: '',
            red: '',
        }
        this.onFormSubmit = this.onFormSubmit.bind(this);

    }

    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };
    disableButton() {
        if(this.state.name==='' || this.state.surname==='' ||  this.state.username==='' || this.state.password==='')
            return true;
    }

    onFormSubmit(event) {
        event.preventDefault();
        axios.post("/user/register", {
            name: this.state.name,
            surname: this.state.surname,
            username: this.state.username,
            password: this.state.password
        }).catch(error => {
            this.setState({
                message: error.response.data.error_message,
            });
        }).then(response=>{if(response!=undefined){if(response.status==200) {
            this.props.history.push('/login');
        }}});
    }
    minLenght() {
        return 6;
    }
    render() {

        return (
            <div className={RegisterFormStyle.registerDiv}>
                <form onSubmit={this.onFormSubmit}>
                    <div className="form-group">
                        <label>Внесете име: </label>
                        <input type="text" className="form-control" id="exampleInputEmail1"
                               onChange={(event => {
                                   this.setState({name: event.target.value})
                               })}/>
                    </div>
                    <div className="form-group">
                        <label>Внесете презиме: </label>
                        <input type="text" className="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp"
                               onChange={(event => {
                                   this.setState({surname: event.target.value})
                               })}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputEmail1">Внесете корисничко име: </label>
                        <input type="text" className="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp"
                               onChange={event => {
                                   this.setState({username: event.target.value})
                               }}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="exampleInputPassword1">Внесете лозинка: </label>
                        <input type="password"  className="form-control" id="exampleInputPassword1"
                               onChange={event => {
                                   this.setState({password: event.target.value})
                               }}

                        />
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={this.onFormSubmit}
                    disabled={this.disableButton()}>Регистрирај се
                    </button>
                    <br/><br/><br/>
                    <div>{this.state.message}</div>
                </form>
            </div>
        );
    }
}
RegisterForm = withRouter(RegisterForm)
export default RegisterForm;