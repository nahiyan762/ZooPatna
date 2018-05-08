package cubex.zoopatna;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by maheshthippala on 16/10/16.
 */
public class MammalsIndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getActivity(),"AMUsement idivisual  Soon", Toast.LENGTH_LONG).show();

        View v=inflater.inflate(R.layout.chimpanzee_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.mamal_llayout);

        wview=(WebView)v.findViewById(R.id.indi_wview);

        switch (AplicationConstants.mamal_name){
            case "Chimpanzee":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.chimpanzee));
                wview.loadData(getString(R.string.chimpanzee), "text/html; charset=utf-8", "utf-8");

                break;
            case "Giraffe":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.giraffe));
                wview.loadData(getString(R.string.giraffe), "text/html; charset=utf-8", "utf-8");

                break;
            case "Hippopotamus":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.hippo));
                wview.loadData(getString(R.string.hippopotamus), "text/html; charset=utf-8", "utf-8");

                break;
            case "Leopard":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.leopard));
                wview.loadData(getString(R.string.leopards), "text/html; charset=utf-8", "utf-8");

                break;
            case "Lion":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.lion));
                wview.loadData(getString(R.string.lion), "text/html; charset=utf-8", "utf-8");

                break;
            case "Otter":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.otter));
                wview.loadData(getString(R.string.otter), "text/html; charset=utf-8", "utf-8");

                break;
            case "Rhinoceros":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rhino));
                wview.loadData(getString(R.string.rhinoceros), "text/html; charset=utf-8", "utf-8");

                break;
            case "Tiger":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.tiger));
                wview.loadData(getString(R.string.tiger), "text/html; charset=utf-8", "utf-8");

                break;
            case "Zebra":
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.zebra));
                wview.loadData(getString(R.string.zebra), "text/html; charset=utf-8", "utf-8");

                break;

        }


        return v;
    }
}
