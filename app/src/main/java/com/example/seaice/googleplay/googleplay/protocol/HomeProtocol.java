package com.example.seaice.googleplay.googleplay.protocol;

import android.database.CursorJoiner;
import android.util.Log;

import com.example.seaice.googleplay.googleplay.bean.AppInfo;
import com.example.seaice.googleplay.googleplay.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by seaice on 2016/8/4.
 */
public class HomeProtocol {

    private static final String HOME_URL = HttpUtils.APP_URL + "home?index=";

    /**
     * 1.先从本地获取json数据
     * 2.如果为空，再从服务器上获取
     * 3.若获取到json,保存到本地
     * 4.获取到json不为空，解析json数据
     */
    public void load(int index) {
        String json = LoadLocal();
        if (json == null) {
            json = loadServer(index);
            if (json != null) {
                saveLocal(json, index);//把json保存到本地
            }
        }
        if (json != null) {
            paserJson(json);
        }
    }

    /**
     * 解析json
     * 遇到大括号用JsonObject,遇到中括号用JsonArray
     * @param json
     */
    private List<AppInfo> paserJson(String json) {
        List<AppInfo> appInfos = new ArrayList<AppInfo>();
        try {
            JSONObject jsonObject =  new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                long id = object.getLong("id");
                String name = object.getString("name");
                String pkgName = object.getString("packageName");
                String iconUrl = object.getString("iconUrl");
                float star = Float.parseFloat(object.getString("star"));
                long size = object.getLong("size");
                String downloadUrl = object.getString("downloadUrl");
                String des = object.getString("des");
                AppInfo appInfo = new AppInfo(id, name, pkgName, iconUrl, star,
                        size,downloadUrl,des);
                appInfos.add(appInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("haibing", appInfos.toString());
        return appInfos;
    }

    //1.把整个json文件写到一个本地文件中
    //2.把每条数据摘出来保存到数据库中
    private void saveLocal(String json, int index) {

    }

    private String LoadLocal() {
        return null;
    }

    private String loadServer(int index) {

        HttpURLConnection conn = null;
        try {
            URL url = new URL(HOME_URL + index);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            int code = conn.getResponseCode();
            Log.e("home", "code="+code);
            if(code == 200){
                InputStream input = conn.getInputStream();
                String result = readFromSteam(input);
                Log.e("home", result);
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("home", "MalformedURLException");
        } catch (ConnectException e) {
            e.printStackTrace();
            Log.e("home", "ConnectException");
        } catch (SocketException e){
            e.printStackTrace();
            Log.e("home", "SocketException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("home", "IOException");
        } finally {
            conn.disconnect();
        }
        return null;

        //同步请求,http://127.0.0.1:8090/home?index=0
//        Request request = new Request.Builder().url(HOME_URL + index).build();
//        Response response = null;
//        try {
//            response = HttpUtils.execute(request);
//            if (response.isSuccessful()) {
//                InputStream inputStream = response.body().byteStream();
//                String json = readFromSteam(inputStream);
//                Log.e("LoadServer1", json.toString());
//                Log.e("LoadServer2", response.toString());
//                Log.e("LoadServer3", response.message());
//                Log.e("LoadServer4", response.body().toString());
//                return json;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    /**
     * 从指定输入流中获取数组，并返回String
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readFromSteam(InputStream inputStream) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int len = 0;

        while((len = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        byte[] data = out.toByteArray();
        String str = new String(data, "utf-8");

        inputStream.close();
        out.close();

        return str;
    }
}
