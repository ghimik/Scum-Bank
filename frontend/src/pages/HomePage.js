import React from "react";
import { useSelector } from "react-redux";

function HomePage() {
    const  username = useSelector((state) => state.username);
    return <p>text: {username}</p>
}

export default HomePage;