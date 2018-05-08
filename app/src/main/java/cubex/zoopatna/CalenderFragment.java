package cubex.zoopatna;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by mahesht on 11/9/2016.
 */

public class CalenderFragment extends Fragment implements View.OnClickListener{

    private static final String tag = "SimpleCalendarViewActivity";

    private ImageView calendarToJournalButton;
    private TextView currentMonth;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private GridView calendarView;
    private GridCellAdapter adapter;
    private Calendar _calendar;
    private TextView dateText;
    private int month, year;
    private final DateFormat dateFormatter = new DateFormat();
    private static final String dateTemplate = "MMMM yyyy";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      View v=inflater.inflate(R.layout.simple_calendar_view,container,false);


        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);
        ////log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);


        prevMonth = (ImageView) v.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (TextView) v.findViewById(R.id.currentMonth);
        currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));

        nextMonth = (ImageView) v.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) v.findViewById(R.id.calendar);

        // Initialised
        adapter = new GridCellAdapter(getActivity().getApplicationContext(), R.id.calendar_day_cell, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

        return v;
    }

    /**
     *
     * @param month
     * @param year
     */
    private void setGridCellAdapterToDate(int month, int year)
    {
        adapter = new GridCellAdapter(getActivity().getApplicationContext(), R.id.calendar_day_cell, month, year);
        _calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
        currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        if (v == prevMonth)
        {
            if (month <= 1)
            {
                month = 12;
                year--;
            }
            else
            {
                month--;
            }
            ////log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(month, year);
        }
        if (v == nextMonth)
        {
            if (month > 11)
            {
                month = 1;
                year++;
            }
            else
            {
                month++;
            }
            ////log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(month, year);
        }

    }

    @Override
    public void onDestroy()
    {
        ////log.d(tag, "Destroying View ...");
        super.onDestroy();
    }

    // ///////////////////////////////////////////////////////////////////////////////////////
    // Inner Class
    public class GridCellAdapter extends BaseAdapter implements View.OnClickListener
    {
        private static final String tag = "GridCellAdapter";
        private final Context _context;

        private final List<String> list;
        private static final int DAY_OFFSET = 1;
        private final String[] weekdays = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        private final String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private final int month, year;
        private int daysInMonth, prevMonthDays;
        private int currentDayOfMonth;
        private int currentWeekDay;
        private ImageView gridcell;
        private TextView num_events_per_day;
        private TextView num_anv_events_per_day;
        private Button dateButton;
        private final HashMap eventsPerMonthMap;
        private final HashMap anvEventsPerMonthMap;
        private final List eventsMap;
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

        // Days in Current Month
        public GridCellAdapter(Context context, int textViewResourceId, int month, int year)
        {
            super();
            this._context = context;
            this.list = new ArrayList<String>();
            this.month = month;
            this.year = year;

            //log.d(tag, "==> Passed in Date FOR Month: " + month + " " + "Year: " + year);
            Calendar calendar = Calendar.getInstance();
            setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
            setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
            //log.d(tag, "New Calendar:= " + calendar.getTime().toString());
            //log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
            //log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

            // Print Month
            printMonth(month, year);

            // Find Number of Events
            //eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
            eventsMap = findNumberOfEventsPerMonth(year, month);
            eventsPerMonthMap = (HashMap) eventsMap.get(0);
            anvEventsPerMonthMap = (HashMap) eventsMap.get(1);
        }
        private String getMonthAsString(int i)
        {
            return months[i];
        }

        private String getWeekDayAsString(int i)
        {
            return weekdays[i];
        }

        private int getNumberOfDaysOfMonth(int i)
        {
            return daysOfMonth[i];
        }

        public String getItem(int position)
        {
            return list.get(position);
        }

        @Override
        public int getCount()
        {
            return list.size();
        }

        /**
         * Prints Month
         *
         * @param mm
         * @param yy
         */
        private void printMonth(int mm, int yy)
        {
            //log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
            // The number of days to leave blank at
            // the start of this month.
            int trailingSpaces = 0;
            int leadSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;

            int currentMonth = mm - 1;
            String currentMonthName = getMonthAsString(currentMonth);
            daysInMonth = getNumberOfDaysOfMonth(currentMonth);

            //log.d(tag, "Current Month: " + " " + currentMonthName + " having " + daysInMonth + " days.");

            // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
            GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
            //log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

            if (currentMonth == 11)
            {
                prevMonth = currentMonth - 1;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
                //log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else if (currentMonth == 0)
            {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 1;
                //log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else
            {
                prevMonth = currentMonth - 1;
                nextMonth = currentMonth + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                ////log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }

            // Compute how much to leave before the first day of the
            // month.
            // getDay() returns 0 for Sunday.
            int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
            trailingSpaces = currentWeekDay;

            ////log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay));
            ////log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
            ////log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

            //goud changed 1 to 2 below
            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 2)
            {
                ++daysInMonth;
            }

            //goud added below if loop to check leap year for previous month
            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 3)
            {
                ++daysInPrevMonth;
            }

            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++)
            {
                ////log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
                list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
            }

            // Current Month Days
            for (int i = 1; i <= daysInMonth; i++)
            {
                ////log.d(currentMonthName, String.valueOf(i) + " " + getMonthAsString(currentMonth) + " " + yy);
                if (i == getCurrentDayOfMonth())
                {
                    list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
                else
                {
                    list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
            }

            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++)
            {
                ////log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
                list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
            }
        }

        /**
         * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * ALL entries from a SQLite database for that month. Iterate over the
         * List of All entries, and get the dateCreated, which is converted into
         * day.
         *
         * @param year
         * @param month
         * @return
         */
        //private HashMap findNumberOfEventsPerMonth(int year, int month)
        private List findNumberOfEventsPerMonth(int year, int month)
        {

            List<HashMap<String, Integer>> eventMaps = new ArrayList<HashMap<String, Integer>>();

            HashMap birthDayMap = new HashMap<String, Integer>();
            //goud wrote below three lines
            birthDayMap.put("11",3);
            birthDayMap.put("20", 2);
            birthDayMap.put("25",1);

            HashMap anniversaryMap = new HashMap<String, Integer>();
            //goud wrote below three lines
            anniversaryMap.put("1",5);
            anniversaryMap.put("5", 2);
            anniversaryMap.put("28",3);
            anniversaryMap.put("20", 5);

            eventMaps.add(birthDayMap);
            eventMaps.add(anniversaryMap);
            // DateFormat dateFormatter2 = new DateFormat();
            //
            // String day = dateFormatter2.format("dd", dateCreated).toString();
            //
            // if (map.containsKey(day))
            // {
            // Integer val = (Integer) map.get(day) + 1;
            // map.put(day, val);
            // }
            // else
            // {
            // map.put(day, 1);
            // }
            //return birthDayMap;
            return eventMaps;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            if (row == null)
            {
                LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
            }

            // Get a reference to the Day gridcell
            dateText=(TextView) row.findViewById(R.id.date_text);
            gridcell = (ImageView) row.findViewById(R.id.calendar_day_cell);
            gridcell.setOnClickListener(this);
            //loads AddEvent activity on longpressing the date
            gridcell.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    Intent in=new Intent(getActivity().getApplicationContext(), AddEvent.class);
                    startActivity(in);
                    return false;
                }
            });

            // ACCOUNT FOR SPACING

            ////log.d(tag, "Current Day: " + getCurrentDayOfMonth());
            String[] day_color = list.get(position).split("-");
            String theday = day_color[0];
            String themonth = day_color[2];
            String theyear = day_color[3];

            Uri baseUri = Uri.parse("content://media/external/images/media");
            //gridcell.setImageURI(baseUri.withAppendedPath(baseUri, "" + 20));
            gridcell.getLayoutParams().height = 40;

            ////log.d(tag, "Setting GridCell: Months[month] is " + months[month-1]+ " ---- themonth is " + themonth);
            if (months[month-1].equals(themonth))
            {
                if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null))
                {
                    if (eventsPerMonthMap.containsKey(theday))
                    {
                        num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);
                        Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
                        num_events_per_day.setText(numEvents.toString());

                        //dateButton = (Button) row.findViewById(R.id.calendar_day_gridcell);
                        //dateButton.setBackgroundResource(R.drawable.hh);
                        //Uri uri = new URI("content://media/external/images/media/13");
                        //gridcell.setImageURI(new Uri("content://media/external/images/media/13"));

                        //gridcell.setImageURI(Uri.parse(new File("content://media/external/images/media/13").toString()));

                        gridcell.setImageURI(baseUri.withAppendedPath(baseUri, "" + 13));
                        gridcell.setAdjustViewBounds(true);
                        //gridcell.fitsSystemWindows();
                        //gridcell.getLayoutParams().height=gridcell.getHeight();
                        //gridcell.getLayoutParams().width=30;


                    }
                }

                if ((!anvEventsPerMonthMap.isEmpty()) && (anvEventsPerMonthMap != null))
                {
                    if (anvEventsPerMonthMap.containsKey(theday))
                    {
                        num_anv_events_per_day = (TextView) row.findViewById(R.id.num_anv_events_per_day);
                        Integer numEvents = (Integer) anvEventsPerMonthMap.get(theday);
                        num_anv_events_per_day.setText(numEvents.toString());
                    }
                }
            }
            // Set the Day GridCell
            dateText.setText(theday);


            gridcell.setTag(theday + "-" + themonth + "-" + theyear);
            //////log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);

            if (day_color[1].equals("GREY"))
            {
                dateText.setTextColor(Color.GRAY);
            }
            if (day_color[1].equals("WHITE"))
            {
                dateText.setTextColor(Color.BLACK);
            }
            if (day_color[1].equals("BLUE"))
            {
                dateText.setTextColor(Color.MAGENTA);
            }
            return row;
        }
        @Override
        public void onClick(View view)
        {
          /*  String date_month_year = (String) view.getTag();
          //  selectedDayMonthYearButton.setText("Selected: " + date_month_year);


            try
            {
                Date parsedDate = dateFormatter.parse(date_month_year);
                ////log.d(tag, "Parsed Date: " + parsedDate.toString());

            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            Intent in = new Intent(getActivity().getApplicationContext(),EventsScreen.class);
            in.putExtra("Month", date_month_year);
            startActivity(in);*/

            Toast.makeText(getActivity().getApplicationContext(),"Comming Soon",Toast.LENGTH_LONG).show();

        }


        public int getCurrentDayOfMonth()
        {
            return currentDayOfMonth;
        }

        private void setCurrentDayOfMonth(int currentDayOfMonth)
        {
            this.currentDayOfMonth = currentDayOfMonth;
        }
        public void setCurrentWeekDay(int currentWeekDay)
        {
            this.currentWeekDay = currentWeekDay;
        }
        public int getCurrentWeekDay()
        {
            return currentWeekDay;
        }
    }
}
