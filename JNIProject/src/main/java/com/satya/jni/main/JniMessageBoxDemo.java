package com.satya.jni.main;

public class JniMessageBoxDemo {
	static {
        // Loads jnimessagebox.dll at runtime
        System.loadLibrary("jnimessagebox");
    }

    // Declare the native method
    public native void showMessageBox(String title, String message);

    public static void main(String[] args) {
        JniMessageBoxDemo demo = new JniMessageBoxDemo();
        System.out.println("Triggering Windows popup via JNI...");
        demo.showMessageBox("Windows Popup", "Hello from a Windows OS API called via JNI!");
    }

}
