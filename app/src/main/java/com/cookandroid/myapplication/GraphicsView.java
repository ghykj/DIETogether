package com.cookandroid.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LGPC on 2015-11-29.
 */

class GraphicsView extends View {

    private RectF bigCircleBounds;
    private Paint paint;
    private float bigCircleRadius = 600;
    private float bigCircleX = bigCircleRadius+120;
    private float bigCircleY = bigCircleRadius+500;

    public GraphicsView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        bigCircleBounds = new RectF();
        paint = new Paint();

    }

    protected void onDraw(Canvas canvas) {

        bigCircleBounds.set(bigCircleX - bigCircleRadius, bigCircleY - bigCircleRadius, bigCircleX + bigCircleRadius, bigCircleY + bigCircleRadius);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        paint.setColor(Color.parseColor("#FF7483"));
        canvas.drawOval(bigCircleBounds, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#787878"));
        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(pedoMsg(),bigCircleX,bigCircleY,paint);
        paint.setTextSize(100);
        canvas.drawText(calMsg(),bigCircleX,bigCircleY+200,paint);
    }
    private String pedoMsg(){
        return "1000걸음";
    }
    private String calMsg() {return "480kcal";}
    private String compareMsg() {return "1등보다 200걸음 모자랍니다.";}
}