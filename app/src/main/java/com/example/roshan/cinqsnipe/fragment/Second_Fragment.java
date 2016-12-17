package com.example.roshan.cinqsnipe.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.roshan.cinqsnipe.BlogDetail;
import com.example.roshan.cinqsnipe.DbHandler;
import com.example.roshan.cinqsnipe.Description;
import com.example.roshan.cinqsnipe.HttpHandler;
import com.example.roshan.cinqsnipe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import static com.example.roshan.cinqsnipe.DbHandler.table_name;

public class Second_Fragment extends Fragment {

    private DbHandler mydb;
    private ProgressDialog pDialog;
    private ListView lv;
    // URL to get contacts JSON
    private static String url = "http://api.mantraideas.com/";
    private View view;
    ArrayList<HashMap<String, String>> newslist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_second, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Latest News");
        newslist = new ArrayList<>();
        lv=(ListView)view.findViewById(R.id.detail_list_view);
        mydb=new DbHandler(getActivity());
        new GetContacts().execute();
        onclick();
        return view;
    }

    public void onclick(){
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        HashMap<String, String> map = newslist.get(position);
                        Intent i=new Intent(getActivity(), Description.class);
                        i.putExtra("title",map.get("title"));
                        i.putExtra("description",map.get("description"));
                        i.putExtra("link", map.get("link"));
                        i.putExtra("author",map.get("author"));
                        i.putExtra("category",map.get("category"));
                        Toast.makeText(getActivity(), map.get("title"), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                }
        );

    }

    private class GetContacts extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog=new ProgressDialog(getContext());
            pDialog.setMessage("Please Wait..");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        public HashMap<String, String> getHashMap(String start, String limit , String action) {
            HashMap<String, String> map = new HashMap<>();
            map.put("action", action);
            map.put("start", start);
            map.put("limit", limit);

            return map;
        }


        @Override
        protected String doInBackground(String... params) {
            HttpHandler sh=new HttpHandler();
            String jsontstrt=sh.performPostCall(url, getHashMap("0", "10", "get_news"));

            if (jsontstrt !=null) {

                try {

                    JSONArray jsonArray = new JSONArray(jsontstrt);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        String id = c.optString("id");
                        String title = c.optString("title");
                        String description = c.optString("description");
                        String link = c.optString("link");
                        String author = c.optString("author");
                        String category = c.optString("category");
                        String image = c.optString("image");
                        String pubDate = c.optString("pubDate");
                        String source = c.optString("source");
                        String sitename = c.optString("sitename");

                         mydb.insertData(id,title, description, link, category);
//                        List<BlogDetail> blogDetails = mydb.viewData();
//
//                        for (BlogDetail bd : blogDetails) {
//                            String log = "id: " + bd.getId() + " ,title: " + bd.getTitle() + " ,description: " + bd.getDescription() + " , link: " + bd.getLink() + " , category: " + bd.getCategory();
//                            Log.d("title: ", log);
//                        }
                        HashMap<String, String> map = new HashMap<>();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                        long time = sdf.parse(pubDate).getTime();
                        long now = System.currentTimeMillis();

                        CharSequence ago =
                                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);


                        //adding each child node to HashMap key ==> value
                        map.put("id", id);
                        map.put("title", title);
                        map.put("description", description);
                        map.put("category", category);
                        map.put("source", source);
                        map.put("link", link);
                        map.put("pubDate", (String) ago);
                        map.put("author", author);
                        newslist.add(map);
                    }
                } catch (final JSONException e) {

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

                return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(getActivity(), "Added To History", Toast.LENGTH_SHORT).show();
            ListAdapter adapter=new SimpleAdapter(
                    getActivity(),newslist,R.layout.list_item, new String[]{
                    "title", "description","category","pubDate","source","link","author"}, new  int[]{
                    R.id.txt_title,R.id.txt_description,R.id.txt_category,R.id.txt_pubdate,R.id.txt_source,R.id.txt_link,R.id.txt_author}
            );
            lv.setAdapter(adapter);

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getActivity(), "Setting Page", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }


}
