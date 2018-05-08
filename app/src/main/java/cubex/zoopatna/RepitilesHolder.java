package cubex.zoopatna;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by mahesht on 10/14/2016.
 */

public class RepitilesHolder extends RecyclerView.ViewHolder {

    ImageView iview;
    TextView tview;
    View v;

    public RepitilesHolder(View itemView) {
        super(itemView);

        iview=(ImageView)itemView.findViewById(R.id.reptiles_iview);
        tview=(TextView)itemView.findViewById(R.id.reptiles_tview);
        v=itemView.findViewById(R.id.reptiles_llayout);
    }

}
