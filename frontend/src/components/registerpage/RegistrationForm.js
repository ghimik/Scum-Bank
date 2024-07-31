import React, { useState } from 'react';
import FormInput from '../FormInput';
import '../../styles/RegistrationForm.css'; 
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { setSessionUUID } from '../../store/actions/setSessionUUID';

function RegistrationForm() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    const requestData = {
        username: formData.username || null, 
        password: formData.password || null
    };

    const registrationHandler = () =>
        formData.password === formData.confirmPassword ? 
            axios
            .post('http://localhost:8081/api/register', requestData) 
            .then(
                axios.post('http://localhost:8081/api/auth', requestData, {
                    withCredentials: true
                })
                .then(response => {
                    if (response.data.authorized === 'true') {
                        dispatch(setSessionUUID(response.data.sessionUUID));
                        navigate('/home');
                    } else {
                        alert('Authorization failed: Incorrect username or password.');
                    }
                })
                .catch(error => {
                    console.error(error);
                    alert('An error occurred during login. Please try again later.');
                })
            )
            .catch(error => alert('unable to register: '+ error) )
        :
            alert('incorrect password confirmation')

    return (
        <form className="registration-form" onSubmit={handleSubmit}>
            <FormInput
                label="Username"
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                required
            />
            <FormInput
                label="Password"
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
            />
            <FormInput
                label="Confirm Password"
                type="password"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                required
            />
            <button onClick={registrationHandler}>Register</button>
        </form>
    );
}

export default RegistrationForm;
