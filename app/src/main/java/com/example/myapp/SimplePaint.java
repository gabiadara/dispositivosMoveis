package com.example.myapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SimplePaint extends View {
    public enum ShapeType {
        TRACO_LIVRE, RETANGULO, CIRCULO;
    }

    private Paint mPaint;
    private Path mPath;
    private ShapeType currentShape = ShapeType.TRACO_LIVRE;
    private float startX, startY;
    private final ArrayList<DrawShape> shapes = new ArrayList<>();

    private static class DrawShape {
        ShapeType shapeType;
        Path path;
        float x, y, endX, endY;
        int color;
        DrawShape(ShapeType shapeType, Path path, float x, float y, float endX, float endY, int color) {
            this.shapeType = shapeType;
            this.path = path;
            this.x = x;
            this.y = y;
            this.endX = endX;
            this.endY = endY;
            this.color = color;
        }
    }

    public void setup() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
    }

    public SimplePaint(Context context) {
        super(context);
        setup();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public SimplePaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (DrawShape drawShape : shapes) {
            Paint paint = new Paint(mPaint);
            paint.setColor(drawShape.color);
            switch (drawShape.shapeType) {
                case TRACO_LIVRE:
                    canvas.drawPath(drawShape.path, paint);
                    break;
                case RETANGULO:
                    canvas.drawRect(drawShape.x, drawShape.y, drawShape.endX, drawShape.endY, paint);
                    break;
                case CIRCULO:
                    float raio = (float) Math.hypot(drawShape.endX - drawShape.x, drawShape.endY - drawShape.y);
                    canvas.drawCircle(drawShape.x, drawShape.y, raio, paint);
                    break;
            }
        }
        if (currentShape == ShapeType.TRACO_LIVRE) {
            canvas.drawPath(mPath, mPaint);
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                if (currentShape == ShapeType.TRACO_LIVRE) {
                    mPath.moveTo(x, y);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (currentShape == ShapeType.TRACO_LIVRE) {
                    mPath.lineTo(x, y);
                }
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                switch (currentShape) {
                    case TRACO_LIVRE:
                        shapes.add(new DrawShape(ShapeType.TRACO_LIVRE, new Path(mPath), 0, 0, 0, 0, mPaint.getColor()));
                        mPath.reset();
                        break;
                    case RETANGULO:
                        shapes.add(new DrawShape(ShapeType.RETANGULO, null, startX, startY, x, y, mPaint.getColor()));
                        break;
                    case CIRCULO:
                        shapes.add(new DrawShape(ShapeType.CIRCULO, null, startX, startY, x, y, mPaint.getColor()));
                        break;
                }
                invalidate();
                return true;
        }
        return false;
    }

    public void setShapeType(ShapeType shapeType) {
        this.currentShape = shapeType;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    public void clean() {
        shapes.clear();
        mPath.reset();
        invalidate();
    }

    public void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            invalidate();
        }
    }
}
