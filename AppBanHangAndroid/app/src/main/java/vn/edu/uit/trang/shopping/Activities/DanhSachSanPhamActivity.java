package vn.edu.uit.trang.shopping.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.adapters.DanhSachSanPhamAdapter;
import vn.edu.uit.trang.shopping.models.SanPham;
import vn.edu.uit.trang.shopping.until.Webserviecs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class DanhSachSanPhamActivity extends AppCompatActivity {

    RecyclerView recyclerViewDSSP;
    DanhSachSanPhamAdapter adapterDSSP;
    ArrayList<SanPham> sanPhams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);
        loadData();
        initControls();
    }

    private void loadData() {
        Intent intent = getIntent();
        String maDM = intent.getStringExtra("danhmuc");
        sanPhams = new ArrayList<>();
        LoadSanPhamTask task = new LoadSanPhamTask();
        task.execute(maDM);
    }

    private void initControls() {
        recyclerViewDSSP = findViewById(R.id.recyclerViewDSSP);
        adapterDSSP = new DanhSachSanPhamAdapter(DanhSachSanPhamActivity.this, sanPhams);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerViewDSSP.setLayoutManager(layoutManager);
        recyclerViewDSSP.setAdapter(adapterDSSP);
    }

    class LoadSanPhamTask extends AsyncTask<String, Void, ArrayList<SanPham>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);
            if(sanPhams != null){
                DanhSachSanPhamActivity.this.sanPhams.clear();
                DanhSachSanPhamActivity.this.sanPhams.addAll(sanPhams);
                adapterDSSP.notifyDataSetChanged();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<SanPham> doInBackground(String... strings) {
            ArrayList<SanPham> sanPhams = new ArrayList<>();
            try{
                //JSONArray jsonArray = Webserviecs.getJsonArray("sanpham?madm=" + strings[0]);
                JSONArray jsonArray = Webserviecs.getJsonArray("api/sanphams?madm=" + strings[0]);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String masp = jsonObject.getString("_id");
                    String ten = jsonObject.getString("tenSP");
                    String mota = jsonObject.getString("moTa");
                    double gia = Double.parseDouble(jsonObject.getString("gia"));
                    double giaCu = Double.parseDouble(jsonObject.getString("giaCu"));
                    double giamGia = Double.parseDouble(jsonObject.getString("tiLeGiamGia"));
                    String hinh = jsonObject.getString("hinh");
                    SanPham sp = new SanPham();
                    sp.setMaSanPham(masp);
                    sp.setTenSanPham(ten);
                    sp.setMoTa(mota);
                    sp.setGia(gia);
                    sp.setGiaCu(giaCu);
                    sp.setGiamGia(giamGia);
                    sp.setHinh(hinh);
                    sanPhams.add(sp);
                }
                return sanPhams;
            }catch (Exception ex){
                Log.e("Loi: ", ex.toString());
            }
            return null;
        }
    }
}
