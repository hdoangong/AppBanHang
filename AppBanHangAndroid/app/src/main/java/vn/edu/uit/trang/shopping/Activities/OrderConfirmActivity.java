package vn.edu.uit.trang.shopping.Activities;

import androidx.appcompat.app.AppCompatActivity;
import vn.edu.uit.trang.shopping.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);


        getSupportActionBar().setTitle("Xác nhận thông tin giao hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView btnComfirm = findViewById(R.id.comfirmbtn);
        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đã đặt hàng thành công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OrderConfirmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
