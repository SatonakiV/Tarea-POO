public abstract class Moneda {

    public Moneda() {
    }

    public abstract int getValor();

    public String getSerie() {
        return this.toString();
    }
}