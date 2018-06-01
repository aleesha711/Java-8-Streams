package com.android.app.java8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Aleesha Kanwal on 27/05/2018.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        // Increase salary by 5% to programmers
        System.out.println("\n\nIncrease salary by 5% to programmers:");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());

        javaProgrammers.forEach(giveRaise);
        javaProgrammers.forEach((p) -> System.out.printf("%s earns now $%,d.%n", p.getFullName(), p.getSalary()));

        // Print PHP programmers that earn more than $1,400
        System.out.println("\nJava programmers that earn more than $1,400:");
        javaProgrammers.stream()
                .filter((p) -> (p.getSalary() > 1400))
                .forEach((p) -> System.out.println(p.getFullName()));


        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println("\n\nFemale Java programmers that earn more than $1,400 and are older than 24 years:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.println(p.getFullName()));


        // Reuse filters
        System.out.println("\n\nFemale Java programmers older than 24 years:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("\n\nPrint first 3 female Java programmers:");
        javaProgrammers.stream()
                .filter(genderFilter)
                .limit(3)
                .forEach((p) -> System.out.println(p.getFullName()));

        // sorted, collect, limit, min, max examples
        System.out.println("\n\nSort and print first 5 Java programmers by name:");
        List<Person> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(toList());

        sortedJavaProgrammers.forEach((p) -> System.out.println(p.getFullName()));

        System.out.println("\nSort and print Java programmers by salary:");
        sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getSalary() - p2.getSalary()))
                .collect(toList());

        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));

        // min is faster than sorting and choosing the first
        System.out.println("\nGet the lowest Java programmer salary:");
        Person pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name:  %s; Salary: $%,d.", pers.getFullName(), pers.getSalary());

        System.out.println("\nGet the highest Java programmer salary:");
        Person person = javaProgrammers
                .stream()
                .max((p, p2) -> (p.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary());

        // map, collect examples
        System.out.println("\nGet Java programmers first name to String:");
        String phpDevelopers = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(joining(" ; "));

        System.out.println(phpDevelopers);

        System.out.println("\nGet Java programmers first name to Set:");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());

        javaDevFirstName.stream().forEach((s) -> System.out.printf("%s ", s));

        javaDevFirstName
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        System.out.println("\nGet Java programmers last name to TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));

        javaDevLastName.stream().forEach((s) -> System.out.printf("%s ", s));

        int numProcessorsOrCores = Runtime.getRuntime().availableProcessors();
        System.out.printf("\n\nParallel version on %s-core machine:", numProcessorsOrCores);

        // parrallel stream, 50% faster than stream
        System.out.println("\nCalculate total money spent for paying Java programmers:");
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();

        System.out.printf("Money spent for paying Java programmers: $%,d %n", totalSalary);

        //Get count, min, max, sum, and average for numbers
        System.out.println("Get Java programmers salary to List:");
        List<Integer> salary = javaProgrammers
                .stream()
                .map(Person::getSalary)
                .collect(toList());

        IntSummaryStatistics stats = salary
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("Summary : " + stats);
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());


        List<Person> filtered =
                javaProgrammers
                        .stream()
                        .filter(p -> p.getFirstName().startsWith("P"))
                        .collect(Collectors.toList());

        System.out.println(filtered);

        //groups all persons by age:
        Map<Integer, List<Person>> personsByAge = javaProgrammers
                .stream()
                .collect(Collectors.groupingBy(p -> p.getAge()));

        personsByAge
                .forEach((age, p) -> System.out.format("Group all persons by age %s: %s\n", age, p));

        //Avergae Age
        Double averageAge = javaProgrammers
                .stream()
                .collect(Collectors.averagingInt(p -> p.getAge()));

        System.out.println(averageAge);


        String phrase = javaProgrammers
                .stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getFirstName())
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);

        Map<Integer, String> map = javaProgrammers
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getAge(),
                        p -> p.getFirstName(),
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);

        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.getFirstName().toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = javaProgrammers
                .stream()
                .collect(personNameCollector);

        System.out.println(names);

        //Reduction
        String s = javaProgrammers.stream().map(e -> e.toString()).reduce("", String::concat);
        System.out.println("Reduced String : " + s);

        String s1 = javaProgrammers.stream().map(i -> i.toString()).collect(Collectors.joining(","));
        System.out.println("Reduced String with collector : " + s1);

        int sum = salary.stream().reduce(0, (x, y) -> x + y);
        System.out.println("Reduced List to sum : " + sum);

    }

}
