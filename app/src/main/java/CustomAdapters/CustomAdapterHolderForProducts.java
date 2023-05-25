package CustomAdapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productshop.R;

import java.util.List;

public class CustomAdapterHolderForProducts extends RecyclerView.Adapter<CustomAdapterHolderForProducts.PrViewHolder> {

    private Context context;
    private final List<CustomAdapterForProducts[]> states;

    public CustomAdapterHolderForProducts(Context context, List<CustomAdapterForProducts[]> states) {
        this.context = context;
        this.states = states;
    }


    @NonNull
    @Override
    public CustomAdapterHolderForProducts.PrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        return new PrViewHolder(view, states);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterHolderForProducts.PrViewHolder holder, int position) {

        CustomAdapterForProducts[] customAdapters =states.get(position);
        for(int i = 0;i<customAdapters.length;i++)
        {
            String name = customAdapters[i].getNames();
            String imgq = customAdapters[i].getImageViews();
            if(name==null && imgq==null ||name=="" && imgq=="" ){
               holder.lins[i].setVisibility(View.INVISIBLE);
            }
            else
            {
                holder.names[i].setText(name);
                int imgIndex = context.getResources().getIdentifier(imgq,"drawable",context.getPackageName());
                holder.img[i].setImageResource(imgIndex);
                holder.buttons[i].setTag(position);
                holder.prices[i].setText(customAdapters[i].getPrice());
            }
        }
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class PrViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView1,nameView2,price1,price2;
        private ImageView imageView1,imageView2;
        private LinearLayout lin1,lin2;
        private final TextView[] names;
        private final ImageView[] img;
        private final TextView[] prices;
        private final LinearLayout[] lins;
        private ImageButton but1,but2;
        private final ImageButton[] buttons;

        private final List<CustomAdapterForProducts[]> listNotes;

        PrViewHolder(View view, List<CustomAdapterForProducts[]> listNotes) {
            super(view);

            nameView1 = (TextView) view.findViewById(R.id.textView21);
            nameView2 = (TextView) view.findViewById(R.id.textView22);
            names = new TextView[]{nameView1,nameView2};
            imageView1 = (ImageView)view.findViewById(R.id.imageView13);
            imageView2 = (ImageView)view.findViewById(R.id.imageView14);
            img = new ImageView[] {imageView1,imageView2};
            price1 = (TextView)view.findViewById(R.id.textView23);
            price2 = (TextView)view.findViewById(R.id.textView24);
            prices = new TextView[] {price1,price2};
            lin1 = (LinearLayout)view.findViewById(R.id.lin3);
            lin2 = (LinearLayout)view.findViewById(R.id.lin4);
            lins = new LinearLayout[] {lin1,lin2};
            but1 = (ImageButton)view.findViewById(R.id.imageButton13);
            but2 = (ImageButton)view.findViewById(R.id.imageButton14);
            buttons = new ImageButton[] {but1,but2};
            this.listNotes = listNotes;

        }



    }
}
