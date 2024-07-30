import React from 'react';
import FriendsBlock from './FriendsBlock';
import TransactionsBlock from './TransactionsBlock';
import StatisticsBlock from './StatisticsBlock';
import '../../styles/MainContent.css';

function MainContent() {
    return (
        <main className="main-content">
            <FriendsBlock />
            <TransactionsBlock />
            <StatisticsBlock />
        </main>
    );
}

export default MainContent;
