var User = require('../models/user.model');
module.exports.requireAuth = function(req, res, next) {
    if (!req.signedCookies.userId) {
        res.redirect('auth/login');
        return;
    }
    var user = User.find({ id: req.signedCookies.userId }).then(function(users) {
        return users[0];
    })
    if (!user) {
        res.redirect('auth/login');
        return;
    }
    res.locals.user = user;
    next();
}