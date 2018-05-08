package cubex.zoopatna;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by mahesht on 10/14/2016.
 */

public class ContactsHolder extends RecyclerView.ViewHolder {

    ImageView iview;
    TextView tview;
    View v;

    public ContactsHolder(View itemView) {
        super(itemView);

        iview=(ImageView)itemView.findViewById(R.id.contacts_iview);
        tview=(TextView)itemView.findViewById(R.id.contacts_tview);
        v=itemView.findViewById(R.id.contacts_llayout);
    }

}
