package cubex.zoopatna;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mahesht on 10/27/2016.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryIndiViewHolder> {

    int[] images_mammals={R.drawable.mam_1,R.drawable.mam_2,R.drawable.mam_3,R.drawable.mam_4,
            R.drawable.mam_5,R.drawable.mam_6,R.drawable.mam_7,R.drawable.mam_8,
            R.drawable.mam_9,R.drawable.mam_10,R.drawable.mam_11,R.drawable.mam_12};

    int[] images_birds={R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4};

    int[] images_reptiles={R.drawable.r1,R.drawable.r2,R.drawable.r3,R.drawable.r4,
            R.drawable.r5,R.drawable.r6,R.drawable.r7,R.drawable.r8,
            R.drawable.r9,R.drawable.r10,R.drawable.r11,R.drawable.r12};


    Activity activity;

    public GalleryAdapter(Activity activity) {
        this.activity=activity;
    }


    @Override
    public GalleryIndiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(activity);

        View v=inflater.inflate(R.layout.gallery_items_indi_view,parent,false);

        return new GalleryIndiViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(GalleryIndiViewHolder holder, final int position) {

      //  holder.layout.setBackgroundDrawable(activity.getResources().getDrawable(images[position]));

        if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Mammals")){

            Bitmap bmp= BitmapFactory.decodeResource(activity.getResources(),images_mammals[position]);

            Bitmap new_bmp=getResizedBitmap(bmp,200,200);

            holder.layout.setBackground(new BitmapDrawable(activity.getResources(),new_bmp));

        }else if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Birds")){

            Bitmap bmp= BitmapFactory.decodeResource(activity.getResources(),images_birds[position]);

            Bitmap new_bmp=getResizedBitmap(bmp,200,200);

            holder.layout.setBackground(new BitmapDrawable(activity.getResources(),new_bmp));

        }else if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Reptiles And Fishes")){

            Bitmap bmp= BitmapFactory.decodeResource(activity.getResources(),images_reptiles[position]);

            Bitmap new_bmp=getResizedBitmap(bmp,200,200);

            holder.layout.setBackground(new BitmapDrawable(activity.getResources(),new_bmp));
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AplicationConstants.gal_indiview_position=position;

                Gallery_Indiview_dialogfrag did=new Gallery_Indiview_dialogfrag();
                did.show(activity.getFragmentManager(),"");


            }
        });


    }

    @Override
    public int getItemCount() {

        int count=0;

        if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Mammals")){

            count=images_mammals.length;

        }else if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Gallery Birds")){

            count=images_birds.length;

        }else if(AplicationConstants.gallery_item_name.equalsIgnoreCase("Reptiles And Fishes")){

            count=images_reptiles.length;
        }


        return count;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }
}
