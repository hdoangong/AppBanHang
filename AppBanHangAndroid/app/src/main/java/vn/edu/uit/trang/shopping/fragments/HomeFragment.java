package vn.edu.uit.trang.shopping.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.edu.uit.trang.shopping.Activities.DanhSachSanPhamActivity;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.adapters.HomeAdvertisementAdapter;
import vn.edu.uit.trang.shopping.adapters.HomeDealAdapter;
import vn.edu.uit.trang.shopping.adapters.HomeItemGroupAdapter;
import vn.edu.uit.trang.shopping.models.DanhMuc;
import vn.edu.uit.trang.shopping.models.HomeAdvertisement;
import vn.edu.uit.trang.shopping.models.HomeDeal;
import vn.edu.uit.trang.shopping.models.HomeItemGroup;
import vn.edu.uit.trang.shopping.models.SanPham;
import vn.edu.uit.trang.shopping.until.Webserviecs;

public class HomeFragment extends Fragment {

//    Danh sách các nhóm mặt hàng dưới thanh search bar
    List<DanhMuc> listItemGroup;

    //    Danh sách quảng cáo
    List<HomeAdvertisement> listAdvertisement;

//    Danh sách các mặt hàng giảm giá
    List<SanPham> listDeal;

    HomeItemGroupAdapter itemGroupAdapter;
    HomeDealAdapter dealAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        Nhập dữ liệu cho nhóm mặt hàng gợi ý dưới thanh search bar
        listItemGroup = new ArrayList<>();
//        listItemGroup.add(new HomeItemGroup("Đồ gia dụng", R.drawable.housewares));
//        listItemGroup.add(new HomeItemGroup("Thực phẩm", R.drawable.food));
//        listItemGroup.add(new HomeItemGroup("Điện thoại và Đồng hồ thông minh", R.drawable.phones_and_watches));
//        listItemGroup.add(new HomeItemGroup("Máy tính", R.drawable.computers));
//        listItemGroup.add(new HomeItemGroup("Thời trang", R.drawable.thoi_trang_tre_em));
//        listItemGroup.add(new HomeItemGroup("TV - Tủ lạnh - Máy giặt", R.drawable.ti_vi_tu_lanh_may_giat));
//        listItemGroup.add(new HomeItemGroup("Giày dép", R.drawable.giay_dep));
//        listItemGroup.add(new HomeItemGroup("Board Games", R.drawable.board_game));
//        listItemGroup.add(new HomeItemGroup("Bia - Rượu - Nước ngọt", R.drawable.bia_ruou_nuoc_ngot));
        GetDanhMucTask task = new GetDanhMucTask();
        task.execute("giadinhnhacua");

//        Nhập dữ liệu cho banner quảng cáo
        listAdvertisement = new ArrayList<>();
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));
        listAdvertisement.add(new HomeAdvertisement("Một vài câu quảng cáo cho một sản phẩm mới nào đớ bla bla", R.drawable.clothes));

//        Nhập dữ liệu cho nhóm mặt hàng giảm giá
        listDeal = new ArrayList<>();
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
//        listDeal.add(new HomeDeal("Máy Ảnh Nikon D3500 KIT 18-55 VR (24.2MP) - Hàng Chính Hãng", R.drawable.nikon, 9590000, 14990000));
        LoadSanPhamTask task2 = new LoadSanPhamTask();
        task2.execute("");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView itemGroupRecyclerView = view.findViewById(R.id.item_group_recyclerview);
        RecyclerView dealRecyclerView = view.findViewById(R.id.deal_recyclerview);
        RecyclerView advertisementRecyclerView = view.findViewById(R.id.advertisement_recyclerview);


        itemGroupAdapter = new HomeItemGroupAdapter(getActivity(), listItemGroup);
        dealAdapter = new HomeDealAdapter(getActivity(), listDeal);
        HomeAdvertisementAdapter advertisementAdapter = new HomeAdvertisementAdapter(getActivity(), listAdvertisement);


        LinearLayoutManager itemGroupLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager dealLayoutManager = new GridLayoutManager(getActivity(), 2);
        LinearLayoutManager advertisementLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


        itemGroupRecyclerView.setLayoutManager(itemGroupLayoutManager);
        itemGroupRecyclerView.setAdapter(itemGroupAdapter);

        advertisementRecyclerView.setLayoutManager(advertisementLayoutManager);
        advertisementRecyclerView.setAdapter(advertisementAdapter);

        dealRecyclerView.setLayoutManager(dealLayoutManager);
        dealRecyclerView.setAdapter(dealAdapter);

        return view;

    }

    class GetDanhMucTask extends AsyncTask<String, Void, ArrayList<DanhMuc>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<DanhMuc> danhMucs) {
            super.onPostExecute(danhMucs);
            if(danhMucs != null){
                listItemGroup.clear();
                listItemGroup.addAll(danhMucs);
                itemGroupAdapter.notifyDataSetChanged();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<DanhMuc> doInBackground(String... strings) {
            ArrayList<DanhMuc> danhMucs = new ArrayList<>();
            try{
               JSONArray jsonArray =
                        Webserviecs.getJsonArray("api/danhmucs?manhomdm="+strings[0]);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String maDanhMuc = jsonObject.getString("_id");
                    String tenDanhMuc = jsonObject.getString("tenDM");
                    String hinh = jsonObject.getString("hinh");
                    DanhMuc dm = new DanhMuc(maDanhMuc,tenDanhMuc,hinh);
                    danhMucs.add(dm);
                }
                return danhMucs;
            }catch (Exception ex){
                Log.e("Loi:",ex.toString());
            }
            return null;
        }
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
                listDeal.clear();
                listDeal.addAll(sanPhams);
                dealAdapter.notifyDataSetChanged();
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
                JSONArray jsonArray = Webserviecs.getJsonArray("api/sanphams");
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
