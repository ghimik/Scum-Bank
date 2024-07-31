import React, { useState } from 'react';
import axios from 'axios';
import '../styles/AddFriendModal.css';
import { useSelector } from 'react-redux';

const AddFriendModal = ({ isOpen, onClose, onFriendAdded }) => {
    const uuid = useSelector((state) => state.sessionUUID);
    const [friendName, setFriendName] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleAddFriend = () => {
        axios.post('http://localhost:8081/api/befriend?sessionUUID='+uuid, { friendName: friendName }, {
            withCredentials: true
        })
            .then(() => {
                setTimeout(() => setErrorMessage('Т ы д о б а в и л д р у г а . П у т и н а з а д н е т .'), 2000)
                onClose();
                onFriendAdded();
                setErrorMessage('');
            })
            .catch(error => {
                setErrorMessage(error.response?.data?.message || 'An error occurred. Please try again.');
            });
    };

    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Add Friend</h2>
                <input
                    type="text"
                    placeholder="Friend's Name"
                    value={friendName}
                    onChange={(e) => setFriendName(e.target.value)}
                    className="modal-input"
                />
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                <button onClick={handleAddFriend} className="modal-button">Add</button>
                <button onClick={onClose} className="modal-close-button">Close</button>
            </div>
        </div>
    );
};

export default AddFriendModal;
