import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Element> elemente = new ArrayList<Element>();
        try (BufferedReader br = new BufferedReader(new FileReader("matricerara.csv"))) {

            String line = br.readLine();
            while (line != null) {
                String[] i = line.split(",");
                Element e = new Element();
                e.setLine(Integer.parseInt(i[0]));
                e.setColoana(Integer.parseInt(i[1]));
                e.setValoare(Double.parseDouble(i[2]));
                elemente.add(e);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Long nrNeg = elemente.stream().filter(e -> e.getValoare() < 0).count();
        System.out.println("Nr negative: " + nrNeg);

        var m = elemente.stream().collect(Collectors.groupingBy(Element::getColoana, Collectors.averagingDouble(Element::getValoare)));
        m.forEach((k, v) -> {
            System.out.println("Coloana " + k + " are media: " + v);
        });

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("diagonal.dat"))) {
            List<Element> diag = new ArrayList<>();
            for (Element e : elemente) {
                if (e.getColoana() == e.getLine()) {
                    diag.add(e);
                }
            }
            oos.writeObject(diag);

        } catch (IOException e) {
            System.err.println(e);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("diagonal.dat"))) {
            List<Element> diag = (List<Element>) ois.readObject();
            System.out.println("Elemente din fis binar:");
            diag.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}