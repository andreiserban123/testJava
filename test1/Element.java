import java.io.Serializable;
import java.util.Objects;

public class Element implements Comparable<Element>, Serializable {
    private int line;
    private int coloana;
    private double valoare;

    public Element() {
    }

    public Element(int line, int coloana, double element) {
        this.line = line;
        this.coloana = coloana;
        this.valoare = element;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColoana() {
        return coloana;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element1 = (Element) o;
        return line == element1.line && coloana == element1.coloana && Double.compare(valoare, element1.valoare) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, coloana, valoare);
    }

    @Override
    public int compareTo(Element o) {
        return Double.compare(valoare, o.valoare);
    }

    @Override
    public String toString() {
        return "Element{" +
                "line=" + line +
                ", coloana=" + coloana +
                ", valoare=" + valoare +
                '}';
    }
}
