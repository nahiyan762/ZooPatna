package cubex.zoopatna;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class Google_Signn_In extends Fragment {

    MainActivity mainActivity;
    SignInButton sign_in;

    private  static final int RC_SIGN_IN = 1;
    GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private static final String TAG="ACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressDialog mPdDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_google__signn__in,container,false);
        mainActivity=(MainActivity)getActivity();
        sign_in=(SignInButton)v.findViewById(R.id.google_signin);
mPdDialog=new ProgressDialog(getActivity());
        mPdDialog.setMessage("Please Wait...");
        mPdDialog.setCancelable(false);
        mAuth=FirebaseAuth.getInstance();


        mAuthStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){

if (mPdDialog.isShowing()){
    mPdDialog.dismiss();
}
                    AplicationConstants.username=firebaseAuth.getCurrentUser().getDisplayName();
                    AplicationConstants.key=firebaseAuth.getCurrentUser().getUid();
                    AplicationConstants.email=firebaseAuth.getCurrentUser().getEmail();

                    mainActivity.goto_comments();
                   // Toast.makeText(getActivity(),firebaseAuth.getCurrentUser().getDisplayName()+"", Toast.LENGTH_LONG).show();
                }
                if (firebaseAuth.getCurrentUser()==null){
                    if (mPdDialog.isShowing()){
                        mPdDialog.dismiss();
                    }
                  //  Toast.makeText(getActivity(),"login failld", Toast.LENGTH_LONG).show();

                }
            }
        };


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        try {

            mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).enableAutoManage(mainActivity, new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                }
            }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

            sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    signIn();

                }
            });
        }catch (Exception e){

        }

        return v;
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                mPdDialog.show();
                GoogleSignInAccount account = result.getSignInAccount();
                Toast.makeText(getActivity(),result.getSignInAccount().getDisplayName()+"", Toast.LENGTH_LONG).show();
                firebaseAuthWithGoogle(account);
            } else {


            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(mainActivity, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(AplicationConstants.mActivity);
        mGoogleApiClient.disconnect();
    }
}
