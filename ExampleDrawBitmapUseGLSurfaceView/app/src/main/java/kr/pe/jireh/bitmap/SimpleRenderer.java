package kr.pe.jireh.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by rasung82 on 2016. 11. 11..
 */

public class SimpleRenderer implements GLSurfaceView.Renderer {


    public static String TAG = SimpleRenderer.class.getSimpleName();


    private Context mContext;

    private static final float VERTEXS[] = {
            -1.0f,  1.0f, 0.0f,	// 左上
            -1.0f, -1.0f, 0.0f,	// 左下
            1.0f,  1.0f, 0.0f,	// 右上
            1.0f, -1.0f, 0.0f	// 右下
    };

    private static final float TEXCOORDS[] = {
            0.0f, 0.0f,	// 左上
            0.0f, 1.0f,	// 左下
            1.0f, 0.0f,	// 右上
            1.0f, 1.0f	// 右下
    };

    private final FloatBuffer mVertexBuffer   = GLES20Utils.createBuffer(VERTEXS);

    private final FloatBuffer mTexcoordBuffer = GLES20Utils.createBuffer(TEXCOORDS);

    private static final String VERTEX_SHADER = "attribute vec4 position;" +
                    "attribute vec2 texcoord;" +
                    "varying vec2 texcoordVarying;" +
                    "void main() {" +
                    "gl_Position = position;" +
                    "texcoordVarying = texcoord;" +
                    "}";

    private static final String FRAGMENT_SHADER = "precision mediump float;" +
                    "varying vec2 texcoordVarying;" +
                    "uniform sampler2D texture;" +
                    "void main() {" +
                    "gl_FragColor = texture2D(texture, texcoordVarying);" +
                    "}";


    private int mProgram;
    private int mPosition;
    private int mTexcoord;
    private int mTexture;
    private int mTextureId;




    public SimpleRenderer(final Context context){
        mContext = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        mProgram = GLES20Utils.createProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        if (mProgram == 0) {
            throw new IllegalStateException();
        }
        GLES20.glUseProgram(mProgram);
        GLES20Utils.checkGlError("glUseProgram");

        mPosition = GLES20.glGetAttribLocation(mProgram, "position");
        GLES20Utils.checkGlError("glGetAttribLocation position");
        if (mPosition == -1) {
            throw new IllegalStateException("Could not get attrib location for position");
        }
        GLES20.glEnableVertexAttribArray(mPosition);

        mTexcoord = GLES20.glGetAttribLocation(mProgram, "texcoord");
        GLES20Utils.checkGlError("glGetAttribLocation texcoord");
        if (mPosition == -1) {
            throw new IllegalStateException("Could not get attrib location for texcoord");
        }
        GLES20.glEnableVertexAttribArray(mTexcoord);

        mTexture = GLES20.glGetUniformLocation(mProgram, "texture");
        GLES20Utils.checkGlError("glGetUniformLocation texture");
        if (mTexture == -1) {
            throw new IllegalStateException("Could not get uniform location for texture");
        }

        final Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.img);
        mTextureId = GLES20Utils.loadTexture(bitmap);
        bitmap.recycle();

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        GLES20Utils.checkGlError("glViewport");

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        Log.d(TAG, "onDrawFrame()");
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // 背景とのブレンド方法を設定します。
        GLES20.glEnable(GLES20.GL_TEXTURE_2D);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);	// 単純なアルファブレンド

        // テクスチャの指定
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId);
        GLES20.glUniform1i(mTexture, 0);
        GLES20.glVertexAttribPointer(mTexcoord, 2, GLES20.GL_FLOAT, false, 0, mTexcoordBuffer);
        GLES20.glVertexAttribPointer(mPosition, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisable(GLES20.GL_BLEND);
        GLES20.glDisable(GLES20.GL_TEXTURE_2D);
    }
}
