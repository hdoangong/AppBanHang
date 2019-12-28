require('dotenv').config();
var express = require('express');
var app = express();
var port = process.env.PORT || 3000;
app.set('view engine', 'pug');
app.set('views', './views');
var mongoose = require('mongoose');
mongoose.connect(process.env.MONGODB_URI);

var bodyParser = require('body-parser');
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded

var cookieParser = require('cookie-parser');
var sessionMiddleware = require('./middlewares/session.middleware');

app.use(express.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded
app.use(cookieParser(process.env.SESSION_SECRET))
app.use(sessionMiddleware);
app.use(express.static('public'));
app.use(express.static('views/layouts'));

var upload = require('./multer')

var cloudinary = require('./cloudinary')

var fs = require('fs')

// Upload mutiple
app.use('/upload-images',upload.array('image'),async(req,res)=>{

    const uploader = async (path) =>await cloudinary.uploads(path,'Images')

    if(req.method ==='POST'){
        const urls = []
        const files = req.files

        for(const file of files){
            const { path } =file

            const newPath = await uploader(path)

            urls.push(newPath)

            fs.unlinkSync(path)
        }

        res.status(200).json({
            message:'Images Uploaded Successfully',
            data:urls
        })
    } else {
        res.status(405).json({
            err:"Images not Uploaded Successfully"
        })
    }
})
// var cloudinary = require('cloudinary').v2;
// cloudinary.config({
//     cloud_name: process.env.CLOUD_NAME,
//     api_key: process.env.API_KEY,
//     api_secret: process.env.API_SECRET
// });

var sanPhamApiRoute = require('./api/routes/sanpham.route');
app.use('/api/sanphams', sanPhamApiRoute);
var danhMucApiRoute = require('./api/routes/danhmuc.route');
app.use('/api/danhmucs', danhMucApiRoute);
var donHangApiRoute = require('./api/routes/donhang.route');
app.use('/api/donhangs', donHangApiRoute);
var apiUserRoute = require('./api/routes/user.route');
app.use('/api/users', apiUserRoute);

var authMiddleware = require('./middlewares/auth.middleware');

var donHangRoute = require('./routes/donhang.route');
app.use('/donhangs', donHangRoute);
var danhMucRoute = require('./routes/danhmuc.route');
app.use('/danhmucs', danhMucRoute);
var userRoute = require('./routes/user.route');
app.use('/users', authMiddleware.requireAuth, userRoute);
var authRoute = require('./routes/auth.route');
app.use('/auth', authRoute);
var productRoute = require('./routes/product.route');
app.use('/products', authMiddleware.requireAuth, productRoute);

app.get('/', authMiddleware.requireAuth, function(req, res) {
    res.render('index', {
        name: 'Tiki'
    });
});
app.listen(port, function() {
    console.log('Example app listening on port' + port);
});