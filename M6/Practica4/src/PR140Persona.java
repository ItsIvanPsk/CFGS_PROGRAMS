import java.io.Serializable;

public class PR140Persona implements Serializable {
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

    @Override
    public String toString() {
        return "PR140Persona{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}
