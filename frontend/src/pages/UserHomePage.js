import React from 'react';
import { useSelector } from "react-redux";

import Header from '../components/Header';
import Footer from '../components/Footer';
import UserSidebar from '../components/userhomepage/UserSidebar';
import MainContent from '../components/userhomepage/MainContent';


import '../styles/UserHomePage.css';
import UnauthorizedPage from './UnauthorizedPage';

function UserHomePage() {
    const uuid = useSelector((state) => state.sessionUUID);


    if (!uuid) {
        return <UnauthorizedPage />;
    }
    return (
        <div className="user-home-page">
            <Header/>
            <div className="user-home-content">
                <UserSidebar />
                <MainContent />
            </div>
            <Footer />
        </div>
    );
}

export default UserHomePage;
