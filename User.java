package com.learn.design;

/**
 * Program to generate hashcode and equals method
 *
 * @author shivam
 */

public class User {
    private String name;
    private int age;
    private char sex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (sex != user.sex) return false;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + (int) sex;
        return result;
    }

    public int hashCode1() {
        int res = 17;
        res = 31 * res + name.hashCode();
        res = 31 * res + age;
        res = 31 * res + (int) sex;
        return res;
    }
}
