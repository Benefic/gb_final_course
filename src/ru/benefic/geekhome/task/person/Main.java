package ru.benefic.geekhome.task.person;

public class Main {
    public static void main(String[] args) {
        Person person = Person.builder()
                .firstName("Alex")
                .lastName("Bobrov")
                .middleName("Petrovich")
                .country("Russia")
                .address("Moscow")
                .phone("+79998887766")
                .age(35)
                .gender("male")
                .build();
        System.out.println(person);
    }
}
