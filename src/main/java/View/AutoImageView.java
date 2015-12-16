package View;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

/**整体思路：
 * 先获得drawable ，利用Xmode转换成想要形状的Bitmap
 * 再Ondraw中 画出转换出来的bitmap
 *
 * Created by Zhaodj on 2015/12/16.
 */
public class AutoImageView extends ImageView {
    private int min;
    private Paint mpaint;
    private int mimageType;
    private static final int IMG_NORMAL=0;
    private static final int IMG_CIRCLE=1;
    private static final int IMG_OVAL=2;
    private static final int IMG_ROUNDREF=3;
    private int mWidth;
    private int mHeight;


    public AutoImageView(Context context) {
        super(context);
        mpaint=new Paint();
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context,attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.AutoImageView);
        mimageType=ta.getInt(R.styleable.AutoImageView_imageType,0);
        mpaint=new Paint();
        ta.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();
        min=Math.min(mWidth,mHeight);
        if(mimageType==IMG_CIRCLE){
            setMeasuredDimension(min, min);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap=null;
        switch (mimageType){
            case IMG_CIRCLE:
                bitmap=getCircleBitmap();
                break;
            case IMG_NORMAL:
                break;
            case IMG_OVAL:
                bitmap=getOvalBitmap();
                break;
            case IMG_ROUNDREF:
                bitmap=getCornerBitmap();
                break;
        }

        if(bitmap==null){
            super.onDraw(canvas);
        }else {
            mpaint.reset();
            canvas.drawBitmap(bitmap,0,0,mpaint);
        }

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Bitmap getCornerBitmap(){

        Bitmap bitmap =null;
        Drawable drawable=getDrawable();
        if(drawable==null){
            return bitmap;
        }else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            mpaint.reset();
            drawable.setBounds(0, 0, mWidth, mHeight);
            drawable.draw(canvas);

            Bitmap out=Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas outCanvas=new Canvas(out);
            mpaint.reset();
            outCanvas.drawRoundRect(0,0,mWidth,mHeight,mWidth/2,mHeight/2,mpaint);
            mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            outCanvas.drawBitmap(bitmap,0,0,mpaint);

            return out;
        }

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Bitmap getOvalBitmap(){

        Bitmap bitmap =null;
        Drawable drawable=getDrawable();
        if(drawable==null){
            return bitmap;
        }else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            mpaint.reset();
            drawable.setBounds(0, 0, mWidth, mHeight);
            drawable.draw(canvas);

            Bitmap out=Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas outCanvas=new Canvas(out);
            mpaint.reset();
            outCanvas.drawOval(0,0,mWidth,mHeight,mpaint);
            mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            outCanvas.drawBitmap(bitmap,0,0,mpaint);

            return out;
        }

    }


    public Bitmap getCircleBitmap() {
        Bitmap bitmap=null;
        Bitmap out=null;
        Drawable drawable=getDrawable();
        if(drawable==null){
            return bitmap;
        }else {
            bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            out=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas=new Canvas(bitmap);
            drawable.setBounds(0, 0, min, min);
            drawable.draw(canvas);

            Canvas Srccanvas = new Canvas(out);
            Srccanvas.drawCircle(min/2,min/2,min/2,mpaint);
            mpaint.setAntiAlias(true);
            mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            Srccanvas.drawBitmap(bitmap,0,0,mpaint);
            return out;
        }
    }
}
