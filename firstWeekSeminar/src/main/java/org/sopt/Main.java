package org.sopt;

import org.sopt.classes.Person;
import org.sopt.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        //Person 클래스의 객체 생성
        Person person = new Person("곽성준", 25, "male");
        //Person 클래스 내부 메소드 호출
        person.walk();

        System.out.println(person.getName());
        person.setName("운팀장");
        System.out.println(person.getName());

        Person personWithBuilder = new
                PersonBuilder()
                .name("곽성준")
                .age(25)
                .sex("male")
                .build();

        Person personWithFactoryMethod = Person.create("곽성준", 25, "male");

    }
}