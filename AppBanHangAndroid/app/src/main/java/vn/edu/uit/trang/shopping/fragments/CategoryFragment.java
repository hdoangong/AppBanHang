
package vn.edu.uit.trang.shopping.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.uit.trang.shopping.adapters.CategoryItemGroupAdapter2;
import vn.edu.uit.trang.shopping.models.CategoryItemGroup;
import vn.edu.uit.trang.shopping.adapters.CategoryItemGroupAdapter;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.DanhMuc;
import vn.edu.uit.trang.shopping.until.Webserviecs;

public class CategoryFragment extends Fragment {

    //     Danh sách các nhóm hàng
    List<DanhMuc> gia_dinh_nha_cua;
    List<CategoryItemGroup> cong_nghe;
    List<CategoryItemGroup> hang_tieu_dung_thuc_pham;
    List<CategoryItemGroup> thoi_trang;
    List<CategoryItemGroup> cong_viec;
    List<CategoryItemGroup> giai_tri;


    CategoryItemGroupAdapter2 gia_dinh_nha_cua_Adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        Thêm chi tiết nhóm hàng gia đình - nhà cửa
        gia_dinh_nha_cua = new ArrayList<>();
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Đồ chơi - Mẹ và Bé", R.drawable.do_choi));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Chăn - Drap - Gối - Nệm", R.drawable.chan_drap));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Dụng cụ dã ngoại", R.drawable.dung_cu_da_ngoai));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Bếp gas - Bếp điện", R.drawable.bep_gas_bep_dien));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Hoa tươi", R.drawable.hoa_tuoi));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Chén - Dĩa", R.drawable.chen_dia));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Dụng cụ nấu ăn", R.drawable.dung_cu_nau_an));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Đồ dùng và thiết bị nhà tắm", R.drawable.do_dung_va_thiet_bi_nha_tam));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Đèn và thiết bị chiếu sáng", R.drawable.den_va_thiet_bi_chieu_sang));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Nội thất", R.drawable.noi_that));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Xe máy - Xe đạp", R.drawable.xe_may_xe_dap));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("Ô tô", R.drawable.o_to));
//        gia_dinh_nha_cua.add(new CategoryItemGroup("TV - Tủ lạnh - Máy giặt", R.drawable.ti_vi_tu_lanh_may_giat));

        GetDanhMucTask task = new GetDanhMucTask();
        task.execute("giadinhnhacua");

//        Thêm chi tiết nhóm hàng Công nghệ
        cong_nghe = new ArrayList<>();
        cong_nghe.add(new CategoryItemGroup("Máy tính xách tay", R.drawable.may_tinh_xach_tay));
        cong_nghe.add(new CategoryItemGroup("Điện thoại thông minh", R.drawable.dien_thoai));
        cong_nghe.add(new CategoryItemGroup("Máy Ảnh - Quay Phim", R.drawable.nikon));
        cong_nghe.add(new CategoryItemGroup("Thiết bị đeo tay thông minh", R.drawable.thiet_bi_deo_tay));
        cong_nghe.add(new CategoryItemGroup("Máy tính bảng", R.drawable.may_tinh_bang));
        cong_nghe.add(new CategoryItemGroup("Máy tính để bàn", R.drawable.may_tinh_de_ban));
        cong_nghe.add(new CategoryItemGroup("Drone - Flycam", R.drawable.drone_va_flycam));
        cong_nghe.add(new CategoryItemGroup("Phụ kiện điện thoại", R.drawable.phu_kien_dien_thoai));
        cong_nghe.add(new CategoryItemGroup("Ổ cứng - Ram", R.drawable.o_cung_ram));

//        Thêm chi tiết nhóm hàng Hàng tiêu dùng - Thực phẩm
        hang_tieu_dung_thuc_pham = new ArrayList<>();
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Đồ hộp", R.drawable.do_hop));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Gia vị", R.drawable.gia_vi));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Thực phẩm ăn liền", R.drawable.thuc_pham_an_lien));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Thực phẩm chay", R.drawable.thuc_pham_chay));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Thịt - Cá", R.drawable.thit_ca));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Trứng", R.drawable.trung));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Bánh - Kẹo", R.drawable.banh_keo));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Bia - Rượu - Nước ngọt", R.drawable.bia_ruou_nuoc_ngot));
        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Sữa - Trà - Cà phê", R.drawable.sua_tra_cafe));

