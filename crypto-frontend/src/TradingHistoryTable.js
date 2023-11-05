import React, { useEffect, useState } from 'react';
import axios from 'axios';

function TradingHistoryTable({ userTransactionId }) {
  const [history, setHistory] = useState([]);

const fetchTradingHistory = () => {
  axios
    .get(`http://localhost:8080/api/transactions/history`, {
      params: { userTransactionId: userTransactionId },
    })
    .then((response) => {
      setHistory(response.data);
    })
    .catch((error) => {
      console.error('Error fetching trading history:', error);
    });
}


useEffect(() => {

  fetchTradingHistory();

  const intervalId = setInterval(fetchTradingHistory, 2000);

  return () => {
     clearInterval(intervalId);
  };

}, [userTransactionId]);

  return (
    <div className="table-responsive">
      <h3 className="mb-3">Trading History</h3>
      <table className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Transaction Type</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Timestamp</th>
          </tr>
        </thead>
        <tbody>
          {history.map((history) => (
            <tr key={history.id}>
              <td>{history.symbol}</td>
              <td>{history.transactionType}</td>
              <td>{history.price}</td>
              <td>{history.quantity}</td>
              <td>{history.timestamp}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TradingHistoryTable;