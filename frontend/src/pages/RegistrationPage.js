import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import RegistrationForm from '../components/registerpage/RegistrationForm';
import '../styles/RegisterPage.css'; 

function RegisterPage() {
    return (
        <div className="register-page-container">
            <Header />
            <main className="register-page-main">
                <h2>Create an Account</h2>
                <RegistrationForm />
            </main>
            <Footer />
        </div>
    );
}

export default RegisterPage;
