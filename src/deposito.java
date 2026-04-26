import java.util.ArrayList;

public class Deposito<T> {
    private ArrayList<T> lista;

    public Deposito() {
        this.lista = new ArrayList<T>();
    }

    public void addElemento(T t) {
        this.lista.add(t);
    }

    public T getElemento() {
        if (lista.isEmpty()) {
            return null;
        }
        return lista.remove(0);
    }
}