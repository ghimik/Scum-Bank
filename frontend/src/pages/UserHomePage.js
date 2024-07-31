import React, { useCallback, useEffect, useState } from 'react';
import { useSelector } from "react-redux";

import Header from '../components/Header';
import Footer from '../components/Footer';
import UserSidebar from '../components/userhomepage/UserSidebar';
import MainContent from '../components/userhomepage/MainContent';


import '../styles/UserHomePage.css';
import UnauthorizedPage from './UnauthorizedPage';
import axios from 'axios';

function UserHomePage() {
    const uuid = useSelector((state) => state.sessionUUID);
    const [friendsList, setFriendsList] = useState([]);

    const fetchFriends = useCallback(() => {
        axios.get('http://localhost:8081/api/friends', {
            params: { sessionUUID: uuid },
            withCredentials: true
        })
        .then(response => {
            setFriendsList(response.data);
        })
        .catch(error => {
            console.error('Error fetching friends:', error);
        });
    }, [uuid]);


    useEffect(() => {
        fetchFriends();
    }, [uuid, fetchFriends]);


    if (!uuid) {
        return <UnauthorizedPage />;
    }
    return (
        <div className="user-home-page">
            <Header/>
            <div className="user-home-content">
                <UserSidebar onFriendAdded={fetchFriends}/>
                <MainContent friendsList={friendsList}/>
            </div>
            <Footer />
        </div>
    );
}

export default UserHomePage;
