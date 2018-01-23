package demo.example.com.jnidemo;


public class JniTest {
    static {
        System.loadLibrary("JNISample");
    }

    public native String test();
}
