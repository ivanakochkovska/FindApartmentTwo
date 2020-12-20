import './App.css';
import axios from 'axios'
import React from 'react'
import {BrowserRouter as Router, Switch, Route, Link} from 'react-router-dom';
import RegisterForm from "./components/registerComponent/RegisterForm";
import LoginForm from "./components/loginComponent/LoginForm";
import ProfilePage from "./pages/ProfilePage";
import NavComponent from "./components/NavBar/NavComponent";
import AddNewAdd from "./pages/AddNewAdd";
import HomePage from "./pages/HomePage";
import PrivateRoute from "./PrivateRoute";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLogged: true,
            navDisappear: true,
            authenticated: false,
        }
        this.getData = this.getData.bind(this);
        //this.getData();
    }

    componentDidMount() {
        this.getData();
    }

    getData() {
        axios.get("/logged").then(
            res => {
                this.setState({authenticated: true})
                console.log("nn")
            }
        );
    }

    render() {
        return (
            <Router>
                <div className="App">
                    <NavComponent logged={this.state.isLogged}/>
                    <Switch>
                        <Route exact path="/" component={HomePage}/>
                        <Route path="/register" component={RegisterForm}/>
                        <Route path="/login" component={LoginForm}/>
                        <PrivateRoute authenticated={this.state.authenticated}
                                      path='/profile' component={ProfilePage}/>
                        <PrivateRoute authenticated={this.state.authenticated}
                                      path='/add/new' component={AddNewAdd}/>
                    </Switch>
                </div>
            </Router>
        );
    }


}

export default App;
