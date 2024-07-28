import axios from "axios";
import React, { useState } from "react";
import { useDispatch } from "react-redux";
import {  useNavigate } from "react-router-dom";


import { setUsername } from '../store/actions/setUsername';
import { setSessionUUID } from '../store/actions/setSessionUUID';

function LoginPage(props) {
    const [username, setLocalUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const dispatch = useDispatch();
    
    const handleNameInput = (e) => {
        setLocalUsername(e.target.value);
    }

    const handlePasswordInput = (e) => {
        setPassword(e.target.value)
    }

    const handleVerification = () => {
        axios
        .post("http://localhost:8081/api/auth", {
            "username": username,
            "password": password
        })
        .then(response => {
            if (response.data.authorized === 'true') {
                console.log(response.data);
                dispatch(setSessionUUID(response.data.sessiondata.sessionUUID));
                dispatch(setUsername(response.data.sessiondata.username));
                navigate('/home');
            }
        })
        .catch(error => console.log(error))
    }
    return (
    <div>
        <h1>name: </h1><input value={username} onChange={handleNameInput} type="text" />
        <h1>password: </h1><input value={password} onChange={handlePasswordInput} type="password" />
        <button onClick={handleVerification}>verificate</button>
    </div>
    )
}

export default LoginPage