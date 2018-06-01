package com.android.app.java8;

/**
 * Created by Aleesha Kanwal on 27/05/2018.
 */

public class Person implements PersonInterface {

    private String firstName, lastName, job, gender;
    private int salary, age;

    public Person(String firstName, String lastName, String job, String gender, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: "+this.getFullName()+" ,Gender: "+this.gender+ " ,Age: "+this.age +  " ,Job: "+this.job +  " & Salary : "+this.salary;
    }

}
