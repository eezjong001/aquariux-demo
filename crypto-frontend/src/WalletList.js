import React, { useState, useEffect } from 'react';
import axios from 'axios';

function WalletList({ userId }) {
  const [wallets, setWallets] = useState([]);

  useEffect(() => {
  axios.get(`http://localhost:8080/api/wallets/balance`, {
    params: {
      id: userId,
    },
  })
    .then((response) => {
      setWallets(response.data);
    })
    .catch((error) => {
      console.error('Error fetching wallets:', error);
    });
}, [userId]);

  return (
    <div className="container mt-4">
      <h3 className="mb-3">Wallet List</h3>
      <table className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Currency</th>
            <th>Balance</th>
          </tr>
        </thead>
        <tbody>
          {wallets.map((wallet) => (
            <tr key={wallet.id}>
              <td>{wallet.currency}</td>
              <td>{wallet.balance}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default WalletList;