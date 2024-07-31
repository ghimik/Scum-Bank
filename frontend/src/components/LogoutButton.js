// LogoutButton.js
import React from "react";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import '../styles/LogoutButton.css';
import { setEmpty } from "../store/actions/setEmpty";

function LogoutButton() {
    const navigate = useNavigate();
    const sessionUUID = useSelector((state) => state.sessionUUID);
    const dispatch = useDispatch();

    const logoutHandler = () => {
        axios.get(`http://localhost:8081/api/unauth?sessionUUID=${sessionUUID}`, { withCredentials: true })
            .then(() => {
                dispatch(setEmpty(""));
                navigate('/');
            })
            .catch((error) => alert("Something went wrong..." + error));
    };

    return (
        <div className="logout-button">
            <button onClick={logoutHandler}>Logout</button>
        </div>
    );
}

export default LogoutButton;
