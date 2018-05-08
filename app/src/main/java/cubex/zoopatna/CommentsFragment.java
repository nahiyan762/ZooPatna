package cubex.zoopatna;

import android.*;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Random;

/**
 * Created by mahesht on 10/28/2016.
 */

public class CommentsFragment extends Fragment {


    ImageView camera,gallery,send;
    EditText et1;
    LinearLayout lLayout;
    boolean camera_sel=false,gallery_sel=false;

    Uri image_uri;


    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirDatabaseReference;

private FirebaseAuth mAuth;
    private ProgressDialog pdialog;

    String comment_key;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.comments_bg,container,false);

         camera=(ImageView)v.findViewById(R.id.c_camera);
         gallery=(ImageView)v.findViewById(R.id.c_gallery);
         send=(ImageView)v.findViewById(R.id.send);
         et1=(EditText)v.findViewById(R.id.c_et1);
         lLayout=(LinearLayout)v.findViewById(R.id.c_photo);

        pdialog=new ProgressDialog(getActivity());
        pdialog.setMessage("Submitting Comment");
        pdialog.setCancelable(false);

mAuth=FirebaseAuth.getInstance();
        mFirebaseStorage=FirebaseStorage.getInstance();
        mStorageReference=mFirebaseStorage.getReference("Comment_Pictures");

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mFirDatabaseReference=mFirebaseDatabase.getReference("Comment_Text");

        camera.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {

                if(isPermissionS_Granted())
                {
                    Intent cameraIntent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 123);
                }


            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isPermissionS_Granted())
                {
                    Intent i=new Intent();
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    i.setType("image/*");
                    startActivityForResult(i,124);
                }


            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (camera_sel || gallery_sel){


                    SharedPreferences spf=AplicationConstants.mActivity.getSharedPreferences("zoopat", AplicationConstants.mActivity.MODE_PRIVATE);
                    String phone_num=spf.getString("phno","No Phone Number");
                    int random_num_=getRandomNum();
                    comment_key=random_num_+"";

                    if (et1.getText().toString().length() > 0) {
                        pdialog.show();
                        createUser(AplicationConstants.username, AplicationConstants.email, phone_num , et1.getText().toString());

                       // getFragmentManager().popBackStack();



                    } else {

                        Toast.makeText(getActivity().getApplicationContext(),
                                "Comments should not be empty", Toast.LENGTH_LONG).show();
                    }

            }else{
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Photo should not be empty", Toast.LENGTH_LONG).show();

                }


            }
        });


        return v;
    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==123 && resultCode== Activity.RESULT_OK){
            image_uri=data.getData();
            Bitmap bmp=(Bitmap)data.getExtras().get("data");

            lLayout.setBackground(new BitmapDrawable(getActivity().getResources(),bmp));
            camera_sel=true;

        }else  if(requestCode==124 && resultCode== Activity.RESULT_OK){

            try {
                image_uri=data.getData();
                Bitmap bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                lLayout.setBackground(new BitmapDrawable(getActivity().getResources(), bmp));
                gallery_sel=true;

            }catch (Exception e){
                e.printStackTrace();
            }
        }




    }

    private void createUser(String name, String email, String phone, String comment) {
        Comment_Model user = new Comment_Model(name, email,phone, comment);
        mFirDatabaseReference.child(comment_key).setValue(user);
        addUserChangeListener();
    }

    private void addUserChangeListener() {


        mFirDatabaseReference.child(comment_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
pdialog.cancel();
                pdialog.setMessage("Uploading Image");
                pdialog.show();
                Comment_Model user = dataSnapshot.getValue(Comment_Model.class);

                // Check for null
                if (user == null) {
                    Log.e("", "User data is null!");
                    return;
                }
                StorageReference path=mStorageReference.child(comment_key);
                path.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pdialog.dismiss();
                        Toast.makeText(getActivity(),"Submitted", Toast.LENGTH_LONG).show();
                        Uri uri=taskSnapshot.getDownloadUrl();
                      //  Picasso.with((getActivity())).load(uri).into(camera);
                        mAuth.signOut();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
                    }
                });




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("", "Failed to read user", error.toException());
            }
        });
    }
    public  boolean isPermissionS_Granted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED&&getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private int getRandomNum() {

        Random rnd=new Random();

        int random=rnd.nextInt(99990-10000)+10000;

        return random;
    }
}
