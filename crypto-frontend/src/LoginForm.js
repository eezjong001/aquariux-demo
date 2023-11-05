import React, { useState } from 'react';
import axios from 'axios';

function LoginForm({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
   axios
      .get('http://localhost:8080/api/accounts/get-demo-credentials')
      .then((response) => {
        const { usernameBackend, passwordBackend } = response.data;
        console.log('Account Credentials:', usernameBackend, passwordBackend);

        //bypass
        onLogin(usernameBackend, passwordBackend);

        if (username === usernameBackend && password === passwordBackend) {
          //if  match, execute the onLogin function
          onLogin(usernameBackend, passwordBackend);
        } else {
          console.error('Username or password does not match.');
        }
      })
      .catch((error) => {
        console.error('Failed to fetch account credentials', error);
      });
  };

  const handleRegisterDemo = () => {
    // API call to register a demo account
    axios
    .post('http://localhost:8080/api/accounts/create-demo', null, {
      params: {
        username: 'demo',
        password: 'demopassword',
      },
    })
      .then((response) => {
        console.log('Demo account registered successfully', response);
      })
      .catch((error) => {
        console.error('Failed to register demo account', error);
      });
  };

  return (
    <div>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
      <button onClick={handleRegisterDemo}>Register Demo</button>
    </div>
  );
}



export default LoginForm;
