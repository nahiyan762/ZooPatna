package cubex.zoopatna;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by mahesht on 10/14/2016.
 */

public class GalleryItemsAdapter extends RecyclerView.Adapter<MammalsHolder> {

    Activity lom;

    int[] mammals_imgs=new int[]{
            R.drawable.mammals1,
            R.drawable.birds1,
            R.drawable.reptiles_item};

    String[] gallery_items;

    public GalleryItemsAdapter(Activity listOfMammals) {

        this.lom=listOfMammals;

        gallery_items=lom.getResources().getStringArray(R.array.gallery_items);

    }
 

    @Override
    public MammalsHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(lom);

        View v=inflater.inflate(R.layout.mammals_indiview,parent,false);

        return new MammalsHolder(v);
    }

    @Override
    public void onBindViewHolder(final MammalsHolder holder, int position) {

        holder.iview.setImageDrawable(lom.getResources().getDrawable(mammals_imgs[position]));
        holder.tview.setText(gallery_items[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.gallery_item_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(5);

              //  Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mammals_imgs.length;
    }
}
