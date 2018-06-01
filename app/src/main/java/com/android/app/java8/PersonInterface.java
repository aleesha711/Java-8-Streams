package com.android.app.java8;

/**
 * Created by Aleesha Kanwal on 27/05/2018.
 */

public interface PersonInterface {
    String getFirstName();
    String getLastName();
    String getJob();
    String getGender();
    int getSalary();
    int getAge();
    void setSalary(int salary);
    void setJob(String job);

    default String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
