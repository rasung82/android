package com.ufract.smartdev.examplerendererscript;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.ufract.smartdev.examplerendererscript.algorithm.IRenderer;
import com.ufract.smartdev.examplerendererscript.algorithm.RendererJava;
import com.ufract.smartdev.examplerendererscript.algorithm.RendererNdk;

import java.util.ArrayList;
import java.util.List;

public class ExampleRendererScriptTest extends Activity {

    public static String TAG = ExampleRendererScriptTest.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ImageView iv = (ImageView) findViewById(R.id.imgOrigin);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        final Bitmap bitmapIn = BitmapFactory.decodeResource(getResources(), R.drawable.img, options);

        iv.setImageBitmap(bitmapIn);

        new Thread(new Runnable(){
            @Override
            public void run()
            {
                //1. 측정할 항목들을 객체로 만들어 리스트에 보관한다.
                List<IRenderer> rendererList = new ArrayList<>();
                rendererList.add(new RendererJava());
                rendererList.add(new RendererNdk());


                //2. 리스트 목록에 있는 항목들을 꺼내어 수행한다.
                for(IRenderer renderer : rendererList){
                    //iv.setImageBitmap(renderer.render(bitmapIn));
                    renderer.render(bitmapIn);
                }
            }
        }).start();

    }
}
