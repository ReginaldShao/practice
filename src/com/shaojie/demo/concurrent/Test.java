package com.shaojie.demo.concurrent;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
import sun.reflect.Reflection;

class User {
    private String name = "";
    private int age = 0;

    public User() {
        this.name = "test";
        this.age = 22;
    }

    @Override
    public String toString() {
        return name + ": " + age;
    }
}

public class Test {
    public static void main(String[] args) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        // 通过反射得到theUnsafe对应的Field对象
        Unsafe.getUnsafe();
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // 设置该Field为可访问
        field.setAccessible(true);
        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
        User user = null;
        try {
            user = (User)unsafe.allocateInstance(User.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(user);

    }
}
