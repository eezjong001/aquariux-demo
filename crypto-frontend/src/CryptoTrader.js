import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CryptoTrader() {
  const [selectedCrypto, setSelectedCrypto] = useState(null);
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

  const handleBuy = () => {
    if (selectedCrypto) {

      alert(`Buy ${selectedCrypto.symbol}`);
    } else {
      alert('Please select a cryptocurrency to buy.');
    }
  };

  const handleSell = () => {
    if (selectedCrypto) {

      alert(`Sell ${selectedCrypto.symbol}`);
    } else {
      alert('Please select a cryptocurrency to sell.');
    }
  };

  return (
    <div className="crypto-trader">
      <h3>Crypto Trader</h3>
      <div className="crypto-list">
        <select
          onChange={(e) => {
            const symbol = e.target.value;
            setSelectedCrypto(cryptoData.find((crypto) => crypto.symbol === symbol));
          }}
        >
          <option value="">Select a cryptocurrency</option>
          {cryptoData.map((crypto) => (
            <option key={crypto.id} value={crypto.symbol}>
              {crypto.symbol}
            </option>
          ))}
        </select>
      </div>
      {selectedCrypto && (
        <div className="crypto-details">
          <p>Symbol: {selectedCrypto.symbol}</p>
          <p>Bid Price: {selectedCrypto.bidPrice}</p>
          <p>Ask Price: {selectedCrypto.askPrice}</p>
        </div>
      )}
      <div className="crypto-actions">
        <button onClick={handleBuy}>Buy</button>
        <button onClick={handleSell}>Sell</button>
      </div>
    </div>
  );
}

export default CryptoTrader;