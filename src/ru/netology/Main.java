package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

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

        //Несовершеннолетние
        long children = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        System.out.println(children);

        //Призывники

        List <String> recruits = persons.stream()
                .filter(x -> (x.getAge() > 17 && x.getAge() < 27))
                .filter(x -> x.getSex().equals(Sex.MAN))
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        System.out.println(recruits.size());

        //Потенциально работоспособные с высшим образованием

        List <Person> workable = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getSex().equals(Sex.MAN) ? x.getAge() < 65 : x.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(workable.size());
    }
}
