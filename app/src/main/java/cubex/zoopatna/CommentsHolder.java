package cubex.zoopatna;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mahesht on 11/9/2016.
 */

public class CommentsHolder extends RecyclerView.ViewHolder {

    ImageView iview;

    TextView name;

    TextView phno;

    TextView email;

    TextView comment;

    public CommentsHolder(View itemView) {
        super(itemView);

        iview=(ImageView)itemView.findViewById(R.id.comment_image);

        name=(TextView)itemView.findViewById(R.id.comment_name);
        phno=(TextView)itemView.findViewById(R.id.comment_phone);
        email=(TextView)itemView.findViewById(R.id.comment_email);
        comment=(TextView)itemView.findViewById(R.id.comment_text);



    }
}