//        Thêm chi tiết cho nhóm hàng Thời trang
        thoi_trang = new ArrayList<>();
        thoi_trang.add(new CategoryItemGroup("Thời trang nam", R.drawable.thoi_trang_nam));
        thoi_trang.add(new CategoryItemGroup("Thời trang nữ", R.drawable.thoi_trang_nu));
        thoi_trang.add(new CategoryItemGroup("Thời trang trẻ em", R.drawable.thoi_trang_tre_em));
        thoi_trang.add(new CategoryItemGroup("Thời trang lớn tuổi", R.drawable.thoi_trang_lon_tuoi));
        thoi_trang.add(new CategoryItemGroup("Thời trang công sở", R.drawable.thoi_trang_cong_so));
        thoi_trang.add(new CategoryItemGroup("Giày - Dép", R.drawable.giay_dep));
        thoi_trang.add(new CategoryItemGroup("Balo - Túi xách", R.drawable.balo_tui_xach));
        thoi_trang.add(new CategoryItemGroup("Giày thể thao", R.drawable.giay_the_thao));

//        Thêm chi tiết cho nhóm hàng Công việc
        cong_viec = new ArrayList<>();
        cong_viec.add(new CategoryItemGroup("Văn phòng phẩm", R.drawable.van_phong_pham));
        cong_viec.add(new CategoryItemGroup("Đồ bảo hộ", R.drawable.do_bao_ho));
        cong_viec.add(new CategoryItemGroup("Máy khoan - Máy cắt", R.drawable.may_khoan_may_cat));
        cong_viec.add(new CategoryItemGroup("Laptop văn phòng", R.drawable.laptop_van_phong));
        cong_viec.add(new CategoryItemGroup("Máy in - Photocopy", R.drawable.may_in_photocopy));
        cong_viec.add(new CategoryItemGroup("Máy cắt cỏ - Máy phun thuốc", R.drawable.may_cat_co_may_phun_thuoc));
        cong_viec.add(new CategoryItemGroup("Thuốc trừ Sâu - Thuốc trừ Cỏ", R.drawable.thuoc_tru_sau));
        cong_viec.add(new CategoryItemGroup("Phân bón", R.drawable.phan_bon));

