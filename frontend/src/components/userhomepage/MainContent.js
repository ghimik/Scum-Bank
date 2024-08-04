import React from 'react';
import FriendsBlock from './FriendsBlock';
import TransactionsBlock from './TransactionsBlock';
import StatisticsBlock from './StatisticsBlock';
import '../../styles/MainContent.css';

function MainContent({ friendsList }) {
    const handledFriendsList = friendsList.map(element => {
        console.log('Avatar data type:', typeof element.avatar);
        console.log('Avatar data:', element.avatar);

        let base64String = element.avatar;
        if (!base64String) {
            return {
                username: element.username,
                avatar: null
            }
        }

        if (!base64String.startsWith('data:image')) {
            const mimeType = 'image/jpeg'; 
            base64String = `data:${mimeType};base64,${base64String}`;
        }

        return {
            username: element.username,
            avatar: base64String
        };
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
