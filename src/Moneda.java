public abstract class Moneda implements Comparable<Moneda> {

    public Moneda() {
    }

    public abstract int getValor();

    public String getSerie() {
        return this.toString();
    }

    @Override
    public int compareTo(Moneda m) {
        return Integer.compare(this.getValor(), m.getValor());
    }
}