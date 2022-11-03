import java.io.Serializable;

public class PR314Animal implements Serializable{
    String name;
    String race;
    int age;
    public PR314Animal(String name, String race, int age) {
        super();
        this.name = name;
        this.race = race;
        this.age = age;
    }

    @Override
    public String toString() {
        return "\n" 
             + "Nombre: " + getName() + "\n" 
             + "Raza: " + getRace() + "\n"
             + "Age: " + getAge() + "\n";
    }

    public String getName()
    {
        return this.name;
    }
    public String getRace()
    {
        return this.race;
    }
    public int getAge()
    {
        return this.age;
    }
}
