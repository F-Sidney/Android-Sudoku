package com.nickwar.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.view.View;

public class PuzzleView extends View {
    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.puzzle_background));
        canvas.drawRect(0.0f,0.0f, getWidth(),getHeight(),background);
        
        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_dark));
        
        Paint hilite = new Paint();
        hilite.setColor(getResources().getColor(R.color.puzzle_hilite));
        
        Paint light = new Paint();
        light.setColor(getResources().getColor(R.color.puzzle_light));
        
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i* height, getWidth(), i*height,light);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
            canvas.drawLine(i * width, 0, i * width, getHeight(),light);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
        }
        
        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0) {
                continue;
            }
            
            canvas.drawLine(0, i * height, getWidth(), i * height, dark);
            canvas.drawLine(0, i*height+1, getWidth(), i*height+1, hilite);
            canvas.drawLine(i*width, 0, i*width, getHeight(), dark);
            canvas.drawLine(i*width+1, 0, i*width+1, getHeight(), hilite);

        }
        
        Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
        foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
        foreground.setStyle(Style.FILL);
        foreground.setTextSize(height * 0.75f);
        foreground.setTextScaleX(width / height);
        foreground.setTextAlign(Paint.Align.CENTER);
        
        FontMetrics fm = foreground.getFontMetrics();
        
        float x = width / 2;
        float y = height / 2 - (fm.ascent + fm.descent) / 2;
        for(int i = 0; i<9; i++){
            for(int j=0; j<9; j++){
                canvas.drawText(this.game.getTileString(i, j), 
                        i * width + x, j * height + y, foreground);
            }
        }
            
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w/9f;
        height = h/9f;
        getRect(selX,selY,selRect);
//        Log.d(TAG,)
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void getRect(int x, int y, Rect rect) {
        rect.set((int)(x*width),(int)(y*height),(int)(x*width + width),(int) (y*height + height));
    }

    private static final String TAG = "Sudoku";
    private final Game game;
    
    private float width;
    private float height;
    private int selX;
    private int selY;
    private final Rect selRect = new Rect();
    public PuzzleView(Context context) {
        super(context);
        this.game = (Game)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        // TODO Auto-generated constructor stub
    }

}
