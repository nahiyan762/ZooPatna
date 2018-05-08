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
public class FishesIndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.color_fishes_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.fishes_llayout);

        wview=(WebView)v.findViewById(R.id.color_fishes_indi_wview);
        switch (AplicationConstants.mamal_name){

            case "Fishes":

                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.r12));
                wview.loadData(getString(R.string.fish), "text/html; charset=utf-8", "utf-8");

                break;
        }

        return v;
    }
}
