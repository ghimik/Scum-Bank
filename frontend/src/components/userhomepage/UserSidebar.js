import React from 'react';
import '../../styles/UserSidebar.css';
import LogoutButton from '../LogoutButton';


function UserSidebar(props) {
    
    return (
        <aside className="user-sidebar">
            <div className="user-info">
                <img src="/path/to/avatar.jpg" alt="User Avatar" className="user-avatar" />
                <h2>{props.username}</h2>
                <p>Balance: {props.balance} тугриков</p>
                <button>Transfer Money</button>
                <button>Add Friend</button>
                <LogoutButton/>
            </div>
            
        </aside>
    );
}

export default UserSidebar;
