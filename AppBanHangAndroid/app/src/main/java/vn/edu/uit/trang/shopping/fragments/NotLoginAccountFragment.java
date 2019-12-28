package vn.edu.uit.trang.shopping.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.uit.trang.shopping.Activities.LoginActivity;
import vn.edu.uit.trang.shopping.Activities.SignupActivity;
import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.adapters.AccountActionAdapter;
import vn.edu.uit.trang.shopping.models.AccountActionListRow;

public class NotLoginAccountFragment extends Fragment {


    //    Danh sách các hành động của tài khoản
    List<AccountActionListRow> actionListRows;
    List<AccountActionListRow> settingListRows;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_not_login_account, container, false);

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

        settingListRows = new ArrayList<>();
        settingListRows.add(new AccountActionListRow(R.drawable.call_icon, "HOT LINE: 1800-0081 (Miễn phí)"));
        settingListRows.add(new AccountActionListRow(R.drawable.help_icon, "Hỗ trợ"));
        settingListRows.add(new AccountActionListRow(R.drawable.setting_icon, "Cài đặt"));


        RecyclerView actionsRecyclerView = view.findViewById(R.id.not_log_in_account_actions_recyclerview);
        RecyclerView settingRecyclerView = view.findViewById(R.id.not_log_in_account_setting_recyclerview);

        AccountActionAdapter actionAdapter = new AccountActionAdapter(getActivity(), actionListRows);
        AccountActionAdapter settingAdapter = new AccountActionAdapter(getActivity(), settingListRows);


        LinearLayoutManager actionLayoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager settingLayoutManager = new LinearLayoutManager(getActivity());

        actionsRecyclerView.setLayoutManager(actionLayoutManager);
        actionsRecyclerView.setAdapter(actionAdapter);

        settingRecyclerView.setLayoutManager(settingLayoutManager);
        settingRecyclerView.setAdapter(settingAdapter);


        TextView signUp = view.findViewById(R.id.sign_up_button);
        TextView logIn = view.findViewById(R.id.login_button);

//        TODO thiết lập sự kiện nhấn cho nút đăng ký
//        Chuyển đến màn hình đăng ký
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SignupActivity.class);
                view.getContext().startActivity(intent);
            }
        });

//        TODO thiết lập sự kiện nhấn cho nút đăng nhập
//        Chuyển đến màn hình đăng nhập
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);
            }
        });


        return view;
    }
}
