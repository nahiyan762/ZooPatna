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

public class FishesAdapter extends RecyclerView.Adapter<FishesHolder> {

    Activity lom;

    int[] fishes_imgs=new int[]{
            R.drawable.mammals1,
           // R.drawable.birds2,
            };

    String[] fishes;

    public FishesAdapter(Activity listOfFishes) {

        this.lom=listOfFishes;

        fishes=lom.getResources().getStringArray(R.array.fishes);

    }


    @Override
    public FishesHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(lom);

        View v=inflater.inflate(R.layout.fishes_indiview,parent,false);

        return new FishesHolder(v);
    }

    @Override
    public void onBindViewHolder(final FishesHolder holder, int position) {
        holder.iview.setImageDrawable(lom.getResources().getDrawable(fishes_imgs[position]));
        holder.tview.setText(fishes[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.mamal_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(7);

                Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return fishes_imgs.length;
    }
}
