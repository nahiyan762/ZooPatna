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
public class Refund_Returns_IndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    WebSettings webSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.refund_returns_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.refund_return_llayout);

        wview=(WebView)v.findViewById(R.id.refund_return_indi_wview);
               // linearLayout.setVisibility(LinearLayout.VISIBLE);
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.sanjay));
                wview.loadData(getString(R.string.refund_return_policy), "text/html; charset=utf-8", "utf-8");

        return v;
    }
}
