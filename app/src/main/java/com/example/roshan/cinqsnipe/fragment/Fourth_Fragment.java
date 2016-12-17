package com.example.roshan.cinqsnipe.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.roshan.cinqsnipe.DbHandler;
import com.example.roshan.cinqsnipe.Description;
import com.example.roshan.cinqsnipe.R;

public class Fourth_Fragment extends Fragment {
  private View view;
    DbHandler myDb;
    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_second, container, false);
        getActivity().setTitle("History");
        lv=(ListView)view.findViewById(R.id.detail_list_view);

        myDb=new DbHandler(getActivity());

        //cursor activity
        Cursor cursor = myDb.viewData();
        String[] from = {myDb.col_2,myDb.col_3,myDb.col_4,myDb.col_5};
        int[] to = {R.id.txt_title1, R.id.txt_description1,R.id.txt_link1,R.id.txt_category1 };
        SimpleCursorAdapter cd = new SimpleCursorAdapter(view.getContext(), R.layout.sqlitesaveddata, cursor, from, to, 0);
        lv.setAdapter(cd);
        setHasOptionsMenu(true);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cursor = null;
                        cursor = (Cursor) parent.getItemAtPosition(position);
                        Intent i=new Intent(getActivity(), Description.class);
                        i.putExtra("title", cursor.getString(cursor.getColumnIndex(myDb.col_2)));
                        i.putExtra("description", cursor.getString(cursor.getColumnIndex(myDb.col_3)));
                        i.putExtra("link", cursor.getString(cursor.getColumnIndex(myDb.col_4)));
                        i.putExtra("category", cursor.getString(cursor.getColumnIndex(myDb.col_5)));
                        Toast.makeText(getActivity(), cursor.getString(cursor.getColumnIndex(myDb.col_2)), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                }
        );

        return view;
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
