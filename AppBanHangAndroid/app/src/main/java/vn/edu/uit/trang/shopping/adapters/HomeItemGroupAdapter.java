package vn.edu.uit.trang.shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.uit.trang.shopping.Activities.DanhSachSanPhamActivity;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.DanhMuc;
import vn.edu.uit.trang.shopping.models.HomeItemGroup;
import vn.edu.uit.trang.shopping.until.ImageLoadTask;

public class HomeItemGroupAdapter extends RecyclerView.Adapter<HomeItemGroupAdapter.MyViewHolder>{
    private Context context;
    private List<DanhMuc> data;

    public HomeItemGroupAdapter(Context context, List<DanhMuc> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HomeItemGroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.home_fragment_item_group_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.title.setText(data.get(position).getTenDanhMuc());
        (new ImageLoadTask(data.get(position).getHinh(),holder.image)).execute();

//        TODO tạo sự kiện nhấn cho nhóm sản phẩm dưới thanh search
//        Chuyển đến màn hình danh sách các mặt hàng
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachSanPhamActivity.class);
                intent.putExtra("danhmuc",data.get(position).getMaDanhMuc());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView image;
        CardView cardView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.home_item_group_textview);
            image = (ImageView) itemView.findViewById(R.id.home_fragment_item_group_item_imageview);
            cardView = (CardView)itemView.findViewById(R.id.home_fragment_item_group_item_container_cardview_id);
        }
    }
}
