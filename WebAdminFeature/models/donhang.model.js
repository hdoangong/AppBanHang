var mongoose = require('mongoose');

var donHangSchema = new mongoose.Schema({
    user_id: String,
    sanpham_id: String,
    soluong: Number,
    daxuat: Boolean,
    danhan: Boolean
});

var DonHang = mongoose.model('DonHang', donHangSchema, 'donhangs');

module.exports = DonHang;