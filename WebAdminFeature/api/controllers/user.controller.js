var User = require('../../models/user.model');


module.exports.index = async function(req,res){

	var email = req.query.email;
	var user = await User.find({ email:email }).then(function(user){
			return user[0];
		});
	if(user)
	{
		res.json(user);
	}
	res.json(await User.find())
};

module.exports.create = async function(req,res){
	var user= new User({
	email: req.query.email,
	password: req.query.password,
	name: req.query.name,
	avatar: req.query.avatar,
	phone: req.query.phone
	})
	res.json(user);
}
