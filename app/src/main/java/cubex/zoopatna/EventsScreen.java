package cubex.zoopatna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class EventsScreen extends Activity {

	Button back, addEvent, eventsOfDay;
	ListView displayEvents;
	ListAdapter eventList;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.events_of_the_day);
	    back=(Button) findViewById(R.id.back_to_calender);
	    eventsOfDay=(Button) findViewById(R.id.eventOfTheDay);
	    Bundle b=getIntent().getExtras();
	    if(b!=null){
	    	String month=b.getString("Month");
		    eventsOfDay.setText(month);

	    }
	    addEvent=(Button) findViewById(R.id.add_event);
	    displayEvents=(ListView) findViewById(R.id.events_list);
	    back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	    addEvent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),AddEvent.class);
				startActivity(intent);
			}
		});
	}

}
