import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CryptoBestPrice({ symbol }) {
  const [bestCryptoData, setBestCryptoData] = useState([]);

useEffect(() => {

  axios.post('http://localhost:8080/tickers/fetch-bestprices', null, {
    params: {
      symbol: symbol,
    },
  })
    .then((response) => {
      const bestPrice = response.data;
      setBestCryptoData(bestPrice);

    })
    .catch((error) => {
      console.error('Error fetching crypto data:', error);
    });
}, [symbol]);



  return (
    <div className="container mt-4">
      <div className="table-responsive">
            <h3 className="mb-3">Best Price</h3>
        <table className="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Symbol</th>
              <th>Best Price</th>
            </tr>
          </thead>
          <tbody>
            <td>{symbol}</td>
            <td>{bestCryptoData}</td>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default CryptoBestPrice;