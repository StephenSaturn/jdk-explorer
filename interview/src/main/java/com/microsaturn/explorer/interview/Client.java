package com.microsaturn.explorer.interview;

public class Client {

    public static void main(String[] args) {
        System.out.println("Client.main");

        System.out.println(Integer.toBinaryString(14));

        System.out.println(Integer.toBinaryString(14 >>> 16));

        // 获取当前机器的cpu核数
        // System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     * =======================================
     * Annotation是JDK1.5新增加的特性之一
     * JDK1.5内部提供的三种注解是: @SuppressWarning(":deprecation")、@Deprecated、@Override
     * =======================================
     */

    @SuppressWarnings("deprecation") // 禁止警告，一个注解就是一个类，在这里使用了这个注解就是创建了SuppressWarnings类的一个实例对象
    public boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated // 已经废弃，不建议使用，在未来版本中有可能会被拿掉
    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @Override // 重写（覆盖）
    public String toString() {
        return "Welcome to Microsaturn!";
    }

    /**
     * =================================================================
     * 总结：注解(Annotation)相当于一个标记，在程序中加入注解等于为程序打上某种标记，后面，javac编译器、开发工具和其他程序可以
     * 通过反射来了解你的类以及各种元素上有无何种标记，根据标记去执行相应的指令，标记可以加在包、类、属性和方法的参数以及局部变量上
     * =================================================================
     */
}
