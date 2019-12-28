var GioHang = require('./model');
var SanPham = require('../sanpham/model');
var User = require('../userapp/model');

module.exports.index = async function(req, res) {
    var userid = req.query.user_id;
    var giohangs;
    if (userid) {
        var giohangs = await GioHang.find({ user_id: userid });
        var result = await Promise.all(giohangs.map(async x => {
            var user = await User.find({ _id: x.user_id });
            var sanpham = await SanPham.find({ _id: x.sanPham_id });
            return {
                _id: x._id,
                username: user[0].name,
                tenSP: sanpham[0].tenSP,
                thanhTien: x.thanhTien,
                soLuong: x.soLuong,
                hinh: x.hinh
            };
        }));
        return res.json(result);
    } else {
        giohangs = await GioHang.find();
    }
    return res.json(giohangs);
}

module.exports.create = async function(req, res) {
    var giohang = req.body;
    if (giohang) { // nếu đã có sản phẩm thì sửa lại số lượng
        var result = await GioHang.updateOne({
            user_id: giohang.user_id,
            sanPham_id: giohang.sanPham_id,
            daXuat: false
        }, { $inc: { soLuong: giohang.soLuong } });
        if (result.n > 0) {
            res.send('success');
        } else {
            var giohang2 = new GioHang(req.body);
            var gio_hang = await GioHang.create(giohang2);
            if (gio_hang) {
                res.send('success');
            } else {
                res.send('error');
            }
        }
    }
}

module.exports.delete = async function(req, res) {
    var id = req.params.id;
    var result = await GioHang.deleteOne({ _id: id });
    if (result.ok === 1) {
        res.send('success');
    } else {
        res.send('error');
    }
}

module.exports.update = async function(req, res) {
    var giohang = req.body;
    if (giohang) {
        var id = giohang._id;
        var sl = giohang.soLuong;
        var result = await GioHang.updateOne({ _id: id }, { $inc: { soLuong: sl } });
        if (result.n > 0) {
            res.send('success');
        } else {
            res.send('error');
        }
    }
}