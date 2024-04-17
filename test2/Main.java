import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Punct> puncte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("puncte.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Punct p = new Punct();
                p.setEtichetaFigurii(data[0]);
                p.setEtichetaPunctului(data[1]);
                p.setOx(Double.parseDouble(data[2]));
                p.setOy(Double.parseDouble(data[3]));
                puncte.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long nrPuncte = puncte.stream().distinct().count();
        System.out.println("nrPuncte: " + nrPuncte);
        Map<String, Long> m = puncte.stream().collect(Collectors.groupingBy(
                Punct::getEtichetaFigurii,
                Collectors.counting()
        ));
        m.forEach((k, v) -> System.out.println(k + ": " + v));
        var puncteOrd = puncte.stream().sorted((p1, p2) -> Double.compare(p2.distanta(), p1.distanta())).toList();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("distante.csv"))) {
            for (Punct p : puncteOrd) {
                bw.write(p.getEtichetaFigurii() + ",");
                bw.write(p.getEtichetaPunctului() + ",");
                bw.write("" + p.distanta());
                bw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
