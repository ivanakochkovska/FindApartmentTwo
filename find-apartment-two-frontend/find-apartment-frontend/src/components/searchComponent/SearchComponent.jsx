import React  from "react";
import SearchComponentStyle from './SearchComponentStyle.module.css'
import '../../App.css'
import axios from 'axios'
import UserAd from "../profileComponent/UserAd";
import ResultAd from "./ResultAd";
class SearchComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state =  {
            municipality: null,
            price_from: null,
            price_to: null,
            size_start: null,
            size_end: null,
            number_of_bathrooms: null,
            number_of_bedrooms: null,
            parking_spot: null,
            additional_information: null,
            prices : [] ,
            sizes: [],
            data: []
        }
        this.handleParkingSpot=this.handleParkingSpot.bind(this);
        this.handleHeating = this.handleHeating.bind(this);
        this.getPrices = this.getPrices.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
    }
    handleParkingSpot(event) {
        if(event.target.value=="НЕ") {
            this.setState({parking_spot: false});
        } else if(event.target.value=="ДА") {
            this.setState({parking_spot: true});
        } else if(event.target.value=="") {
            this.setState({parking_spot: null});
        }
    }
    componentDidMount() {
        this.getPrices();
        this.getSizes()
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
    getPrices() {
        let array = [];
        array[0]=''
        for(let i =1; i<2000;i+=1) {
            if(i%10==0)
                array[i]=i
        }
        this.setState({prices: array});
        console.log(array)
    }
    getSizes() {
        let array = [];
        array[0]=''
        for(let i =1; i<400;i+=1) {
            if(i%10==0)
                array[i]=i
        }
        this.setState({sizes: array});
    }

    handleSearch() {
        axios.post("/ad/search",{
            municipality: this.state.municipality,
            price_from: this.state.price_from,
            price_to: this.state.price_to,
            size_start: this.state.size_start,
            size_end: this.state.size_end,
            number_of_bathrooms: this.state.number_of_bathrooms,
            number_of_bedrooms: this.state.number_of_bedrooms,
            parking_spot: this.state.parking_spot,
            additional_information: this.state.additional_information
        }).then(res=>{this.setState({data: res.data})})
    }

    render() {
        return(
            <div className={SearchComponentStyle.divCenter}>
                <br/>
            <table className="table table-bordered">
                <thead>
                <tr>
                </tr>
                <tr></tr>
                </thead>
                <tbody>
                <tr>
                <td>
                <label htmlFor="exampleInputEmail1">Општина: </label>
                <input type="text" className="form-control" id="exampleInputEmail1"
                       aria-describedby="emailHelp"
                       onChange={(e)=>{this.setState({municipality: e.target.value})}}
                       />

                </td>
                <td>
                <label htmlFor="exampleFormControlSelect1">Број на бањи: </label>
                <select  className="form-control" id="exampleFormControlSelect1"
                         onChange={(e)=>{this.setState({number_of_bathrooms: e.target.value})}}>
                    <option> </option>
                    <option>0</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
                </td>

                <td>
                <label htmlFor="exampleFormControlSelect1">Број на спални соби: </label>
                <select  className="form-control" id="exampleFormControlSelect1"
                         onChange={(e)=>{this.setState({number_of_bedrooms: e.target.value})}}>
                    <option> </option>
                    <option>0</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
                </td>

                <td>
                <label htmlFor="exampleFormControlSelect1">Паркинг место: </label>
                <select  className="form-control" id="exampleFormControlSelect1" onChange={this.handleParkingSpot}>
                    <option></option>
                    <option>ДА</option>
                    <option>НЕ</option>
                </select>
                </td>

                <td>
                <label htmlFor="exampleFormControlSelect1">Греење: </label>
                <select  className="form-control" id="exampleFormControlSelect1" onChange={this.handleHeating}>
                    <option></option>
                    <option>Сопствено греење</option>
                    <option>Централно греење</option>
                    <option>Електрична енергија</option>
                </select>
                </td>
                <br/><br/>
                </tr>
                <tr>
                    <td>
                    <label htmlFor="exampleFormControlSelect1">Цена (евра) од: </label>
                    <select  className="form-control" id="exampleFormControlSelect1"
                             onChange={(e)=>{this.setState({price_from: e.target.value})}}>
                        {
                            this.state.prices.map(
                                el =>
                                    <option>{el}</option>
                            )

                        }

                    </select>
                    </td>

                    <td>
                        <label htmlFor="exampleFormControlSelect1">Цена (евра) до: </label>
                        <select  className="form-control" id="exampleFormControlSelect1"
                                 onChange={(event => {this.setState({price_to: event.target.value})})}>
                            {
                                this.state.prices.map(
                                    el =>
                                        <option>{el}</option>
                                )

                            }

                        </select>
                    </td>

                    <td>
                        <label htmlFor="exampleFormControlSelect1">Големина (m2) од: </label>
                        <select  className="form-control" id="exampleFormControlSelect1"
                                 onChange={(event => {this.setState({size_start: event.target.value})})}>
                            {
                                this.state.sizes.map(
                                    el =>
                                        <option>{el}</option>
                                )

                            }

                        </select>
                    </td>
                    <td>
                        <label htmlFor="exampleFormControlSelect1">Големина (m2) дo: </label>
                        <select  className="form-control" id="exampleFormControlSelect1"
                                 onChange={(event => {this.setState({size_end: event.target.value})})}>
                            {
                                this.state.sizes.map(
                                    el =>
                                        <option>{el}</option>
                                )

                            }

                        </select>
                    </td>
                    <td><br/>
                        <div className={SearchComponentStyle.button}><button type="submit" className="btn btn-primary"
                       onClick={this.handleSearch} >Пребарувај</button></div></td>

                </tr>
                </tbody>
            </table>
                {this.state.data.length>0 && this.state.data.map(
                    (element) =>
                        <div key={element.id}>
                            <ResultAd isAuth={this.props.isAuth} description={element.description}
                                    email={element.email}
                                    name={element.surname}
                                    surname={element.surname}
                                    phone_number={element.phone_number}
                                    price={element.price}
                                    size={element.apartment.size}
                                    additional_information={element.apartment.additional_information}
                                    number_of_bathrooms={element.apartment.number_of_bathrooms}
                                    number_of_bedrooms={element.apartment.number_of_bedrooms}
                                    parking_spot={element.apartment.parking_spot}
                                    municipality={element.apartment.municipality}
                                    clicked={element.id}
                                    bookmark="0"/>

                        </div>
                )}
            </div>
        );
    }
}
export default SearchComponent;