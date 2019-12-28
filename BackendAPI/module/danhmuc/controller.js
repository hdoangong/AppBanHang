var DanhMuc = require('./model');

module.exports.index = async function(req, res) {
    var manhomdm = req.query.manhomdm;
    var danhmucs;
    if (manhomdm) {
        danhmucs = await DanhMuc.find({ maNhomDM: manhomdm });
    } else {
        danhmucs = await DanhMuc.find();
    }
    return res.json(danhmucs);
}