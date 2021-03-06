package cubex.zoopatna;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

/**
 * Created by maheshthippala on 16/10/16.
 */
public class Term_Condition_IndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    WebSettings webSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.term_condition_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.term_condition_llayout);

        wview=(WebView)v.findViewById(R.id.term_condition_indi_wview);
               // linearLayout.setVisibility(LinearLayout.VISIBLE);
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.sanjay));
                wview.loadData(getString(R.string.term_and_condition), "text/html; charset=utf-8", "utf-8");

        return v;
    }
}
