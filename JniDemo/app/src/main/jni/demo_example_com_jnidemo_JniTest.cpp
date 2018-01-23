//
// Created by 丁军明 on 2018/1/22.
//
#include "demo_example_com_jnidemo_JniTest.h"

JNIEXPORT jstring JNICALL Java_demo_example_com_jnidemo_JniTest_test(JNIEnv *env, jobject obj)
{
return env ->NewStringUTF("nihao");
}

