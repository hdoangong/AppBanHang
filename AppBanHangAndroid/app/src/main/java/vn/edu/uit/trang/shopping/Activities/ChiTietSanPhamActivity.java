package vn.edu.uit.trang.shopping.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.adapters.DanhSachHinhItemAdapter;
import vn.edu.uit.trang.shopping.models.SanPham;
import vn.edu.uit.trang.shopping.until.ImageLoadTask;
import vn.edu.uit.trang.shopping.until.UserPresent;
import vn.edu.uit.trang.shopping.until.Webserviecs;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSanPhamActivity extends AppCompatActivity{

    TextView txtTenSanPham, txtGiaSanPham, txtGiaCu, txtGiamGia, txtMoTa;
    Spinner spinner;
    Button btnChonSanPham;
    ImageView imgSanPham;
    RecyclerView recyclerViewHinhSanPhams;

    SanPham sp;

    ArrayList<String> hinhs;

    RecyclerViewClickListener clickListener;
    DanhSachHinhItemAdapter adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        getData();
        initControls();
        loadDataToView();
        addEvents();
    }

    private void addEvents() {
        btnChonSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserPresent.user_id == null) {
                    Intent intent = new Intent(ChiTietSanPhamActivity.this, LoginActivity.class);
                    intent.putExtra("chitietsanpham", 1);
                    startActivity(intent);
                    Toast.makeText(ChiTietSanPhamActivity.this, "Bạn cần đăng nhập trước", Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                    PostThemVaoGioHangTask task = new PostThemVaoGioHangTask();
                    StringWriter output = new StringWriter();
                    writeJsonStream(output, sp);
                    String jsonText = output.toString();
                    task.execute(jsonText);
                } catch (Exception ex){
                    Log.e("Loi_ChiTietSanPhamActivity: ", ex.toString());
                }
            }
        });
    }

    public void writeJsonStream(Writer output, SanPham sp) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);
        jsonWriter.beginObject();// begin root
        jsonWriter.name("user_id").value(UserPresent.user_id);
        jsonWriter.name("sanPham_id").value(sp.getMaSanPham());
        jsonWriter.name("soLuong").value(Integer.parseInt(spinner.getSelectedItem().toString()));
        jsonWriter.name("thanhTien").value(sp.getGia());
        jsonWriter.name("daXuat").value(false);
        jsonWriter.name("hinh").value(sp.getHinh());
        jsonWriter.endObject(); //end root
    }

    private void loadDataToView() {
        txtTenSanPham.setText(sp.getTenSanPham());
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        txtGiaSanPham.setText(dcf.format(sp.getGia()) + "đ");
        txtGiaCu.setText(dcf.format(sp.getGiaCu()) + "đ");
        txtGiaCu.setPaintFlags(txtGiaCu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txtGiamGia.setText("- " + dcf.format(sp.getGiamGia()) + "%");
        txtMoTa.setText(sp.getMoTa());
        (new ImageLoadTask(sp.getHinh(),imgSanPham)).execute();
    }

    private void getData() {
        Intent intent = getIntent();
        sp = (SanPham) intent.getSerializableExtra("sanpham");
        hinhs = new ArrayList<>();
//        getHinhSanPhamTask task = new getHinhSanPhamTask();
//        task.execute(sp.getMaSanPham());
    }

    private void initControls() {
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtGiaSanPham = findViewById(R.id.txtGiaSanPham);
        txtGiaCu = findViewById(R.id.txtGiaCu);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtMoTa = findViewById(R.id.txtMotaSanPham);
        btnChonSanPham = findViewById(R.id.btnChonMuaSanPham);
        imgSanPham = findViewById(R.id.imgSanPham);
        spinner = findViewById(R.id.spinerSoLuongSanPham);
        Integer[] soLuong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,soLuong);
        spinner.setAdapter(adapter);

        clickListener = new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(int position) {
                (new ImageLoadTask(hinhs.get(position), imgSanPham)).execute();
            }
        };

        recyclerViewHinhSanPhams = findViewById(R.id.recyclerImgSanPhams);
        adapterItem = new DanhSachHinhItemAdapter(getApplicationContext(), hinhs,clickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHinhSanPhams.setLayoutManager(layoutManager);
        recyclerViewHinhSanPhams.setAdapter(adapterItem);
    }

    class getHinhSanPhamTask extends AsyncTask<String, Void, ArrayList<String>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            if(strings != null){
                hinhs.clear();
                hinhs.addAll(strings);
                adapterItem.notifyDataSetChanged();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            ArrayList<String> hinhs = new ArrayList<>();
            try{
                JSONArray jsonArray = Webserviecs.getJsonArray("hinhanhsanpham?masp=" + strings[0]);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String h = jsonObject.getString("stringBase64");
                    hinhs.add(h);
                }
                return hinhs;
            }catch (Exception ex){
                Log.e("Loi: ", ex.toString());
            }
            return null;
        }
    }

    class PostThemVaoGioHangTask extends AsyncTask<String, Void, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue() == true){
                Intent intent = new Intent(ChiTietSanPhamActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                return Webserviecs.postAPI("api/giohangs/create", strings[0]);
            } catch (Exception ex){}
            return false;
        }
    }
}
