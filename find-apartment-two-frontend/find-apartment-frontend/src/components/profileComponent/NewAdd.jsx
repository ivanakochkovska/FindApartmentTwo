import React from 'react'
import ProfileComponent from './ProfileComponent.module.css'
import axios from 'axios'
import PropTypes from "prop-types";
import { withRouter } from 'react-router'
class NewAdd extends React.Component {
    constructor(props) {
        super(props);
        this.state =  {
            status: false,
            price: '',
            name: '',
            surname: '',
            email: '',
            phone_number: '',
            description: '',
            municipality: '',
            size: '',
            number_of_bathrooms: '',
            number_of_bedrooms: '',
            parking_spot: null,
            additional_information: '',
            disabled: true,
        }
        this.handleParkingSpot=this.handleParkingSpot.bind(this);
        this.handleHeating = this.handleHeating.bind(this);
        this.disableButton=this.disableButton.bind(this);
        this.onFormSubmit=this.onFormSubmit.bind(this);
    }
    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };
    handleParkingSpot(event) {
        if(event.target.value=="НЕ") {
            this.setState({parking_spot: false});
        } else if(event.target.value=="ДА") {
            this.setState({parking_spot: true});
        } else if(event.target.value=="") {
            this.setState({parking_spot: null});
        }
    }
    handleHeating(event) {
        if(event.target.value=="Сопствено греење") {
            this.setState({additional_information: "OWN_HEATING"});
        } else if(event.target.value=="Централно греење") {
            this.setState({additional_information: "CENTRAL_HEATING"});
        } else if(event.target.value=="Електрична енергија") {
            this.setState({additional_information: "ELECTRICITY"});
        } else if (event.target.value=='') {
            this.setState({additional_information: ''})
        }
    }
    disableButton() {
        if(this.state.name=='' || this.state.surname=='' || this.state.email=='' || this.state.phone_number==''
        || this.state.municipality=='' || this.state.price=='' || this.state.size=='' || this.state.number_of_bathrooms=='' || this.state.number_of_bedrooms==''
        || this.state.parking_spot==null || this.state.additional_information=='' || this.state.description=='') {
            return true;
        } else {
            return false;
        }
    }
    onFormSubmit(event) {
        event.preventDefault();
        axios.post("/ad/save",{
            status: true,
            price: this.state.price,
            name: this.state.name,
            surname: this.state.surname,
            email: this.state.email,
            phone_number: this.state.phone_number,
            description: this.state.description,
            apartment: {
                municipality: this.state.municipality,
                size: this.state.size,
                number_of_bathrooms: this.state.number_of_bathrooms,
                number_of_bedrooms: this.state.number_of_bedrooms,
                parking_spot: this.state.parking_spot,
                additional_information: this.state.additional_information,
            }
            }).then(this.props.history.push('/profile'));
    }
    render() {
        return (
          <div className={ProfileComponent.newAddDiv}>
              <div className="card">
                  <div className="card-body">
            <form onSubmit={this.onFormSubmit}>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Име на огласувач:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={(e)=>{this.setState({name: e.target.value})}}
                  />

              </div>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Презиме на огласувач:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={(e)=>{this.setState({surname: e.target.value})}}
                  />

              </div>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Email на огласувач:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={(e)=>{this.setState({email: e.target.value})}}
                  />

              </div>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Телефонски број на огласувач:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={(e)=>{this.setState({phone_number: e.target.value})}}
                  />

              </div>
              <div>Информации за станот: </div>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Општина:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={e=>{this.setState({municipality: e.target.value})}}
                  />

              </div>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Цена:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={(e)=>{this.setState({price: e.target.value})}}
                  />

              </div>
              <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Квадратура:  </label>
                  <input type="text" className="form-control" id="exampleInputEmail1"
                         aria-describedby="emailHelp"
                         onChange={(e)=>{this.setState({size: e.target.value})}}
                  />

              </div>
              <div className="form-group">
                  <label htmlFor="exampleFormControlSelect1">Број на бањи: </label>
                  <select  className="form-control" id="exampleFormControlSelect1" onChange={(e)=>{this.setState({number_of_bathrooms: e.target.value})}}>
                      <option> </option>
                      <option>0</option>
                      <option>1</option>
                      <option>2</option>
                      <option>3</option>
                      <option>4</option>
                      <option>5</option>
                  </select>
              </div>
              <div className="form-group">
                  <label htmlFor="exampleFormControlSelect1">Број на спални соби: </label>
                  <select  className="form-control" id="exampleFormControlSelect1" onChange={(e)=>{this.setState({number_of_bedrooms: e.target.value})}}>
                      <option> </option>
                      <option>0</option>
                      <option>1</option>
                      <option>2</option>
                      <option>3</option>
                      <option>4</option>
                      <option>5</option>
                  </select>
              </div>
              <div className="form-group">
                  <label htmlFor="exampleFormControlSelect1">Паркинг место: </label>
                  <select  className="form-control" id="exampleFormControlSelect1" onChange={this.handleParkingSpot}>
                      <option></option>
                      <option>ДА</option>
                      <option>НЕ</option>
                  </select>
              </div>

              <div className="form-group">
                  <label htmlFor="exampleFormControlSelect1">Греење: </label>
                  <select  className="form-control" id="exampleFormControlSelect1" onChange={this.handleHeating}>
                      <option> </option>
                      <option>Сопствено греење</option>
                      <option>Централно греење</option>
                      <option>Електрична енергија</option>
                  </select>
              </div>
              <div className="form-group">
                  <label htmlFor="exampleFormControlTextarea1">Дополнителен опис: </label>
                  <textarea className="form-control" id="exampleFormControlTextarea1" rows="5"
                            onChange={(e)=>{this .setState({description: e.target.value})}}></textarea>
              </div>
                <button type="button" className="btn btn-primary" disabled={this.disableButton()}
                        onClick={this.onFormSubmit}>Објави</button>
            </form>
              </div>
              </div>
          </div>
        );
    }
}
NewAdd = withRouter(NewAdd);
export default NewAdd;