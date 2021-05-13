package com.revature.vinson_chin_p0.models;

/**
 * AppUser class used for constructing an AppUser object
 * @author Vinson Chin
 *
 */
public class AppUser {

    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String dob;
    private long phone;

    public AppUser() {
        super();
    }

    public AppUser(String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName,
                   String dob,
                   long phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
    }

    public AppUser(int id,
                   String username,
                   String password,
                   String email,
                   String firstName,
                   String lastName,
                   String dob,
                   long phone) {
        this(username, password, email, firstName, lastName, dob, phone);
        this.id = id;
    }

    /**
     * Getters and Setters
     *
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * Method to create a string containing the information of the AppUser object
     *
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUser{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth='").append(dob).append('\'');
        sb.append(", phone='").append(phone);
        sb.append('}');
        return sb.toString();
    }
    
}
