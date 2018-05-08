package cubex.zoopatna;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

/**
 * Created by maheshthippala on 16/10/16.
 */
public class BirdsIndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.emu_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.birds_llayout);

        wview=(WebView)v.findViewById(R.id.emu_indi_wview);
        //Toast.makeText(getActivity(),AplicationConstants.birds_name+"aaaaaaaaaaaaaaaa",Toast.LENGTH_LONG).show();
        switch (AplicationConstants.mamal_name){

            case "Emu":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.emu));
                wview.loadData(getString(R.string.emu), "text/html; charset=utf-8", "utf-8");

                break;
            case "Ostrich":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ostrich));
                wview.loadData(getString(R.string.ostrich), "text/html; charset=utf-8", "utf-8");

                break;

        }

        return v;
    }
}
