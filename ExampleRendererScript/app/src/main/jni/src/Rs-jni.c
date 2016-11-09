
#include <android/log.h>
#include <android/bitmap.h>
#include "Rs-jni.h"
#include "Allocation.h"



#define LOG_TAG     "RS-JNI"
#define LOGE(...)   __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define LOGV(...)   __android_log_print(ANDROID_LOG_VERBOSE,LOG_TAG,__VA_ARGS__)


/*********************************************************
 * 메모리 관련 함수에 대한 인터페이스
 *********************************************************/

/**
 * 메모리 할당 및 Pixels 배열 복사
 */
void Java_com_ufract_smartdev_examplerendererscript_RsJni_CopyToIn(JNIEnv *env, jobject thiz, jobject bitmap)
{
    LOGV(__func__);
    AndroidBitmapInfo info;
    void* pixels;
    int ret;

    if((ret = AndroidBitmap_getInfo(env, bitmap, &info)) < 0)
    {
        LOGE("AndroidBitmap_getInfo() failed ! error=%d", ret);
        return;
    }

    if(info.format != ANDROID_BITMAP_FORMAT_RGBA_8888)
    {
        LOGE("Bitmap format is not RGBA_8888 !");
        return;
    }

    if((ret = AndroidBitmap_lockPixels(env, bitmap, &pixels)) < 0 )
    {
        LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
    }

    copy_to_in(pixels, info.width * info.height);

    AndroidBitmap_unlockPixels(env, bitmap);
}


void Java_com_ufract_smartdev_examplerendererscript_RsJni_CreateMemoryToOut(JNIEnv *env, jobject thiz, jint size)
{
    LOGV("[rasung82]:RsJNI_CreateMemoryToOut");
}


void Java_com_ufract_smartdev_examplerendererscript_RsJni_CopyFromOut(JNIEnv *env, jobject thiz, jobject bitmap)
{
    LOGV("[rasung82]:RsJNI_CopyFromOut");
}
