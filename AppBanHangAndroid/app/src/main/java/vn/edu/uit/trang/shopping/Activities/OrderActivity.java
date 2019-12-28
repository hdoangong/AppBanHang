package vn.edu.uit.trang.shopping.Activities;

import androidx.appcompat.app.AppCompatActivity;
import vn.edu.uit.trang.shopping.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setTitle("Thông tin giao hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView btnContinue = findViewById(R.id.continue_button);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderConfirmActivity.class);
                startActivity(intent);
            }
        });
        TextView btnChange = findViewById(R.id.change_button);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, AddressActivity.class);
                startActivity(intent);
            }
        });
    }
}
