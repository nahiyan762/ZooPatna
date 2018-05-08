package cubex.zoopatna;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

/**
 * Created by mahesht on 10/14/2016.
 */

public class Map extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.zoo_map,container,false);

        WebView map=(WebView)v.findViewById(R.id.webView);

        map.loadUrl("file:///android_asset/docccc.html");
        map.getSettings().setBuiltInZoomControls(true);
        map.setInitialScale(getScale());





        return v;
    }

    private int getScale(){
        Display display = ((WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(4963);
        val = val * 100d;
        return val.intValue();
    }
}
