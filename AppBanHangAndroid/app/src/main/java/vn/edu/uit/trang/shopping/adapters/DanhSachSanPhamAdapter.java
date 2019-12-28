package vn.edu.uit.trang.shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.uit.trang.shopping.Activities.ChiTietSanPhamActivity;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.SanPham;
import vn.edu.uit.trang.shopping.until.ImageLoadTask;

public class DanhSachSanPhamAdapter extends RecyclerView.Adapter<DanhSachSanPhamAdapter.MyViewHolder> {

    private Context context;
    private List<SanPham> data;

    public DanhSachSanPhamAdapter(Context context, List<SanPham> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.danh_sach_san_pham_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final SanPham sp = data.get(position);

        DecimalFormat dcf = new DecimalFormat("###,###,###");

        holder.txtTenSanPham.setText(sp.getTenSanPham());
        holder.txtGiaSanPham.setText(dcf.format(sp.getGia()) + "đ");
        holder.txtGiaCu.setText(dcf.format(sp.getGiaCu()) + "đ");
        holder.txtGiaCu.setPaintFlags(holder.txtGiaCu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtGiamGia.setText("- " + dcf.format(sp.getGiamGia()) + "%");
        new ImageLoadTask(sp.getHinh(), holder.img).execute();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("sanpham", sp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtTenSanPham, txtGiaSanPham, txtGiaCu, txtGiamGia;
        ImageView img;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenSanPham = itemView.findViewById(R.id.txtItemTenSanPham);
            txtGiaSanPham = itemView.findViewById(R.id.txtItemGiaSanPham);
            txtGiaCu = itemView.findViewById(R.id.txtItemGiaCu);
            txtGiamGia = itemView.findViewById(R.id.txtItemGiamGia);
            cardView = itemView.findViewById(R.id.cardview_danhsachsanpham);
            img = itemView.findViewById(R.id.imgItemSanPham);
        }
    }


}
