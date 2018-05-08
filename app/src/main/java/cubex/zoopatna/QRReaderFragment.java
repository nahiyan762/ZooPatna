package cubex.zoopatna;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;


public class QRReaderFragment extends Fragment implements OnQRCodeReadListener
{

	private Vibrator vibrator;
  	private QRCodeReaderView mydecoderview;
	private ImageView line_image;
	AlertDialog.Builder builder;
	OnQRCodeReadListener qrCodeReader;


	 @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
		 vibrator=(Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		 
			View view=inflater.inflate(R.layout.activity_decoder, container, false);

			this.qrCodeReader=this;

		 	mydecoderview = (QRCodeReaderView)view.findViewById(R.id.qrdecoderview);
        	mydecoderview.setOnQRCodeReadListener(this);

        
        line_image = (ImageView)view.findViewById(R.id.red_line_image);
        
        TranslateAnimation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
       mAnimation.setDuration(1000);
       mAnimation.setRepeatCount(0);
       mAnimation.setRepeatMode(Animation.REVERSE);
       mAnimation.setInterpolator(new LinearInterpolator());
       line_image.setAnimation(mAnimation);
       
     return view;   
    }
    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
	@Override
	public void onQRCodeRead(final String text, PointF[] points) {
		//myTextView.setText(text);

		Toast.makeText(getActivity().getApplicationContext(),
					text, Toast.LENGTH_LONG).show();

		builder=new AlertDialog.Builder(getActivity()); //ApplicationConstants.getAlertDialog(getActivity(), "QR Result");
		builder.setMessage(text);

		mydecoderview.getCameraManager().stopPreview();
		
		mydecoderview.setOnQRCodeReadListener(null);
		
		builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(final DialogInterface dialog, int which) {

				dialog.dismiss();

				getFragmentManager().popBackStack();

				}
		});
		
        builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				mydecoderview.getCameraManager().startPreview();
				mydecoderview.setOnQRCodeReadListener(qrCodeReader);
				
			dialog.dismiss();
				
			}
		});
		builder.show();
		
		
	}

	
	// Called when your device have no camera
	@Override
	public void cameraNotFound() {
		
	}

	// Called when there's no QR codes in the camera preview image
	@Override
	public void QRCodeNotFoundOnCamImage() {
		
	}
    
	@Override
	public void onResume() {
		super.onResume();
		mydecoderview.getCameraManager().startPreview();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mydecoderview.getCameraManager().stopPreview();
	}
}
