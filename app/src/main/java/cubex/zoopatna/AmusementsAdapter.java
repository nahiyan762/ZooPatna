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

public class AmusementsAdapter extends RecyclerView.Adapter<AmusementsHolder> {

    Activity lom;

    int[] amusements_imgs=new int[]{
            R.drawable.amusements1,
            R.drawable.amusements2,
            R.drawable.amusements3,
            R.drawable.amusements4,
            R.drawable.amusements5,
            R.drawable.amusements6,
            R.drawable.amusements7,
            R.drawable.amusements8};

    String[]amusements ;

    public AmusementsAdapter(Activity listOfMammals) {

        this.lom=listOfMammals;

        amusements=lom.getResources().getStringArray(R.array.amusements);

    }


    @Override
    public AmusementsHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(lom);

        View v=inflater.inflate(R.layout.mammals_indiview,parent,false);

        return new AmusementsHolder(v);
    }

    @Override
    public void onBindViewHolder(final AmusementsHolder holder, int position) {
        holder.iview.setImageDrawable(lom.getResources().getDrawable(amusements_imgs[position]));
        holder.tview.setText(amusements[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.mamal_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(4);

                Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }



    @Override
    public int getItemCount() {
        return amusements_imgs.length;
    }
}
