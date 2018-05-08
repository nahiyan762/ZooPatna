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

public class BirdsAdapter extends RecyclerView.Adapter<BirdsHolder> {

    Activity lom;

    int[] birds_imgs=new int[]{
            R.drawable.birds1,
            R.drawable.birds2,
            };

    String[] birds;

    public BirdsAdapter(Activity listOfBirds) {

        this.lom=listOfBirds;

        birds=lom.getResources().getStringArray(R.array.birds);

    }


    @Override
    public BirdsHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(lom);

        View v=inflater.inflate(R.layout.birds_indiview,parent,false);

        return new BirdsHolder(v);
    }

    @Override
    public void onBindViewHolder(final BirdsHolder holder, int position) {

        holder.iview.setImageDrawable(lom.getResources().getDrawable(birds_imgs[position]));
        holder.tview.setText(birds[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.mamal_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(2);

                Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return birds_imgs.length;
    }
}
