package game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.bcgtgjyb.fladdybird.Tool;

/**
 * Created by bigwen on 2016/1/18.
 */
public class GameSurface extends SurfaceView implements Runnable, SurfaceHolder.Callback {


    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private String TAG = GameSurface.class.getName();
    private Context mContext;
    private Thread thread;
    private Pipe pipe;
    private boolean running = true;
    private boolean startGame = false;
    private int piHeight [] ;

    public GameSurface(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mContext = context;
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);

        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        background = new Background(mContext);
        bird = new Bird(mContext);
        pipe = new Pipe(mContext);
        // 设置常亮
        this.setKeepScreenOn(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private boolean init = false;
    @Override
    public void run() {
        while (running) {
            if(!init) {
                piHeight = new int[]{Tool.getInstance().pipeCenterHeight/5, -Tool.getInstance().pipeCenterHeight/2,
                        (int)(Tool.getInstance().pipeCenterHeight*0.8), -(int)(Tool.getInstance().pipeCenterHeight*0.3)};
                init = true;
            }
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
//                Thread.sleep(100);
            } catch (Exception e) {
                Log.e(TAG, "run " + e.toString());
            }
            if (startGame == false) {
                startGame = true;
                running = false;
            }
        }

    }

    private Background background;
    private Bird bird;

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                background.draw(mCanvas);
                drawBirdDrop();
                drawPipe();
                drawScore();
                isPass();
            }
        } catch (Exception e) {
            Log.e(TAG, "draw ");
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }


    private int birdHeight = 0;

    private int riseStart = 0;

    private void drawBirdDrop() {
        if (birdHeight == 0) {
            birdHeight = mCanvas.getHeight() / 2;
        }
        //上升
        if (rise >= 0) {
            birdHeight = birdHeight - getRise();
        }
        //下降
        else {
            birdHeight = birdHeight + getH();
        }

        if (birdHeight <= mCanvas.getHeight()) {
            bird.draw(mCanvas, birdHeight);
        }
        //到顶部
        else if (birdHeight <= 0) {
            bird.draw(mCanvas, 0);
        }
        //到底边
        else {
            birdHeight = mCanvas.getHeight() / 2;
            h = 0;
        }
    }

    int rise = -1;

    private int getRise() {
        rise = rise + Tool.getInstance().jumpHeight*15/50;
        Log.i(TAG, "getRise "+riseStart+"   "+birdHeight);
        if (rise > Tool.getInstance().jumpHeight) {
            rise = -1;
            return -1;
        } else {
            return rise;
        }
    }


    private int h = 0;

    //加速度函数
    private int getH() {
        h = h + 10;
        return h;
    }


    //水管位置
    int p = -1;
    int lastPipiWidth = 10;
    int hhh = 0;
    private void drawPipe() {

        if (p == -1) {
            p = mCanvas.getWidth();
            hhh = piHeight[score % 4];
        }

        pipe.draw(mCanvas, p,hhh);
        lastPipiWidth = pipe.ww;
        p -=  Tool.getInstance().jumpHeight*15/70;
        if (p < 0) {
            p = -1;
        }
    }

    private boolean startPass = false;
    private int score = 0;


    private void isPass() {
        Log.i(TAG, "isPass ddd" + pipe.getWidth()[0] + "   " + (bird.getRect().right) + "  " + pipe.getWidth()[1] + "   " + bird.getRect().left);
        if (pipe.getWidth()[0] <= (bird.getRect().right) && pipe.getWidth()[1] >= bird.getRect().left) {
            startPass = true;
            Log.i(TAG, "isPass " + bird.getHeight() + "   " + pipe.getPass()[0] + "   " + pipe.getPass()[1]);
            if (birdHeight - bird.getBirdHeight() / 2 > pipe.getPass()[0] && birdHeight + bird.getBirdHeight() / 2 < pipe.getPass()[1]) {
                Log.i(TAG, "isPass true");
            }
            //死掉
            else {
                Log.i(TAG, "isPass false");
                rise = -1;
                h = 0;
                p = -1;
                score = 0;
            }
        }
        if (pipe.getWidth()[1] < bird.getRect().left && startPass == true) {
            score++;
            startPass = false;
            Log.i(TAG, "isPass 1   " + score);
        }
    }

    private void drawScore() {
        Paint paint = new Paint();
        paint.setTextSize(32);
        paint.setColor(Color.parseColor("#333333"));
        mCanvas.drawText(score + "", mCanvas.getWidth() / 2, mCanvas.getHeight() / 3, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent ");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (running == false) {
                running = true;
                new Thread(this).start();
            } else {
                riseStart = birdHeight;
                rise = 0;
                h = 0;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getRunning() {
        return this.running;
    }

    public void setResume() {
        running = true;
        startGame = false;
    }
}
