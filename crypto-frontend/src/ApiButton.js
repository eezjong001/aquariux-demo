import React, { Component } from 'react';
import axios from 'axios';

class ApiButton extends Component {

  handleClick = () => {

    axios.get('https://api.example.com/data')
      .then(response => {

        console.log(response.data);
      })
      .catch(error => {

        console.error(error);
      });
  };

  render() {
    return (
      <div>
        <button onClick={this.handleClick}>Fetch Data</button>
      </div>
    );
  }
}

export default ApiButton;
