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
public class RepitilesIndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.gharial_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.reptiles_llayout);

        wview=(WebView)v.findViewById(R.id.gharial_indi_wview);
        switch (AplicationConstants.mamal_name){

            case "Gharial":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.gharial));
                wview.loadData(getString(R.string.gharial), "text/html; charset=utf-8", "utf-8");

                break;
            case "Turtle":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.turtle));
                wview.loadData(getString(R.string.turtle), "text/html; charset=utf-8", "utf-8");

                break;
            case "Snake":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.r4));
                wview.loadData(getString(R.string.snake), "text/html; charset=utf-8", "utf-8");

                break;
        }

        return v;
    }
}
