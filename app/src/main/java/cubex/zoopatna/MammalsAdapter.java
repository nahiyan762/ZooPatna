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

public class MammalsAdapter extends RecyclerView.Adapter<MammalsHolder> {

    Activity lom;

    int[] mammals_imgs=new int[]{
            R.drawable.mammals1,
            R.drawable.mammals2,
            R.drawable.mammals3,
            R.drawable.mammals4,
            R.drawable.mammals5,
            R.drawable.mammals6,
            R.drawable.mammals7,
            R.drawable.mammals8,
            R.drawable.mammals9};

    String[] mammals;

    public MammalsAdapter(Activity listOfMammals) {

        this.lom=listOfMammals;

        mammals=lom.getResources().getStringArray(R.array.mammals);

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
        holder.tview.setText(mammals[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.mamal_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(1);



                Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mammals_imgs.length;
    }
}
