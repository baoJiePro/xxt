package com.baojie.learn.project_jni.activity;

import static com.blankj.utilcode.util.CrashUtils.init;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baojie.learn.project_jni.R;

public class SplashActivity extends AppCompatActivity {

    /*
   签名规则 大写

   javap -s -p MainActivity     必须是.class

    Java的boolean  --- Z  注意点
    Java的byte  --- B
    Java的char  --- C
    Java的short  --- S
    Java的int  --- I
    Java的long  --- J     注意点
    Java的float  --- F
    Java的double  --- D
    Java的void  --- V
    Java的引用类型  --- Lxxx/xxx/xx/类名;
    Java的String  --- Ljava/lang/String;
    Java的array  int[]  --- [I         double[][][][]  --- [[[D
    int add(char c1, char c2) ---- (CC)I
    void a()     ===  ()V

    javap -s -p xxx.class    -s 输出xxxx.class的所有属性和方法的签名，   -p 忽略私有公开的所有属性方法全部输出
 */

    public String name = "anJin";
    private TextView tvName;

    public static int age = 29;

    public native void changeName();

    public static native void changeAge();

    public native void callAddMethod();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvName = findViewById(R.id.tv_name);
        initName();

    }

    private void initName() {
        changeName();
        tvName.setText(name);
        changeAge();
        tvName.setText("" + age);
        callAddMethod();
    }

    // 专门写一个函数，给native成调用
    public int add(int number1, int number2){
        return number1 + number2;
    }
}