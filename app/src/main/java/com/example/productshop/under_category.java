package com.example.productshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import CustomAdapters.CustomAdapter;
import CustomAdapters.CustomAdapterForProducts;
import CustomAdapters.CustomAdapterForSkidka;
import CustomAdapters.CustomAdapterHolderForProd;
import CustomAdapters.CustomAdapterHolderForProd1;
import CustomAdapters.CustomAdapterHolderForSkidka;
import dbHelpers.ForCategories;
import dbHelpers.ForSkidky;
import dbHelpers.ForUnderCategories;

public class under_category extends AppCompatActivity {

    private ArrayList<CustomAdapter[]> podcategory = new ArrayList<CustomAdapter[]>();
    private RecyclerView recyclerView;
    private RecyclerView rec_skidka;
    private ArrayList<CustomAdapterForSkidka[]> skidka = new ArrayList<CustomAdapterForSkidka[]>();
    private CustomAdapterHolderForProd customAdapterHolder;
    private CustomAdapterHolderForSkidka customAdapterHolderForSkidka;
    private CustomAdapterHolderForProd1  customAdapterHolderForProd1;
    private Cursor cursor;
    private SQLiteDatabase databasePodCat;
    private SQLiteDatabase databaseSkidky;
    private ForSkidky dbHelperSkidky;
    private ForUnderCategories dbhelperUnder;
    private String id_cat;
    private TextView name;
    private ImageButton back,send;
    private Korzina korzina;
    private ImageButton korz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.under_category);

        korzina = Korzina.getInstance();

        back = (ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        korz = (ImageButton)findViewById(R.id.imageButton2);
        korz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentKorzina(korz);
            }
        });


        name = (TextView)findViewById(R.id.textView8);

        id_cat = getIntent().getStringExtra("id");
        String forname = getIntent().getStringExtra("name");
        name.setText(forname);

        send = (ImageButton)findViewById(R.id.imageButton1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(under_category.this, skidka.class).putExtra("name",forname).putExtra("id",id_cat).putExtra("flag",0));
            }
        });

        dbHelperSkidky = new ForSkidky(this);
        dbHelperSkidky.create_db();
        databaseSkidky = dbHelperSkidky.open();

        dbhelperUnder = new ForUnderCategories(this);
        dbhelperUnder.create_db();
        databasePodCat = dbhelperUnder.open();

        recyclerView = (RecyclerView)findViewById(R.id.rec_podcat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);


        if(id_cat.equals("1")) {
            setInformationForListProducts();
            customAdapterHolder = new CustomAdapterHolderForProd(this, podcategory);
            recyclerView.setAdapter(customAdapterHolder);
        }
        else{
            setInformationForList();
            customAdapterHolderForProd1 = new CustomAdapterHolderForProd1(this, podcategory);
            recyclerView.setAdapter(customAdapterHolderForProd1);
        }

        rec_skidka = (RecyclerView)findViewById(R.id.rec_skidka);
        rec_skidka.setLayoutManager(new LinearLayoutManager(this));

        setInformationForListSkidky();
        customAdapterHolderForSkidka = new CustomAdapterHolderForSkidka(this,skidka);
        rec_skidka.setAdapter(customAdapterHolderForSkidka);

    }
    private void setInformationForListProducts()
    {
        try{
            // array CustomAdapters
            CustomAdapter[] customAdapters = new CustomAdapter[3];
            int index = 0;
            String selection = "id_category = ?";
            //get information from database by condition
            cursor = databasePodCat.query(ForUnderCategories.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForCategories.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForCategories.KEY_IMG);
                do{
                    if(index!=3)
                    {
                        //get strings from columns
                        String nameMenu = cursor.getString(nameIndex);
                        String imgMenu = cursor.getString(imgIndex);
                        //create object CustomAdapter and add it in array "CustomAdapters"
                        CustomAdapter customAdapter = new CustomAdapter(imgMenu,nameMenu);
                        customAdapters[index] = customAdapter;
                        index++;
                    }
                    if (index==3)
                    {
                        //if there are three elements in the array then index = 0 and redefining the array reference
                        index=0;
                        podcategory.add(customAdapters);
                        customAdapters=new CustomAdapter[3];
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
                    podcategory.add(customAdapters);
                }

            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    private void setInformationForList()
    {
        try{
            // array CustomAdapters
            CustomAdapter[] customAdapters = new CustomAdapter[2];
            int index = 0;
            String selection = "id_category = ?";
            //get information from database by condition
            cursor = databasePodCat.query(ForUnderCategories.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForUnderCategories.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForUnderCategories.KEY_IMG);
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
                        podcategory.add(customAdapters);
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
                    podcategory.add(customAdapters);
                }

            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    private void setInformationForListSkidky()
        {
        try{
            // array CustomAdapters
            CustomAdapterForSkidka[] customAdapters = new CustomAdapterForSkidka[2];
            int index = 0;
            String selection = "id_category = ?";
            //get information from database by condition
            cursor = databaseSkidky.query(ForSkidky.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForSkidky.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForSkidky.KEY_IMG);
                int new_price = cursor.getColumnIndex(ForSkidky.KEY_NEW_PRICE);
                int prev_price = cursor.getColumnIndex(ForSkidky.KEY_PREV_PRICE);
                do{
                    if(skidka.size()!=1)
                    {
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
                            skidka.add(customAdapters);
                            customAdapters=new CustomAdapterForSkidka[2];
                        }
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
                    skidka.add(customAdapters);
                }

            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }

    public void sendIntent(View view)
    {

        Intent intent = new Intent(under_category.this,products.class);
        intent.putExtra("flag",1);
        int position = Integer.parseInt(view.getTag().toString());
        switch (view.getId())
        {
            case R.id.imageView9:
                String name = podcategory.get(position)[0].getNames();
                intent.putExtra("id",getID(name).toString());
                intent.putExtra("name",name);
                startActivity(intent);
                break;
            case R.id.imageView10:
                String name1 = podcategory.get(position)[1].getNames();
                intent.putExtra("id",getID(name1).toString());
                intent.putExtra("name",name1);
                startActivity(intent);
                break;
            case R.id.imageView5:
                String name2 = podcategory.get(position)[0].getNames();
                intent.putExtra("id",getID(name2).toString());
                intent.putExtra("name",name2);
                startActivity(intent);

                break;
            case R.id.imageView7:
                String name3 = podcategory.get(position)[1].getNames();
                intent.putExtra("id",getID(name3).toString());
                intent.putExtra("name",name3);
                startActivity(intent);
                break;
            case R.id.imageView8:
                String name4 = podcategory.get(position)[2].getNames();
                intent.putExtra("id",getID(name4).toString());
                intent.putExtra("name",name4);
                startActivity(intent);
                break;
        }
    }
    private Integer getID(String name)
    {
        Integer id =-1;
        try{
            String selection = "name = ?";
            cursor = databasePodCat.query(ForUnderCategories.TABLE_CONTACT,null,selection,new String[]{name},null,null,null);

            if(cursor.moveToFirst()){
                int idIndex = cursor.getColumnIndex(ForUnderCategories.KEY_ID);
                id = cursor.getInt(idIndex);
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
        return id;
    }
    public void setCountSkidka(View view)throws Exception{
        int position = Integer.parseInt(view.getTag().toString());
        CustomAdapterForSkidka customAdapterForSkidka;
        CustomAdapterForProducts customAdapterForProducts;
        switch (view.getId())
        {
            case R.id.imageButton33:
                customAdapterForSkidka = skidka.get(position)[0];
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
                customAdapterForSkidka.setCount(count);
                break;
            case R.id.imageButton4:
                customAdapterForSkidka = skidka.get(position)[1];
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
                    CustomAdapterForProducts cus1 = korzina.getProduct(index1);
                    count1 = cus1.getCount();
                    count1++;
                    cus1.setCount(count1);
                    korzina.setProduct(index1,cus1);
                }
                customAdapterForSkidka.setCount(count1);
                break;
        }
        Toast.makeText(this,"Продукт успешно добавлен в корзину",Toast.LENGTH_SHORT).show();
    }
    public void intentKorzina(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton2:
                startActivity(new Intent(under_category.this,Korzina1.class));
                break;
        }
    }
}
