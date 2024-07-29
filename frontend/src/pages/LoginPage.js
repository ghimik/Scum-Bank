import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { setSessionUUID } from '../store/actions/setSessionUUID';
import Header from '../components/Header';
import Footer from '../components/Footer';
import FormInput from '../components/FormInput';
import '../styles/LoginPage.css';

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const dispatch = useDispatch();
    
    const handleVerification = () => {
        axios.post('http://localhost:8081/api/auth', {
            username,
            password,
        }, {
            withCredentials: true,
        })
        .then(response => {
            if (response.data.authorized === 'true') {
                dispatch(setSessionUUID(response.data.sessionUUID));
                navigate('/home');
            }
        })
        .catch(error => console.error(error));
    };

    return (
        <div className="login-page-container">
            <Header />
            <main className="login-page-main">
                <h2>Login</h2>
                <FormInput 
                    label="Username" 
                    type="text" 
                    value={username} 
                    onChange={(e) => setUsername(e.target.value)} 
                />
                <FormInput 
                    label="Password" 
                    type="password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                />
                <button className="login-button" onClick={handleVerification}>Login</button>
            </main>
            <Footer />
        </div>
    );
}

export default LoginPage;
