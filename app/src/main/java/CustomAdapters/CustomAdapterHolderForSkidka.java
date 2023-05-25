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

public class CustomAdapterHolderForSkidka extends RecyclerView.Adapter<CustomAdapterHolderForSkidka.PrViewHolder> {

    private Context context;
    private final List<CustomAdapterForSkidka[]> states;

    public CustomAdapterHolderForSkidka(Context context, List<CustomAdapterForSkidka[]> states) {
        this.context = context;
        this.states = states;
    }


    @NonNull
    @Override
    public CustomAdapterHolderForSkidka.PrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skidka_list_item, parent, false);
        return new PrViewHolder(view, states);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterHolderForSkidka.PrViewHolder holder, int position) {

        CustomAdapterForSkidka[] customAdapters =states.get(position);
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
                holder.prevPrice[i].setText(customAdapters[i].getPrevPrice());
                holder.prevPrice[i].setPaintFlags(holder.prevPrice[i].getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.newPrice[i].setText(customAdapters[i].getNewPrice());
                holder.buttons[i].setTag(position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class PrViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView1,nameView2,newPrice1,newPrice2,prevPrice1,prevPrice2;
        private ImageView imageView1,imageView2;
        private LinearLayout lin1,lin2;
        private final TextView[] names;
        private final ImageView[] img;
        private final TextView[] newPrice;
        private final TextView[] prevPrice;
        private final LinearLayout[] lins;
        private ImageButton but1,but2;
        private final ImageButton[] buttons;

        private final List<CustomAdapterForSkidka[]> listNotes;

        PrViewHolder(View view, List<CustomAdapterForSkidka[]> listNotes) {
            super(view);

            nameView1 = (TextView) view.findViewById(R.id.textView13);
            nameView2 = (TextView) view.findViewById(R.id.textView14);
            names = new TextView[]{nameView1,nameView2};
            imageView1 = (ImageView)view.findViewById(R.id.imageView9);
            imageView2 = (ImageView)view.findViewById(R.id.imageView10);
            img = new ImageView[] {imageView1,imageView2};
            newPrice1 = (TextView)view.findViewById(R.id.textView16);
            newPrice2 = (TextView)view.findViewById(R.id.textView18);
            newPrice = new TextView[]{newPrice1,newPrice2};
            prevPrice1 = (TextView)view.findViewById(R.id.textView15);
            prevPrice2 = (TextView)view.findViewById(R.id.textView17);
            prevPrice = new TextView[] {prevPrice1,prevPrice2};
            lin1 = (LinearLayout)view.findViewById(R.id.lin1);
            lin2 = (LinearLayout)view.findViewById(R.id.lin2);
            lins = new LinearLayout[] {lin1,lin2};
            but1 = (ImageButton)view.findViewById(R.id.imageButton33);
            but2 = (ImageButton)view.findViewById(R.id.imageButton4);
            buttons = new ImageButton[] {but1,but2};
            this.listNotes = listNotes;

        }



    }
}
