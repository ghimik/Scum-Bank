import axios from "axios";
import React, { useState } from "react";
import {  useNavigate } from "react-router-dom";

function RegistrationPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    
    const handleNameInput = (e) => {
        setUsername(e.target.value)
    }

    const handlePasswordInput = (e) => {
        setPassword(e.target.value)
    }

    const handleRegistration = () => {
        axios
        .post("http://localhost:8081/api/register", {
            "username": username,
            "password": password
        })
        .then(response => {
            navigate('/home');
        })
        .catch(error => {
            alert("error" + error)
            
        })
    }
    return (
    <div>
        <h1>name: </h1><input value={username} onChange={handleNameInput} type="text" />
        <h1>password: </h1><input value={password} onChange={handlePasswordInput} type="password" />
        <button onClick={handleRegistration}>register</button>
    </div>
    )
}

export default RegistrationPage;