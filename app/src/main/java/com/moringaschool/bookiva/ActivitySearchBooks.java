package com.moringaschool.bookiva;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class ActivitySearchBooks extends AppCompatActivity {
    public static final String TAG = ActivitySearchBooks.class.getSimpleName();

    SearchView mSearchView;
    ListView listView;
    String[] marray = {"mohamed", "ibrahim", "nasra", "yahya", "roney", "ali" , "seattle", "minnesota", "america", "mombasa", "kenya","mohamed", "ibrahim", "nasra", "yahya", "roney", "ali" , "seattle", "minnesota", "america", "mombasa", "kenya"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

            mSearchView = findViewById(R.id.mSearchView);
            listView = findViewById(R.id.listView);
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, marray);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(ActivitySearchBooks.this, "clicked: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                }
            });

            //now filter searchview from array items

            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    ActivitySearchBooks.this.arrayAdapter.getFilter().filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ActivitySearchBooks.this.arrayAdapter.getFilter().filter(newText);

                    return false;
                }
            });



        }


}
