import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<ContContabil> conturi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Conturi.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                ContContabil c = new ContContabil();
                c.setSimbolCont(Integer.parseInt(elements[0]));
                c.setDenumire(elements[1]);
                c.setFunctiuneaContabila(elements[2]);
                c.setSoldInitial(Double.parseDouble(elements[3]));
                c.setRulajDebitor(Double.parseDouble(elements[4]));
                c.setRulajCreditor(Double.parseDouble(elements[5]));
                conturi.add(c);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        conturi.forEach(System.out::println);

        System.out.println("Dupa nota contabila");
        try (BufferedReader br = new BufferedReader(new FileReader("NotaContabila.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
                int debitor = Integer.parseInt(elements[0]);
                int creditor = Integer.parseInt(elements[1]);
                double suma = Double.parseDouble(elements[2]);
                for (ContContabil c : conturi) {
                    if (c.getSimbolCont() == debitor) {
                        c.setRulajDebitor(c.getRulajDebitor() + suma);
                    } else if (c.getSimbolCont() == creditor) {
                        c.setRulajCreditor(c.getRulajCreditor() + suma);
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        conturi.forEach(System.out::println);

        System.out.println("Salvare in binar");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Balanta.dat"))) {
            oos.writeObject(conturi);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<ContContabil> conturi2;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(("Balanta.dat")))) {
            conturi2 = (List<ContContabil>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Cont dupa binar");
        conturi2.forEach(System.out::println);

        System.out.println("Sortare conturi");
        var sortedList = conturi.stream().sorted((c1, c2) -> {
            double sold1, sold2;
            if (c1.getFunctiuneaContabila().equals("A")) {
                sold1 = c1.getSoldInitial() + c1.getRulajDebitor() - c1.getRulajCreditor();
            } else {
                sold1 = c1.getSoldInitial() + c1.getRulajCreditor() + c1.getRulajDebitor();
            }
            if (c2.getFunctiuneaContabila().equals("A")) {
                sold2 = c2.getSoldInitial() + c2.getRulajDebitor() - c2.getRulajCreditor();
            } else {
                sold2 = c2.getSoldInitial() + c2.getRulajCreditor() + c2.getRulajDebitor();
            }
            return Double.compare(sold1, sold2);
        }).toList();
        sortedList.forEach(System.out::println);

        Map<String, Double> m = conturi.stream().collect(Collectors.groupingBy(
                ContContabil::getFunctiuneaContabila,
                Collectors.summingDouble(c1 -> {
                    double sold1;
                    if (c1.getFunctiuneaContabila().equals("A")) {
                        sold1 = c1.getSoldInitial() + c1.getRulajDebitor() - c1.getRulajCreditor();
                    } else {
                        sold1 = c1.getSoldInitial() + c1.getRulajCreditor() + c1.getRulajDebitor();
                    }
                    return sold1;
                })
        ));
        m.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
