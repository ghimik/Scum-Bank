import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import axios from 'axios';

import Header from '../components/Header';
import Footer from '../components/Footer';
import UserSidebar from '../components/userhomepage/UserSidebar';
import MainContent from '../components/userhomepage/MainContent';

import { setBalance } from "../store/actions/setBalance";
import { setUsername} from "../store/actions/setUsername";

import '../styles/UserHomePage.css';

function UserHomePage() {
    const uuid = useSelector((state) => state.sessionUUID);
    const dispatch = useDispatch();
    const url = "http://localhost:8081/api/generalinfo?UUID="+uuid;
    console.log("url: " + url);
    useEffect(() => {
        if (uuid) {  
            const url = `http://localhost:8081/api/generalinfo`;
            axios.get(url, {
                params: {UUID: uuid},
                withCredentials: true
            })
            .then(response => {
                     dispatch(setUsername(response.data.username));
                     dispatch(setBalance(response.data.balance));
            })
            .catch(error => console.log(error));
        }
    }, [uuid, dispatch]);

    const userName = useSelector((state) => state.username); 
    const userBalance = useSelector((state) => state.balance);

    console.log(userName + " " + userBalance)

    return (
        <div className="user-home-page">
            <Header/>
            <div className="user-home-content">
                <UserSidebar username={userName} balance={userBalance} />
                <MainContent />
            </div>
            <Footer />
        </div>
    );
}

export default UserHomePage;
