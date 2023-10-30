import java.io.*;
import java.util.Properties;

/**
 * @version 1.0
 */
public class Main2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Properties properties = new Properties();
        properties.setProperty("name", "tom");
        properties.setProperty("age", "5");
        properties.setProperty("color", "red");
        properties.store(new FileWriter("d:/dog.properties"),"123asas");
        Dog dog = new Dog(properties.getProperty("name"),
                Integer.parseInt(properties.getProperty("age")), properties.getProperty("color"));
        System.out.println("Dog: " + dog);
        ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("d:/dog.bat"));
        ob.writeObject(dog);
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("d:/dog.bat"));
        Dog dog1= (Dog) oi.readObject();
        System.out.println("Dog1: " + dog1);
    }
}
class Dog implements Serializable{
    String name ;
    int age ;
    String color ;

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
