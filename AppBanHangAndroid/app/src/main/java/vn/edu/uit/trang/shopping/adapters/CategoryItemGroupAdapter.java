package vn.edu.uit.trang.shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.CategoryItemGroup;
import vn.edu.uit.trang.shopping.Activities.DanhSachSanPhamActivity;

public class CategoryItemGroupAdapter extends RecyclerView.Adapter<CategoryItemGroupAdapter.MyViewHolder> {

    private Context context;
    private List<CategoryItemGroup> data;

    public CategoryItemGroupAdapter(Context context, List<CategoryItemGroup> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.category_fragment_item_group_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_image_title.setText(data.get(position).getTitle());
        holder.img_image.setImageResource(data.get(position).getImage());

//        TODO Tạo sự kiện nhấn cho các nhóm hàng trong category fragment
//        Chuyển đến màn hình danh sách các mặt hàng
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachSanPhamActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_image_title;
        ImageView img_image;
        LinearLayout linearLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_image_title = itemView.findViewById(R.id.item_title);
            img_image = itemView.findViewById(R.id.item_image);
            linearLayout = itemView.findViewById(R.id.container);

        }
    }
}
