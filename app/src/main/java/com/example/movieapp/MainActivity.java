package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "img";
    public static final String EXTRA_NAME = "movieName";
    public static final String EXTRA_DESCRIPTION = "movieDescription";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_RELEASE = "release";
    public static final String EXTRA_URLIMG2 = "imgUrl2";

    List<MovieModel> movieList;
    RecyclerView recyclerView;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        //button = (Button) findViewById(R.id.btn_cek);

        //ShowPopup();
        getData();

    }

    private void getData() {

        String url = "https://nad.my.id/uts.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Res : ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String movie = jsonObject1.getString("title");
                            String description = jsonObject1.getString("overview");
                            String img = jsonObject1.getString("poster_path");
                            String rating = jsonObject1.getString("vote_average");
                            String release = jsonObject1.getString("release_date");
                            float ratingbar = (float) jsonObject1.getDouble("vote_average");
                            String img2 = jsonObject1.getString("backdrop_path");

                            movieList.add(new MovieModel(movie, description, img, rating, release, ratingbar, img2));
                        }
                        MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(movieAdapter);
                        MovieAdapter.setOnItemClickListener(MainActivity.this);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailMovie.class);
        MovieModel clickItem = movieList.get(position);

        detailIntent.putExtra(EXTRA_NAME, clickItem.getMovieName());
        detailIntent.putExtra(EXTRA_DESCRIPTION, clickItem.getMovieDescription());
        detailIntent.putExtra(EXTRA_URL, clickItem.getImg());
        detailIntent.putExtra(EXTRA_RATING, clickItem.getRating());
        detailIntent.putExtra(EXTRA_RELEASE, clickItem.getRelease());
        detailIntent.putExtra(EXTRA_URLIMG2, clickItem.getImg2());

        startActivity(detailIntent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
////    public void ShowPopup(View view) {
////        dialog.setContentView(R.layout.activity_pop_up);
////    }
////
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.settingToolbar: {
//                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//                startActivity(intent);
//
//                return true;
//            }
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    public void ShowPopupAccount(View v) {

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_account);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();

        //btn_lang = (Button) dialog.findViewById(R.id.lang);
        //btn_about = (Button) dialog.findViewById(R.id.about);

//        btn_lang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//                startActivity(intent);
//            }
//        });
//
//        btn_about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, PopUp.class);
//                startActivity(intent);
//            }
//        });
    }

    public void ShowPopupSetting(View v) {
        Button btn_lang;
        Button btn_about;

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        btn_lang = (Button) dialog.findViewById(R.id.lang);
        btn_about = (Button) dialog.findViewById(R.id.about);

        btn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopUp.class);
                startActivity(intent);
            }
        });


        dialog.show();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.show();
//            }
//        });



    }
}






//            @Override
//            public void onResponse(String response) {
//
////                for (int i = 0 ; i < response.length() ; i++){
////                    try {
////                        JSONObject jsonObject1 = response.getJSONObject(i);
////
////                        String id = jsonObject1.getString("vote_average");
////                        String title = jsonObject1.getString("title");
////                        String poster = jsonObject1.getString("poster_path");
////
////                        MovieModel movieModel = new MovieModel(id, title, poster);
////
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
//                try {
//                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
//                    JSONArray jsonArray = jsonObject.getJSONArray("results");
//                    if (jsonArray.length()>0) {
//
//
//                        for (int i = 0; i < jsonArray.length(); i++) {
//
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                            MovieModel model = new MovieModel();
//                            model.setId(jsonObject1.getString("vote_average"));
//                            model.setName(jsonObject1.getString("title"));
//                            model.setImg(jsonObject1.getString("poster_path"));
//
//                            movieList.add(model);
//                        }
//
//                        MovieAdapter adapter = new MovieAdapter(MainActivity.this, movieList);
//
//                        recyclerView.setAdapter(adapter);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        requestQueue.add(jsonArrayRequest);


//    public class GetData extends AsyncTask<String, String, String>{
//
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            String current = "";
//            try {
//                URL url;
//                HttpURLConnection urlConnection = null;
//
//                try {
//                    url = new URL(JSON_URL);
//                    urlConnection = (HttpURLConnection) url.openConnection();
//
//                    InputStream is = urlConnection.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//
//                    int data = isr.read();
//                    while ( data != -1){
//                        current += (char) data;
//                        data = isr.read();
//                    }
//                    return current;
//                }catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    if(urlConnection != null) {
//                        urlConnection.disconnect();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            return current;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray jsonArray = jsonObject.getJSONArray("results");
//
//                for (int i = 0 ; i < jsonArray.length() ; i++) {
//
//                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                    MovieModel model = new MovieModel();
//                    model.setId(jsonObject1.getString("vote_average"));
//                    model.setName(jsonObject1.getString("title"));
//                    model.setImg(jsonObject1.getString("poster_path"));
//
//                    movieList.add(model);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            PutDataIntoRecyclerView( movieList);
//        }
//    }
//
//    private void PutDataIntoRecyclerView(List<MovieModel> movieList) {
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
//        recyclerView.setLayoutManager(llm);
//        recyclerView.setAdapter(movieAdapter);
//    }
