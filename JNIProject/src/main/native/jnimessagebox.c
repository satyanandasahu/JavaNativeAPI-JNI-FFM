#include <jni.h>
#include <windows.h>
#include "com_satya_jni_main_JniMessageBoxDemo.h"

JNIEXPORT void JNICALL Java_com_satya_jni_main_JniMessageBoxDemo_showMessageBox(
    JNIEnv *env, 
    jobject obj, 
    jstring title, 
    jstring message) {
    
    const char *nativeTitle = (*env)->GetStringUTFChars(env, title, NULL);
    const char *nativeMsg = (*env)->GetStringUTFChars(env, message, NULL);

    MessageBoxA(NULL, nativeMsg, nativeTitle, MB_OK);

    (*env)->ReleaseStringUTFChars(env, title, nativeTitle);
    (*env)->ReleaseStringUTFChars(env, message, nativeMsg);
}