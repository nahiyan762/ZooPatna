package cubex.zoopatna;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by mahesht on 10/14/2016.
 */

public class FishesHolder extends RecyclerView.ViewHolder {

    ImageView iview;
    TextView tview;
    View v;

    public FishesHolder(View itemView) {
        super(itemView);

        iview=(ImageView)itemView.findViewById(R.id.fishes_iview);
        tview=(TextView)itemView.findViewById(R.id.fishes_tview);
        v=itemView.findViewById(R.id.fishes_llayout);
    }

}
