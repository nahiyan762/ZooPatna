package cubex.zoopatna;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * Created by maheshthippala on 16/10/16.
 */
public class ContactsIndiviewFragment extends Fragment {

    WebView wview;

    LinearLayout linearLayout;

    WebSettings webSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.facebook_fragment,container,false);

       linearLayout=(LinearLayout)v.findViewById(R.id.contacts_llayout);

        wview=(WebView)v.findViewById(R.id.facebook_indi_wview);
        switch (AplicationConstants.mamal_name){

            case "Facebook":
               // wview = (WebView) v.findViewById(R.id.facebook_indi_wview);

                linearLayout.setVisibility(LinearLayout.GONE);
                webSettings= wview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setBuiltInZoomControls(true);
                wview.loadUrl("https://www.facebook.com/patnazoo11");
                wview.setWebViewClient(new WebViewClient());

                break;
            case "Twitter":
                linearLayout.setVisibility(LinearLayout.GONE);
                //wview = (WebView) v.findViewById(R.id.facebook_indi_wview);
                WebSettings webSettings = wview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setBuiltInZoomControls(true);
                wview.loadUrl("http://www.twitter.com");
                wview.setWebViewClient(new WebViewClient());

                break;

            case "Youtube":
                linearLayout.setVisibility(LinearLayout.GONE);
              //  wview = (WebView) v.findViewById(R.id.facebook_indi_wview);
                webSettings= wview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setBuiltInZoomControls(true);
                wview.loadUrl("https://www.youtube.com/channel/UCoRQFR3CktSGF78KxTl1Qyg");
                wview.setWebViewClient(new WebViewClient());

                break;

            case "Reach Us":
                linearLayout.setVisibility(LinearLayout.VISIBLE);
                linearLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.sanjay));
                wview.loadData(getString(R.string.reachus), "text/html; charset=utf-8", "utf-8");

                break;
        }

        return v;
    }
}
