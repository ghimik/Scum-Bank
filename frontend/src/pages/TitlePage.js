import React from "react";
import { useNavigate } from "react-router-dom";

function TitlePage() {
    const navigate = useNavigate();

    return (
        <div>
            <button onClick={() => navigate('register', {replace: true})}>Sign up</button>
            <button onClick={() => navigate('login', {replace: true})}>Log in</button>
        </div>
    )
}

export default TitlePage;