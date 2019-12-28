var SanPham = require('./model');

module.exports.index = async function(req, res) {
    var madm = req.query.madm;
    var sanphams;
    if (madm)
        sanphams = await SanPham.find({ maDM: madm });
    else
        sanphams = await SanPham.find();
    res.json(sanphams);
}