package com.gesuper.lightclock.view;

import java.util.Calendar;
import java.util.Date;

import com.gesuper.lightclock.R;

import android.app.AlertDialog;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class ClockView extends LinearLayout {

	private EditText etCalendar;
	private Button btnCalendar;
	
	private EditText etTime;
	private Button btnTime;
	private long time;
	
	public ClockView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		inflate(context, R.layout.activity_clock, this);
		initResource();
		
	}

	private void initResource() {
		// TODO Auto-generated method stub
		this.time = new Date().getTime();
		this.etCalendar = (EditText)findViewById(R.id.clock_calendar_et);
		this.btnCalendar = (Button)findViewById(R.id.clock_calendar_btn);
		
		this.etTime = (EditText)findViewById(R.id.clock_time_et);
		this.btnTime = (Button)findViewById(R.id.clock_time_btn);
		
		this.btnCalendar.setOnClickListener(new OnClickListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout mCalendarView = 
						(LinearLayout) LayoutInflater.from(ClockView.this.getContext()).
						inflate(R.layout.activity_clock_calendar, null);
				final DatePicker localDatePicker = (DatePicker)mCalendarView.findViewById(R.id.clock_datepicker);
		        final Date localDate = new Date(ClockView.this.time);
		        localDatePicker.init(1900 + localDate.getYear(), localDate.getMonth(), localDate.getDate(), null);
				new AlertDialog.Builder(ClockView.this.getContext()).
					setTitle(R.string.clock_calendar).
					setView(mCalendarView).
					setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							localDate.setYear(-1900 + localDatePicker.getYear());
				            localDate.setMonth(localDatePicker.getMonth());
				            localDate.setDate(localDatePicker.getDayOfMonth());
							ClockView.this.etCalendar.setText(DateUtils.formatDateTime(ClockView.this.getContext(),
					                localDate.getTime(), DateUtils.FORMAT_SHOW_DATE
					                | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
						}
						
					}).
					setNegativeButton(R.string.dialog_cancel, null).show();
			}
			
		});
		
		this.btnTime.setOnClickListener(new OnClickListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LinearLayout mTimeView = 
						(LinearLayout) LayoutInflater.from(ClockView.this.getContext()).
						inflate(R.layout.activity_clock_time, null);
				final TimePicker localTimePicker = (TimePicker)mTimeView.findViewById(R.id.clock_timepicker);
		        final Date localDate = new Date(ClockView.this.time);
		        localTimePicker.setIs24HourView(Boolean.valueOf(true));
		        localTimePicker.setCurrentHour(Integer.valueOf(localDate.getHours()));
		        localTimePicker.setCurrentMinute(Integer.valueOf(localDate.getMinutes()));
				new AlertDialog.Builder(ClockView.this.getContext()).
					setTitle(R.string.clock_time).
					setView(mTimeView).
					setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							localDate.setHours(localTimePicker.getCurrentHour().intValue());
				            localDate.setMinutes(localTimePicker.getCurrentMinute().intValue());
				            localDate.setSeconds(0);
				            ClockView.this.etTime.setText(DateUtils.formatDateTime(ClockView.this.getContext(),
				                    ClockView.this.time,
				                    DateUtils.FORMAT_24HOUR | DateUtils.FORMAT_SHOW_TIME));
						}
						
					}).
					setNegativeButton(R.string.dialog_cancel, null).show();
			}
			
		});
		this.etCalendar.setText(DateUtils.formatDateTime(this.getContext(),
                this.time, DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
		this.etTime.setText(DateUtils.formatDateTime(ClockView.this.getContext(),
                this.time,
                DateUtils.FORMAT_24HOUR | DateUtils.FORMAT_SHOW_TIME));
	}

	public long getTime(){
		return 0;
	}
}
