import React, { useState } from 'react';
import axios from 'axios';
import '../styles/TransferMoneyModal.css';
import { useSelector } from 'react-redux';

function TransferMoneyModal({ isOpen, onClose, onTransfer }) {
    const uuid = useSelector((state) => state.sessionUUID)
    const [recipient, setRecipient] = useState('');
    const [amount, setAmount] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        setError('');

        axios
        .post('http://localhost:8081/api/transferMoney?sessionUUID=' + uuid, {
            receiverName: recipient, 
            amount: amount
        }, {
            withCredentials: true
        })
        .then(response => {
            if (response.data.success) {
                onTransfer();
                onClose();
            } else {
                setError('Transaction failed. Please try again.');
            }
        })
        .catch(err => {
            setError('An error occurred. Please check your balance and try again.');
        });
    };

    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Transfer Money</h2>
                {error && <div className="error-message">{error}</div>}
                <form onSubmit={handleSubmit}>
                    <input 
                        type="text" 
                        placeholder="Recipient Username" 
                        value={recipient} 
                        onChange={(e) => setRecipient(e.target.value)} 
                        required 
                    />
                    <input 
                        type="number" 
                        placeholder="Amount" 
                        value={amount} 
                        onChange={(e) => setAmount(e.target.value)} 
                        required 
                    />
                    <div className="modal-buttons">
                        <button type="submit">Transfer</button>
                        <button type="button" onClick={onClose}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default TransferMoneyModal;
