package game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.bcgtgjyb.fladdybird.R;

/**
 * Created by bigwen on 2016/1/18.
 */
public class Background {
    private Context mContext;
    private Paint paint;
    public Background(Context context) {
        mContext = context;
        paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setDither(true);
    }
    public void draw(Canvas canvas){
        int width  = canvas.getWidth();
        int height = canvas.getHeight();
        Bitmap bitmap = PhotoRes.getBitmap(mContext, R.drawable.bg1, width, height);
        canvas.drawBitmap(bitmap,null,new Rect(0,0,width,height),null);
    }
}
