package cubex.zoopatna;

import android.app.Application;
import android.app.DialogFragment;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mahesht on 10/28/2016.
 */

public class Gallery_Indiview_dialogfrag extends DialogFragment {

    int[] images_mammals={R.drawable.mam_1,R.drawable.mam_2,R.drawable.mam_3,R.drawable.mam_4,
            R.drawable.mam_5,R.drawable.mam_6,R.drawable.mam_7,R.drawable.mam_8,
            R.drawable.mam_9,R.drawable.mam_10,R.drawable.mam_11,R.drawable.mam_12};

    int[] images_birds={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4};

    int[] images_reptiles={R.drawable.r1,R.drawable.r2,R.drawable.r3,R.drawable.r4,
            R.drawable.r5,R.drawable.r6,R.drawable.r7,R.drawable.r8,
            R.drawable.r9,R.drawable.r10,R.drawable.r11,R.drawable.r12};

    LinearLayout iview;

    ImageView previous;

    ImageView next;

    TextView tview;

    int size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.gallery_dialog,container,false);

        iview=(LinearLayout) v.findViewById(R.id.gd_view);
        previous=(ImageView)v.findViewById(R.id.back);
        next=(ImageView)v.findViewById(R.id.next);
        tview=(TextView)v.findViewById(R.id.gallery_indi_tv1);

       // getDialog().getWindow().setTitle(AplicationConstants.gallery_item_name);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(AplicationConstants.gal_indiview_position<(size-1)) {

                    AplicationConstants.gal_indiview_position = AplicationConstants.gal_indiview_position + 1;

                    if (AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Mammals")) {
                        // iview.setImageResource(images_mammals[AplicationConstants.gal_indiview_position]);

                        iview.setBackgroundResource(images_mammals[AplicationConstants.gal_indiview_position]);
                    } else if (AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Birds")) {
                        // iview.setImageResource(images_birds[AplicationConstants.gal_indiview_position]);

                        iview.setBackgroundResource(images_birds[AplicationConstants.gal_indiview_position]);

                    } else if (AplicationConstants.gallery_item_name.equalsIgnoreCase("Reptiles And Fishes")) {
                        // iview.setImageResource(images_reptiles[AplicationConstants.gal_indiview_position]);

                        iview.setBackgroundResource(images_reptiles[AplicationConstants.gal_indiview_position]);

                    }
                    tview.setText((AplicationConstants.gal_indiview_position+1)+"/"+size);
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(AplicationConstants.gal_indiview_position>0) {

                    AplicationConstants.gal_indiview_position = AplicationConstants.gal_indiview_position - 1;

                    if (AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Mammals")) {
                        // iview.setImageResource(images_mammals[AplicationConstants.gal_indiview_position]);

                        iview.setBackgroundResource(images_mammals[AplicationConstants.gal_indiview_position]);
                    } else if (AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Birds")) {
                        // iview.setImageResource(images_birds[AplicationConstants.gal_indiview_position]);

                        iview.setBackgroundResource(images_birds[AplicationConstants.gal_indiview_position]);

                    } else if (AplicationConstants.gallery_item_name.equalsIgnoreCase("Reptiles And Fishes")) {
                        // iview.setImageResource(images_reptiles[AplicationConstants.gal_indiview_position]);

                        iview.setBackgroundResource(images_reptiles[AplicationConstants.gal_indiview_position]);

                    }

                    tview.setText((AplicationConstants.gal_indiview_position+1)+"/"+size);

                }
            }
        });


        if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Mammals")){
               // iview.setImageResource(images_mammals[AplicationConstants.gal_indiview_position]);

                  iview.setBackgroundResource(images_mammals[AplicationConstants.gal_indiview_position]);

                  size=images_mammals.length;

        }else if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Birds")){
           // iview.setImageResource(images_birds[AplicationConstants.gal_indiview_position]);

            iview.setBackgroundResource(images_birds[AplicationConstants.gal_indiview_position]);

                size=images_birds.length;

        }else if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Reptiles And Fishes")){
           // iview.setImageResource(images_reptiles[AplicationConstants.gal_indiview_position]);

            iview.setBackgroundResource(images_reptiles[AplicationConstants.gal_indiview_position]);

            size=images_reptiles.length;

        }

        tview.setText((AplicationConstants.gal_indiview_position+1)+"/"+size);


        return v;
    }
}
