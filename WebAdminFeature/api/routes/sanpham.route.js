var express = require('express');
var controller = require('../controllers/sanpham.controller');

var router = express.Router();

router.get('/', controller.index);

router.get('/:id', controller.getByID);

router.post('/create', controller.create);

module.exports = router;