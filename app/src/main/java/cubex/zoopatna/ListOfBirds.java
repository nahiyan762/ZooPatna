package cubex.zoopatna;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mahesht on 10/14/2016.
 */

public class ListOfBirds extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.list_of_birds,container,false);

        RecyclerView rview=(RecyclerView)v.findViewById(R.id.list_of_birds);

        RecyclerView.LayoutManager lmanager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        rview.setLayoutManager(lmanager);

        rview.setAdapter(new BirdsAdapter(getActivity()));

        return v;
    }
}
