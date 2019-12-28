module.exports.postCreate = function(req,res,next){
	var errors=[];
		if (!req.body.name){
			errors.push('Tên người dùng không được bỏ trống');
		}
		if (!req.body.password){
			errors.push('Mật khẩu không được bỏ trống');
		}
		if (errors.length){
			res.render('users/create', {
				errors: errors,
				values: req.body
			});
			return;
		}
		next();
}