import React, { useState, useEffect } from 'react';
import axios from 'axios';
import TransactionModal from './TransactionModal'; 
import '../../styles/TransactionsBlock.css';
import { useSelector } from 'react-redux';

function TransactionsBlock({ refreshTrigger }) {
    const uuid = useSelector((state) => state.sessionUUID);

    const [transactions, setTransactions] = useState([]);
    const [showAll, setShowAll] = useState(false);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        fetchTransactions();
    }, [uuid, refreshTrigger]);

    const fetchTransactions = () => {
        axios.get('http://localhost:8081/api/transactions', {
            params: { sessionUUID: uuid },
            withCredentials: true
        })
        .then(response => {
            setTransactions(response.data);
        })
        .catch(error => {
            console.error('Error fetching transactions:', error);
        });
    };

    const handleShowAll = () => {
        setShowAll(true);
        setShowModal(true);
    };

    return (
        <div className="transactions-block">
            <h3>Recent Transactions</h3>
            <ul className="transaction-list">
                {transactions.slice(0, 5).map((transaction, index) => (
                    <li key={index}>
                        <span>to {transaction.receiverName}</span>
                        <span>from {transaction.senderName}</span>
                        <span>{new Date(transaction.date).toLocaleString()}</span>
                        <span>{transaction.amount.toFixed(2)} Тугриков</span>
                    </li>
                ))}
            </ul>
            {transactions.length > 5 && (
                <button onClick={handleShowAll} className="show-all-button">Show All</button>
            )}

            {showModal && (
                <TransactionModal transactions={transactions} onClose={() => setShowModal(false)} />
            )}
        </div>
    );
}

export default TransactionsBlock;
