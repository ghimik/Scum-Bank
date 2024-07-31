import React from 'react';
import FriendsBlock from './FriendsBlock';
import TransactionsBlock from './TransactionsBlock';
import StatisticsBlock from './StatisticsBlock';
import '../../styles/MainContent.css';

function MainContent({friendsList}) {
    return (
        <main className="main-content">
            <FriendsBlock friendsList={friendsList} />
            <TransactionsBlock />
            <StatisticsBlock />
        </main>
    );
}

export default MainContent;
