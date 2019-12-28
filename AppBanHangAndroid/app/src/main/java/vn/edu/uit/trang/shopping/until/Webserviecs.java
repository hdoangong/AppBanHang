package vn.edu.uit.trang.shopping.until;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Webserviecs {
    public static String host = "https://appbanhanghtt.herokuapp.com/";
    //public static String host = "http://192.168.1.230:3001/";
    public static String token = null;

    public static Boolean login(String link, String email, String password){
        try{
            String jsonInputString = "{\"email\": \"" + email + "\", \"password\": \"" + password  + "\"}";
            //mở kết nối
            URL url = new URL(host +link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoInput(true);

            //ghi vô body
            OutputStream os = connection.getOutputStream() ;
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);

            //đọc kết quả trả về
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            br.close();
            JSONObject jsonObject = new JSONObject(builder.toString());
            if(jsonObject.has("message") && jsonObject.getString("message").equals("error"))
                return false;
            if(jsonObject.has("token")){
                token = jsonObject.getString("token");
            }
            if(jsonObject.has("user")){
                UserPresent.user_id = jsonObject.getString("user");
            }
            return true;
        }catch (Exception ex){
            Log.e("Loi:",ex.toString());
        }
        return false;
    }

    private static void mySetAuth(HttpURLConnection connection) {
        if(token != null){
            connection.setRequestProperty("authorization", "bearer " + token);
        }
    }

    public static JSONArray getJsonArray(String link){
        try{
        URL url = new URL(host +link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        mySetAuth(connection);

        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String line = null;
        while((line = br.readLine()) != null){
            builder.append(line);
        }
            br.close();
        JSONArray jsonArray =  new JSONArray(builder.toString());
        return jsonArray;
        }catch (Exception ex){
            Log.e("Loi:",ex.toString());
        }
        return null;
    }


    public static boolean postAPI(String link, String json){
        try{
            URL url = new URL(host +link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            mySetAuth(connection);

            OutputStream os = connection.getOutputStream() ;
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);

            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            String kq = builder.toString();
            br.close();
            if(kq.equals("success"))
                return true;
        }catch (Exception ex){
            Log.e("Loi:",ex.toString());
        }
        return false;
    }

    public static boolean putAPI(String link, String json){
        try{
            URL url = new URL(host +link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            mySetAuth(connection);

            OutputStream os = connection.getOutputStream() ;
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);

            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            String kq = builder.toString();
            br.close();
            if(kq.equals("success"))
                return true;
        }catch (Exception ex){
            Log.e("Loi:",ex.toString());
        }
        return false;
    }

    public static boolean deleteAPI(String link){
        try{
            URL url = new URL(host +link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestMethod("DELETE");
            mySetAuth(connection);
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            String kq = builder.toString();
            br.close();
            if(kq.equals("success"))
                return true;
        }catch (Exception ex){
            Log.e("Loi:",ex.toString());
        }
        return false;
    }
}
