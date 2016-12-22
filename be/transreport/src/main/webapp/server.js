var express = require('express');
var bodyParser = require('body-parser');
var morgan = require('morgan');

var app = express();
var port = 3000;

app.use('/', express.static(__dirname));
//app.use('/fe', express.static(__dirname + "/views"));

//app.use('/', express.static(__dirname + "/node_modules"));
//app.use('/', express.static(__dirname + "/bower_components"));


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }))
//app.use('/api', bodyParser.json());
app.use('/', morgan('dev'));

app.set('views', express.static(__dirname + "/views"));
app.set( 'view engine', 'html' );

require('./fe/routes')(app);

app.listen(port, function() {
	console.log('app listening on port: ' + port);
});