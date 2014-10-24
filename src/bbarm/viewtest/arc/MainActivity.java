package bbarm.viewtest.arc;

import android.util.Log;
import android.widget.Toast;
import bbarm.viewtest.view.ArcView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Menu;

public class MainActivity extends Activity {
    
    ArcView arcView;
    
    DisplayMetrics dm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        arcView = (ArcView) findViewById(R.id.arcView);
        
        arcView.getLayoutParams().width = (int) (dm.widthPixels * 0.6);
        arcView.getLayoutParams().height = (int) (dm.widthPixels * 0.6);
        
        final int ARC_WIDTH = dm.widthPixels / 12;
        final int ARC_MARGIN = dm.widthPixels / 16;

        arcView.setColor(Color.parseColor("#eacd05"));
        arcView.setStrokeWidth(ARC_WIDTH);
        arcView.setOvalSize(ARC_MARGIN, ARC_MARGIN,
        arcView.getLayoutParams().width - ARC_MARGIN,
        arcView.getLayoutParams().width - ARC_MARGIN);
        arcView.setStartAngle(0);
        arcView.setEndAngle(180);
        arcView.setFlippingEnabled(true);
        arcView.setFlippingUnit(30);
        arcView.setOnAngleChangedListener(new ArcView.OnAngleChangedListener() {
            @Override
            public void onChanging(float angle) {
                Log.d("view.view", angle + "");
            }

            @Override
            public void onChanged(float angle) {
                Toast.makeText(MainActivity.this, "angle = " + angle, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
