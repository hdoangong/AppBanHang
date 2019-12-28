package vn.edu.uit.trang.shopping.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.adapters.AccountActionAdapter;
import vn.edu.uit.trang.shopping.models.AccountActionListRow;
import vn.edu.uit.trang.shopping.until.UserPresent;

public class LoggedInAccountFragment extends Fragment {


    //    Danh sách các hành động và cài đặt
    List<AccountActionListRow> actionListRows;
    List<AccountActionListRow> settingListRows;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_logged_in_account, container, false);

        final TextView logout_button = view.findViewById(R.id.logout_btn);

        ImageView avatar = view.findViewById(R.id.avatar);
        TextView name = view.findViewById(R.id.logged_in_account_name);
        TextView mail = view.findViewById(R.id.logged_in_account_mail);
        TextView phone = view.findViewById(R.id.logged_in_account_phone);

//        TODO truyền thông tin người dùng để hiển thị
        avatar.setImageResource(R.drawable.avatar_img);
        name.setText("Hoàng Thùy Trang");
        mail.setText("tranght.uit@gmail.com");
        phone.setText("0352902224");


//        Danh sách các hành động dành cho tài khoản
        actionListRows = new ArrayList<>();
        actionListRows.add(new AccountActionListRow(R.drawable.cart_action_icon, "Xem giỏ hàng"));
        actionListRows.add(new AccountActionListRow(R.drawable.recept_action_icon, "Quản lý đơn hàng"));
        actionListRows.add(new AccountActionListRow(R.drawable.bought_action_icon, "Sản phẩm đã mua"));
        actionListRows.add(new AccountActionListRow(R.drawable.watched_action_icon, "Sản phẩm đã xem"));
        actionListRows.add(new AccountActionListRow(R.drawable.love_action_icon, "Sản phẩm yêu thích"));
        actionListRows.add(new AccountActionListRow(R.drawable.mark_action_icon, "Sản phẩm mua sau"));
        actionListRows.add(new AccountActionListRow(R.drawable.comment_action_icon, "Nhận xét của tôi"));
        actionListRows.add(new AccountActionListRow(R.drawable.offer_action_icon, "Ưu đãi của tôi"));
        actionListRows.add(new AccountActionListRow(R.drawable.bubble_icon, "Một chức năng nào đó"));

//        Danh sách các cài đặt
        settingListRows = new ArrayList<>();
        settingListRows.add(new AccountActionListRow(R.drawable.call_icon, "HOT LINE: 1800-0081 (Miễn phí)"));
        settingListRows.add(new AccountActionListRow(R.drawable.help_icon, "Hỗ trợ"));
        settingListRows.add(new AccountActionListRow(R.drawable.setting_icon, "Cài đặt"));


        RecyclerView actionsRecyclerView = view.findViewById(R.id.logged_in_account_actions_recyclerview);
        RecyclerView settingRecyclerView = view.findViewById(R.id.logged_in_account_setting_recyclerview);

        AccountActionAdapter actionAdapter = new AccountActionAdapter(getActivity(), actionListRows);
        AccountActionAdapter settingAdapter = new AccountActionAdapter(getActivity(), settingListRows);


        LinearLayoutManager actionLayoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager settingLayoutManager = new LinearLayoutManager(getActivity());

        actionsRecyclerView.setLayoutManager(actionLayoutManager);
        actionsRecyclerView.setAdapter(actionAdapter);

        settingRecyclerView.setLayoutManager(settingLayoutManager);
        settingRecyclerView.setAdapter(settingAdapter);

//        TODO thiết lập sự kiện nhấn cho nút đăng xuất
//        Chuyển đếm màn hình Not_log_in_account_fragment
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPresent.user_id = null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new NotLoginAccountFragment());
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
