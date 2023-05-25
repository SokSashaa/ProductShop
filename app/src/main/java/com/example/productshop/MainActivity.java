package com.example.productshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import CustomAdapters.CustomAdapter;
import CustomAdapters.CustomAdapterHolder;
import dbHelpers.ForCategories;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CustomAdapter[]> category = new ArrayList<CustomAdapter[]>();
    private RecyclerView recyclerView;
    private CustomAdapterHolder customAdapterHolder;
    private Cursor cursor;
    private SQLiteDatabase database;
    private ForCategories forCategories;
    private ArrayList<Integer> ids = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forCategories = new ForCategories(this);
        forCategories.create_db(); // create database
        database = forCategories.open();
        setInformationForList();

        recyclerView = (RecyclerView)findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapterHolder = new CustomAdapterHolder(this, category);
        recyclerView.setAdapter(customAdapterHolder);

        //startActivity(new Intent(MainActivity.this,under_category.class));
    }
    public void Send(View v) {
        Intent intent = new Intent(MainActivity.this, under_category.class);
        switch (v.getId()) {
            case R.id.imageView3:
                int position = Integer.parseInt(v.getTag().toString());
                String name = category.get(position)[0].getNames();
                intent.putExtra("id",getID(name).toString());
                intent.putExtra("name",name);
                startActivity(intent);
                break;
            case R.id.imageView4:
                int position1 = Integer.parseInt(v.getTag().toString());
                String name1 = category.get(position1)[1].getNames();
                intent.putExtra("id",getID(name1).toString());
                intent.putExtra("name",name1);
                startActivity(intent);
                break;
        }
    }
    private Integer getID(String name)
    {
        Integer id =-1;
        try{
            String selection = "name = ?";
            cursor = database.query(ForCategories.TABLE_CONTACT,null,selection,new String[]{name},null,null,null);

            if(cursor.moveToFirst()){
                int idIndex = cursor.getColumnIndex(ForCategories.KEY_ID);
                id = cursor.getInt(idIndex);
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
        return id;
    }
    private void setInformationForList()
    {
        try{
            // array CustomAdapters
            CustomAdapter[] customAdapters = new CustomAdapter[2];
            int index = 0;

            //get information from database by condition
            cursor = database.query(ForCategories.TABLE_CONTACT,null,null,null,null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForCategories.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForCategories.KEY_IMG);
                int idIndex = cursor.getColumnIndex(ForCategories.KEY_ID);
                do{
                    if(index!=2)
                    {
                        //get strings from columns
                        String nameMenu = cursor.getString(nameIndex);
                        String imgMenu = cursor.getString(imgIndex);
                        //create object CustomAdapter and add it in array "CustomAdapters"
                        CustomAdapter customAdapter = new CustomAdapter(imgMenu,nameMenu);
                        customAdapters[index] = customAdapter;
                        index++;
                    }
                    if (index==2)
                    {
                        //if there are three elements in the array then index = 0 and redefining the array reference
                        index=0;
                        category.add(customAdapters);
                        customAdapters=new CustomAdapter[2];
                    }

                }
                while(cursor.moveToNext());
                //if array contains less than three elements then add new elements
                if(index!=0)
                {
                    for(int i=index;i<customAdapters.length;i++)
                    {
                        CustomAdapter customAdapter1 = new CustomAdapter("","");
                        customAdapters[i]=customAdapter1;

                    }
                    category.add(customAdapters);
                }

            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
}