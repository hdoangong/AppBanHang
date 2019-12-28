var SanPham = require('../../models/sanpham.model');

module.exports.index = async function(req, res) {
    var madm = req.query.madm;
    var sanphams;
    if (madm)
        sanphams = await SanPham.find({ maDM: madm });
    else
        sanphams = await SanPham.find();
    res.json(sanphams);
}

module.exports.getByID = async function(req, res) {
    var id = req.params.id;
    var result = await SanPham.find({ _id: id });
    res.json(result);
}

module.exports.create = async function(req, res) {
    var sanpham = await SanPham.create(req.body);
    res.json(sanpham);
}