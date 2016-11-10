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
import com.ufract.smartdev.examplerendererscript.algorithm.RendererNdkNeon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExampleRendererScriptTest extends Activity {

    public static String TAG = ExampleRendererScriptTest.class.getSimpleName();

    private static final int LoopTime = 10;


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
                rendererList.add(new RendererNdkNeon());


                //2. 리스트 목록에 있는 항목들을 꺼내어 수행한다.
                for(IRenderer renderer : rendererList){
                    renderer.render(bitmapIn);
                }

                // 3. 리스트 목록에 있는 테스트 본격 수행
                Map<String, Long> sumMap = new LinkedHashMap<String, Long>();
                for (int i = 0; i < LoopTime; i++)
                {
                    for (IRenderer renderer : rendererList) {
                        long start = System.currentTimeMillis();
                        renderer.render(bitmapIn);
                        long end = System.currentTimeMillis();

                        if (sumMap.containsKey(renderer.getRendererName()) == false) {
                            sumMap.put(renderer.getRendererName(), 0l);
                        }
                        sumMap.put(renderer.getRendererName(), sumMap.get(renderer.getRendererName()) + (end - start));

                        Log.d(TAG, String.format("Time) %-20s: %d", renderer.getRendererName(), (end - start)));
                    }
                }

                // 4. 측정 결과 출력
                StringBuffer resultBuffer = new StringBuffer();
                for (Map.Entry<String, Long> entry : sumMap.entrySet()) {
                    resultBuffer.append(String.format("%-20s: %d\n", entry.getKey(), entry.getValue() / LoopTime));
                }
                final String resultString = resultBuffer.toString();

//                ExampleRendererScriptTest.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        result.setText(resultString);
//                    }
//                });
                Log.d(TAG, resultString);
            }
        }).start();



    }
}
