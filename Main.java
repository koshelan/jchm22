package hmjc.hm2.hm22;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> stream1 = persons.stream().
                filter(person -> person.getAge() < 18);
        System.out.println("1. Несовершенно летних: " + stream1.count());

        Stream<Person> stream2 = persons.stream().
                filter(person -> person.getSex().equals(Sex.MAN)).
                filter(person -> (person.getAge() >= 18) && (person.getAge() <= 27));
        List<String> conscript = stream2.map(Person::getFamily).collect(Collectors.toList());
//        System.out.println("2. Призывники: " + conscript);


        Stream<Person> stream3 = persons.stream().
                filter(person -> person.getEducation().equals(Education.HIGHER)).
                filter(person -> (person.getAge() >= 18) && (
                        ((person.getSex().equals(Sex.WOMEN)) && (person.getAge() <= 60)) ||
                        ((person.getSex().equals(Sex.MAN)) && (person.getAge() <= 65)))).
                sorted(Comparator.comparing(Person::getFamily));
        List<Person> workAble = stream3.collect(Collectors.toList());
//        System.out.println("3. Работоспособные: \n" + workAble);
    }

}
