package cubex.zoopatna;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahesht on 11/9/2016.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsHolder> {

    ArrayList<String> names;
    ArrayList<String> comments;
    ArrayList<String> phones;
    ArrayList<String> emails;


    FirebaseStorage mFirebaseStorage;
    StorageReference mStorageReference;

    public CommentsAdapter(ArrayList<String> name, ArrayList<String> comment, ArrayList<String> email, ArrayList<String> phone) {
        names=name;
        comments=comment;
        phones=phone;
        emails=email;
    }

    @Override
    public CommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(AplicationConstants.mActivity);

        View v=inflater.inflate(R.layout.comments_view,parent,false);


         mFirebaseStorage=FirebaseStorage.getInstance();
         mStorageReference=mFirebaseStorage.getReference("Comment_Pictures");


        return new CommentsHolder(v);
    }

    @Override
    public void onBindViewHolder(final CommentsHolder holder, int i) {


        holder.name.setText("Name :"+names.get(i));
        holder.email.setText("Mail :"+emails.get(i));
        holder.phno.setText("Phone :"+phones.get(i));
        holder.comment.setText("Comment :"+comments.get(i));


        mStorageReference.child(AplicationConstants.keys.get(i)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(AplicationConstants.mActivity).load(uri).into(holder.iview);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


    }

    @Override
    public int getItemCount() {
        return AplicationConstants.keys.size();
    }
}
