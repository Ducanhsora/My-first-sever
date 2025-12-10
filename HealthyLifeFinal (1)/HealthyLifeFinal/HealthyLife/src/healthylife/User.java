/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthylife;

/**
 *
 * @author Admin
 */
public class User {
    private String name;
    private String password;
    private int age;
    private double height;
    private double weight;
    private String gender;

    public User(String name, String password, int age, double height, double weight, String gender) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }
    
     @Override
    public String toString() {
        return "Name: " + name +
               "\nAge: " + age +
               "\nHeight: " + height +
               "\nWeight: " + weight +
               "\nGender: " + gender;
    }
    
}
