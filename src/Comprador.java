public class Comprador {
    private String sonido;
    private int vuelto;

    public Comprador(Moneda m, Precios tipo, Expendedor exp) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        this.vuelto = 0;
        this.sonido = null;

        // 1. Intentamos comprar.
        // Si hay un error (ej. falta plata), el Expendedor lanzará la excepción,
        // el código se cortará aquí mismo y el error "subirá" al Main.
        Producto p = exp.comprarProducto(m, tipo);

        // 2. Si la compra fue exitosa (no hubo excepciones), consumimos el producto.
        if (p != null) {
            this.sonido = p.Consumir();
        }

        // 3. Recoger todo el vuelto del depósito, moneda por moneda, hasta que el getVuelto() nos dé null.
        Moneda v = exp.getVuelto();
        while (v != null) {
            this.vuelto += v.getValor();
            v = exp.getVuelto();
        }
    }

    // Retorna la cantidad total de dinero que recuperó
    public int cuantoVuelto() {
        return this.vuelto;
    }

    // Retorna el sonido/sabor del producto consumido
    public String queConsumiste() {
        return this.sonido;
    }
}