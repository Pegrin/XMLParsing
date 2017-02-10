package org.wtiger.inno.day5xmlparsing.anotherone;

/**
 * Created by olymp on 10.02.2017.
 */
public class People {
    private String name;
    Integer age;
    Double salary;
    int a[];
    int[] b;
    People people;

    public People(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        a = new int[] {1, 2};
        b = new int[] {3, 4};
    }
}
