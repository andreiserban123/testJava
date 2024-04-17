import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Proiect> proiecte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("proiecte.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                Proiect p = new Proiect();
                p.setCodProiect(Integer.parseInt(elements[0]));
                p.setAcronim(elements[1]);
                p.setSefProiect(elements[2]);
                p.setDepartament(elements[3]);
                p.setBuget(Double.parseDouble(elements[4]));
                p.setNrMembrii(Integer.parseInt(elements[5]));
                proiecte.add(p);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Proiecte:");
        proiecte.forEach(System.out::println);
        var cerinta = proiecte.stream().collect(Collectors.groupingBy(Proiect::getDepartament));
        cerinta.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(System.out::println);
        });
        var proiecteSortate = proiecte.stream().sorted((p1, p2) -> Double.compare(p2.getBuget(), p1.getBuget()))
                .toList();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("valoare_proiecte.csv"))) {
            for (Proiect p : proiecteSortate) {
                bw.write(p.getCodProiect() + ",");
                bw.write(p.getAcronim() + ",");
                bw.write(p.getBuget() + ",");
                bw.write("" + p.getNrMembrii());
                bw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("proiecte.dat"))) {
            oos.writeObject(proiecte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("proiecte.dat"))) {
            var lista = (List<Proiect>) ois.readObject();
            System.out.println("Lista din binar:");
            lista.forEach(System.out::println);
            Map<Integer, ?> map = lista.stream().collect(Collectors.toMap(
                    Proiect::getCodProiect,
                    proiect ->
                            new Object() {
                                String acronimul = proiect.getAcronim();
                                double buget = proiect.getBuget();

                                @Override
                                public String toString() {
                                    return acronimul + "," + buget;
                                }
                            }
            ));
            map.forEach((k, v) -> {
                System.out.println(k + ":" + v);
            });
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
