import axios from "axios";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setBalance } from "../store/actions/setBalance";
import { setUsername} from "../store/actions/setUsername";

function HomePage() {
    const uuid = useSelector((state) => state.sessionUUID);
    const dispatch = useDispatch();
    const url = "http://localhost:8081/api/generalinfo?UUID="+uuid;
    console.log("url: " + url);
    useEffect(() => {
        if (uuid) {  
            const url = `http://localhost:8081/api/generalinfo`;
            axios.get(url, {
                params: {UUID: uuid},
                withCredentials: true
            })
                 .then(response => {
                     dispatch(setUsername(response.data.username));
                     dispatch(setBalance(response.data.balance));
                 })
                 .catch(error => console.log(error));
        }
    }, [uuid, dispatch]);

    const username = useSelector((state) => state.username); 
    return <p>text: {username}</p>
}

export default HomePage;