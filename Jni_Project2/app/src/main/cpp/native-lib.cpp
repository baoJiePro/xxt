#include <jni.h>
#include <string>
// NDK工具链里面的 log 库 引入过来
#include <android/log.h>
#include "DParcel.h"

#define TAG "baoJie"
// ... 我都不知道传入什么  借助JNI里面的宏来自动帮我填充
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)


// JNIEnv * env  JNI：的桥梁环境    300多个函数，所以的JNI操作，必须靠他

// jobject jobj  谁调用，就是谁的实例  MainActivity this
// jclass clazz 谁调用，就是谁的class MainActivity.class
extern "C" JNIEXPORT jstring JNICALL
Java_com_baojie_learn_project_1jni_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_activity_SplashActivity_changeName(JNIEnv *env, jobject thiz) {
    // 获取class
    jclass j_cls = env->GetObjectClass(thiz);
    // 获取属性  L对象类型 都需要L
    jfieldID j_fid = env->GetFieldID(j_cls, "name", "Ljava/lang/String;");
    // 转换工作
    jstring j_str = static_cast<jstring>(env->GetObjectField(thiz, j_fid));
    // 打印字符串  目标
    char *c_str = const_cast<char *>(env->GetStringUTFChars(j_str, NULL));
    LOGD("native: %s\n", c_str);
    LOGE("native: %s\n", c_str);
    LOGI("native: %s\n", c_str);
    jstring jName = env->NewStringUTF("bob");
    env->SetObjectField(thiz, j_fid, jName);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_activity_SplashActivity_changeAge(JNIEnv *env, jclass clazz) {
    jfieldID j_fid = env->GetStaticFieldID(clazz, "age", "I");
    jint age = env->GetStaticIntField(clazz, j_fid);
    age += 10;
    env->SetStaticIntField(clazz, j_fid, age);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_activity_SplashActivity_callAddMethod(JNIEnv *env,
                                                                         jobject thiz) {
    jclass j_cls = env->GetObjectClass(thiz);
    jmethodID jmethodId = env->GetMethodID(j_cls, "add", "(II)I");
    jint sum = env->CallIntMethod(thiz, jmethodId, 1, 2);
    LOGE("sum result: %d\n", sum);
}

// jint == int
// jstring == String
// jintArray == int[]
// jobjectArray == 引用类型对象，例如 String[]   Test[]   Student[]  Person[]
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day19_JavaObjectActivity_testArrayAction(JNIEnv *env,
                                                                            jobject thiz,
                                                                            jint count,
                                                                            jstring text_info,
                                                                            jintArray ints,
                                                                            jobjectArray strings) {
    // ① 基本数据类型  jint count， jstring text_info， 最简单的
    // jint本质是int，所以可以用int接收
    int countInt = count;
    LOGI("参数1值：%d\n", countInt);
    // const char* GetStringUTFChars(jstring string, jboolean* isCopy)
    const char *stringValue = env->GetStringUTFChars(text_info, NULL);
    LOGI("参数2值：%s\n", stringValue);

    // ② 把int[] 转成 int*
    // jint* GetIntArrayElements(jintArray array, jboolean* isCopy)
    int *jintArrays = env->GetIntArrayElements(ints, NULL);
    // Java层数组的长度
    // jsize GetArrayLength(jarray array) // jintArray ints 可以放入到 jarray的参数中去
    jsize size = env->GetArrayLength(ints);

    for (int i = 0; i < size; ++i) {
        // C++的修改，影响不了Java层
        *(jintArrays + i) += 100;
        LOGI("参数3值：%d\n", *jintArrays + i);
    }
    /**
    * 0:           刷新Java数组，并 释放C++层数组
    * JNI_COMMIT:  只提交 只刷新Java数组，不释放C++层数组
    * JNI_ABORT:   只释放C++层数组
    */
    env->ReleaseIntArrayElements(ints, jintArrays, 0);

    // ③：jobjectArray 代表是Java的引用类型数组，不一样
    jsize strSize = env->GetArrayLength(strings);
    for (int i = 0; i < strSize; ++i) {
        //获取jstring，需要使用static_cast进行强转
        //jobject GetObjectArrayElement(jobjectArray array, jsize index)
        jstring jobj = static_cast<jstring>(env->GetObjectArrayElement(strings, i));
        const char *jobjCharp = env->GetStringUTFChars(jobj, NULL);
        LOGI("参数4值：%s\n", jobjCharp);
        env->ReleaseStringUTFChars(jobj, jobjCharp);
    }
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day19_JavaObjectActivity_putObject(JNIEnv *env,
                                                                      jobject thiz,
                                                                      jobject student,
                                                                      jstring name) {

    const char *cName = env->GetStringUTFChars(name, NULL);
    LOGI("strChar：%s\n", cName);
    env->ReleaseStringUTFChars(name, cName);

    // --------------
    // 1.寻找类 Student
    // jclass studentClass = env->FindClass("com/derry/as_jni_project/Student"); // 第一种
    jclass studentClass = env->FindClass("com/baojie/learn/project_jni/day19/Student");
    //第二种 根据已有的对象获取jclass
//    jclass studentClass2 = env->GetObjectClass(student);
    // 2.Student类里面的函数规则  签名
    jmethodID setName = env->GetMethodID(studentClass, "setName", "(Ljava/lang/String;)V");
    jmethodID setAge = env->GetMethodID(studentClass, "setAge", "(I)V");
    jmethodID getName = env->GetMethodID(studentClass, "getName", "()Ljava/lang/String;");
    jmethodID getAge = env->GetMethodID(studentClass, "getAge", "()I");
    jmethodID showInfo = env->GetStaticMethodID(studentClass, "showInfo", "(Ljava/lang/String;)V");
    //调用方法
    // 3.调用 setName
    jstring setNameValue = env->NewStringUTF("aaaa");
    env->CallVoidMethod(student, setName, setNameValue);
    // 3.调用 setAge
    env->CallVoidMethod(student, setAge, 20);
    // 3.调用 getName
    jstring getNameResult = static_cast<jstring>(env->CallObjectMethod(student, getName));
    const char *getNameValue = env->GetStringUTFChars(getNameResult, NULL);
    LOGI("调用到getName方法，值是:%s\n", getNameValue);
    //3. 调用getAge
    int ageValue = env->CallIntMethod(student, getAge);
    LOGI("调用到getAge方法，值是:%d\n", ageValue);
    //调用静态showInfo
    jstring showInfoParam = env->NewStringUTF("静态方法你好，我是c++");
    env->CallStaticVoidMethod(studentClass, showInfo, showInfoParam);

}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day19_JavaObjectActivity_insertObject(JNIEnv *env,
                                                                         jobject thiz) {
    // 1.通过包名+类名的方式 拿到 Student class  凭空拿class
    jclass studentClass = env->FindClass("com/baojie/learn/project_jni/day19/Student");
    // 2.通过student的class  实例化此Student对象   C++ new Student
    // AllocObject 只实例化对象，不会调用对象的构造函数
    jobject student = env->AllocObject(studentClass);

    jmethodID setName = env->GetMethodID(studentClass, "setName", "(Ljava/lang/String;)V");
    jmethodID setAge = env->GetMethodID(studentClass, "setAge", "(I)V");
    jmethodID toString = env->GetMethodID(studentClass, "toString", "()Ljava/lang/String;");
    jstring strValue = env->NewStringUTF("derry");
    env->CallVoidMethod(student, setName, strValue);
    env->CallVoidMethod(student, setAge, 35);
    jstring toStringVal = static_cast<jstring>(env->CallObjectMethod(student, toString));
    const char *studentToString = env->GetStringUTFChars(toStringVal, NULL);
    LOGI("设置的Student.toString: %s\n", studentToString);

    jclass personClass = env->FindClass("com/baojie/learn/project_jni/day19/Person");
    // AllocObject 只实例化对象，不会调用对象的构造函数
    jobject person = env->AllocObject(personClass);
    jmethodID setStudent = env->GetMethodID(personClass, "setStudent",
                                            "(Lcom/baojie/learn/project_jni/day19/Student;)V");
    env->CallVoidMethod(person, setStudent, student);

    jmethodID putStudent = env->GetStaticMethodID(personClass, "putStudent",
                                                  "(Lcom/baojie/learn/project_jni/day19/Student;)V");
    env->CallStaticVoidMethod(personClass, putStudent, student);

    // 规范：一定记得释放【好习惯】
    // 第一类
    env->DeleteLocalRef(student);
    env->DeleteLocalRef(studentClass);
    env->DeleteLocalRef(person);
    env->DeleteLocalRef(personClass);
    // 第二类
    // env->ReleaseStringUTFChars()
    // TODO 局部引用： jobject jclass jstring ...  【函数结束后，会自动释放】
}

jclass dogClass;

extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day19_JavaObjectActivity_testQuote(JNIEnv *env, jobject thiz) {
    if (dogClass == NULL){
//        dogClass = env->FindClass("com/baojie/learn/project_jni/day19/Dog");
        jclass temp = env->FindClass("com/baojie/learn/project_jni/day19/Dog");
        dogClass = static_cast<jclass>(env->NewGlobalRef(temp));
        // 记住：用完了，如果不用了，马上释放，C C++ 工程师的赞美
        env->DeleteLocalRef(temp);
    }
    // <init> V  是不会变的
    //空参构造函数
    jmethodID init = env->GetMethodID(dogClass, "<init>", "()V");
    jobject dog = env->NewObject(dogClass, init);

    init = env->GetMethodID(dogClass, "<init>", "(I)V");
    dog = env->NewObject(dogClass, init, 100);

    init = env->GetMethodID(dogClass, "<init>", "(II)V");
    dog = env->NewObject(dogClass, init, 100, 200);

    init = env->GetMethodID(dogClass, "<init>", "(III)V");
    dog = env->NewObject(dogClass, init, 100,200,300);
    env->DeleteLocalRef(dog);
//    dog = NULL;
//    env->DeleteLocalRef(dogClass);
//    dogClass = NULL;
}

extern int age;
extern void show();

extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day19_JavaObjectActivity_delQuote(JNIEnv *env, jobject thiz) {
    if (dogClass != NULL){
        LOGE("全局引用释放完毕，上面的按钮已经失去全局引用，再次点击会报错");
        env->DeleteGlobalRef(dogClass);
        dogClass = NULL;
    }

    show();
}
extern "C"
JNIEXPORT jlong JNICALL
Java_com_baojie_learn_project_1jni_day23_DParcel_nativeCreate(JNIEnv *env, jobject thiz) {
    DParcel * parcel = new DParcel();
    return reinterpret_cast<jlong>(parcel);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day23_DParcel_nativeWriteInt(JNIEnv *env, jobject thiz,
                                                                jlong native_ptr, jint data) {
    DParcel * parcel = reinterpret_cast<DParcel *>(native_ptr);
    parcel->writeInt(data);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_baojie_learn_project_1jni_day23_DParcel_nativeSetDataPosition(JNIEnv *env, jobject thiz,
                                                                       jlong native_ptr, jint pos) {
    DParcel * parcel = reinterpret_cast<DParcel *>(native_ptr);
    parcel->setDataPosition(pos);
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_baojie_learn_project_1jni_day23_DParcel_nativeReadInt(JNIEnv *env, jobject thiz,
                                                               jlong native_ptr) {
    DParcel * parcel = reinterpret_cast<DParcel *>(native_ptr);
    parcel->readInt();
}