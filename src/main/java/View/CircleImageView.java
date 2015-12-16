package View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2015/12/15.
 */
public class CircleImageView extends ImageView {
    Paint paint;
    int min;

    public CircleImageView(Context context) {
        super(context);

    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        min=Math.min(width,height);
        setMeasuredDimension(min, min);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap mSrc=getBitmap();
//        if(getDrawable()!=null){
//            min=Math.min(getWidth(),getDrawable().getIntrinsicWidth());
//        }
//            canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
        canvas.drawBitmap(mSrc, 0, 0, paint);
        paint.reset();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawCircle(100,100,100,paint);


    }
    private Bitmap getBitmap(){
        Bitmap bitmap=null;
        Drawable drawable=getDrawable();
        if(drawable==null){
            bitmap=Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
            return bitmap;
        }
            bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas=new Canvas(bitmap);
        int radius=Math.min(getWidth(),drawable.getIntrinsicWidth());
            drawable.setBounds(0, 0, radius, radius);
            drawable.draw(canvas);
        return bitmap;
    }


    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

}
