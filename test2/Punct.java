import java.util.Objects;

public class Punct implements Comparable<Punct> {
    private String etichetaFigurii;
    private String etichetaPunctului;
    private double Ox;
    private double Oy;

    public Punct() {
    }

    public Punct(String etichetaFigurii, String etichetaPunctului, double ox, double oy) {
        this.etichetaFigurii = etichetaFigurii;
        this.etichetaPunctului = etichetaPunctului;
        Ox = ox;
        Oy = oy;
    }

    double distanta() {
        return Math.sqrt(Math.pow(Ox, 2) + Math.pow(Oy, 2));
    }

    public String getEtichetaFigurii() {
        return etichetaFigurii;
    }

    public void setEtichetaFigurii(String etichetaFigurii) {
        this.etichetaFigurii = etichetaFigurii;
    }

    public String getEtichetaPunctului() {
        return etichetaPunctului;
    }

    public void setEtichetaPunctului(String etichetaPunctului) {
        this.etichetaPunctului = etichetaPunctului;
    }

    public double getOx() {
        return Ox;
    }

    public void setOx(double ox) {
        Ox = ox;
    }

    public double getOy() {
        return Oy;
    }

    public void setOy(double oy) {
        Oy = oy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punct punct = (Punct) o;
        return Objects.equals(etichetaPunctului, punct.etichetaPunctului);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(etichetaPunctului);
    }

    @Override
    public String toString() {
        return "Punct{" +
                "etichetaFigurii='" + etichetaFigurii + '\'' +
                ", etichetaPunctului='" + etichetaPunctului + '\'' +
                ", Ox=" + Ox +
                ", Oy=" + Oy +
                '}';
    }

    @Override
    public int compareTo(Punct o) {
        return Double.compare(distanta(), o.distanta());
    }
}