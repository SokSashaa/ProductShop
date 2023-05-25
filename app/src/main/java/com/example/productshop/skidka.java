package com.example.productshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import CustomAdapters.CustomAdapterForProducts;
import CustomAdapters.CustomAdapterForSkidka;
import CustomAdapters.CustomAdapterHolderForSkidka;
import dbHelpers.ForSkidky;

public class skidka extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    private String id_cat;
    private SQLiteDatabase database;
    private ForSkidky dbHelper;
    private RecyclerView skidky;
    private ArrayList<CustomAdapterForSkidka[]> arrayListSkid = new ArrayList<CustomAdapterForSkidka[]>();
    private Cursor cursor;
    private CustomAdapterHolderForSkidka customAdapterHolderForSkidka;
    private Korzina korzina;
    private ImageButton korz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skidka);

        korzina = Korzina.getInstance();

        korz = (ImageButton)findViewById(R.id.imageButton2);
        korz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentKorzina(korz);
            }
        });

        dbHelper = new ForSkidky(this);
        dbHelper.create_db();
        database = dbHelper.open();

        Integer index = getIntent().getIntExtra("flag",-1);

        String name = getIntent().getStringExtra("name");
        TextView textView = (TextView)findViewById(R.id.textView8);
        textView.setText(name);

        id_cat = getIntent().getStringExtra("id");

        if(index == 0) {
            setInformationForListCategory();
        } else if(index == 1){
            setInformationForListPodCategory();
        }

        skidky = (RecyclerView)findViewById(R.id.rec_skid);
        skidky.setLayoutManager(new LinearLayoutManager(this));
        skidky.setNestedScrollingEnabled(false);
        customAdapterHolderForSkidka = new CustomAdapterHolderForSkidka(this,arrayListSkid);
        skidky.setAdapter(customAdapterHolderForSkidka);

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setInformationForListCategory()
    {
        try{
            // array CustomAdapters
            CustomAdapterForSkidka[] customAdapters = new CustomAdapterForSkidka[2];
            int index = 0;
            String selection = "id_category = ?";
            //get information from database by condition
            cursor = database.query(ForSkidky.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForSkidky.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForSkidky.KEY_IMG);
                int new_price = cursor.getColumnIndex(ForSkidky.KEY_NEW_PRICE);
                int prev_price = cursor.getColumnIndex(ForSkidky.KEY_PREV_PRICE);
                do{
                        if(index!=2)
                        {
                            //get strings from columns
                            String nameMenu = cursor.getString(nameIndex);
                            String imgMenu = cursor.getString(imgIndex);
                            String prev_p = cursor.getString(prev_price);
                            String new_p = cursor.getString(new_price);
                            //create object CustomAdapter and add it in array "CustomAdapters"
                            CustomAdapterForSkidka customAdapter = new CustomAdapterForSkidka(imgMenu,nameMenu,new_p,prev_p);
                            customAdapters[index] = customAdapter;
                            index++;
                        }
                        if (index==2)
                        {
                            //if there are three elements in the array then index = 0 and redefining the array reference
                            index=0;
                            arrayListSkid.add(customAdapters);
                            customAdapters=new CustomAdapterForSkidka[2];
                        }
                }
                while(cursor.moveToNext());
                //if array contains less than three elements then add new elements
                if(index!=0)
                {
                    for(int i=index;i<customAdapters.length;i++)
                    {
                        CustomAdapterForSkidka customAdapter1 = new CustomAdapterForSkidka("","","","");
                        customAdapters[i]=customAdapter1;

                    }
                    arrayListSkid.add(customAdapters);
                }
            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    private void setInformationForListPodCategory()
    {
        try{
            // array CustomAdapters
            CustomAdapterForSkidka[] customAdapters = new CustomAdapterForSkidka[2];
            int index = 0;
            String selection = "id_und_category = ?";
            //get information from database by condition
            cursor = database.query(ForSkidky.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForSkidky.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForSkidky.KEY_IMG);
                int new_price = cursor.getColumnIndex(ForSkidky.KEY_NEW_PRICE);
                int prev_price = cursor.getColumnIndex(ForSkidky.KEY_PREV_PRICE);
                do{
                    if(index!=2)
                    {
                        //get strings from columns
                        String nameMenu = cursor.getString(nameIndex);
                        String imgMenu = cursor.getString(imgIndex);
                        String prev_p = cursor.getString(prev_price);
                        String new_p = cursor.getString(new_price);
                        //create object CustomAdapter and add it in array "CustomAdapters"
                        CustomAdapterForSkidka customAdapter = new CustomAdapterForSkidka(imgMenu,nameMenu,new_p,prev_p);
                        customAdapters[index] = customAdapter;
                        index++;
                    }
                    if (index==2)
                    {
                        //if there are three elements in the array then index = 0 and redefining the array reference
                        index=0;
                        arrayListSkid.add(customAdapters);
                        customAdapters=new CustomAdapterForSkidka[2];
                    }
                }
                while(cursor.moveToNext());
                //if array contains less than three elements then add new elements
                if(index!=0)
                {
                    for(int i=index;i<customAdapters.length;i++)
                    {
                        CustomAdapterForSkidka customAdapter1 = new CustomAdapterForSkidka("","","","");
                        customAdapters[i]=customAdapter1;

                    }
                    arrayListSkid.add(customAdapters);
                }

            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    public void intentKorzina(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton2:
                startActivity(new Intent(skidka.this,Korzina1.class));
                break;
        }
    }

    public void setCountSkidka(View view)throws Exception{
        int position = Integer.parseInt(view.getTag().toString());
        CustomAdapterForSkidka customAdapterForSkidka;
        CustomAdapterForProducts customAdapterForProducts;
        switch (view.getId())
        {
            case R.id.imageButton33:
                customAdapterForSkidka = arrayListSkid.get(position)[0];
                customAdapterForProducts = new CustomAdapterForProducts(customAdapterForSkidka.getImageViews(),customAdapterForSkidka.getNames(),customAdapterForSkidka.getNewPrice(),customAdapterForSkidka.getCount());
                int count = customAdapterForProducts.getCount();
                count++;
                int index = korzina.getIndexProduct(customAdapterForProducts);
                if(index==-1)
                {
                    if(count!=1 )
                    {
                        count=1;
                    }
                    customAdapterForProducts.setCount(count);
                    korzina.setProduct(customAdapterForProducts);
                }
                else {
                    CustomAdapterForProducts cus = korzina.getProduct(index);
                    count = cus.getCount();
                    count++;
                    cus.setCount(count);
                    korzina.setProduct(index,cus);
                }
                customAdapterForProducts.setCount(count);
                customAdapterForSkidka.setCount(count);
                break;
            case R.id.imageButton4:
                customAdapterForSkidka = arrayListSkid.get(position)[1];
                customAdapterForProducts = new CustomAdapterForProducts(customAdapterForSkidka.getImageViews(),customAdapterForSkidka.getNames(),customAdapterForSkidka.getNewPrice(),customAdapterForSkidka.getCount());
                int count1 = customAdapterForProducts.getCount();
                count1++;
                int index1 = korzina.getIndexProduct(customAdapterForProducts);
                if(index1==-1)
                {
                    if(count1!=1 )
                    {
                        count1=1;
                    }
                    customAdapterForProducts.setCount(count1);
                    korzina.setProduct(customAdapterForProducts);
                }
                else {
                    CustomAdapterForProducts cus = korzina.getProduct(index1);
                    count1 = cus.getCount();
                    count1++;
                    cus.setCount(count1);
                    korzina.setProduct(index1,cus);
                }
                customAdapterForProducts.setCount(count1);
                customAdapterForSkidka.setCount(count1);
                break;
        }
        Toast.makeText(this,"Продукт успешно добавлен в корзину",Toast.LENGTH_SHORT).show();
    }

}
