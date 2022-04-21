package com.example.p3l09byumegansprite;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {
    Sprite girl;
    Sprite boy;
    List<Sprite> kids = new ArrayList<>();
    Paint paint=new Paint();
    private float width, height;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();

        girl = new Sprite(0,0,10,10,6,10,Color.DKGRAY);
        boy = new Sprite(width/2,height/2,width/2+10,height/2+10,-5,-7,Color.DKGRAY);

        girl.grow(200);
        girl.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.spritexb_7313));

        boy.grow(200);
        boy.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.spritexb_2204));
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boy.update(canvas);
        girl.update(canvas);

        for (int i = 0; i < kids.size(); i++)
            kids.get(i).update(canvas);


        if(RectF.intersects(boy, girl)){
            boy.setdX(boy.getdX()*-1);
            boy.setdY(boy.getdY()*-1);

            girl.setdX(girl.getdX()*-1);
            girl.setdY(girl.getdY()*-1);

            Sprite kid = new Sprite(girl.getX(),girl.getY(),girl.getX()+10,girl.getY()+10,2,3,Color.DKGRAY);
            kid.grow(75);
            kid.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.spritexb_6941));
            kids.add(kid);
        }

        boy.draw(canvas);
        girl.draw(canvas);

        for (int i = 0; i < kids.size(); i++)
            kids.get(i).draw(canvas);

        invalidate();
    }
}
