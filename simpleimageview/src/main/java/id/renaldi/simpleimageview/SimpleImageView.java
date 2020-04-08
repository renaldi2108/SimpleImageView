package id.renaldi.simpleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SimpleImageView extends ImageView {
    private Context context;
    private float mTopLeftRadius = 0.0f;
    private float mTopRightRadius = 0.0f;
    private float mBottomLeftRadius = 0.0f;
    private float mBottomRightRadius = 0.0f;

    private boolean isSquare;

    public SimpleImageView(Context context) {
        super(context);
        this.context = context;
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init(attrs);
    }

    public SimpleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // If one of the measures is match_parent, use that one to determine the size.
        // If not, use the default implementation of onMeasure.
        if(isSquare) {
            if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                setMeasuredDimension(widthSize, widthSize);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.clipPath(setRoudedView());
        super.draw(canvas);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.SimpleImageView
        );

        isSquare = a.getBoolean(R.styleable.SimpleImageView_isSquare, false);
        mTopLeftRadius = a.getDimensionPixelSize(
                R.styleable.SimpleImageView_siv_top_left_radius, 0);
        mTopRightRadius = a.getDimensionPixelSize(
                R.styleable.SimpleImageView_siv_top_right_radius, 0);
        mBottomLeftRadius = a.getDimensionPixelSize(
                R.styleable.SimpleImageView_siv_bottom_left_radius, 0);
        mBottomRightRadius = a.getDimensionPixelSize(
                R.styleable.SimpleImageView_siv_bottom_right_radius, 0);
    }

    private Path setRoudedView() {
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        float[] mRadii = new float[] {
                mTopLeftRadius, mTopLeftRadius,
                mTopRightRadius, mTopRightRadius,
                mBottomRightRadius, mBottomRightRadius,
                mBottomLeftRadius, mBottomLeftRadius
        };
        clipPath.addRoundRect(rect, mRadii, Path.Direction.CW);

        return clipPath;
    }
}
