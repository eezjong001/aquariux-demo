import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CryptoTrader() {
  const [selectedCrypto, setSelectedCrypto] = useState(null);
  const [cryptoData, setCryptoData] = useState([]);
  const [buyQuantity, setBuyQuantity] = useState(0); // Initialize with 0
  const [sellQuantity, setSellQuantity] = useState(0); // Initialize with 0

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
    if (selectedCrypto && buyQuantity > 0) {
      const buyTransaction = {
        symbol: selectedCrypto.symbol,
        price: selectedCrypto.bidPrice,
        quantity: buyQuantity,
        userTransactionId: 1,
        timestamp: new Date().toISOString(),
      };

      axios
        .post('http://localhost:8080/api/transactions/buy', buyTransaction)
        .then((response) => {
          alert('Buy transaction created successfully');
        })
        .catch((error) => {
          console.error('Error creating buy transaction:', error);
        });
    } else {
      alert('Please select a cryptocurrency and enter a valid quantity to buy.');
    }
  };

  const handleSell = () => {
    if (selectedCrypto && sellQuantity > 0) {
      const sellTransaction = {
        symbol: selectedCrypto.symbol,
        price: selectedCrypto.askPrice,
        quantity: sellQuantity,
        userTransactionId: 1,
        timestamp: new Date().toISOString(),
      };

      axios
        .post('http://localhost:8080/api/transactions/sell', sellTransaction)
        .then((response) => {
          alert('Sell transaction created successfully');
        })
        .catch((error) => {
          console.error('Error creating sell transaction:', error);
        });
    } else {
      alert('Please select a cryptocurrency and enter a valid quantity to sell.');
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
        <input
          type="number"
          placeholder="Quantity to Buy"
          value={buyQuantity}
          onChange={(e) => setBuyQuantity(parseInt(e.target.value, 10))}
        />
        <button onClick={handleBuy}>Buy</button>
        <input
          type="number"
          placeholder="Quantity to Sell"
          value={sellQuantity}
          onChange={(e) => setSellQuantity(parseInt(e.target.value, 10))}
        />
        <button onClick={handleSell}>Sell</button>
      </div>
    </div>
  );
}

export default CryptoTrader;