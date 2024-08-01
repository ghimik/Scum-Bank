import React from 'react';
import '../../styles/TransactionModal.css';

function TransactionModal({ transactions, onClose }) {
    return (
        <div className="transaction-modal">
            <div className="modal-content">
                <h3>All Transactions</h3>
                <ul className="transaction-list">
                    {transactions.map((transaction, index) => (
                        <li key={index}>
                            <span>to {transaction.receiverName}</span>
                            <span>from {transaction.senderName}</span>
                            <span>{new Date(transaction.date).toLocaleString()}</span>
                            <span>{transaction.amount.toFixed(2)} Тугриков</span>
                        </li>
                    ))}
                </ul>
                <button onClick={onClose} className="close-button">Close</button>
            </div>
        </div>
    );
}

export default TransactionModal;
