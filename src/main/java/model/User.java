package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "city")
    private String city;

    protected User(){

    }

    public User (String name, int age, String password, String city) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.city = city;
    }

    public User (Long id, String name, int age, String password, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
