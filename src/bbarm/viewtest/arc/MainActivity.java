package bbarm.viewtest.arc;

import bbarm.viewtest.view.ArcView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ArcView hourBackArcView;
	ArcView minuteBackArcView;
	
	ArcView hourArcView;
	ArcView minuteArcView;

	ImageButton amButton;
	ImageButton pmButton;
	
	TextView timeTextView;
	
	String amOrPm;
	String hour;
	String minute;
	
	DisplayMetrics dm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		amOrPm = "AM";
		hour = String.format("%02d", 6);
		minute = String.format("%02d", 0);

		hourBackArcView = (ArcView) findViewById(R.id.hourBackArcView);
		minuteBackArcView = (ArcView) findViewById(R.id.minuteBackArcView);
		
		hourArcView = (ArcView) findViewById(R.id.hourArcView);
		minuteArcView = (ArcView) findViewById(R.id.minuteArcView);
		
		amButton = (ImageButton) findViewById(R.id.amButton);
		pmButton = (ImageButton) findViewById(R.id.pmButton);
		pmButton.setVisibility(View.GONE);
		
		timeTextView = (TextView) findViewById(R.id.timeTextView);

		hourBackArcView.getLayoutParams().width = (int) (dm.widthPixels * 0.6);
		hourBackArcView.getLayoutParams().height = (int) (dm.widthPixels * 0.6);
		minuteBackArcView.getLayoutParams().width = (int)dm.widthPixels;
		minuteBackArcView.getLayoutParams().height = (int)dm.widthPixels;
		
		hourArcView.getLayoutParams().width = (int) (dm.widthPixels * 0.6);
		hourArcView.getLayoutParams().height = (int) (dm.widthPixels * 0.6);
		minuteArcView.getLayoutParams().width = (int)dm.widthPixels;
		minuteArcView.getLayoutParams().height = (int)dm.widthPixels;
		
		final int ARC_WIDTH = dm.widthPixels / 12;
		final int HOUR_ARC_MARGIN = dm.widthPixels / 16;
		final int MINUTE_ARC_MARGIN = dm.widthPixels / 8;
		final int HOUR_ARC_RADIUS = (hourArcView.getLayoutParams().width - (HOUR_ARC_MARGIN * 2)) / 2;
		final int MINUTE_ARC_RADIUS = (minuteArcView.getLayoutParams().width - (MINUTE_ARC_MARGIN * 2)) / 2;
		final int ARC_CENTER_X = dm.widthPixels / 2;
		final int ARC_CENTER_Y = dm.heightPixels / 2;
		final int AM_PM_BUTTON_WIDTH_HEIGHT = (HOUR_ARC_RADIUS*2)-(dm.widthPixels/5);
		
		amButton.getLayoutParams().width = AM_PM_BUTTON_WIDTH_HEIGHT;
		amButton.getLayoutParams().height = AM_PM_BUTTON_WIDTH_HEIGHT;
		
		pmButton.getLayoutParams().width = AM_PM_BUTTON_WIDTH_HEIGHT;
		pmButton.getLayoutParams().height = AM_PM_BUTTON_WIDTH_HEIGHT;
		
		hourBackArcView.setColor(Color.parseColor("#444444"));
		hourBackArcView.setWidth(ARC_WIDTH);
		hourBackArcView.setOvalSize(HOUR_ARC_MARGIN, HOUR_ARC_MARGIN,
				hourArcView.getLayoutParams().width - HOUR_ARC_MARGIN,
				hourArcView.getLayoutParams().width - HOUR_ARC_MARGIN);
		hourBackArcView.setStartAngle(-90);
		hourBackArcView.setEndAngle(270);

		hourArcView.setColor(Color.parseColor("#eacd05"));
		hourArcView.setWidth(ARC_WIDTH);
		hourArcView.setOvalSize(HOUR_ARC_MARGIN, HOUR_ARC_MARGIN,
				hourArcView.getLayoutParams().width - HOUR_ARC_MARGIN,
				hourArcView.getLayoutParams().width - HOUR_ARC_MARGIN);
		hourArcView.setStartAngle(-90);
		hourArcView.setEndAngle(90);

		minuteBackArcView.setColor(Color.parseColor("#444444"));
		minuteBackArcView.setWidth(ARC_WIDTH);
		minuteBackArcView.setOvalSize(MINUTE_ARC_MARGIN, MINUTE_ARC_MARGIN,
				minuteArcView.getLayoutParams().width - MINUTE_ARC_MARGIN,
				minuteArcView.getLayoutParams().width - MINUTE_ARC_MARGIN);
		minuteBackArcView.setStartAngle(-90);
		minuteBackArcView.setEndAngle(270);
		
		minuteArcView.setColor(Color.parseColor("#ebebeb"));
		minuteArcView.setWidth(ARC_WIDTH);
		minuteArcView.setOvalSize(MINUTE_ARC_MARGIN, MINUTE_ARC_MARGIN,
				minuteArcView.getLayoutParams().width - MINUTE_ARC_MARGIN,
				minuteArcView.getLayoutParams().width - MINUTE_ARC_MARGIN);
		minuteArcView.setStartAngle(-90);
		minuteArcView.setEndAngle(90);
		
		
		hourArcView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				double distance = Math.sqrt(Math.pow(event.getX()
						- (ARC_CENTER_X - hourArcView.getLeft()), 2)
						+ Math.pow(
								event.getY()
										- (ARC_CENTER_Y - hourArcView.getTop() - HOUR_ARC_MARGIN),
								2));

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (distance > HOUR_ARC_RADIUS + (ARC_WIDTH / 2)) {
						return false;
					} else {
						// TODO:normal
					}

				case MotionEvent.ACTION_MOVE:
					float x = event.getX();
					float y = event.getY();
					
					float centerX = hourArcView.getWidth() / 2;
					float centerY = hourArcView.getHeight() / 2;

					float relativeX = x - centerX;
					float relativeY = y - centerY;

					double cos = Math.abs(relativeX)
							/ Math.sqrt(Math.pow(relativeX, 2)
									+ Math.pow(relativeY, 2));
					double rad = Math.acos(cos);

					if (relativeX < 0 && relativeY > 0) {
						rad = Math.PI - rad;
					} else if (relativeX < 0 && relativeY < 0) {
						rad = Math.PI + rad;
					} else if (relativeX > 0 && relativeY < 0) {
						rad = Math.PI * 2 - rad;
					}
					double angle = rad * 180 / Math.PI;

					hourArcView.setEndAngle((float) angle);
					hourArcView.setWidth((float) (ARC_WIDTH * 1.2));
					hourArcView.invalidate();
					
					break;
					
				case MotionEvent.ACTION_UP:
					hourArcView.setWidth(ARC_WIDTH);
					float touchUpAngle = hourArcView.getEndAngle();
					float flipToAngle = touchUpAngle - touchUpAngle % 30;
					
					if(touchUpAngle % 30 >= 15) {
						flipToAngle = flipToAngle + 30;
					}
					hourArcView.setEndAngle(flipToAngle);
					
					hourArcView.invalidate();
					
					int flipToHour = (int)(hourArcView.getEndAngle() / 30) + 3;
					
					if(hourArcView.getEndAngle() / 30 >= 10) {
						flipToHour = flipToHour - 12;
					}
					hour = String.format("%02d", flipToHour);
					
					timeTextView.setText(amOrPm + " " + hour + ":" + minute);
					break;
				}
				return true;
			}
		});

		minuteArcView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				double distance = Math.sqrt(Math.pow(event.getX()
						- (ARC_CENTER_X - minuteArcView.getLeft()), 2)
						+ Math.pow(
								event.getY()
										- (ARC_CENTER_Y
												- minuteArcView.getTop() - MINUTE_ARC_MARGIN),
								2));

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (distance < MINUTE_ARC_RADIUS - (ARC_WIDTH / 2)) {
						return false;
					} else {
						// TODO:normal
					}

				case MotionEvent.ACTION_MOVE:
					float x = event.getX();
					float y = event.getY();
					float centerX = minuteArcView.getWidth() / 2;
					float centerY = minuteArcView.getHeight() / 2;

					float relativeX = x - centerX;
					float relativeY = y - centerY;

					double cos = relativeX
							/ Math.sqrt(Math.pow(relativeX, 2)
									+ Math.pow(relativeY, 2));
					double rad = Math.acos(cos);
					rad = relativeY > 0 ? rad : Math.PI * 2 - rad;
					double angle = rad * 180 / Math.PI;

					minuteArcView.setEndAngle((float) angle);
					minuteArcView.setWidth((float) (ARC_WIDTH*1.2));
					minuteArcView.invalidate();
					break;
					
				case MotionEvent.ACTION_UP:
					minuteArcView.setWidth(ARC_WIDTH);
					float touchUpAngle = minuteArcView.getEndAngle();
					float flipToAngle = touchUpAngle - touchUpAngle % 30;
					
					if(touchUpAngle % 30 >= 15) {
						flipToAngle = flipToAngle + 30;
					}
					minuteArcView.setEndAngle(flipToAngle);
					
					minuteArcView.invalidate();
					
					int flipToMinute = ((int)(minuteArcView.getEndAngle() / 30) + 3)*5;
					
					if(minuteArcView.getEndAngle() / 30 >= 10) {
						flipToMinute = flipToMinute - 60;
					}
					minute = String.format("%02d", flipToMinute);
					
					timeTextView.setText(amOrPm + " " + hour + ":" + minute);
					break;
				}
				return true;
			}

		});
		
		OnClickListener toggleAmPm = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getTag().equals("am")) {
					amButton.setVisibility(View.GONE);
					pmButton.setVisibility(View.VISIBLE);
					amOrPm = "PM";
				} else {
					amButton.setVisibility(View.VISIBLE);
					pmButton.setVisibility(View.GONE);
					amOrPm = "AM";
				}
				timeTextView.setText(amOrPm + " " + hour + ":" + minute);
			}
		};
		
		amButton.setOnClickListener(toggleAmPm);
		pmButton.setOnClickListener(toggleAmPm);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
