import java.io.Serializable;
import java.util.Objects;

public class Proiect implements Serializable {
    private long cod;
    private String acronim;
    private int anulLansarii;
    private String departament;
    private double buget;
    private int durata;
    private String director;
    private double cheltuieli;

    public Proiect(long cod, String acronim, int anulLansarii, String departament, double buget, int durata, String director, double cheltuieli) {
        this.cod = cod;
        this.acronim = acronim;
        this.anulLansarii = anulLansarii;
        this.departament = departament;
        this.buget = buget;
        this.durata = durata;
        this.director = director;
        this.cheltuieli = cheltuieli;
    }

    public Proiect() {
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getAcronim() {
        return acronim;
    }

    public void setAcronim(String acronim) {
        this.acronim = acronim;
    }

    public int getAnulLansarii() {
        return anulLansarii;
    }

    public void setAnulLansarii(int anulLansarii) {
        this.anulLansarii = anulLansarii;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public double getBuget() {
        return buget;
    }

    public void setBuget(double buget) {
        this.buget = buget;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getCheltuieli() {
        return cheltuieli;
    }

    public void setCheltuieli(double cheltuieli) {
        this.cheltuieli = cheltuieli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proiect proiect = (Proiect) o;
        return cod == proiect.cod && anulLansarii == proiect.anulLansarii && Double.compare(buget, proiect.buget) == 0 && durata == proiect.durata && Double.compare(cheltuieli, proiect.cheltuieli) == 0 && Objects.equals(acronim, proiect.acronim) && Objects.equals(departament, proiect.departament) && Objects.equals(director, proiect.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod, acronim, anulLansarii, departament, buget, durata, director, cheltuieli);
    }

    @Override
    public String toString() {
        return cod +
                "," + acronim +
                "," + anulLansarii +
                "," + departament +
                "," + buget +
                "," + durata +
                "," + director +
                "," + cheltuieli;
    }
}
