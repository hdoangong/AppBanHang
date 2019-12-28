var ctdh = require('./model');
var sp = require('../sanpham/model');
var user = require('../userapp/model');
var giohang = require('../giohang/model');

module.exports.banchay = async(req, res) => {
    var giohangQuery = ctdh.find();
    giohangQuery.select('giohang_id');
    var giohang_id = await giohangQuery.exec();
    var sanphams = giohang_id.map(gh_id => {
        var giohangtmp = await giohang.find({ _id: gh_id });
        return {
            sanpham_id: giohangtmp.sanpham_id,
            soLuong: giohangtmp.soLuong
        }
    });



}