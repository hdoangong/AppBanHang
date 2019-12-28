package vn.edu.uit.trang.shopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import vn.edu.uit.trang.shopping.Activities.ChiTietSanPhamActivity;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.HomeDeal;
import vn.edu.uit.trang.shopping.models.SanPham;
import vn.edu.uit.trang.shopping.until.ImageLoadTask;

public class HomeDealAdapter extends RecyclerView.Adapter<HomeDealAdapter.MyViewHolder> {

    private Context context;
    private List<SanPham> data;


    public HomeDealAdapter(Context context, List<SanPham> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.home_fragment_deal_item, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        String newPrice =  dcf.format(data.get(position).getGia()) + " đ";
        SpannableString oldPrice = new SpannableString( dcf.format(data.get(position).getGiaCu()) + " đ");
//        Tính số phần trăm được giảm giá và chuyển về String
        String discountPercent = "-" + dcf.format(data.get(position).getGiamGia()) + "%";

//        Tạo gạch ngang cho giá cũ
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        oldPrice.setSpan(strikethroughSpan, 0, oldPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//        Gán các giá trị cho holder
        holder.title.setText(data.get(position).getTenSanPham());
        (new ImageLoadTask(data.get(position).getHinh(), holder.image)).execute();
        holder.newPrice.setText(newPrice);
        holder.oldPrice.setText(oldPrice);
        holder.discountPercent.setText(discountPercent);

//        TODO Tạo sự kiện nhấn cho sản phẩm
//        Chuyển đến màn hình chi tiết mặt hàng
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("sanpham", data.get(position));
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
        TextView newPrice;
        TextView oldPrice;
        TextView discountPercent;
        CardView cardView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.home_deal_item_title_id);
            image = itemView.findViewById(R.id.home_deal_item_image_id);
            newPrice = itemView.findViewById(R.id.home_deal_item_new_price_id);
            oldPrice = itemView.findViewById(R.id.home_deal_item_old_price_id);
            discountPercent = itemView.findViewById(R.id.home_deal_item_discount_percent_id);
            cardView = itemView.findViewById(R.id.home_deal_item_card_view_id);

        }
    }
}
