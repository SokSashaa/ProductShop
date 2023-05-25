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

public class CustomAdapterHolderForProd1 extends RecyclerView.Adapter<CustomAdapterHolderForProd1.PrViewHolder> {

    private Context context;
    private final List<CustomAdapter[]> states;

    public CustomAdapterHolderForProd1(Context context, List<CustomAdapter[]> states) {
        this.context = context;
        this.states = states;
    }


    @NonNull
    @Override
    public PrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.podcategory_list_item1, parent, false);
        return new PrViewHolder(view, states);
    }

    @Override
    public void onBindViewHolder(@NonNull PrViewHolder holder, int position) {

        CustomAdapter[] customAdapters =states.get(position);
        for(int i = 0;i<customAdapters.length;i++)
        {
            holder.names[i].setText(customAdapters[i].getNames());
            String img_res = customAdapters[i].getImageViews();
            int imgIndex = context.getResources().getIdentifier(img_res,"drawable",context.getPackageName());
            holder.img[i].setImageResource(imgIndex);
        }
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class PrViewHolder extends RecyclerView.ViewHolder {
        TextView nameView1,nameView2;
        ImageView imageView1,imageView2;
        private final TextView[] names;
        private final ImageView[] img;
        private final List<CustomAdapter[]> listNotes;

        PrViewHolder(View view, List<CustomAdapter[]> listNotes) {
            super(view);

            nameView1 = (TextView) view.findViewById(R.id.textView19);
            nameView2 = (TextView) view.findViewById(R.id.textView20);
            names = new TextView[]{nameView1,nameView2};
            imageView1 = (ImageView)view.findViewById(R.id.imageView11);
            imageView2 = (ImageView)view.findViewById(R.id.imageView12);
            img = new ImageView[] {imageView1,imageView2};

            this.listNotes = listNotes;

        }



    }
}
