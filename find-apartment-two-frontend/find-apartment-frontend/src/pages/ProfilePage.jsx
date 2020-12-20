import React from 'react'
import NewAdd from "../components/profileComponent/NewAdd";
import UserAds from "../components/profileComponent/UserAds";
import NavProfile from "../components/NavBar/NavProfile";

class ProfilePage extends React.Component {
    render() {
        return(
            <div>
                <NavProfile/>

          <UserAds/>
            </div>
        );
    }
}
export default ProfilePage;