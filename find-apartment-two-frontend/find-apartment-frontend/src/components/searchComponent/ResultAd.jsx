import React from "react";
import Button from "react-bootstrap/Button";
import Collapse from "react-bootstrap/Collapse";
import axios from "axios";

class ResultAd extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: false,
            color: '',
            auth: false
        }
        this.setCollapse = this.setCollapse.bind(this);
        this.getData = this.getData.bind(this);
    }
    clicked(id) {
        return id;
    }
    bookmarkAd(id) {
        axios.post("/user/bookmarkAd",{
            ad_user_id: id
        });
        this.setState({color:"green"})
    }
    setCollapse() {
        this.setState({collapse: !this.state.collapse});
    }
    componentDidMount() {
        this.getData();
    }

    getData() {
        axios.get("/logged").then(
            res => {
                this.setState({auth: true})
            }
        );
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

                                    <button type="button" onClick={() => this.bookmarkAd(this.props.clicked)}
                                            class="btn btn-secondary"
                                            style={{backgroundColor: `${this.state.color}`}}
                                    >Зачувај</button>

                            </div>
                        </Collapse>
                    </div>
                </div>
            </div>
        );
    }
}
export default ResultAd;