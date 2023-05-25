package com.example.productshop;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import CustomAdapters.CustomAdapterForProducts;
import CustomAdapters.CustomAdapterHolderForKorzina;

public class Korzina1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapterHolderForKorzina customAdapterHolderForKorzina;
    private ImageView close;
    private TextView clear;
    private Korzina korzina;
    private ArrayList <CustomAdapterForProducts> list;
    private TextView count,sum,sum1;
    private LinearLayout lin_of;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korzina);
        count = (TextView)findViewById(R.id.textView31);
        sum = (TextView)findViewById(R.id.textView34);
        sum1 = (TextView)findViewById(R.id.textView37);

        korzina = Korzina.getInstance();
        list = korzina.getAllProducts();

        recyclerView = (RecyclerView)findViewById(R.id.rec_korz);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setNestedScrollingEnabled(false);
        customAdapterHolderForKorzina = new CustomAdapterHolderForKorzina(this,list);
        recyclerView.setAdapter(customAdapterHolderForKorzina);
        lin_of = (LinearLayout)findViewById(R.id.lin_of);

        get();


        close = (ImageView)findViewById(R.id.imageView16);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        clear = (TextView)findViewById(R.id.textView28);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                count.setText("0");
                sum.setText("0");
                recyclerView.setAdapter(customAdapterHolderForKorzina);
                lin_of.setVisibility(View.INVISIBLE);
            }
        });

        lin_of.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Korzina1.this);
                builder.setTitle("Примечание");
                builder.setMessage("Ваш заказ принят.\n" +
                        "Получить можно с 18-00 и до 23-00\n" +
                        "По адресу: ул. Новоселов 19");
                builder.setCancelable(true);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Отпускает диалоговое окно
                        finish();
                        list.clear();
                        korzina.deleteAll_Product();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void get()
    {
        String for_count = String.valueOf(list.size());
        count.setText(for_count);

        String for_sum = getSum().toString();
        sum.setText(for_sum);
        sum1.setText(for_sum + "Р");

        if(list.isEmpty()){
            lin_of.setVisibility(View.INVISIBLE);
        }
    }

    private Integer getSum()
    {
        int sum = 0;
        CustomAdapterForProducts customAdapterForProducts;
        for(int i =0;i<list.size();i++)
        {
            customAdapterForProducts = list.get(i);
            sum += customAdapterForProducts.getCount() * Integer.parseInt(customAdapterForProducts.getPrice().replace("Р",""));
        }

        return sum;
    }
    public void setCounts(View view) throws Exception {
        int position = Integer.parseInt(view.getTag().toString());
        CustomAdapterForProducts customAdapterForProducts;
        switch (view.getId())
        {
            case R.id.imageView18:
                customAdapterForProducts = list.get(position);
                int count = customAdapterForProducts.getCount();
                count++;
                customAdapterForProducts.setCount(count);
                korzina.setProduct(position,customAdapterForProducts);
                break;
            case R.id.imageView17:
                customAdapterForProducts = list.get(position);
                int count1 = customAdapterForProducts.getCount();
                if(count1>1)
                {
                    count1--;
                    customAdapterForProducts.setCount(count1);
                    korzina.setProduct(position,customAdapterForProducts);
                }
                else
                {
                    count1--;
                    customAdapterForProducts.setCount(count1);
                    korzina.setProduct(position,customAdapterForProducts);
                    list.remove(position);
                    korzina.deleteProduct(position);

                }
                break;

        }
        recyclerView.setAdapter(customAdapterHolderForKorzina);
        get();
    }
}
