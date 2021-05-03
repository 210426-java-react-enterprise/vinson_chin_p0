package com.revature.project0.models;

/*
    Classes must be named the exact same as the file itself!

    Class names should (for best practice) be in PascalCase
        - not to be confused with camelCase

    POJO = Plain Ol' Java Object
        - Does not (usually) contain any methods beyond simple getters and setters
            + maybe the occasional convenience method
 */
public class AppUser {

    private String username;
    private String password;
    private String email;
    private String firstName; // variables should be in camelCase
    private String lastName;
    private int age;

    public AppUser(String username, String password, String email, String firstName, String lastName, int age) {
        System.out.println("AppUser constructor invoked!");
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getUsername() {
        // you do not have to include "this." here because there is no other variable
        // with the same name in this scope
        return username;
    }

    public void setUsername(String username) {
        // "this." is required here, otherwise you do not target the correct variable
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toFileString() {
        return String.format("%s;%s;%s;%s;%s;%d", username, password, email, firstName, lastName, age);
    }

//    @Override
//    public String toString() {
//        return "AppUser{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", age=" + age +
//                '}';
//    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
    
}
