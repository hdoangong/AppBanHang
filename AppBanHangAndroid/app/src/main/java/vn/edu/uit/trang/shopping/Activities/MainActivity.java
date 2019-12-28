package vn.edu.uit.trang.shopping.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.fragments.CategoryFragment;
import vn.edu.uit.trang.shopping.fragments.HomeFragment;
import vn.edu.uit.trang.shopping.fragments.LoggedInAccountFragment;
import vn.edu.uit.trang.shopping.fragments.NotLoginAccountFragment;
import vn.edu.uit.trang.shopping.fragments.SearchFragment;
import vn.edu.uit.trang.shopping.until.UserPresent;

public class MainActivity extends AppCompatActivity {

    //    Tạo biến đếm cho số sản phẩm trong giỏ hàng
    TextView itemCounter;
    int numOfItem = 0;
    MenuItem menuItem;


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

//            Gán fragment cho thanh điều hướng bên dưới
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_category:
                    selectedFragment = new CategoryFragment();
                    break;
//                case R.id.nav_search:
//                    selectedFragment = new SearchFragment();
//                    break;
                case R.id.nav_account_information:

//                    TODO Thêm điều kiện để hiển thị loại màn hình tài khoản khi bấm vào biểu tượng tài khoản dưới thanh nav
//                    Nếu tài khoản chưa đăng nhập thì hiển thị màn hình NotLoginAccountFragment
//                    Nếu tài khoản đã đăng nhập thì hiển thị màn hình LoggedInAccountFragment
                    if (UserPresent.user_id != null)
                        selectedFragment = new LoggedInAccountFragment();
                    else
                        selectedFragment = new NotLoginAccountFragment();
                    break;
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        Tạo thanh actions bar
        getMenuInflater().inflate(R.menu.main_actions, menu);

//        Xét các trường hợp cho icon giỏ hàng
//        Nếu biến đếm = 0 thì không hiển thị số sản phẩm
//        Nếu biến đếm khác 0 thì hiển thị số sản phẩm
        menuItem = menu.findItem(R.id.shopping_cart);
        if (numOfItem == 0) {
            menuItem.setActionView(null);
        } else {
            menuItem.setActionView(R.layout.shopping_cart_actionbar_icon);
            View view = menuItem.getActionView();
            itemCounter = view.findViewById(R.id.item_count);
            itemCounter.setText(String.valueOf(numOfItem));
        }
//        TODO Chưa thêm được onClickListener cho icon giỏ hàng có số đếm

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//  Tạo thanh điều hướng bên dưới cho activity
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                Toast.makeText(this, "Về ứng dụng", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(this, "Giúp đỡ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.shopping_cart:
                //    TODO Gán hành động cho việc nhấn vào giỏ hàng trên actions bar, chuyển đến màn hình giỏ hàng
//                Nếu giỏ hàng trống thì chuyển đến empty activity
//                Nếu giỏ hàng không trống thì chuyển đến giỏ hàng
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                this.startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
