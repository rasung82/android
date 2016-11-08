package com.ufract.smartdev.examplerendererscript.algorithm;

import android.graphics.Bitmap;

/**
 * Created by rasung82 on 2016. 11. 7..
 */

public interface IRenderer
{
    String getRendererName();

    Bitmap render(Bitmap bitmapOrigin);
}
