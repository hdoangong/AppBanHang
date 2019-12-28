var User = require('../module/userapp/model');
const jwt = require('jsonwebtoken');
const tokenConfig = require('../config').token;

module.exports.login = async function(req, res) {
    try {
        var email = req.body.email;
        var password = req.body.password;
        if (email && password) {
            var user = await User.findOne({
                email: email,
                password: password
            });
            if (user) {
                var token = jwt.sign({}, tokenConfig.secret, { algorithm: 'HS256', expiresIn: tokenConfig.expiresIn });
                return res.json({
                    token: token,
                    user: user._id
                });
            } else {
                return res.json({
                    message: 'error'
                })
            }
        }
    } catch (ex) {
        return res.json({
            message: 'error'
        })
    }
}