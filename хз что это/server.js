const express = require('express');
const app = express();
const fs = require('fs');
const bodyParser = require('body-parser');
// подключаем его к модулю express
app.use(bodyParser.urlencoded({ extended: true }));
require('./app/routes')(app, fs);
app.use(express.static('public'));
app.listen(80);
console.log("Server started at 80");