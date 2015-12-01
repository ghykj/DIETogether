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
    private float bigCircleX = bigCircleRadius+60;
    private float bigCircleY = bigCircleRadius+150;

    public GraphicsView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        bigCircleBounds = new RectF();
        paint = new Paint();

    }

    protected void onDraw(Canvas canvas) {

        bigCircleBounds.set(bigCircleX - bigCircleRadius, bigCircleY - bigCircleRadius, bigCircleX + bigCircleRadius, bigCircleY + bigCircleRadius);
        paint.setColor(Color.parseColor("#FF7483"));
        canvas.drawOval(bigCircleBounds, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        canvas.drawText(pedoMsg(),bigCircleX,bigCircleY,paint);
    }
    private String pedoMsg(){
        return "1000걸음";
    }
}