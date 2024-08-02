import React, { useState } from 'react';
import axios from 'axios';
import '../styles/UserAvatarUploadModal.css';
import {  useSelector } from 'react-redux';

function UserAvatarUploadModal({ isOpen, onClose }) {
    const uuid = useSelector((state) => state.sessionUUID);
    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileChange = (e) => {
        setSelectedFile(e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedFile) return;

        const formData = new FormData();
        formData.append('blob', selectedFile);

        await 
        axios.post(`http://localhost:8081/api/avatar?sessionUUID=` + uuid, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            withCredentials: true
        })
        .then(alert('sucess!'))
        .catch((error) => console.error(error))
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="modal">
            <div className="modal-content">
                <span className="close" onClick={onClose}>&times;</span>
                <h2>Upload Avatar</h2>
                <form onSubmit={handleSubmit}>
                    <input type="file" onChange={handleFileChange} />
                    <button type="submit">Upload</button>
                </form>
            </div>
        </div>
    );
}

export default UserAvatarUploadModal;
