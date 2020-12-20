import React from 'react'
import { Link } from 'react-router-dom'
class NavComponent extends React.Component {
    constructor() {
        super();
    }

    render() {
        return(
            <div>{
                !this.props.isLogged &&
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="navbar-brand">Пронајди сместување во Скопје</div>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to="/">Почетна</Link>
                            </li>

                            <li className="nav-item active">
                                <Link className="nav-link" to="/register">Регистрирај се</Link>
                            </li>


                            <li className="nav-item active">
                                <Link className="nav-link" to="/login">Логирај се </Link>
                            </li>

                        </ul>

                    </div>
                </nav>
            }
            </div>

        );

    }
}
export default NavComponent;