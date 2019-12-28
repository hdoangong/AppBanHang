package vn.edu.uit.trang.shopping.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.uit.trang.shopping.R;
import vn.edu.uit.trang.shopping.adapters.ShoppingCartItemDetailAdapter;
import vn.edu.uit.trang.shopping.models.ShoppingCartItemDetail;
import vn.edu.uit.trang.shopping.until.UserPresent;
import vn.edu.uit.trang.shopping.until.Webserviecs;

public class ShoppingCartActivity extends AppCompatActivity {

    List<ShoppingCartItemDetail> shoppingCartItemList;
    ShoppingCartItemDetailAdapter shoppingCartItemDetailAdapter;
    LinearLayout shoppingCartLayout;
    TextView oderButton;
    TextView oldPrice;
    TextView totalPrice;
    ConstraintLayout emptyCart;
    TextView emptyText;
    TextView continueButton;

    final DecimalFormat dcf = new DecimalFormat( "#,###,###,###" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shoppingCartLayout = this.findViewById(R.id.shopping_cart_layout);
        oldPrice = this.findViewById(R.id.old_price);
        totalPrice = this.findViewById(R.id.total_price);
        emptyText = this.findViewById(R.id.empty_text);
        continueButton = this.findViewById(R.id.continue_button);

//        TODO Thiết lập thanh action bar, thay đổi chức năng nút close
        getSupportActionBar().setTitle(R.string.shopping_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_action);

        emptyCart = this.findViewById(R.id.empty_shopping_cart_constraintlayout);

        shoppingCartItemList = new ArrayList<>();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(UserPresent.user_id == null)
                    intent = new Intent(ShoppingCartActivity.this, LoginActivity.class);
                else
                    intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        if(UserPresent.user_id == null){
            emptyText.setText("Bạn chưa đăng nhập, vui lòng đăng nhập");
            continueButton.setText(R.string.login);
            emptyCart.setVisibility(View.VISIBLE);
        }

        else {
            LoadGioHangTask task = new LoadGioHangTask();
            task.execute("?user_id=" + UserPresent.user_id);
        }
//      Danh sách các mặt hàng trong giỏ hàng

        shoppingCartItemDetailAdapter = new ShoppingCartItemDetailAdapter(this, shoppingCartItemList, new MyAdapterListener() {
            @Override
            public void btnDeleteClick(View v, int position) {
                final ShoppingCartItemDetail item = shoppingCartItemDetailAdapter.getData().get(position);
                final int pos = position;
                shoppingCartItemDetailAdapter.remove(position);
                Snackbar snackbar = Snackbar
                        .make(shoppingCartLayout, "Sản phẩm sẽ bị xoá khỏi giỏ hàng", Snackbar.LENGTH_LONG);
                snackbar.setAction("Hoàn lại", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shoppingCartItemDetailAdapter.restore(item, pos);
                    }
                }).addCallback(new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                        if(event != DISMISS_EVENT_ACTION){
                            DeleteGioHangTask task = new DeleteGioHangTask();
                            task.execute(item.getId());
                        }
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
                Toast.makeText(ShoppingCartActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onIncAndDesClick(int position, int n) {
                ShoppingCartItemDetail item = shoppingCartItemDetailAdapter.getData().get(position);
                try{
                    StringWriter output = new StringWriter();
                    JsonWriter jsonWriter = new JsonWriter(output);
                    jsonWriter.beginObject();// begin root
                    jsonWriter.name("_id").value(item.getId());
                    jsonWriter.name("soLuong").value(n);
                    jsonWriter.endObject();
                    item.setNumberOfItem(item.getNumberOfItem() + n);
                    UpdateGioHangTask task = new UpdateGioHangTask();
                    task.execute(output.toString());
                } catch (Exception ex){
                    Log.e("Loi_LoginActivity: ", ex.toString());
                }
            }
        });

        RecyclerView itemListRecyclerView = this.findViewById(R.id.item_list);
        LinearLayoutManager itemListLayoutManager = new LinearLayoutManager(this);
        itemListRecyclerView.setLayoutManager(itemListLayoutManager);
        itemListRecyclerView.setAdapter(shoppingCartItemDetailAdapter);

//      TODO tạo sự kiện nhấn cho nút TIẾN HÀNH ĐẶT HÀNG
//      Chuyển đến màn hình thông tin đặt hàng
        oderButton = this.findViewById(R.id.oder_button);
        oderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Chuyển đến màn hình đặt hàng", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShoppingCartActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        //load danh sach trong gio hang

    }

    private void LoadPrice(){
        double giaTien = 0;
        for(ShoppingCartItemDetail item : shoppingCartItemDetailAdapter.getData()){
            giaTien += item.getPrice() * item.getNumberOfItem();
        }
        totalPrice.setText(dcf.format(giaTien) + "đ");
        oldPrice.setText(dcf.format(giaTien) + "đ");
    }

    class UpdateGioHangTask extends AsyncTask<String, Void, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue() == true){
                LoadPrice();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                return Webserviecs.putAPI("api/giohangs/update", strings[0]);
            }catch (Exception ex){
                Log.e("Loi ShoppingCartActivity put api: ", ex.toString());
            }
            return null;
        }
    }

    final class DeleteGioHangTask extends AsyncTask<String, Void, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue() == true){
                LoadPrice();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                return Webserviecs.deleteAPI("api/giohangs/delete/" + strings[0] );
            }catch (Exception ex){
                Log.e("Loi delete api: ", ex.toString());
            }
            return null;
        }
    }

    class LoadGioHangTask extends AsyncTask<String, Void, ArrayList<ShoppingCartItemDetail>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<ShoppingCartItemDetail> shoppingCartItemDetails) {
            super.onPostExecute(shoppingCartItemDetails);
            if(shoppingCartItemDetails != null) {
                if(shoppingCartItemDetails.size() > 0){
                    emptyCart.setVisibility(View.INVISIBLE);
                } else {
                    emptyText.setText(R.string.shopping_cart_is_empty);
                }

                shoppingCartItemList.clear();
                shoppingCartItemList.addAll(shoppingCartItemDetails);
                shoppingCartItemDetailAdapter.notifyDataSetChanged();
                LoadPrice();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<ShoppingCartItemDetail> doInBackground(String... strings) {
            ArrayList<ShoppingCartItemDetail> data = new ArrayList<>();
            try{
                JSONArray jsonArray = Webserviecs.getJsonArray("api/giohangs" + strings[0]);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String gioHangId = jsonObject.getString("_id");
                    String tenSP = jsonObject.getString("tenSP");
                    double thanhTien = Double.parseDouble(jsonObject.getString("thanhTien"));
                    int soLuong = jsonObject.getInt("soLuong");
                    String hinh = jsonObject.getString("hinh");
                    ShoppingCartItemDetail item = new ShoppingCartItemDetail(gioHangId, tenSP, "uit thsn", hinh, thanhTien, soLuong);
                    data.add(item);
                }
                return data;
            }catch (Exception ex){
                Log.e("Loi: ", ex.toString());
            }
            return null;
        }
    }
}
