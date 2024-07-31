import React, { useEffect, useState } from 'react';
import '../../styles/UserSidebar.css';
import LogoutButton from '../LogoutButton';
import TransferMoneyModal from '../TransferMoneyModal';
import { useDispatch, useSelector } from 'react-redux';
import {setUsername } from "../../store/actions/setUsername"
import {setBalance} from "../../store/actions/setBalance"
import axios from 'axios';

function refreshBalance(uuid, dispatch) {
    const url = `http://localhost:8081/api/balance`;
    axios.get(url, {
        params: {sessionUUID: uuid},
        withCredentials: true
    })
    .then(response => {
        dispatch(setBalance(response.data.balance));
    })
    .catch(error => console.log(error));
}


function UserSidebar(props) {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const balance = useSelector((state) => state.balance);
    const uuid = useSelector((state) => state.sessionUUID);

    const dispatch = useDispatch();

    useEffect(() => {
        if (uuid) {  
            const url = `http://localhost:8081/api/generalinfo`;
            axios.get(url, {
                params: {sessionUUID: uuid},
                withCredentials: true
            })
            .then(response => {
                dispatch(setUsername(response.data.username));
                dispatch(setBalance(response.data.balance));
            })
            .catch(error => console.log(error));
        }
    }, [balance, uuid, dispatch]);

    const userName = useSelector((state) => state.username); 
    const userBalance = useSelector((state) => state.balance);
    
    return (
        <aside className="user-sidebar">
            <div className="user-info">
                <img src="/path/to/avatar.jpg" alt="User Avatar" className="user-avatar" />
                <h2>{userName}</h2>
                <p>Balance: {userBalance} тугриков</p>
                <button onClick={() => setIsModalOpen(!isModalOpen)}>Transfer Money</button>
                <button>Add Friend</button>
                <TransferMoneyModal 
                    isOpen={isModalOpen} 
                    onClose={() => setIsModalOpen(false)}
                    onTransfer={() => refreshBalance(uuid, dispatch)}
                />
            </div>
            <LogoutButton/>
            
        </aside>
    );
}

export default UserSidebar;
