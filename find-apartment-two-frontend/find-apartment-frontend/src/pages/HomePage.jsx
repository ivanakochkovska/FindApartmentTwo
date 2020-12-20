import React from 'react'
import NavComponent from "../components/NavBar/NavComponent";
import ProfilePage from "./ProfilePage";
import SearchComponent from "../components/searchComponent/SearchComponent";

class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: []
        }

    }
    render() {
        return(
          <div>
              <SearchComponent/>

          </div>
        );
    }

}
export default HomePage;