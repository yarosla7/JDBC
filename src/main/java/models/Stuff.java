package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "stuff")
public class Stuff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false)
    private City city;

    public Stuff() {
    }

    public Stuff(int id, String first_name, String last_name, String gender, int age, City city) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Stuff(String first_name, String last_name, String gender, int age, City city) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Stuff(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", city=" + city +
                '}';
    }
}