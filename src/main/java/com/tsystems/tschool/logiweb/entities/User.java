package com.tsystems.tschool.logiweb.entities;

import com.tsystems.tschool.logiweb.entities.Statuses.UserRole;

import javax.persistence.*;


@Entity
@NamedQuery(name="User.findUserByEmail" ,
        query="SELECT user FROM User user WHERE user.mail=:email")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id", unique = true, nullable = false)
    private int id;

    @Column(name = "user_mail", nullable = false, unique = true)
    private String mail;

    public User(String mail, UserRole role, String password) {
        this.mail = mail;
        this.role = role;
        this.password = password;
    }

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "user_password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "userAccount")
    private Driver attachedDriver;

    public User() {
    }

    public Driver getAttachedDriver() {
        return attachedDriver;
    }

    public void setAttachedDriver(Driver attachedDriver) {
        this.attachedDriver = attachedDriver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passMd5) {
        this.password = passMd5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!mail.equals(user.mail)) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mail.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}

