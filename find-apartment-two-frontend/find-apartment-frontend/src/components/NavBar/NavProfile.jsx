import React from 'react'
import {Link} from "react-router-dom";
import navProfile from "./navComponent.module.css";
import axios from "axios";
class NavProfile extends React.Component {
    constructor(props) {
        super(props);
        this.logout = this.logout.bind(this);
    }
    logout() {
        axios.post("/logout");
    }


    render() {
        return(
          <div>
              <nav className="navbar navbar-expand-lg navbar-light bg-light">
                  <div className="navbar-brand" >Пронајди сместување во Скопје</div>
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

                          <div className={navProfile.navProfile}>
                              <li className="nav-item active">
                                  <Link className="nav-link" to="/profile">Кориснички профил </Link>
                              </li>
                          </div>


                          <div className={navProfile.navProfile}>
                              <li className="nav-item active">
                                  <Link className="nav-link" to="/" onClick={()=>{this.logout()}}>Одјави се </Link>
                              </li>
                          </div>

                          <div className={navProfile.navProfile}>
                              <li className="nav-item active">
                                  <Link className="nav-link" to="/add/new">Објави оглас </Link>
                              </li>
                          </div>

                      </ul>

                  </div>
              </nav>
          </div>
        );
    }

}
export default NavProfile;