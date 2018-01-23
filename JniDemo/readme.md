#Android NDK开发（java与C/C++的互相调用）
Ndk帮助开发者快速开发C/C++的动态库，并能自动将so和java一起打包成apk。而JNI是Java语言提供的Java和C/C++相互沟通的机制，Java可以通过JNI调用C/C++代码，C/C++的代码也可以调用java代码。
NDK可以为我们生成了C/C++的动态链接库，JNI是java和C/C++沟通的接口，安卓是java程序语言开发，然后通过JNI又能与C/C++沟通，所以我们可以使用NDK+JNI来实现“Java+C”的开发方式。


#实现的核心代码是
###步骤一:配置你的NDK环境(如果没有NDK，去Tools --> Android  --> SDK Manager --> SDK Tools下，下载LLDB和NDK)
File--> Project Stucture,进入后需要配置你SDK,JDK,NDK路径！
在Project下gradle.properties文件中添加
android.useDeprecatedNdk=true
###步骤二:
创建一个java类

public class JniTest {
    static {
        System.loadLibrary("JNISample");
    }

    public native String test();
}
###步骤三:
使用Terminal命令生成一个.class文件和.h的头文件，具体如下：
a.  cd 你java类的文件路径
b.  javac 你的java类名
c.  cd 你的java包的路径（你项目下的java包，不是你的java类）
d.  javah 你的.class文件名
###步骤四:
在src包下新建一个jni文件夹，把你所生成的头文件拖进去，并新建一个C++文件
//引用你的头文件
#include "你头文件的名称"
//从.h文件中复制方法过来（方法名、参数类型、返回值等必须一致
JNIEXPORT jstring JNICALL 你复制的方法名(JNIEnv *env, jobject obj)
{
//返回值
return env ->NewStringUTF(任意字符串);
}
###步骤五:
在app下的build.gradle中的defaultConfig中添加
ndk{
            moduleName "JNISample"
            stl "stlport_static"
            ldLibs "log"
            abiFilters 'armeabi-v7a', 'arm64-v8a' // 'x86', 'x86_64' may be added
        }
同步后再Rebuild Project
###步骤六:
在activity中调用你的方法即可

