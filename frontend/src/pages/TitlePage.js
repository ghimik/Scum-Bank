import React from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import Header from '../components/Header';
import Footer from '../components/Footer';
import '../styles/TitlePage.css';
import LogoutButton from "../components/LogoutButton";
import NewsTape from "../components/titlepage/NewsTape";

function TitlePage() {
    const navigate = useNavigate();
    const authorized = useSelector((state) => state.sessionUUID);
    const username = useSelector((state) => state.username);
    const avatar = useSelector((state) => state.avatar)

    return (
        <div className="title-page-container">
            <Header visibleButton={false} />
            <div className="content-container">
                <aside className="sidebar">
                    {authorized ? (
                        <div className="profile-sidebar">
                            <img className="profile-avatar" src={avatar} alt="User Avatar"/>
                            <div className="profile-info">
                                <h3>{username}</h3>
                                <button className="profile-button" onClick={() => navigate('/home')}>Go to Home</button>
                                <LogoutButton/>
                            </div>
                        </div>
                    ) : (
                        <div className="auth-buttons">
                            <button onClick={() => navigate('register')}>Sign up</button>
                            <button onClick={() => navigate('login')}>Log in</button>
                        </div>
                    )}
                </aside>
                <main className="main-content">
                    <NewsTape />
                </main>
            </div>
            <Footer />
        </div>
    )
}

export default TitlePage;
