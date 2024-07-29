import React, { useState } from 'react';
import FormInput from '../FormInput';
import '../../styles/RegistrationForm.css'; 

function RegistrationForm() {
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
            <button type="submit">Register</button>
        </form>
    );
}

export default RegistrationForm;
