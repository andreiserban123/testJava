import java.io.*;

public class LucruCSV {
    public static void main(String[] args) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("test.csv"))) {
            bw.write("test,");
            bw.write(String.valueOf(12 + 3));
            bw.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (BufferedReader br = new BufferedReader(new FileReader("test.csv"))) {
            String line = br.readLine();
            while (line != null) {
                String[] elements = line.split(",");
//                Proiect p = new Proiect();
//                p.setCodProiect(Integer.parseInt(elements[0]));
//                p.setAcronim(elements[1]);
//                p.setSefProiect(elements[2]);
//                p.setDepartament(elements[3]);
//                p.setBuget(Double.parseDouble(elements[4]));
//                p.setNrMembrii(Integer.parseInt(elements[5]));
//                proiecte.add(p);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