//        Thêm chi tiết cho nhóm hàng Giải trí
        giai_tri = new ArrayList<>();
        giai_tri.add(new CategoryItemGroup("Laptop Gamming", R.drawable.laptop_gamming));
        giai_tri.add(new CategoryItemGroup("PC Gamming", R.drawable.pc_gamming));
        giai_tri.add(new CategoryItemGroup("Bàn phím cơ", R.drawable.ban_phim_co));
        giai_tri.add(new CategoryItemGroup("Chuột Gamming", R.drawable.chuot_gamming));
        giai_tri.add(new CategoryItemGroup("Tai nghe", R.drawable.tai_nghe_gamming));
        giai_tri.add(new CategoryItemGroup("Play Station - Xbox", R.drawable.play_station_xbox));
        giai_tri.add(new CategoryItemGroup("Vợt cầu lông - Vợt tennis", R.drawable.vot_cau_long_tennis));
        giai_tri.add(new CategoryItemGroup("Bóng đá - Bóng chuyền", R.drawable.bong_da_bong_chuyen));
        giai_tri.add(new CategoryItemGroup("Board Games", R.drawable.board_game));




        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView giaDinhNhaCuaRecyclerView = view.findViewById(R.id.recycler_view_giadinh_nhacua);
        RecyclerView congNgheRecyclerView = view.findViewById(R.id.recycler_view_congnghe);
        RecyclerView hangTieuDungRecyclerView = view.findViewById(R.id.recycler_view_hangtieudung_thucpham);
        RecyclerView thoiTrangRecyclerView = view.findViewById(R.id.recycler_view_thoitrang);
        RecyclerView congViecRecyclerView = view.findViewById(R.id.recycler_view_congviec);
        RecyclerView giaiTriRecyclerView = view.findViewById(R.id.recycler_view_giaitri);

        gia_dinh_nha_cua_Adapter = new CategoryItemGroupAdapter2(getActivity(), gia_dinh_nha_cua);
        CategoryItemGroupAdapter cong_nghe_Adapter = new CategoryItemGroupAdapter(getActivity(), cong_nghe);
        CategoryItemGroupAdapter hangTieuDungAdapter = new CategoryItemGroupAdapter(getActivity(), hang_tieu_dung_thuc_pham);
        CategoryItemGroupAdapter thoiTrangAdapter = new CategoryItemGroupAdapter(getActivity(), thoi_trang);
        CategoryItemGroupAdapter congViecAdapter = new CategoryItemGroupAdapter(getActivity(), cong_viec);
        CategoryItemGroupAdapter giaiTriAdapter = new CategoryItemGroupAdapter(getActivity(), giai_tri);


        LinearLayoutManager giaDinhNhaCuaLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        LinearLayoutManager congNgheLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        LinearLayoutManager hangTieuDungLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        LinearLayoutManager thoiTrangLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        LinearLayoutManager congViecLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        LinearLayoutManager giaiTriLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);


        giaDinhNhaCuaRecyclerView.setLayoutManager(giaDinhNhaCuaLayoutManager);
        congNgheRecyclerView.setLayoutManager(congNgheLayoutManager);
        hangTieuDungRecyclerView.setLayoutManager(hangTieuDungLayoutManager);
        thoiTrangRecyclerView.setLayoutManager(thoiTrangLayoutManager);
        congViecRecyclerView.setLayoutManager(congViecLayoutManager);
        giaiTriRecyclerView.setLayoutManager(giaiTriLayoutManager);


        giaDinhNhaCuaRecyclerView.setAdapter(gia_dinh_nha_cua_Adapter);
        congNgheRecyclerView.setAdapter(cong_nghe_Adapter);
        hangTieuDungRecyclerView.setAdapter(hangTieuDungAdapter);
        thoiTrangRecyclerView.setAdapter(thoiTrangAdapter);
        congViecRecyclerView.setAdapter(congViecAdapter);
        giaiTriRecyclerView.setAdapter(giaiTriAdapter);

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
                gia_dinh_nha_cua.clear();
                gia_dinh_nha_cua.addAll(danhMucs);
                gia_dinh_nha_cua_Adapter.notifyDataSetChanged();
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
                JSONArray jsonArray = Webserviecs.getJsonArray("api/danhmucs?manhomdm="+strings[0]);
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
}















