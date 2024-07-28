import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";
import TitlePage from "./pages/TitlePage";
import RegistrationPage from "./pages/RegistrationPage";
import NotFountPage from "./pages/NotFountPage";


function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<TitlePage />} />
                <Route path="/login" element={<LoginPage/>} />
                <Route path="/register" element={<RegistrationPage/>} />
                <Route path="/home" element={<HomePage />} />
                <Route path="*" element={<NotFountPage />} />
            </Routes>
        </BrowserRouter>
    )
}

export default App