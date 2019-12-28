var DonHang = require('../../models/donhang.model');

module.exports.index = function(req, res) {
    // var manhomdm = req.query.manhomdm;
    // var danhmucs;
    // if (manhomdm) {
    //     danhmucs = await DanhMuc.find({ maNhomDM: manhomdm });
    // } else {
    //     danhmucs = await DanhMuc.find();
    // }
    // res.json(danhmucs);
    res.send('hello, this is index of donhang');
}

module.exports.create = async function(req, res) {
    var donhang = new DonHang({
        user_id: req.query.user_id,
        sanpham_id: req.query.sanpham_id,
        soluong: req.query.soluong,
        daxuat: req.query.daxuat,
        danhan: req.query.danhan
    })
    var don_hang = await DonHang.create(donhang);
    if (don_hang) {
        res.json(don_hang);
    } else {
        res.send("");
    }
}