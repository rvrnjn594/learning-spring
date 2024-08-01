package springdatajpa;
// when using JPA, we do not need to create a table.

import jakarta.persistence.*;

import java.util.Date;

// If we want to map this class to a table with different nane, we can use the @Table annotation and provide the name of the table to which the bean maps to.
// Since the name of the entity and table match, we do not need the @Table annotation.
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue // whenever a new row is inserted, we do not need to supply the Id value
    private int id;
    private String name;

    @Column(name = "nationality")
    private String nationality;

    private Date birthDate;
    private int titles;

    // create two constructors (with arguments and no-arguments), getters and setters, as well as a toString() method for the fields.
    // no-arg constructor is a requirement of the BeanPropertyRowMapper.

    public Player() {
    }

    public Player(int id, String name, String nationality, Date birthDate, int titles) {
        super();
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.titles = titles;
    }


    // defining a constructor that initialized all attributes except Id
    public Player(String name, String nationality, Date birthDate, int titles) {
        super();
        this.name = name;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.titles = titles;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getTitles() {
        return titles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthDate=" + birthDate +
                ", titles=" + titles +
                '}';
    }
}
