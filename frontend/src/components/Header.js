import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Header.css"

function Header({visibleButton = true}) {
    const navigate = useNavigate();
    
    return (
        <header className="title-page-header">
            <h1>Scum-Bank</h1>
            {visibleButton && (
                <button 
                    className="header-button"
                    onClick={() => navigate('/')}
                    type="button"
                >
                    Go to main
                </button>
            )}
        </header>
    );
}

export default Header;
