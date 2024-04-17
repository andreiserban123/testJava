import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Proiect> lista;
        Map<String, List<String>> acronymsByDepartment;

        try (BufferedReader br = new BufferedReader(new FileReader("proiecte.csv"))) {
            lista = br.lines().skip(1)
                    .map(Main::parseLine)
                    .toList();

            acronymsByDepartment = lista.stream().collect(
                    Collectors.groupingBy(
                            Proiect::getDepartament,
                            Collectors.mapping(Proiect::getAcronim, Collectors.toList())
                    )
            );
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        acronymsByDepartment.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });

        double totalBudget = lista.stream().mapToDouble(Proiect::getBuget).sum();
        System.out.println("Bugetul Total: " + totalBudget);


        List<Proiect> listSort = lista.stream().filter(proiect -> proiect.getBuget() > 0)
                .sorted((p1, p2) -> Double.compare(p2.getNrMembrii(), p1.getNrMembrii())
                ).toList();
        System.out.println("Sorted List: ");
        listSort.forEach(System.out::println);

        // Save into a new CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("valoare_proiecte.csv"))) {
            for (Proiect p : listSort) {
                bw.write(p.getCodProiect() + "," + p.getAcronim() + "," + p.getSefProiect() + "," + p.getDepartament() + ","
                        + p.getBuget() + "," + p.getNrMembrii());
                bw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Save into a binary file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("proiecte.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(dos)
        ) {
            oos.writeObject(listSort);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Read from binary file and print map
        try (DataInputStream dis = new DataInputStream(new FileInputStream("proiecte.dat"));
             ObjectInputStream ois = new ObjectInputStream(dis)
        ) {
            List<Proiect> fromBin = (List<Proiect>) ois.readObject();
            System.out.println("From bin");
            Map<Integer, Map<String, Double>> map = new HashMap<>();
            for (Proiect p : fromBin) {
                System.out.println(p);
                map.computeIfAbsent(p.getCodProiect(), k -> new HashMap<>()).put(p.getAcronim(), p.getBuget());
            }

            // Print map
            map.forEach((key, value) -> {
                System.out.println("Cod Proiect: " + key);
                value.forEach((acronim, budget) -> {
                    System.out.println("Acronim: " + acronim + ", Budget: " + budget);
                });
            });
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Proiect parseLine(String line) {
        String[] split = line.split(",");
        return new Proiect(Integer.parseInt(split[0]), split[1], split[2], split[3],
                Double.parseDouble(split[4]), Integer.parseInt(split[5]));
    }
}
