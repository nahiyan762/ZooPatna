package cubex.zoopatna;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by mahesht on 10/27/2016.
 */

public class GalleryIndiViewHolder extends RecyclerView.ViewHolder {

    LinearLayout layout;

    public GalleryIndiViewHolder(View itemView) {
        super(itemView);

        layout=(LinearLayout)itemView.findViewById(R.id.lview_imgbg);

    }
}
