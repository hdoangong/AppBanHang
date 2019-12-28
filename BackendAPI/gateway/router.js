const express = require('express');
const router = new express.Router();

var controller = require('./controller');
router.use(controller.checkAuth);

router.use('/danhmucs', require('../module/danhmuc').router);
router.use('/giohangs', require('../module/giohang').router);
router.use('/sanphams', require('../module/sanpham').router);
router.use('/users', require('../module/userapp').router);

module.exports = router;