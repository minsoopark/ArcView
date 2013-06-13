package bbarm.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ArcView extends View{

	Paint paint;
	Path path;
	Canvas arcCanvas;
	RectF arcOval = new RectF();
	int color;
	float width;
	float left, right, top, bottom;
	float startAngle, endAngle;
	
	public ArcView(Context context) {
		super(context);
		init();
	}

	public ArcView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ArcView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);

	}

	public void setColor(int color) {
		paint.setColor(color);
	}

	public void setWidth(float width) {
		paint.setStrokeWidth(width);
	}

	public void setOvalSize(float left, float top, float right, float bottom) {
		arcOval.set(left, top, right, bottom);
	}
	
	public void setStartAngle(float startAngle) {
		this.startAngle = startAngle;
	}
	
	
	public Canvas getCanvas() {
		return arcCanvas;
	}
	
	public void setEndAngle(float endAngle){
		this.endAngle = endAngle;
	}
	
	public float getEndAngle(){
		return this.endAngle;
	}
	
	public RectF getOval() {
		return arcOval;
	}

	public Paint getPaint() {
		return paint;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		arcCanvas = canvas;
		float sweep = this.getEndAngle() - startAngle;
		if (sweep > 360){
			sweep -= 360;
		}
		arcCanvas.drawArc(arcOval, startAngle, sweep, false, paint);
		
	}

}
