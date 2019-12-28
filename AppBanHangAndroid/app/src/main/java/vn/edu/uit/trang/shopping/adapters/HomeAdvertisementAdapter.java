package vn.edu.uit.trang.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.HomeAdvertisement;

public class HomeAdvertisementAdapter extends RecyclerView.Adapter<HomeAdvertisementAdapter.MyViewHolder>{

    private Context context;
    private List<HomeAdvertisement> data;

    public HomeAdvertisementAdapter(Context context, List<HomeAdvertisement> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.home_fragment_advertisement_banner, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.banner.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getTitle());

//        TODO tạo sự kiện nhấn cho khung quảng cáo
//        Chuyển đến màn hình chi tiết mặt hàng
        holder.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Quảng cáo", Toast.LENGTH_SHORT).show();
            }
        });

//        TODO Tạo sự kiện nhấn cho nút BUY NOW
//        Thêm mặt hàng vào giỏ hàng
        holder.buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "MUA NGAY", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView banner;
        TextView title;
        Button buyNowButton;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.home_advertisement_banner_title_id);
            banner = itemView.findViewById(R.id.home_advertisement_banner_image_id);
            buyNowButton = itemView.findViewById(R.id.home_advertisement_banner_button_id);
        }

    }

}


