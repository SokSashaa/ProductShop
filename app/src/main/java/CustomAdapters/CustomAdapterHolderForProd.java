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

public class CustomAdapterHolderForProd extends RecyclerView.Adapter<CustomAdapterHolderForProd.PrViewHolder> {

    private Context context;
    private final List<CustomAdapter[]> states;

    public CustomAdapterHolderForProd(Context context, List<CustomAdapter[]> states) {
        this.context = context;
        this.states = states;
    }


    @NonNull
    @Override
    public CustomAdapterHolderForProd.PrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.podcategory_list_item, parent, false);
        return new PrViewHolder(view, states);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterHolderForProd.PrViewHolder holder, int position) {

        CustomAdapter[] customAdapters =states.get(position);
        for(int i = 0;i<customAdapters.length;i++)
        {
            holder.names[i].setText(customAdapters[i].getNames());
            String img_res = customAdapters[i].getImageViews();
            int imgIndex = context.getResources().getIdentifier(img_res,"drawable",context.getPackageName());
            holder.img[i].setImageResource(imgIndex);
            holder.img[i].setTag(position);


        }
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class PrViewHolder extends RecyclerView.ViewHolder {
        TextView nameView1,nameView2,nameView3;
        ImageView imageView1,imageView2,imageView3;
        private final TextView[] names;
        private final ImageView[] img;

        private final List<CustomAdapter[]> listNotes;

        PrViewHolder(View view, List<CustomAdapter[]> listNotes) {
            super(view);

            nameView1 = (TextView) view.findViewById(R.id.textView9);
            nameView2 = (TextView) view.findViewById(R.id.textView11);
            nameView3 = (TextView) view.findViewById(R.id.textView12);
            names = new TextView[]{nameView1,nameView2,nameView3};
            imageView1 = (ImageView)view.findViewById(R.id.imageView5);
            imageView2 = (ImageView)view.findViewById(R.id.imageView7);
            imageView3 = (ImageView)view.findViewById(R.id.imageView8);
            img = new ImageView[] {imageView1,imageView2,imageView3};

            this.listNotes = listNotes;

        }



    }
}
