#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_baojie_learn_jni_1project_1video_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_jni_1project_1video_FastPlayer_prepareNative(JNIEnv *env, jobject thiz,
                                                                   jstring data_source) {

}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_jni_1project_1video_FastPlayer_startNative(JNIEnv *env, jobject thiz) {


}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_jni_1project_1video_FastPlayer_stopNative(JNIEnv *env, jobject thiz) {


}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_jni_1project_1video_FastPlayer_releaseNative(JNIEnv *env, jobject thiz) {


}