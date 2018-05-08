package cubex.zoopatna;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahesht on 11/9/2016.
 */

public class List_Adapter extends BaseAdapter {

    Activity activity_;
    ArrayList<String> name_, email_, phone_, comment_;

    public List_Adapter(Activity activity, ArrayList<String> name, ArrayList<String> comment, ArrayList<String> email, ArrayList<String> phone) {


   this.name_=name;
        this.activity_=activity;
        this.phone_=phone;
        this.comment_=comment;
        this.email_=email;
        this.name_=name;
    }

    @Override
    public int getCount() {
        return name_.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(activity_);
        View v=inflater.inflate(R.layout.comments_view, null);

        FirebaseStorage mFirebaseStorage=FirebaseStorage.getInstance();
        StorageReference mStorageReference=mFirebaseStorage.getReference("Comment_Pictures");

        final ImageView image=(ImageView)v.findViewById(R.id.comment_image);
        TextView txt_name, txt_comment, txt_mail, txt_phone;
        txt_name=(TextView)v.findViewById(R.id.comment_name);
        txt_mail=(TextView)v.findViewById(R.id.comment_email);
        txt_comment=(TextView)v.findViewById(R.id.comment_text);
        txt_name=(TextView)v.findViewById(R.id.comment_name);
        txt_phone=(TextView)v.findViewById(R.id.comment_phone);


        txt_name.setText("Name :"+name_.get(i));
        txt_mail.setText("Mail :"+email_.get(i));
        txt_phone.setText("Phone :"+phone_.get(i));
        txt_comment.setText("Comment :"+comment_.get(i));



        mStorageReference.child(AplicationConstants.keys.get(i)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(activity_).load(uri).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });



        return null;
    }
}
