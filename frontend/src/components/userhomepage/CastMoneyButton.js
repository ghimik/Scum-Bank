import React, { useState } from 'react';
import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import '../../styles/CastMoneyButton.css';
import { setBalance } from '../../store/actions/setBalance';

function CastMoneyButton() {
    const balance = useSelector((state) => state.balance)
    const dispatch = useDispatch();
    const [isModalOpen, setModalOpen] = useState(false);
    const [amount, setAmount] = useState('');
    const uuid = useSelector((state) => state.sessionUUID);

    const handleCastMoney = () => {
        if (amount && !isNaN(amount)) {
            axios.post('http://localhost:8081/api/castMoney', null, {
                params: { sessionUUID: uuid, amount },
                withCredentials: true
            })
            .then(response => {
                alert("Money cast successfully!");
                dispatch(setBalance(balance + amount))
                setModalOpen(false);
                setAmount('');
            })
            .catch(error => {
                if (error.response.status === 403) {
                    alert("You are not sigma, sorry.");
                } else {
                    alert("An error occurred: " + error.message);
                }
            });
        } else {
            alert("Please enter a valid amount.");
        }
    };

    return (
        <div className="cast-money-button">
            <button onClick={() => setModalOpen(true)}>Cast $$$ from nothing</button>
            
            {isModalOpen && (
                <div className="modal">
                    <div className="modal-content">
                        <span className="close-button" onClick={() => setModalOpen(false)}>&times;</span>
                        <h2>Cast Money</h2>
                        <input
                            type="text"
                            placeholder="Enter amount"
                            value={amount}
                            onChange={(e) => setAmount(e.target.value)}
                        />
                        <button onClick={handleCastMoney}>Submit</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default CastMoneyButton;
