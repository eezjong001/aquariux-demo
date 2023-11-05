How to use this demo

1. Run Discovery Server
2. Run H2Database Service
3. Go to the /crypto-frontend folder, and use npm install
4. Go to the /crypto-frontend folder, and use npm start
5. Go to localhost:3000
6. Press "Register Demo" , this will register a real demo account that does not exist yet in the H2 DB
7. h2 DB is mapped here if needed :http://localhost:8080/h2-console/
8. use the credentials username:"demo" and password:"demopassword" to login
9. Once logged in there will be a few segments:
- Crypto Table, this pulls live API that updates at the backend every 10 seconds from two sources , binance and huobi
- Best Price Checker, this checks the best aggregated price (binance +huobi tickers) prices dynamically as the field changes to show the prices for them
- Wallets, this shows the Account holder's curret wallets details and balance
- Crypto Trader, this portion allows the user to buy and sell crypto populated from a list in the DB , buying or selling will update the trading history AND wallets
- Trading History, this tracks the users buy and sell activities and refreshes every 2 seconds with the most updated list

10. Still some bugs here and there and the application architecture is a bit sub-optimal due to time constraints.