package com.example.med.bd.doctor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "doctor")
public class Doctor implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "surname")
    String surname;

    @ColumnInfo(name = "patronymic")
    String patronymic;

    @ColumnInfo(name = "age")
    int age;

    @ColumnInfo(name = "phone_number")
    String phone_number;

    @ColumnInfo(name = "email")
    String email;

    public Doctor(long id, String name, String surname, String patronymic, int age, String phone_number, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.phone_number = phone_number;
        this.email = email;
    }

    @Ignore
    public Doctor(String name, String surname, String patronymic, int age, String phone_number, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.phone_number = phone_number;
        this.email = email;
    }

    public long getId() {return this.id;}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age='" + age +'\'' +
                ", phone number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
