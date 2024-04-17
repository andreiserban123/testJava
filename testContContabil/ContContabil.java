import java.io.Serializable;

public class ContContabil implements Serializable {
    private int simbolCont;
    private String denumire;
    private String functiuneaContabila;
    private double soldInitial;
    private double rulajDebitor;
    private double rulajCreditor;

    public ContContabil() {
    }

    public ContContabil(int simbolCont, String denumire, String functiuneaContabila, double soldInitial, double rulajDebitor, double rulajCreditor) {
        this.simbolCont = simbolCont;
        this.denumire = denumire;
        this.functiuneaContabila = functiuneaContabila;
        this.soldInitial = soldInitial;
        this.rulajDebitor = rulajDebitor;
        this.rulajCreditor = rulajCreditor;
    }

    @Override
    public String toString() {
        return simbolCont +
                "," + denumire +
                "," + functiuneaContabila +
                "," + soldInitial +
                "," + rulajDebitor +
                "," + rulajCreditor;
    }

    public int getSimbolCont() {
        return simbolCont;
    }

    public void setSimbolCont(int simbolCont) {
        this.simbolCont = simbolCont;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getFunctiuneaContabila() {
        return functiuneaContabila;
    }

    public void setFunctiuneaContabila(String functiuneaContabila) {
        this.functiuneaContabila = functiuneaContabila;
    }

    public double getSoldInitial() {
        return soldInitial;
    }

    public void setSoldInitial(double soldInitial) {
        this.soldInitial = soldInitial;
    }

    public double getRulajDebitor() {
        return rulajDebitor;
    }

    public void setRulajDebitor(double rulajDebitor) {
        this.rulajDebitor = rulajDebitor;
    }

    public double getRulajCreditor() {
        return rulajCreditor;
    }

    public void setRulajCreditor(double rulajCreditor) {
        this.rulajCreditor = rulajCreditor;
    }
}
