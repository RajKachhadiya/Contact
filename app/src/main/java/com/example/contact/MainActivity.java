package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    RecyclerView recyclerView;
    SearchView searchView;
    public static ArrayList<UserContact> contactlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactlist.clear();

        add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.list);
        searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<UserContact> list = new ArrayList();

                for(int i=0; i<contactlist.size();i++)
                {

                    String name1 = contactlist.get(i).getNam();
                    String number1 = contactlist.get(i).getNum();

                    if (name1.toLowerCase().contains(newText.toLowerCase()) || number1.toLowerCase().contains(newText.toLowerCase()))
                    {
                        list.add(contactlist.get(i));
                    }

                }
                Myadapter adapter = new Myadapter(MainActivity.this,list);
                recyclerView.setAdapter(adapter);

                return false;
            }
        });

        Dbhelper db = new Dbhelper(MainActivity.this);

        Cursor cursor = db.viewdata();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String nam = cursor.getString(1);
            String num = cursor.getString(2);

            // Make Model Class

            UserContact userContact = new UserContact(id, nam, num);
            contactlist.add(userContact);

            Log.e("===", "onCreate: id "+id +" Name "+nam+" Number "+num);
        }

        Myadapter adapter = new Myadapter(MainActivity.this,contactlist);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, DetailActivity.class));

            }
        });
    }
}