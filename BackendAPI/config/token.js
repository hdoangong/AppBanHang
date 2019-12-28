module.exports = {
    secret: process.env.JWT_SECRET || 'ThanhVaTrang',
    expiresIn: 60 * 60 * 24 * 30 // thời gian hết hạn token 1 tháng
}