package com.example.news;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String myResponse;
    ListView lv;
    ArrayList<News> newsData;

    private static CustomAdapter adapter;

//    ArrayList<HashMap<String,String>> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        arrayList=new ArrayList<>();
        newsData=new ArrayList<>();
        lv =findViewById(R.id.istView);

        adapter= new CustomAdapter(newsData,getApplicationContext());

        OkHttpClient client = new OkHttpClient();
        String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject reader = new JSONObject(myResponse);
                                JSONArray data = reader.getJSONArray("data"); // get the whole json array list
//                                System.out.println("json size is : "+data.length());
                                for(int i = 0;i<data.length();i++)
                                {

                                    JSONObject hero = data.getJSONObject(i);
                                    String id=hero.getString("id");
                                    int idd=Integer.parseInt(id);
                                    String url=hero.getString("url");
                                    String description = hero.getString("description");
                                    String heading = hero.getString("heading");
                                    System.out.println(i+" id: "+idd +" Description: "+description +" Heading : "+heading);
//                                    HashMap<String,String> dataa = new HashMap<>();

                                    News ne=new News(idd,url,description,heading);
                                    newsData.add(ne);



//                                    dataa.put("id",id);
//
//                                    dataa.put("url",url);
//                                    dataa.put("description",description);
//                                    dataa.put("heading",heading);
//                                    arrayList.add(dataa);
//                                    ImageView image = findViewById(R.id.image);
//                                    Picasso.with(context).load(url).into(image);
//                                    Picasso.get().load(url).into(image);

//                                    ListAdapter adapter = new SimpleAdapter(MainActivity.this,newsData,R.layout.listview_layout
//                                            ,new String[]{"id","image","description","heading"},new int[]{R.id.id,R.id.url,R.id.name,R.id.power});
//


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            lv.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }
}