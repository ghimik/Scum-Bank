import React from "react";
import { useNavigate } from "react-router-dom";
import Header from '../components/Header';
import Footer from '../components/Footer';
import NewsSlide from '../components/titlepage/NewsSlide';
import '../styles/TitlePage.css';

function TitlePage() {
    const navigate = useNavigate();

    return (
        <div className="title-page-container">
            <Header visibleButton={false} />

            <main className="title-page-main">
                <div className="button-container">
                    <button onClick={() => navigate('register')}>Sign up</button>
                    <button onClick={() => navigate('login')}>Log in</button>
                </div>
                <div className="news-slides">
                    <NewsSlide title="Scum News #1" content="There are rumors that this section will be written someday" />
                </div>
            </main>

            <Footer />
        </div>
    )
}

export default TitlePage;
