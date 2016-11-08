
#include <android/log.h>
#include <android/bitmap.h>



#define LOG_TAG     "RS-JNI"
#define LOGE(...)   __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)


/*********************************************************
 * 메모리 관련 함수에 대한 인터페이스
 *********************************************************/

void Java_com_ufract_smartdev_examplerendererscript_RsJni_CopyToIn
    (JNIEnv *env, jobject thiz, jobject bitmap)
{
    LOGE("[rasung82]:RsJNI_CopyToIn");
}


void Java_com_ufract_smartdev_examplerendererscript_RsJni_CreateMemoryToOut
    (JNIEnv *env, jobject thiz, jint size)
{
    LOGE("[rasung82]:RsJNI_CreateMemoryToOut");
}

void Java_com_ufract_smartdev_examplerendererscript_RsJni_CopyFromOut
    (JNIEnv *env, jobject thiz, jobject bitmap)
{
    LOGE("[rasung82]:RsJNI_CopyFromOut");
}
