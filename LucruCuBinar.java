import java.io.*;

public class LucruCuBinar {
    public static void main(String[] args) {
        Student s1 = new Student("andrei", 18, 9.3f);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(s1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student s4 = (Student) ois.readObject();
            System.out.println(s4);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
