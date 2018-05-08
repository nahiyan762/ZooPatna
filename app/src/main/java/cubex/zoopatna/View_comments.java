package cubex.zoopatna;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class View_comments extends Fragment {

// ListView comments_list;

    FirebaseDatabase mFirebaseDatabase;
    FirebaseStorage mFirebaseStorage;

    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;

    ArrayList<String> name, comment, email, phone, keys;
    ArrayList<Uri> image_urlss;
    ProgressDialog pdialog;

    RecyclerView rview_Comments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.view_comments,container, false);
       // comments_list=(ListView)v.findViewById(R.id.comments_list);
        mFirebaseStorage=FirebaseStorage.getInstance();
        mStorageReference=mFirebaseStorage.getReference("Comment_Pictures");

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("Comment_Text");


        name=new ArrayList<String>();
        comment=new ArrayList<String>();
        email=new ArrayList<String>();
        phone=new ArrayList<String>();
        keys=new ArrayList<String>();
        image_urlss=new ArrayList<Uri>();


        pdialog=new ProgressDialog(getActivity());
        pdialog.setMessage("Getting Comments");
        pdialog.setCancelable(false);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name.clear();
                comment.clear();
                email.clear();
                phone.clear();
                keys.clear();
                for (DataSnapshot object : dataSnapshot.getChildren()) {

                    keys.add(object.getKey());
                    Comment_Model user =object.getValue(Comment_Model.class);
                    Toast.makeText(getActivity(),user.comment,Toast.LENGTH_SHORT).show();
                    name.add(user.name);
                    comment.add(user.comment);
                    email.add(user.email);
                    phone.add(user.phone_num);

                }
                // image_urls(keys);
                AplicationConstants.keys=keys;
                pdialog.cancel();
               /* List_Adapter adp=new List_Adapter(getActivity(),name, comment, email,phone);
                comments_list.setAdapter(adp);*/


                rview_Comments.setAdapter(new CommentsAdapter(name, comment, email,phone));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Firebase Error", Toast.LENGTH_LONG);
            }
        });

        rview_Comments=(RecyclerView)v.findViewById(R.id.rview_vcomments);

        RecyclerView.LayoutManager lManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        rview_Comments.setLayoutManager(lManager);



        return v;
    }
}
