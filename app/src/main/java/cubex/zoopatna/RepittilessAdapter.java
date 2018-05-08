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

public class RepittilessAdapter extends RecyclerView.Adapter<RepitilesHolder> {

    Activity lom;

    int[] repitles_imgs=new int[]{
            R.drawable.mammals1,
            R.drawable.mammals2,
            R.drawable.mammals3,
            };

    String[] repitles;

    public RepittilessAdapter(Activity listOfRepitiles) {

        this.lom=listOfRepitiles;

        repitles=lom.getResources().getStringArray(R.array.reptiles);

    }


    @Override
    public RepitilesHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(lom);

        View v=inflater.inflate(R.layout.reptiles_indiview,parent,false);

        return new RepitilesHolder(v);
    }

    @Override
    public void onBindViewHolder(final RepitilesHolder holder, int position) {
        holder.iview.setImageDrawable(lom.getResources().getDrawable(repitles_imgs[position]));
        holder.tview.setText(repitles[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.mamal_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(3);

                Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return repitles_imgs.length;
    }
}
