package com.baojie.learn.project_jni.day19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.baojie.learn.project_jni.R;

public class JavaObjectActivity extends AppCompatActivity {

    private final static String TAG = JavaObjectActivity.class.getSimpleName();

    static {
        // System.load(D:/xxx/xxxx/xxx/native-lib); 这种是可以绝对路径的加载动态链接库文件
        // 这种是从库目录遍历层级目录，去自动的寻找   apk里面的lib/libnative-lib.so
//        System.loadLibrary("native-lib");
    }

    /**
     * 下面是 native 区域
     */
    // String引用类型，玩数组
    public native void testArrayAction(int count, String textInfo, int[] ints, String[] strings);
    // 传递引用类型，传递对象
    public native void putObject(Student student, String name);
    // 凭空创建Java对象
    public native void insertObject();
    // 测试引用
    public native void testQuote();

    public native void delQuote();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_object);
    }

    public void test01(View view) {
        int[] ints = new int[]{1,2,3,4,5,6};
        String[] strings = new String[]{"aa", "bb", "cc"};
        testArrayAction(99, "哈哈", ints, strings);
    }

    public void test02(View view) {
        Student student = new Student(); // Java new
        student.name = "史泰龙";
        student.age = 88;
        putObject(student, "九阳神功");

    }

    public void test03(View view) {
        insertObject();
    }

    public void test04(View view) {
        testQuote();
    }

    public void test05(View view) {
        delQuote();
    }
}