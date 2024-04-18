import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Proiect> proiecte = new ArrayList<Proiect>();
        try (BufferedReader br = new BufferedReader(new FileReader("P.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                Proiect pr = new Proiect();
                pr.setCod(Long.parseLong(elements[0]));
                pr.setAcronim(elements[1]);
                pr.setAnulLansarii(Integer.parseInt(elements[2]));
                pr.setDepartament(elements[3]);
                pr.setBuget(Double.parseDouble(elements[4]));
                pr.setDurata(Integer.parseInt(elements[5]));
                pr.setDirector(elements[6]);

                proiecte.add(pr);

                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Proiecte");
        proiecte.forEach(System.out::println);
        try (BufferedReader br = new BufferedReader(new FileReader("Cheltuieli.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                long id = Long.parseLong(elements[0]);
                String name = elements[1];
                double price = Double.parseDouble(elements[2]);
                for (Proiect p : proiecte) {
                    if (p.getCod() == id) {
                        p.setCheltuieli(p.getCheltuieli() + price);
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Dupa cheltuieli");
        proiecte.forEach(System.out::println);

        System.out.println("Salvare in binar");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Proiecte.dat"))) {
            oos.writeObject(proiecte);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Proiect> proiecteBinar;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Proiecte.dat"))) {
            proiecteBinar = (List<Proiect>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Din binar");
        proiecteBinar.forEach(System.out::println);
        System.out.println("Sortare dupa buget Coane Fanica");
        List<Proiect> listaNoua = proiecte.stream().sorted(Comparator.comparingDouble(l -> l.getBuget() - l.getCheltuieli())
        ).toList();
        listaNoua.forEach(System.out::println);
        System.out.println("-----Dupa departamente-----------");
        Map<String, List<Proiect>> map = proiecte.stream().collect(Collectors.groupingBy(
                Proiect::getDepartament
        ));
        map.forEach((k, v) -> {
            System.out.println("Departament: " + k);
            v.forEach(System.out::println);
        });
    }
}