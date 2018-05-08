package cubex.zoopatna;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class AddEvent extends Activity {

	private static final int READ_REQUEST_CODE=42;
	ImageView addPhoto;
	Button add,cancel;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.add_event);
	    cancel=(Button) findViewById(R.id.cancel);
	    add=(Button) findViewById(R.id.add);
	    cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	    addPhoto=(ImageView) findViewById(R.id.addPhoto);
	    addPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in=new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				in.setType("image/*");
				startActivityForResult(in, READ_REQUEST_CODE);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
		super.onActivityResult(requestCode, resultCode, resultData);
		if (requestCode== READ_REQUEST_CODE && resultCode== Activity.RESULT_OK){
			Uri uri = null;
	        if (resultData != null) {
	            uri = resultData.getData();
	            addPhoto.setImageURI(uri);
	            addPhoto.getLayoutParams().height=addPhoto.getHeight();
	            addPhoto.getLayoutParams().width=addPhoto.getWidth();
	}
	        
}
}
}