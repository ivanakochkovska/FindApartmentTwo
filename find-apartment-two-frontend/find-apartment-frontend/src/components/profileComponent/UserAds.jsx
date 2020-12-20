import React from 'react'
import axios from 'axios'
import Button from 'react-bootstrap/Button'
import Collapse from 'react-bootstrap/Collapse'
import profileStyle from './ProfileComponent.module.css'
import UserAd from "./UserAd";
class UserAds extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            bookmarked: [],
        }
        this.getData = this.getData.bind(this);


    }

    getData() {
        axios.get("/user/ads").then(response => {
            this.setState({data: response.data});
            console.log(response.data)
        });
    }

    getBookmarked() {
        axios.get("/user/bookmarkedAds")
            .then(response=>{this.setState({bookmarked: response.data})});
    }
    componentDidMount() {
        this.getBookmarked()
        this.getData();
    }


    render() {
        return (
            <div className={profileStyle.baseDiv}>

                <table className="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Листа на Ваши објавени огласи</th>
                        <th scope="col">Листа на Ваши зачувани огласи</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                    <td className={profileStyle.columndWidth}>
                {this.state.data.length>0 && this.state.data.map(
                    (element) =>
                        <div key={element.id}>
                            <UserAd description={element.description}
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
                    </td>
                        <td className={profileStyle.columndWidth}>
                            {this.state.bookmarked.length>0 && this.state.bookmarked.map(
                                (element) =>
                                    <div key={element.id}>
                                        <UserAd description={element.description}
                                                email={element.email}
                                                surname={element.surname}
                                                phone_number={element.phone_number}
                                                price={element.price}
                                                size={element.size}
                                                additional_information={element.apartment.additional_information}
                                                number_of_bathrooms={element.apartment.number_of_bathrooms}
                                                number_of_bedrooms={element.apartment.number_of_bedrooms}
                                                parking_spot={element.apartment.parking_spot}
                                                municipality={element.apartment.municipality}
                                                clicked={element.id}
                                                bookmark="1"/>
                                    </div>
                            )}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }

}

export default UserAds;