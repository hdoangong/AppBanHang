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
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import vn.edu.uit.trang.shopping.Activities.ChiTietSanPhamActivity;
import vn.edu.uit.trang.shopping.Activities.MyAdapterListener;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.ShoppingCartItemDetail;
import vn.edu.uit.trang.shopping.until.ImageLoadTask;

public class ShoppingCartItemDetailAdapter extends RecyclerView.Adapter<ShoppingCartItemDetailAdapter.MyViewHolder> {

    private Context context;
    private List<ShoppingCartItemDetail> data;
    public MyAdapterListener onClickListener;

    public ShoppingCartItemDetailAdapter(Context context, List<ShoppingCartItemDetail> data, MyAdapterListener onClickListener) {
        this.context = context;
        this.data = data;
        this.onClickListener = onClickListener;
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void restore(ShoppingCartItemDetail item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public List<ShoppingCartItemDetail> getData() {
        return data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.shopping_cart_item_detail, parent, false);

        return new MyViewHolder(view);
    }

    private void changeActivity(String id){
        Intent intent = new Intent(this.context, ChiTietSanPhamActivity.class);
        intent.putExtra("chitietsanpham", id);
        this.context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final ShoppingCartItemDetail item = data.get(position);
//        Gán các giá trị cho holder
        holder.name.setText(item.getName());
        DecimalFormat dcf = new DecimalFormat( "#,###,###,###" );
        holder.price.setText(dcf.format(item.getPrice()) + " đ");
        holder.seller.setText("Cung cấp bởi " + item.getSeller());
        holder.numberOfItem.setText(item.getNumberOfItem() + "");
        (new ImageLoadTask(item.getImage(),holder.image)).execute();

//      TODO Tạo sự kiện nhấn cho sản phẩm trong giỏ hàng (Nhấn vào hình hoặc tiêu đề)
//       Chuyển đến màn hình chi tiết mặt hàng

//        Khi nhấn vào hình ảnh mặt hàng
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(item.getId());
            }
        });
//        Khi nhấn vào tiêu đề sản phẩm
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(item.getId());
            }
        });

//        TODO Tạo sự kiện nhấn cho nút delete
//        Xóa mặt hàng trong danh sách
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Xóa sản phẩm", Toast.LENGTH_SHORT).show();
                onClickListener.btnDeleteClick(v, position);
            }
        });

//        TODO Tạo sự kiện nhấn cho nút Thêm và Bớt mặt hàng trong giỏ hàng
        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getNumberOfItem()<=1)
                {
                    return;
                }
                onClickListener.onIncAndDesClick(position, -1);
                holder.numberOfItem.setText(item.getNumberOfItem() + "");
            }
        });
        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getNumberOfItem()>10)
                {
                    Toast.makeText(context, "Số lượng không thể vượt quá 10", Toast.LENGTH_SHORT).show();
                    return;
                }
                onClickListener.onIncAndDesClick(position, 1);
                holder.numberOfItem.setText(item.getNumberOfItem() + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView price;
        TextView seller;
        TextView deleteButton;
        ImageView decreaseButton;
        ImageView increaseButton;
        TextView numberOfItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_detail_name);
            image = itemView.findViewById(R.id.item_image);
            price = itemView.findViewById(R.id.item_detail_new_price);
            seller = itemView.findViewById(R.id.item_detail_seller);
            deleteButton = itemView.findViewById(R.id.delete_button);
            decreaseButton = itemView.findViewById(R.id.decrease_button);
            increaseButton = itemView.findViewById(R.id.increase_button);
            numberOfItem = itemView.findViewById(R.id.number_of_item);
        }
    }
}
