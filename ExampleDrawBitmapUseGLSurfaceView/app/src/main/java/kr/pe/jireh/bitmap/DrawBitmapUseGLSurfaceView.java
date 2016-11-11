package kr.pe.jireh.bitmap;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 *  Example bitmap drawing use GLSurfaceView
 *
 *  @see http://d.hatena.ne.jp/orangesignal/20120813/1344848284
 *
 */
public class DrawBitmapUseGLSurfaceView extends Activity {


    public static String TAG = DrawBitmapUseGLSurfaceView.class.getSimpleName();


    private GLSurfaceView mGLSurfaceView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setRenderer(new SimpleRenderer(getApplicationContext()));
        setContentView(mGLSurfaceView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

}
