package com.example.productshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import CustomAdapters.CustomAdapter;
import CustomAdapters.CustomAdapterForProducts;
import CustomAdapters.CustomAdapterForSkidka;
import CustomAdapters.CustomAdapterHolderForProd;
import CustomAdapters.CustomAdapterHolderForProducts;
import CustomAdapters.CustomAdapterHolderForSkidka;
import dbHelpers.ForProducts;
import dbHelpers.ForSkidky;
import dbHelpers.ForUnderCategories;

public class products extends AppCompatActivity {

    private RecyclerView pr_skid;
    private RecyclerView prod;
    private SQLiteDatabase databaseProducts;
    private SQLiteDatabase databaseSkid;
    private CustomAdapterHolderForSkidka customAdapterHolderForSkidka;
    private CustomAdapterHolderForProducts customAdapterHolderForProducts;
    private ForProducts dbHelperProducts;
    private ForSkidky dbHelperSkidky;
    private String id_cat;
    private Cursor cursor;
    public ArrayList<CustomAdapterForProducts[]> product = new ArrayList<CustomAdapterForProducts[]>();
    private ArrayList<CustomAdapterForSkidka[]> sk = new ArrayList<CustomAdapterForSkidka[]>();
    private Korzina korzina;
    private ImageButton korz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        korzina = Korzina.getInstance();

        ImageButton back =(ImageButton)findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        korz =(ImageButton)findViewById(R.id.imageButton2);
        korz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentKorzina(korz);
            }
        });

        dbHelperSkidky = new ForSkidky(this);
        dbHelperSkidky.create_db();
        databaseSkid = dbHelperSkidky.open();

        dbHelperProducts = new ForProducts(this);
        dbHelperProducts.create_db();
        databaseProducts = dbHelperProducts.open();

        String name = getIntent().getStringExtra("name");
        id_cat = getIntent().getStringExtra("id");

        TextView textView = (TextView)findViewById(R.id.textView8);
        textView.setText(name);

        setInformationForList();
        setInformationForListSkidky();

        pr_skid = (RecyclerView)findViewById(R.id.pr_skid);
        pr_skid.setLayoutManager(new LinearLayoutManager(this));
        pr_skid.setNestedScrollingEnabled(false);
        customAdapterHolderForSkidka = new CustomAdapterHolderForSkidka(this, sk);
        pr_skid.setAdapter(customAdapterHolderForSkidka);

        prod = (RecyclerView)findViewById(R.id.prod);
        prod.setLayoutManager(new LinearLayoutManager(this));
        prod.setNestedScrollingEnabled(false);
        customAdapterHolderForProducts = new CustomAdapterHolderForProducts(this, product);
        prod.setAdapter(customAdapterHolderForProducts);

        if(sk.isEmpty()) {
            TextView textView1 = (TextView) findViewById(R.id.textView25);
            textView1.setText("Скидок на эту категорию нет");
        }


    }

    private void setInformationForList()
    {
        try{
            // array CustomAdapters
            CustomAdapterForProducts[] customAdapters = new CustomAdapterForProducts[2];
            int index = 0;
            String selection = "id_podcategory = ?";
            //get information from database by condition
            cursor = databaseProducts.query(ForProducts.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

            if(cursor.moveToFirst()){
                // get index columns
                int nameIndex = cursor.getColumnIndex(ForProducts.KEY_NAME);
                int imgIndex = cursor.getColumnIndex(ForProducts.KEY_IMG);
                int priceIndex = cursor.getColumnIndex(ForProducts.KEY_PRICE);
                do{
                    if(index!=2)
                    {
                        //get strings from columns
                        String nameMenu = cursor.getString(nameIndex);
                        String imgMenu = cursor.getString(imgIndex);
                        String price = cursor.getString(priceIndex);
                        //create object CustomAdapter and add it in array "CustomAdapters"
                        CustomAdapterForProducts customAdapter = new CustomAdapterForProducts(imgMenu,nameMenu,price,0);
                        customAdapters[index] = customAdapter;
                        index++;
                    }
                    if (index==2)
                    {
                        //if there are three elements in the array then index = 0 and redefining the array reference
                        index=0;
                        product.add(customAdapters);
                        customAdapters=new CustomAdapterForProducts[2];
                    }

                }
                while(cursor.moveToNext());
                //if array contains less than three elements then add new elements
                if(index!=0)
                {
                    for(int i=index;i<customAdapters.length;i++)
                    {
                        CustomAdapterForProducts customAdapter1 = new CustomAdapterForProducts("","","",0);
                        customAdapters[i]=customAdapter1;

                    }
                    product.add(customAdapters);
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
            String selection = "id_und_category = ?";
            //get information from database by condition
            cursor = databaseSkid.query(ForSkidky.TABLE_CONTACT,null,selection,new String[]{id_cat},null,null,null);

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
                            sk.add(customAdapters);
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
                    sk.add(customAdapters);
                }

            }
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    public void setCounts(View view) throws Exception {
        int position = Integer.parseInt(view.getTag().toString());
        CustomAdapterForProducts customAdapterForProducts;
        switch (view.getId())
        {
            case R.id.imageButton13:
                customAdapterForProducts = product.get(position)[0];
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
                break;
            case R.id.imageButton14:
                customAdapterForProducts = product.get(position)[1];
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
                break;

        }
        Toast.makeText(this,"Продукт успешно добавлен в корзину",Toast.LENGTH_SHORT).show();

    }
    public void setCountSkidka(View view)throws Exception{
        int position = Integer.parseInt(view.getTag().toString());
        CustomAdapterForSkidka customAdapterForSkidka;
        CustomAdapterForProducts customAdapterForProducts;
        switch (view.getId())
        {
            case R.id.imageButton33:
                customAdapterForSkidka = sk.get(position)[0];
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
                customAdapterForSkidka = sk.get(position)[1];
                customAdapterForProducts = new CustomAdapterForProducts(customAdapterForSkidka.getImageViews(),customAdapterForSkidka.getNames(),customAdapterForSkidka.getNewPrice(),customAdapterForSkidka.getCount());
                int count1 = customAdapterForProducts.getCount();
                count1++;
                int index1 = korzina.getIndexProduct(customAdapterForProducts);
                if(index1==-1)
                {
                    if(count1!=1 )
                    {
                        count1=1;
                        customAdapterForProducts.setCount(count1);
                    }
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
    public void intentKorzina(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton2:
                startActivity(new Intent(products.this,Korzina1.class));
                break;
        }
    }
}
