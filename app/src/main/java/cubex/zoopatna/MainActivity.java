package cubex.zoopatna;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    static ArrayList<Integer> ids = new ArrayList<Integer>();

    int random_num;
    String phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Patna Zoo");

        // toolbar.setNavigationIcon(R.drawable.menu);

        setSupportActionBar(toolbar);


        AplicationConstants.mActivity = this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setHomeAsUpIndicator(R.drawable.menu);

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ids.add(R.id.tickets);
        ids.add(R.id.maps);
        ids.add(R.id.gallery);
        ids.add(R.id.mammals);
        ids.add(R.id.birds);
        ids.add(R.id.reptiles);
        ids.add(R.id.fishes);
        ids.add(R.id.amusements);
        ids.add(R.id.contactus);


        for (Integer i : ids) {
            ((ImageView) findViewById(i)).setOnClickListener(this);
        }


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();

        switch (id){

            case R.id.rentals:
                FragmentManager rManager = getFragmentManager();
                FragmentTransaction rtx = rManager.beginTransaction();
                rtx.addToBackStack("back");
                rtx.replace(R.id.container, new RentalsIndiviewFragment());
                Toast.makeText(this,"Rental......",Toast.LENGTH_LONG).show();
                rtx.commit();

                break;

            case R.id.timing:
                FragmentManager tManager = getFragmentManager();
                FragmentTransaction ttx = tManager.beginTransaction();
                ttx.addToBackStack("back");
                ttx.replace(R.id.container, new TimingsIndiviewFragment());
                Toast.makeText(this,"Rental......",Toast.LENGTH_LONG).show();
                ttx.commit();

                break;
            case R.id.qr_code:

                if(isStoragePermissionGranted()) {
                    FragmentManager fManager = getFragmentManager();
                    FragmentTransaction tx = fManager.beginTransaction();
                    tx.addToBackStack("back");
                    tx.replace(R.id.container, new QRReaderFragment());
                    tx.commit();
                }

                break;
            case R.id.refund:
                FragmentManager refManager = getFragmentManager();
                FragmentTransaction reftx = refManager.beginTransaction();
                reftx.addToBackStack("back");
                reftx.replace(R.id.container, new Refund_Returns_IndiviewFragment());
                reftx.commit();

                break;
            case R.id.policy:
                FragmentManager pManager = getFragmentManager();
                FragmentTransaction ptx = pManager.beginTransaction();
                ptx.addToBackStack("back");
                ptx.replace(R.id.container, new Privacy_Policy_IndiviewFragment());
                ptx.commit();

                break;

            case R.id.membership:

                break;

            case R.id.zoo_calendar:

                FragmentManager calendar_fManager = getFragmentManager();
                FragmentTransaction calendar_tx = calendar_fManager.beginTransaction();
                calendar_tx.addToBackStack("back");
                calendar_tx.replace(R.id.container, new CalenderFragment());
                calendar_tx.commit();

                break;
            case R.id.comments:

                final SharedPreferences spf=getSharedPreferences("zoopat", Context.MODE_PRIVATE);
                String phno=spf.getString("phno","no value");
                if(phno.equalsIgnoreCase("no value")){





                    final Dialog d=new Dialog(this);
                    d.setTitle("Patna Zoo");
                    d.setContentView(R.layout.otp_dialog);
                    d.show();

                    final LinearLayout linearLayout1=(LinearLayout)d.findViewById(R.id.otp_llayout1);
                    final LinearLayout linearLayout2=(LinearLayout)d.findViewById(R.id.otp_llayout2);

                    final EditText otp_et1=(EditText)d.findViewById(R.id.opt_et1);
                    final EditText otp_et2=(EditText)d.findViewById(R.id.opt_et2);


                    ImageView otp_send1=(ImageView)d.findViewById(R.id.otp_send1);
                    ImageView otp_send2=(ImageView)d.findViewById(R.id.otp_send2);


                    otp_send1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(otp_et1.getText().toString().length()>0 && otp_et1.getText().toString().length()==10){

                                int random_num_=getRandomNum();

                                random_num=random_num_;
                                phone_number=otp_et1.getText().toString();
                                OTPSendTask task=new OTPSendTask(otp_et1.getText().toString(),random_num+"");
                                task.execute();

                                linearLayout1.setVisibility(View.GONE);
                                linearLayout2.setVisibility(View.VISIBLE);


                            }else{

                                otp_et1.setError("Enter Valid Phone Number");
                            }


                        }
                    });

                    otp_send2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(otp_et2.getText().toString().length()>0 && otp_et2.getText().toString().length()==4){

                                if(otp_et2.getText().toString().equalsIgnoreCase(random_num+"")){

                                    d.dismiss();

                                    SharedPreferences.Editor spe=spf.edit();
                                    spe.putString("phno",phone_number);
                                    spe.commit();

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(AplicationConstants.mActivity);
                                    builder.setTitle("Message");
                                    builder.setMessage("You have to SignIn with your Google Account to give comment , please click ok button to Signin");

                                    AlertDialog.OnClickListener listener = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            if (i == dialogInterface.BUTTON_POSITIVE) {

                                                FragmentManager fManager = getFragmentManager();
                                                FragmentTransaction tx = fManager.beginTransaction();
                                                tx.addToBackStack("back");
                                                tx.replace(R.id.container, new Google_Signn_In());
                                                tx.commit();


                                            }

                                            if (i == dialogInterface.BUTTON_NEGATIVE) {
                                                dialogInterface.dismiss();
                                            }

                                        }
                                    };


                                    builder.setPositiveButton("Ok", listener);
                                    builder.setNegativeButton("Cancel", listener);
                                    builder.show();



                                }

                            }else{
                                otp_et1.setError("OTP should not be Empty");
                            }

                        }
                    });


                }else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Message");
                    builder.setMessage("You have to SignIn with your Google Account to give comment , please click ok button to Signin");

                    AlertDialog.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (i == dialogInterface.BUTTON_POSITIVE) {

                                FragmentManager fManager = getFragmentManager();
                                FragmentTransaction tx = fManager.beginTransaction();
                                tx.addToBackStack("back");
                                tx.replace(R.id.container, new Google_Signn_In());
                                tx.commit();


                            }

                            if (i == dialogInterface.BUTTON_NEGATIVE) {
                                dialogInterface.dismiss();
                            }

                        }
                    };


                    builder.setPositiveButton("Ok", listener);
                    builder.setNegativeButton("Cancel", listener);
                    builder.show();


                /*
                FragmentManager fManager = getFragmentManager();
                FragmentTransaction tx = fManager.beginTransaction();
                tx.addToBackStack("back");
                tx.replace(R.id.container, new CommentsFragment());
                tx.commit();
*/

                } //else
                break;
            case R.id.terms:
                FragmentManager termManager = getFragmentManager();
                FragmentTransaction termtx = termManager.beginTransaction();
                termtx.addToBackStack("back");
                termtx.replace(R.id.container, new Term_Condition_IndiviewFragment());
                termtx.commit();

                break;

            case R.id.view_comments:



                final AlertDialog.Builder Abuilder=new AlertDialog.Builder(this);
                Abuilder.setTitle("Message");
                Abuilder.setMessage("Enter PIN Number");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                Abuilder.setView(input);
                AlertDialog.OnClickListener Alistener=new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(i==dialogInterface.BUTTON_POSITIVE){

                            if (input.getText().toString().trim().equals("1108")){
                                FragmentManager termManagerv = getFragmentManager();
                                FragmentTransaction termtv = termManagerv.beginTransaction();
                                termtv.addToBackStack("back");
                                termtv.replace(R.id.container, new View_comments());
                                termtv.commit();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Wrong PIN Number", Toast.LENGTH_SHORT).show();
                            }


                        }

                        if(i==dialogInterface.BUTTON_NEGATIVE){
                            dialogInterface.dismiss();
                        }

                    }
                };


                Abuilder.setPositiveButton("Ok",Alistener);
                Abuilder.setNegativeButton("Cancel",Alistener);
                Abuilder.show();


                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private int getRandomNum() {

        Random rnd=new Random();

        int random=rnd.nextInt(9999-1000)+1000;

        return random;
    }


    public void goto_comments(){
    FragmentManager fManager = getFragmentManager();
    FragmentTransaction tx = fManager.beginTransaction();
    tx.addToBackStack("back");
    tx.replace(R.id.container, new CommentsFragment());
    tx.commit();
}
    @Override
    public void onClick(View v) {

       // Toast.makeText(MainActivity.this, "Comming_____________ Soon", Toast.LENGTH_LONG).show();

        switch (v.getId()) {

            case R.id.tickets:
                Toast.makeText(MainActivity.this, "Comming Soon", Toast.LENGTH_LONG).show();

                break;
            case R.id.maps:
                FragmentManager fManager = getFragmentManager();
                FragmentTransaction tx = fManager.beginTransaction();
                tx.addToBackStack("back");
                tx.replace(R.id.container, new Map());
                tx.commit();
                break;
            case R.id.gallery:

                FragmentManager gManager = getFragmentManager();
                FragmentTransaction gxm = gManager.beginTransaction();
                gxm.addToBackStack("back");
                gxm.replace(R.id.container, new ListOfGallery());
                gxm.commit();


                break;
            case R.id.mammals:

                Toast.makeText(MainActivity.this, "mammlsentry Soon", Toast.LENGTH_LONG).show();

                FragmentManager mManager = getFragmentManager();
                FragmentTransaction txm = mManager.beginTransaction();
                txm.addToBackStack("back");
                txm.replace(R.id.container, new ListOfMammals());
                txm.commit();


                break;
            case R.id.birds:

                FragmentManager bManager = getFragmentManager();
                FragmentTransaction txb = bManager.beginTransaction();
                txb.addToBackStack("back");
                txb.replace(R.id.container, new ListOfBirds());
                txb.commit();

                break;
            case R.id.reptiles:

                FragmentManager rManager = getFragmentManager();
                FragmentTransaction txr = rManager.beginTransaction();
                txr.addToBackStack("back");
                txr.replace(R.id.container, new ListOfRepitiles());
                txr.commit();

                break;
            case R.id.fishes:

                FragmentManager fishManager = getFragmentManager();
                FragmentTransaction txf = fishManager.beginTransaction();
                txf.addToBackStack("back");
                txf.replace(R.id.container, new ListOfFishes());
                txf.commit();

                break;
            case R.id.amusements:

                FragmentManager aManager = getFragmentManager();
                FragmentTransaction txa = aManager.beginTransaction();
                txa.addToBackStack("back");
                txa.replace(R.id.container, new ListOfAmusements());
                txa.commit();

                Toast.makeText(MainActivity.this, "AMUZEMENT", Toast.LENGTH_LONG).show();
                break;
            case R.id.contactus:

                FragmentManager cManager = getFragmentManager();
                FragmentTransaction txc = cManager.beginTransaction();
                txc.addToBackStack("back");
                txc.replace(R.id.container, new LisOfContacts());
                txc.commit();

                break;
        }

    }

    public void displayFragment(int fragment_id) {
        switch (fragment_id) {

            case 1:
                FragmentManager fManager = getFragmentManager();
                FragmentTransaction tx = fManager.beginTransaction();
                tx.replace(R.id.container, new MammalsIndiviewFragment());
                tx.addToBackStack("back");
                tx.commit();

                break;

            case 2:

                FragmentManager bManager = getFragmentManager();
                FragmentTransaction txb = bManager.beginTransaction();
                txb.replace(R.id.container, new BirdsIndiviewFragment());
                txb.addToBackStack("back");
                txb.commit();

                break;

            case 3:

                FragmentManager rManager = getFragmentManager();
                FragmentTransaction txr = rManager.beginTransaction();
                txr.replace(R.id.container, new RepitilesIndiviewFragment());
                txr.addToBackStack("back");
                txr.commit();

                break;

            case 4:

                FragmentManager aManager = getFragmentManager();
                FragmentTransaction txa = aManager.beginTransaction();
                txa.replace(R.id.container, new AmugmentsIndiviewFragment());
                txa.addToBackStack("back");
                txa.commit();


                break;

            case 5:

                FragmentManager giManager = getFragmentManager();
                FragmentTransaction gixa = giManager.beginTransaction();
                gixa.replace(R.id.container, new GalleryIndiItems());
                gixa.addToBackStack("back");
                gixa.commit();


                break;

            case 6:

                FragmentManager cManager = getFragmentManager();
                FragmentTransaction cxa = cManager.beginTransaction();
                cxa.replace(R.id.container, new ContactsIndiviewFragment());
                cxa.addToBackStack("back");
                cxa.commit();


                break;

            case 7:

                FragmentManager fishManager = getFragmentManager();
                FragmentTransaction fxa = fishManager.beginTransaction();
                fxa.replace(R.id.container, new FishesIndiviewFragment());
                fxa.addToBackStack("back");
                fxa.commit();


                break;







        }


    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }


    class OTPSendTask extends AsyncTask{

        String phno_;
        String otp_num_;


        InputStream isr;

        public OTPSendTask(String phno,String otp_num){
            phno_=phno;
            otp_num_=otp_num;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object[] objects) {


            try {
                URL u = new URL("http://bsms.smarteria.in/http-api.php?username=zoopat&password=123456&senderid=ZooPat&route=2&number="+phno_+"&message=OTP:"+otp_num_);
                isr=u.openStream();

            }catch (Exception e){
                e.printStackTrace();

            }

            return null;
        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);



        }
    }




}