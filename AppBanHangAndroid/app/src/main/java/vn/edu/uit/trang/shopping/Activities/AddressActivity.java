package vn.edu.uit.trang.shopping.Activities;

import androidx.appcompat.app.AppCompatActivity;
import vn.edu.uit.trang.shopping.R;

import android.os.Bundle;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        getSupportActionBar().setTitle("Nhập địa chỉ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
