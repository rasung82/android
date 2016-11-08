#ifndef __RS_JNI_H__
#define __RS_JNI_H__


#include <jni.h>



//http://egloos.zum.com/jimbo73/v/1486292
#ifdef __cplusplus
extern "C" {
#endif


/*********************************************************
 * 메모리 관련 함수에 대한 인터페이스
 *********************************************************/

/// in 메모리 할당 및 pixels 배열 복사 (size * 4)
JNIEXPORT void JNICALL
    Java_com_ufract_smartdev_examplerenderscript_RsJNI_CopyToIn
    (JNIEnv *env, jobject thiz, jobject bitmap);

/// out 메모리 할당 (size * 4)
JNIEXPORT void JNICALL
    Java_com_ufract_smartdev_examplerenderscript_RsJNI_CreateMemoryToOut
    (JNIEnv *env, jobject thiz, jint size);

/// out 메모리의 내용을 pixels 배열로 복사 (size * 4)
JNIEXPORT void JNICALL
    Java_com_ufract_smartdev_examplerenderscript_RsJNI_CopyFromOut
    (JNIEnv *env, jobject thiz, jobject bitmap);




#ifdef __cplusplus
}
#endif

#endif /* __RS_JNI_H__ */
