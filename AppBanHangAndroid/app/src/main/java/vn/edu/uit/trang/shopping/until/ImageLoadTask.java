package vn.edu.uit.trang.shopping.until;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    //Link url hình ảnh bất kỳ
    private String url;
    //Control ImageView bất kỳ
    private ImageView imageView;

    public ImageLoadTask(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
//Tiến hành tạo đối tượng URL
            URL urlConnection = new URL(url);
//Mở kết nối
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
//Đọc dữ liệu
            InputStream input = connection.getInputStream();
//Tiến hành convert qua hình ảnh
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
//lấy kết quả hiển thị lên giao diện:
        imageView.setImageBitmap(result);
    }

}