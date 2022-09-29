import java.io.Serializable;

public class PR140Persona implements Serializable{
    private String name;
    private String surname;
    private int age;
    private String city;

    public PR140Persona(String name, String surname, int age, String city){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }
}