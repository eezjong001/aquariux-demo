import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';
import ApiButton from './ApiButton';
import LoginForm from './LoginForm';
import CryptoTable from './CryptoTable';
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // For Testing not defined
  const [accountName, setAccountName] = useState("demo");
  return (
    <div className="App">
      {isLoggedIn ? (

        <div>
          <h2>Evan's Crypto Trading System</h2>
            <p>Welcome, {accountName}!</p>
             <CryptoTable />
        </div>
      ) : (

        <LoginForm onLogin={() => setIsLoggedIn(true) } />
      )}
    </div>
  );
}

export default App;
