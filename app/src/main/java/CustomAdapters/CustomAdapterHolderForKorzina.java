package CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productshop.R;

import java.util.List;

public class CustomAdapterHolderForKorzina extends RecyclerView.Adapter<CustomAdapterHolderForKorzina.PrViewHolder> {

    private Context context;
    private final List<CustomAdapterForProducts> states;

    public CustomAdapterHolderForKorzina(Context context, List<CustomAdapterForProducts> states) {
        this.context = context;
        this.states = states;
    }


    @NonNull
    @Override
    public CustomAdapterHolderForKorzina.PrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.korzina_list_item, parent, false);
        return new PrViewHolder(view, states);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterHolderForKorzina.PrViewHolder holder, int position) {

        CustomAdapterForProducts customAdapter = states.get(position);
        holder.nameView1.setText(customAdapter.getNames());

        String img_res = customAdapter.getImageViews();
        int imgIndex = context.getResources().getIdentifier(img_res,"drawable",context.getPackageName());
        holder.imageView1.setImageResource(imgIndex);

        Integer count = customAdapter.getCount();
        holder.countView.setText(count.toString());

        int price = Integer.parseInt(customAdapter.getPrice().replace("Р",""));
        holder.priceView.setText(price*count + "Р");

        holder.minus.setTag(position);
        holder.plus.setTag(position);
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class PrViewHolder extends RecyclerView.ViewHolder {
        TextView nameView1,countView,priceView;
        ImageView imageView1,plus,minus;
        private final List<CustomAdapterForProducts> listNotes;

        PrViewHolder(View view, List<CustomAdapterForProducts> listNotes) {
            super(view);

            nameView1 = (TextView) view.findViewById(R.id.textView26);
            imageView1 = (ImageView)view.findViewById(R.id.imageView15);
            countView = (TextView)view.findViewById(R.id.textView29);
            priceView = (TextView)view.findViewById(R.id.textView27);
            plus = (ImageView)view.findViewById(R.id.imageView18);
            minus = (ImageView)view.findViewById(R.id.imageView17);

            this.listNotes = listNotes;

        }



    }
}
