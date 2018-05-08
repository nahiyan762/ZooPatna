package cubex.zoopatna;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by mahesht on 10/14/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsHolder> {

    Activity lom;

    int[] contacts_imgs=new int[]{
            R.drawable.amusements1,
            R.drawable.amusements2,
            R.drawable.amusements3,
            R.drawable.amusements4,
            };

    String[]contacts ;

    public ContactsAdapter(Activity listOfContacts) {

        this.lom=listOfContacts;

        contacts=lom.getResources().getStringArray(R.array.contactus);

    }


    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater=LayoutInflater.from(lom);

        View v=inflater.inflate(R.layout.contacts_indiview,parent,false);

        return new ContactsHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContactsHolder holder, int position) {
        holder.iview.setImageDrawable(lom.getResources().getDrawable(contacts_imgs[position]));
        holder.tview.setText(contacts[position]);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AplicationConstants.mamal_name=holder.tview.getText().toString();
                AplicationConstants.mActivity.displayFragment(6);

                Toast.makeText(lom,holder.tview.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return contacts_imgs.length;
    }
}
