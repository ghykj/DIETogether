package com.cookandroid.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;



class GraphicsView extends View {

    private RectF bigCircleBounds;
    private Paint paint;
    private float bigCircleRadius = 600;
    private float bigCircleX = bigCircleRadius+120;
    private float bigCircleY = bigCircleRadius+500;
   // private BroadcastReceiver receiver;
    //private boolean flag = true;
    //private String serviceData;
    //MyMainLocalRecever receiver;
    //private Intent intentMyService;

    public GraphicsView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        bigCircleBounds = new RectF();
        paint = new Paint();
       // intentMyService = new Intent(getContext(), MyServiceIntent.class);
        //receiver = new MyMainLocalRecever();
        //IntentFilter mainFilter = new IntentFilter("com.cookandroid.mayapplication.step");
        //context.registerReceiver(receiver, mainFilter);
        //context.startService(intentMyService);

    }

    /*public void turnOnPedometer(){
        try {

            //IntentFilter mainFilter = new IntentFilter("com.cookandroid.mayapplication.step");

            //getContext().registerReceiver(receiver, mainFilter);

            //getContext().startService(intentMyService);
            //txtMsg.setText("After stoping Service:\n"+service.getClassName());
            //Toast.makeText(getApplicationContext(), "���� ����", 1).show();
        } catch (Exception e) {
            // TODO: handle exception
            //Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
        }
    }
    public void turnOffPedometer(){
        try {

            getContext().unregisterReceiver(receiver);

            getContext().stopService(intentMyService);

            //Toast.makeText(getApplicationContext(), "���� ����", 1).show();
            //txtMsg.setText("After stoping Service:\n"+service.getClassName());
        } catch (Exception e) {
            // TODO: handle exception
            //Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
        }
    }*/



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
        //canvas.drawText(getContext().receiver.getCount(),bigCircleX,bigCircleY,paint);
        //paint.setTextSize(100);
        //canvas.drawText(calMsg(), bigCircleX, bigCircleY + 200, paint);
        invalidate();
    }
    private String calMsg() {return "480kcal";}
    private String compareMsg() {return "1등보다 200걸음 모자랍니다.";}



    /*class MyMainLocalRecever extends BroadcastReceiver {
        String count;

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            serviceData = intent.getStringExtra("serviceData");
            count = serviceData;
            //Toast.makeText(getApplicationContext(), "Walking . . . ", 1).show();
        }
        public String getCount(){
            return count;
        }

    }*/
}