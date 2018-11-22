package soexample.umeng.com.jindong.Activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



public class Waterlines extends View {

    private Paint paint;
    private Paint paint1;
    private Path path;
    private Path path1;

    private float φ;

    public Waterlines(Context context) {
        super(context);
        init(context);
    }
    public Waterlines(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public Waterlines(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setAntiAlias(true);
        paint1.setAlpha(60);
        path = new Path();
        path1 = new Path();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path1.reset();
        // 路径起始的位置
        path.moveTo(getLeft(), getBottom());
        path1.moveTo(getLeft(), getBottom());
        //路径终止的位置
        path.lineTo(getRight(), getBottom());
        path1.lineTo(getRight(), getBottom());
        double m = Math.PI * 2 / getWidth();
        float y, y2;
        φ -= 0.1f;
        for (float x = 0; x <= getWidth(); x += 20) {
            y = (float) (10 * Math.cos(m * x + φ)) + 10;
            y2 = (float) (10 * Math.sin(m * x + φ));
            path.lineTo(x, y);
            path1.lineTo(x, y2);
            listener.animation(y);
        }
        canvas.drawPath(path, paint);
        canvas.drawPath(path1, paint1);

        postInvalidateDelayed(20);
    }

    private AnimationListener listener;

    //传递接口
    public void animation(AnimationListener listener) {
        this.listener=listener;
    }

    public interface AnimationListener {
        void animation(float y);
    }

}
