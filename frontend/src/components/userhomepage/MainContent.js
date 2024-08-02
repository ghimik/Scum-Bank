import React from 'react';
import FriendsBlock from './FriendsBlock';
import TransactionsBlock from './TransactionsBlock';
import StatisticsBlock from './StatisticsBlock';
import '../../styles/MainContent.css';

function MainContent({friendsList}) {
    let handledFriendsList = [];
    friendsList.forEach(element => {
        console.log(element.avatar)
        const base64String = btoa(
            new Uint8Array(element.avatar).reduce((data, byte) => data + String.fromCharCode(byte), '')
            );
        console.log(base64String);
        handledFriendsList.push({username: element.username, avatar: 'data:application/json;base64,' + base64String});
    });
    return (
        <main className="main-content">
            <FriendsBlock friendsList={handledFriendsList} />
            <TransactionsBlock />
            <StatisticsBlock />
        </main>
    );
}

export default MainContent;
