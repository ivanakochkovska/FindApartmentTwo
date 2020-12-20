import React from "react";
import Button from "react-bootstrap/Button";
import Collapse from "react-bootstrap/Collapse";
import axios from "axios";
class UserAd extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: false,
        }
        this.setCollapse = this.setCollapse.bind(this);
    }
    clicked(id) {
        return id;
    }
    deleteAdd(id, where) {
        if(where==="0") {
            axios.delete(`/user/deleteAd/${id}`)
                .then(window.location.reload(true));
        }
        else if(where==="1")
        {
            axios.delete(`/user/deleteBookmarkAd/${id}`)
                .then(window.location.reload(true));
        }
    }
    setCollapse() {
        this.setState({collapse: !this.state.collapse});
    }
    render() {
        return(
            <div>
                <div className="card">
                    <div className="card-body">
                        <div><b>Опис: </b></div>
                        <div>{this.props.description}</div>
                        <br/>
                        <Button onClick={this.setCollapse}
                                aria-controls="example-collapse-text"
                                aria-expanded={this.state.collapse}>
                            ...
                        </Button>
                        <br/>
                        <Collapse in={this.state.collapse}>
                            <div id="example-collapse-text">
                                <br/>
                                <div><b>Еmail на огласувач: </b></div>
                                <div>{this.props.email}</div>
                                <div><b>Име на огласувач: </b></div>
                                <div>{this.props.name}</div>
                                <div><b>Презиме на огласувач:</b> </div>
                                <div>{this.props.surname}</div>
                                <div><b>Телефонски број на огласувач:</b> </div>
                                <div>{this.props.phone_number}</div>
                                <div><b>Цена:</b> </div>
                                <div>{this.props.price}</div>
                                <div><b>Квадратура: </b></div>
                                <div>{this.props.size}</div>
                                <div><b>Греење: </b></div>
                                <div>{this.props.additional_information}</div>
                                <div><b>Општина: </b></div>
                                <div>{this.props.municipality}</div>
                                <div><b>Број на спални соби:</b> </div>
                                <div>{this.props.number_of_bathrooms}</div>
                                <div><b>Број на бањи:</b> </div>
                                <div>{this.props.number_of_bedrooms}</div>
                                <div><b>Паркинг:</b></div>
                                <div>{this.props.parking_spot}</div>
                                <br/>
                                <button type="button" onClick={()=>this.deleteAdd(this.props.clicked, this.props.bookmark)}className="btn btn-outline-danger" >Избриши</button>
                            </div>
                        </Collapse>
                    </div>
                </div>
            </div>
        );
    }
}
export default UserAd;