package cubex.zoopatna;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mahesht on 10/27/2016.
 */

public class GalleryIndiItems extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.list_of_gallery_items,container,false);

        RecyclerView rview=(RecyclerView)v.findViewById(R.id.gallery_items);

       // RecyclerView.LayoutManager lmanager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        GridLayoutManager gManager=new GridLayoutManager(getActivity(),2);

        rview.setLayoutManager(gManager);

        rview.setAdapter(new GalleryAdapter(getActivity()));

        return v;
    }
}
