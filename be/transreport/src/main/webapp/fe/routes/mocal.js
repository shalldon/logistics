module.exports = function ( app ) {
    app.get('/register', function(req, res) {

		
    });

    app.post('/register', function (req, res) {

	   
    });

    app.get('/createGroup',function (req, res) {
    	var groupName = req.query.groupName
    	res.json({groupID: "0001",groupName:groupName});
	   
    });
}