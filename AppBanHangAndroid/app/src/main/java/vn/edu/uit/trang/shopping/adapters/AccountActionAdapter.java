package vn.edu.uit.trang.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.models.AccountActionListRow;

public class AccountActionAdapter extends RecyclerView.Adapter<AccountActionAdapter.MyViewHolder> {

    private Context context;
    private List<AccountActionListRow> data;


    public AccountActionAdapter(Context context, List<AccountActionListRow> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.account_fragment_action, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

//        Gán các giá trị cho holder
        holder.title.setText(data.get(position).getTitle());
        holder.icon.setImageResource(data.get(position).getIcon());

//        TODO Tạo sự kiện nhấn cho các chức năng của tài khoản
//        Nếu chưa đăng nhập thì hiển thị màn hình Đăng nhập
//        Nếu đã đăng nhập thì hiển thị Toast thông báo chức năng đã được nhấn
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        ConstraintLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.action_title);
            icon = itemView.findViewById(R.id.action_icon);
            container = itemView.findViewById(R.id.container);
        }
    }
}