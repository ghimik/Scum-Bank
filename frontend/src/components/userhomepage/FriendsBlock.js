import React from 'react';
import '../../styles/FriendsBlock.css';

function FriendsBlock({ friendsList }) {
    return (
        <section className="friends-block">
            <h3>Friends</h3>
            {friendsList.length === 0 ? (
                <p>No friends found.</p>
            ) : (
                <div className="friends-list">
                    {friendsList.map((friend, index) => (
                        <div key={index} className="friend-card">
                            <img src={friend.avatar} alt="Avatar" className="friend-avatar" />
                            <div className="friend-info">
                                <h4>{friend.username}</h4>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </section>
    );
}

export default FriendsBlock;
