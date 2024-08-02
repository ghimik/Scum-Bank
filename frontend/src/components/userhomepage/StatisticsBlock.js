import React, { useCallback, useEffect, useState } from 'react';
import '../../styles/StatisticsBlock.css';
import axios from 'axios';
import { useSelector } from 'react-redux';

function StatisticsBlock() {
    const uuid = useSelector((state) => state.sessionUUID)
    const [stats, setStats] = useState({
        income: null,
        outcome: null,
        count: null
    });

    const fetchTransactions = useCallback(() => {
        axios.get('http://localhost:8081/api/stats', {
            params: { sessionUUID: uuid },
            withCredentials: true
        })
        .then(response => {
            setStats(response.data);
        })
        .catch(error => {
            console.error('Error fetching transactions:', error);
        });
    }, [uuid]);

    useEffect(() => {
        fetchTransactions();
    }, [fetchTransactions])


    return (
        <section className="statistics-block">
            <h3>Monthly Statistics</h3>
            <p>income: {stats.income}</p>
            <p>outcome: {stats.outcome}</p>
            <p>total count: {stats.count}</p>

        </section>
    );
}

export default StatisticsBlock;
