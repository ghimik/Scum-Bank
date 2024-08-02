import React, { useEffect, useState } from 'react';
import '../../styles/UserSidebar.css';
import LogoutButton from '../LogoutButton';
import TransferMoneyModal from '../TransferMoneyModal';
import { useDispatch, useSelector } from 'react-redux';
import {setUsername } from "../../store/actions/setUsername"
import {setBalance} from "../../store/actions/setBalance"
import axios from 'axios';
import AddFriendModal from '../AddFriendModal';
import CastMoneyButton from './CastMoneyButton';
import { setAvatar } from '../../store/actions/setAvatar';
import UserAvatarUploadModal from '../UserAvatarUploadModal';

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


function UserSidebar({onFriendAdded}) {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isAvatarUploadModalOpen, setIsAvatarUploadModalOpen] = useState(false);
    const [isAddFriendModalOpen, setAddFriendModalOpen] = useState(false);

    const openAddFriendModal = () => setAddFriendModalOpen(true);
    const closeAddFriendModal = () => setAddFriendModalOpen(false);

    const balance = useSelector((state) => state.balance);
    const uuid = useSelector((state) => state.sessionUUID);

    const dispatch = useDispatch();

    useEffect(() => {
        if (uuid) {  
            const url = `http://localhost:8081/api`;
            axios.get(url + '/generalinfo', {
                params: {sessionUUID: uuid},
                withCredentials: true
            })
            .then(response => {
                dispatch(setUsername(response.data.username));
                dispatch(setBalance(response.data.balance));
            })
            .catch(error => console.log(error));

            axios.get(url + '/avatar', {
                params: {sessionUUID: uuid}, 
                responseType: 'arraybuffer',
                withCredentials: true
            })
            .then(response => {
                const base64String = btoa(
                    new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), '')
                    );
                const src = `data:${response.headers['content-type'].toLowerCase()};base64,${base64String}`;
                dispatch(setAvatar(src));
            })
            .catch(error => console.error(error));

        }
    }, [balance, uuid, dispatch]);

    const handleAvatarClick = () => {
        setIsAvatarUploadModalOpen(true);
    };

    const handleModalClose = () => {
        setIsAvatarUploadModalOpen(false);
    };


    const userName = useSelector((state) => state.username); 
    const userBalance = useSelector((state) => state.balance);
    const avatar = useSelector((state) => state.avatar)
    
    return (
        <aside className="user-sidebar">
            <div className="user-info">
                <div className="avatar-container" onClick={handleAvatarClick}>
                    <img src={avatar} alt="User Avatar" className="user-avatar" />
                </div>
                <h2>{userName}</h2>
                <p>Balance: {userBalance} тугриков</p>
                <button onClick={() => setIsModalOpen(!isModalOpen)}>Transfer Money</button>
                <button onClick={openAddFriendModal}>Add Friend</button>
                <TransferMoneyModal 
                    isOpen={isModalOpen} 
                    onClose={() => setIsModalOpen(false)}
                    onTransfer={() => refreshBalance(uuid, dispatch)}
                />
                <UserAvatarUploadModal 
                    isOpen={isAvatarUploadModalOpen} 
                    onClose={handleModalClose} 
                    userId={uuid} 
                />
            </div>
            <AddFriendModal onFriendAdded={onFriendAdded} isOpen={isAddFriendModalOpen} onClose={closeAddFriendModal} />
            <CastMoneyButton />

            <LogoutButton/>
            
        </aside>
    );
}

export default UserSidebar;
