import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Proiect implements Comparator<Proiect>, Serializable {
    private int codProiect;
    private String acronim;
    private String sefProiect;
    private String departament;
    private double buget;
    private int nrMembrii;


    public Proiect() {
    }

    public Proiect(int codProiect, String acronim, String sefProiect, String departament, double buget, int nrMembrii) {
        this.codProiect = codProiect;
        this.acronim = acronim;
        this.sefProiect = sefProiect;
        this.departament = departament;
        this.buget = buget;
        this.nrMembrii = nrMembrii;
    }

    public int getCodProiect() {
        return codProiect;
    }

    public void setCodProiect(int codProiect) {
        this.codProiect = codProiect;
    }

    public String getAcronim() {
        return acronim;
    }

    public void setAcronim(String acronim) {
        this.acronim = acronim;
    }

    public String getSefProiect() {
        return sefProiect;
    }

    public void setSefProiect(String sefProiect) {
        this.sefProiect = sefProiect;
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

    public int getNrMembrii() {
        return nrMembrii;
    }

    public void setNrMembrii(int nrMembrii) {
        this.nrMembrii = nrMembrii;
    }

    @Override
    public int compare(Proiect o1, Proiect o2) {
        return Double.compare(o1.buget, o2.buget);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proiect proiect = (Proiect) o;
        return codProiect == proiect.codProiect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codProiect);
    }

    @Override
    public String toString() {
        return "Proiect{" +
                "codProiect=" + codProiect +
                ", acronim='" + acronim + '\'' +
                ", sefProiect='" + sefProiect + '\'' +
                ", departament='" + departament + '\'' +
                ", buget=" + buget +
                ", nrMembrii=" + nrMembrii +
                '}';
    }
}
