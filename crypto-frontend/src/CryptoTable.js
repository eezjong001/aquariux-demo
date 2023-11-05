import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CryptoTable() {
  const [cryptoData, setCryptoData] = useState([]);

  useEffect(() => {

    axios.get('http://localhost:8080/tickers')
      .then((response) => {
        setCryptoData(response.data);
      })
      .catch((error) => {
        console.error('Error fetching crypto data:', error);
      });
  }, []);

    //const top20CryptoData = cryptoData.slice(0, 20);


return (
  <div className="container mt-4">
    <div className="table-responsive" style={{ maxHeight: '400px', overflowY: 'scroll' }}>
      <h3 className="mb-3">Crypto Data Table</h3>
      <table className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Bid Price</th>
            <th>Bid Qty</th>
            <th>Ask Price</th>
            <th>Ask Qty</th>
          </tr>
        </thead>
        <tbody>
          {cryptoData.map((crypto) => (
            <tr key={crypto.id}>
              <td>{crypto.symbol}</td>
              <td>{crypto.bidPrice}</td>
              <td>{crypto.bidQty}</td>
              <td>{crypto.askPrice}</td>
              <td>{crypto.askQty}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  </div>
);
}

export default CryptoTable;