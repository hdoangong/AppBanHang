require('dotenv').config();

var express = require('express');
var app = express();
var port = process.env.PORT || 3001;

var mongoose = require('mongoose');
mongoose.connect(process.env.MONGODB_URI);

var bodyParser = require('body-parser');
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded

var cookieParser = require('cookie-parser');
app.use(express.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded
app.use(cookieParser(process.env.SESSION_SECRET))

var cloudinary = require('cloudinary').v2;
cloudinary.config({
    cloud_name: process.env.CLOUD_NAME,
    api_key: process.env.API_KEY,
    api_secret: process.env.API_SECRET
});

app.use('/api', require('./gateway').router);
app.use(require('./auth').router);
app.get('/', function(req, res) {
    res.send('This is API Backend Android Page');
});

app.listen(port, function() {
    console.log('Example app listening on port ' + port);
});