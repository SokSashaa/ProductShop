package com.example.productshop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import CustomAdapters.CustomAdapterForProducts;
import CustomAdapters.CustomAdapterHolderForKorzina;
import CustomAdapters.CustomAdapterHolderForSkidka;

public class Korzina{


    private ArrayList<CustomAdapterForProducts> list;
    private static  Korzina instance;


    private Korzina() {
        list = new ArrayList<>();
    }
    public Integer getCount() {return list.size();}

    public CustomAdapterForProducts getProduct(int index){
        return list.get(index);
    }

    public ArrayList<CustomAdapterForProducts> getAllProducts() {
        return list;
    }
    public void setProduct(Integer index,CustomAdapterForProducts customAdapterForProducts) {
        if(customAdapterForProducts!=null){
            list.set(index,customAdapterForProducts);
        }
    }
    public Integer getIndexProduct(CustomAdapterForProducts customAdapterForProducts) {
        CustomAdapterForProducts prod;
        for(int i=0;i<list.size();i++)
        {
            prod = list.get(i);
            if(prod.getNames().equals(customAdapterForProducts.getNames())) return i;
        }
        return -1;
    }
    public void setProduct(CustomAdapterForProducts product) throws Exception {
        if(product != null)
            list.add(product);
        else {
            throw new Exception("this product == null");
        }
    }

    public void deleteProduct(CustomAdapterForProducts products) throws Exception {
        if (products != null)
            list.remove(products);
        else {
            throw new Exception("this product == null");
        }
    }

    public void deleteAll_Product() {
        list.clear();
    }
    public static Korzina getInstance() {
        if(instance == null) {
            instance = new Korzina();
        }
        return instance;
    }
    public void deleteProduct(Integer index)
    {
        list.remove(index);
    }

}
