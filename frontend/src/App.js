import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import TitlePage from "./pages/TitlePage";
import RegistrationPage from "./pages/RegistrationPage";
import NotFountPage from "./pages/NotFountPage";
import UserHomePage from "./pages/UserHomePage";


function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<TitlePage />} />
                <Route path="/login" element={<LoginPage/>} />
                <Route path="/register" element={<RegistrationPage/>} />
                <Route path="/home" element={<UserHomePage />} />
                <Route path="*" element={<NotFountPage />} />
            </Routes>
        </BrowserRouter>
    )
}

export default App