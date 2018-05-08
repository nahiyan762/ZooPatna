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
public class AmugmentsIndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.greenhouse_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.amusment_llayout);

        wview=(WebView)v.findViewById(R.id.green_house_indi_wview);

        switch (AplicationConstants.mamal_name){
            case "Green House":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_house));
                wview.loadData(getString(R.string.greenhouse), "text/html; charset=utf-8", "utf-8");
                break;
            case "Herbal Garden":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.herbal_garden));
                wview.loadData(getString(R.string.herbalgarden), "text/html; charset=utf-8", "utf-8");

                break;
            case "Rose Garden":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rose_garden));
                wview.loadData(getString(R.string.rosegarden), "text/html; charset=utf-8", "utf-8");

                break;
            case "Children Park":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.children_park));
                wview.loadData(getString(R.string.childrenpark), "text/html; charset=utf-8", "utf-8");

                break;
            case "Jungle Trail":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.jungle_trial));
                wview.loadData(getString(R.string.jungletrail), "text/html; charset=utf-8", "utf-8");

                break;
            case "Lake":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.lake));
                wview.loadData(getString(R.string.lake), "text/html; charset=utf-8", "utf-8");

                break;
            case "Toy Train":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.toy_trian));
                wview.loadData(getString(R.string.toytrain), "text/html; charset=utf-8", "utf-8");

                break;
            case "Tree House":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.tree_house));
                wview.loadData(getString(R.string.treehouse), "text/html; charset=utf-8", "utf-8");

                break;

        }


        return v;
    }
}
