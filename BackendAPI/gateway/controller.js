const jwt = require('jsonwebtoken');
const tokenConfig = require('../config').token;

const requestNeedAuthen = [{
    url: '/giohangs/*',
    method: 'GET'
}]

module.exports.checkAuth = function(req, res, next) {
    const arrRequestNeedAuthen = requestNeedAuthen.find(r => req.path.match(r.url + '$') && r.method === req.method);
    if (!arrRequestNeedAuthen) {
        next();
        return;
    }
    try {
        var decode = null;
        if (req.headers && req.headers.authorization) {
            var arr = req.headers.authorization.split(' ');
            var bearer = arr[0].toLowerCase();
            if (bearer === 'bearer') {
                var token = arr[1];
                decode = jwt.verify(token, tokenConfig.secret);
            }
        }
        if (decode) {
            return next();
        }
        return res.status(403).send({
            message: 'Unauthorized'
        });
    } catch (err) {
        return res.status(403).send({
            message: 'Unauthorized'
        });
    }
};