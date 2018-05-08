package cubex.zoopatna;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by mahesht on 10/14/2016.
 */

public class BirdsHolder extends RecyclerView.ViewHolder {

    ImageView iview;
    TextView tview;
    View v;

    public BirdsHolder(View itemView) {
        super(itemView);

        iview=(ImageView)itemView.findViewById(R.id.birds_iview);
        tview=(TextView)itemView.findViewById(R.id.birds_tview);
        v=itemView.findViewById(R.id.birds_llayout);
    }

}
