module.exports.postCreate = function(req, res, next) {
    var errors = [];
    if (!req.body.tenSP) {
        errors.push('Tên sản phẩm không được bỏ trống');
    }
    if (!req.body.moTa) {
        errors.push('Mô tả không được bỏ trống');
    }
    if (!req.body.gia) {
        errors.push('Giá không được bỏ trống');
    }
    if (!req.body.giaCu) {
        errors.push('Giá cũ không được bỏ trống');
    }
    if (!req.body.tiLeGiamGia) {
        errors.push('Tỉ lệ giảm giá không được bỏ trống');
    }
    if (!req.body.maDM) {
        errors.push('Mã danh mục không được bỏ trống');
    }
    if (errors.length) {
        res.render('product/create', {
            errors: errors,
            values: req.body
        });
        return;
    }
    next();
}