import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';
import ApiButton from './ApiButton';
import LoginForm from './LoginForm';
import CryptoTable from './CryptoTable';
import CryptoBestPrice from './CryptoBestPrice';
import WalletList from './WalletList';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // For Testing not defined
  const [accountName, setAccountName] = useState("demo");
  const [showTable, setShowTable] = useState(false);
  const [showTable2, setShowTable2] = useState(false);
  const [symbol, setSymbol] = useState('');
  const [userId, setUserId] = useState('1');

  const toggleTableVisibility = () => {
    setShowTable((prevShowTable) => !prevShowTable); // Toggle the value of showTable
  };

  const toggleTable2Visibility = () => {
    setShowTable2((prevShowTable2) => !prevShowTable2); // Toggle the value of showTable
  };

  const handleSymbolChange = (e) => {
    setSymbol(e.target.value);
  };
  return (
    <div className="App">
      {isLoggedIn ? (
        <div>
          <h2>Evan's Crypto Trading System</h2>
          <p>Welcome, {accountName}!</p>

          <div className="table-container">
            <div className="table-wrapper">
              <button onClick={toggleTableVisibility}>
                {showTable ? 'Hide Crypto Table' : 'Show Crypto Table'}
              </button>
              {showTable ? <CryptoTable /> : null}
            </div>

            <div className="table-wrapper">
              <input
                type="text"
                id="symbol"
                value={symbol}
                onChange={handleSymbolChange}
              />

              <button onClick={toggleTable2Visibility}>
                Toggle Best Price
              </button>
              {showTable2 ? <CryptoBestPrice symbol={symbol} /> : null}
            </div>
            <div className="table-wrapper">
               <WalletList userId={userId} />
            </div>
          </div>
        </div>
      ) : (
        <LoginForm onLogin={() => setIsLoggedIn(true)} />
      )}
    </div>
  );
}

export default App;