////////////////////////////////
//package vn.edu.uit.trang.shopping.fragments;
//
//        import android.os.AsyncTask;
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//
//        import org.json.JSONArray;
//        import org.json.JSONObject;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.fragment.app.Fragment;
//        import androidx.recyclerview.widget.GridLayoutManager;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import vn.edu.uit.trang.shopping.adapters.CategoryItemGroupAdapter2;
//        import vn.edu.uit.trang.shopping.models.CategoryItemGroup;
//        import vn.edu.uit.trang.shopping.adapters.CategoryItemGroupAdapter;
//        import vn.edu.uit.trang.shopping.R;
//        import vn.edu.uit.trang.shopping.models.DanhMuc;
//        import vn.edu.uit.trang.shopping.until.Webserviecs;
//
//public class CategoryFragment extends Fragment {
//
//    //     Danh sách các nhóm hàng
//    List<DanhMuc> gia_dinh_nha_cua;
//    List<DanhMuc> cong_nghe;
//    List<DanhMuc> hang_tieu_dung_thuc_pham;
//    List<DanhMuc> thoi_trang;
//    List<DanhMuc> cong_viec;
//    List<DanhMuc> giai_tri;
//
//
//    CategoryItemGroupAdapter2 gia_dinh_nha_cua_Adapter;
//    CategoryItemGroupAdapter2 cong_nghe_Adapter;
//    CategoryItemGroupAdapter2 hangTieuDungAdapter;
//    CategoryItemGroupAdapter2 thoiTrangAdapter;
//    CategoryItemGroupAdapter2 congViecAdapter;
//    CategoryItemGroupAdapter2 giaiTriAdapter;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
////        Thêm chi tiết nhóm hàng gia đình - nhà cửa
//        gia_dinh_nha_cua = new ArrayList<>();
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Đồ chơi - Mẹ và Bé", R.drawable.do_choi));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Chăn - Drap - Gối - Nệm", R.drawable.chan_drap));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Dụng cụ dã ngoại", R.drawable.dung_cu_da_ngoai));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Bếp gas - Bếp điện", R.drawable.bep_gas_bep_dien));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Hoa tươi", R.drawable.hoa_tuoi));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Chén - Dĩa", R.drawable.chen_dia));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Dụng cụ nấu ăn", R.drawable.dung_cu_nau_an));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Đồ dùng và thiết bị nhà tắm", R.drawable.do_dung_va_thiet_bi_nha_tam));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Đèn và thiết bị chiếu sáng", R.drawable.den_va_thiet_bi_chieu_sang));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Nội thất", R.drawable.noi_that));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Xe máy - Xe đạp", R.drawable.xe_may_xe_dap));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("Ô tô", R.drawable.o_to));
////        gia_dinh_nha_cua.add(new CategoryItemGroup("TV - Tủ lạnh - Máy giặt", R.drawable.ti_vi_tu_lanh_may_giat));
//
//        GetDanhMucTask task = new GetDanhMucTask();
//        task.execute("giadinhnhacua");
//
////        Thêm chi tiết nhóm hàng Công nghệ
//        cong_nghe = new ArrayList<>();
////        GetDanhMucTask taskcongnghe = new GetDanhMucTask();
////        taskcongnghe.execute("congnghe");
//
////        cong_nghe.add(new CategoryItemGroup("Máy tính xách tay", R.drawable.may_tinh_xach_tay));
////        cong_nghe.add(new CategoryItemGroup("Điện thoại thông minh", R.drawable.dien_thoai));
////        cong_nghe.add(new CategoryItemGroup("Máy Ảnh - Quay Phim", R.drawable.nikon));
////        cong_nghe.add(new CategoryItemGroup("Thiết bị đeo tay thông minh", R.drawable.thiet_bi_deo_tay));
////        cong_nghe.add(new CategoryItemGroup("Máy tính bảng", R.drawable.may_tinh_bang));
////        cong_nghe.add(new CategoryItemGroup("Máy tính để bàn", R.drawable.may_tinh_de_ban));
////        cong_nghe.add(new CategoryItemGroup("Drone - Flycam", R.drawable.drone_va_flycam));
////        cong_nghe.add(new CategoryItemGroup("Phụ kiện điện thoại", R.drawable.phu_kien_dien_thoai));
////        cong_nghe.add(new CategoryItemGroup("Ổ cứng - Ram", R.drawable.o_cung_ram));
//
////        Thêm chi tiết nhóm hàng Hàng tiêu dùng - Thực phẩm
//        hang_tieu_dung_thuc_pham = new ArrayList<>();
////        GetDanhMucTask taskhangtieudungthucpham = new GetDanhMucTask();
////        taskhangtieudungthucpham.execute("hangtieudungthucpham");
//
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Đồ hộp", R.drawable.do_hop));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Gia vị", R.drawable.gia_vi));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Thực phẩm ăn liền", R.drawable.thuc_pham_an_lien));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Thực phẩm chay", R.drawable.thuc_pham_chay));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Thịt - Cá", R.drawable.thit_ca));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Trứng", R.drawable.trung));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Bánh - Kẹo", R.drawable.banh_keo));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Bia - Rượu - Nước ngọt", R.drawable.bia_ruou_nuoc_ngot));
////        hang_tieu_dung_thuc_pham.add(new CategoryItemGroup("Sữa - Trà - Cà phê", R.drawable.sua_tra_cafe));
//
////        Thêm chi tiết cho nhóm hàng Thời trang
//        thoi_trang = new ArrayList<>();
////        GetDanhMucTask taskthoitrang = new GetDanhMucTask();
////        taskthoitrang.execute("thoitrang");
//
////        thoi_trang.add(new CategoryItemGroup("Thời trang nam", R.drawable.thoi_trang_nam));
////        thoi_trang.add(new CategoryItemGroup("Thời trang nữ", R.drawable.thoi_trang_nu));
////        thoi_trang.add(new CategoryItemGroup("Thời trang trẻ em", R.drawable.thoi_trang_tre_em));
////        thoi_trang.add(new CategoryItemGroup("Thời trang lớn tuổi", R.drawable.thoi_trang_lon_tuoi));
////        thoi_trang.add(new CategoryItemGroup("Thời trang công sở", R.drawable.thoi_trang_cong_so));
////        thoi_trang.add(new CategoryItemGroup("Giày - Dép", R.drawable.giay_dep));
////        thoi_trang.add(new CategoryItemGroup("Balo - Túi xách", R.drawable.balo_tui_xach));
////        thoi_trang.add(new CategoryItemGroup("Giày thể thao", R.drawable.giay_the_thao));
//
////        Thêm chi tiết cho nhóm hàng Công việc
//        cong_viec = new ArrayList<>();
////        GetDanhMucTask taskcongviec = new GetDanhMucTask();
////        taskcongviec.execute("congviec");
//
////        cong_viec.add(new CategoryItemGroup("Văn phòng phẩm", R.drawable.van_phong_pham));
////        cong_viec.add(new CategoryItemGroup("Đồ bảo hộ", R.drawable.do_bao_ho));
////        cong_viec.add(new CategoryItemGroup("Máy khoan - Máy cắt", R.drawable.may_khoan_may_cat));
////        cong_viec.add(new CategoryItemGroup("Laptop văn phòng", R.drawable.laptop_van_phong));
////        cong_viec.add(new CategoryItemGroup("Máy in - Photocopy", R.drawable.may_in_photocopy));
////        cong_viec.add(new CategoryItemGroup("Máy cắt cỏ - Máy phun thuốc", R.drawable.may_cat_co_may_phun_thuoc));
////        cong_viec.add(new CategoryItemGroup("Thuốc trừ Sâu - Thuốc trừ Cỏ", R.drawable.thuoc_tru_sau));
////        cong_viec.add(new CategoryItemGroup("Phân bón", R.drawable.phan_bon));
//
////        Thêm chi tiết cho nhóm hàng Giải trí
//        giai_tri = new ArrayList<>();
////        GetDanhMucTask taskgiaitri = new GetDanhMucTask();
////        taskgiaitri.execute("giaitri");
//
////        giai_tri.add(new CategoryItemGroup("Laptop Gamming", R.drawable.laptop_gamming));
////        giai_tri.add(new CategoryItemGroup("PC Gamming", R.drawable.pc_gamming));
////        giai_tri.add(new CategoryItemGroup("Bàn phím cơ", R.drawable.ban_phim_co));
////        giai_tri.add(new CategoryItemGroup("Chuột Gamming", R.drawable.chuot_gamming));
////        giai_tri.add(new CategoryItemGroup("Tai nghe", R.drawable.tai_nghe_gamming));
////        giai_tri.add(new CategoryItemGroup("Play Station - Xbox", R.drawable.play_station_xbox));
////        giai_tri.add(new CategoryItemGroup("Vợt cầu lông - Vợt tennis", R.drawable.vot_cau_long_tennis));
////        giai_tri.add(new CategoryItemGroup("Bóng đá - Bóng chuyền", R.drawable.bong_da_bong_chuyen));
////        giai_tri.add(new CategoryItemGroup("Board Games", R.drawable.board_game));
//
//
//
//
//        View view = inflater.inflate(R.layout.fragment_category, container, false);
//
//        RecyclerView giaDinhNhaCuaRecyclerView = view.findViewById(R.id.recycler_view_giadinh_nhacua);
//        RecyclerView congNgheRecyclerView = view.findViewById(R.id.recycler_view_congnghe);
//        RecyclerView hangTieuDungRecyclerView = view.findViewById(R.id.recycler_view_hangtieudung_thucpham);
//        RecyclerView thoiTrangRecyclerView = view.findViewById(R.id.recycler_view_thoitrang);
//        RecyclerView congViecRecyclerView = view.findViewById(R.id.recycler_view_congviec);
//        RecyclerView giaiTriRecyclerView = view.findViewById(R.id.recycler_view_giaitri);
//
//        gia_dinh_nha_cua_Adapter = new CategoryItemGroupAdapter2(getActivity(), gia_dinh_nha_cua);
//   //      cong_nghe_Adapter = new CategoryItemGroupAdapter2(getActivity(), cong_nghe);
////         hangTieuDungAdapter = new CategoryItemGroupAdapter2(getActivity(), hang_tieu_dung_thuc_pham);
////         thoiTrangAdapter = new CategoryItemGroupAdapter2(getActivity(), thoi_trang);
////         congViecAdapter = new CategoryItemGroupAdapter2(getActivity(), cong_viec);
////         giaiTriAdapter = new CategoryItemGroupAdapter2(getActivity(), giai_tri);
//
//
//        LinearLayoutManager giaDinhNhaCuaLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager congNgheLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager hangTieuDungLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager thoiTrangLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager congViecLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager giaiTriLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//
//
//        giaDinhNhaCuaRecyclerView.setLayoutManager(giaDinhNhaCuaLayoutManager);
//        congNgheRecyclerView.setLayoutManager(congNgheLayoutManager);
//        hangTieuDungRecyclerView.setLayoutManager(hangTieuDungLayoutManager);
//        thoiTrangRecyclerView.setLayoutManager(thoiTrangLayoutManager);
//        congViecRecyclerView.setLayoutManager(congViecLayoutManager);
//        giaiTriRecyclerView.setLayoutManager(giaiTriLayoutManager);
//
//
//        giaDinhNhaCuaRecyclerView.setAdapter(gia_dinh_nha_cua_Adapter);
//    //    congNgheRecyclerView.setAdapter(cong_nghe_Adapter);
////        hangTieuDungRecyclerView.setAdapter(hangTieuDungAdapter);
////        thoiTrangRecyclerView.setAdapter(thoiTrangAdapter);
////        congViecRecyclerView.setAdapter(congViecAdapter);
////        giaiTriRecyclerView.setAdapter(giaiTriAdapter);
//
//        return view;
//    }
//
//    class GetDanhMucTask extends AsyncTask<String, Void, ArrayList<DanhMuc>> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<DanhMuc> danhMucs) {
//            super.onPostExecute(danhMucs);
//            if(danhMucs != null){
////                String maNhomDM = danhMucs.get(0).getMaNhomDM();
////                if(maNhomDM.equals("giadinhnhacua")){
//                    gia_dinh_nha_cua.clear();
//                    gia_dinh_nha_cua.addAll(danhMucs);
//                    gia_dinh_nha_cua_Adapter.notifyDataSetChanged();
//            //    }
////                else if(maNhomDM.equals("congnghe")){
////                    cong_nghe.clear();
////                    cong_nghe.addAll(danhMucs);
////                    cong_nghe_Adapter.notifyDataSetChanged();
////                }
////                else if(maNhomDM.equals("hangtieudungthucpham")){
////                    hang_tieu_dung_thuc_pham.clear();
////                    hang_tieu_dung_thuc_pham.addAll(danhMucs);
////                    hangTieuDungAdapter.notifyDataSetChanged();
////                }
////                else if(maNhomDM.equals("thoitrang")){
////                    thoi_trang.clear();
////                    thoi_trang.addAll(danhMucs);
////                    thoiTrangAdapter.notifyDataSetChanged();
////                }
////                else if(maNhomDM.equals("congviec")){
////                    cong_viec.clear();
////                    cong_viec.addAll(danhMucs);
////                    congViecAdapter.notifyDataSetChanged();
////                }
////                else if(maNhomDM.equals("giaitri")){
////                    giai_tri.clear();
////                    giai_tri.addAll(danhMucs);
////                    giaiTriAdapter.notifyDataSetChanged();
////                }
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected ArrayList<DanhMuc> doInBackground(String... strings) {
//            ArrayList<DanhMuc> danhMucs = new ArrayList<>();
//            try{
//                JSONArray jsonArray = Webserviecs.getJsonArray("api/danhmucs?manhomdm="+strings[0]);
//                for(int i = 0; i < jsonArray.length(); i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                   // String maNhomDM = jsonObject.getString("maNhomDM");
//                    String maDanhMuc = jsonObject.getString("_id");
//                    String tenDanhMuc = jsonObject.getString("tenDM");
//                    String hinh = jsonObject.getString("hinh");
//                    DanhMuc dm = new DanhMuc(maDanhMuc,tenDanhMuc,hinh);
//                    danhMucs.add(dm);
//                }
//                return danhMucs;
//            }catch (Exception ex){
//                Log.e("Loi:",ex.toString());
//            }
//            return null;
//        }
//    }
//}

