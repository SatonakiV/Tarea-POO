/**
 * Representa a un cliente que interactúa con la máquina expendedora.
 * El comprador intenta realizar una compra ingresando una moneda y seleccionando un producto.
 * Se encarga de almacenar el sonido (o sabor) del producto consumido y calcular el vuelto recolectado.
 */
public class Comprador {
    private String sonido;
    private int vuelto;
    /**
     * Construye un nuevo Comprador y ejecuta la lógica de compra de forma inmediata.
     * Intenta comprar el producto en el expendedor; si tiene éxito, lo consume y
     * recolecta el vuelto moneda a moneda. Si la compra falla, captura la excepción
     * y guarda la moneda de rechazo como vuelto.
     *
     * @param m    La moneda que el comprador utiliza para pagar.
     * @param cual El tipo de producto que el comprador desea adquirir (del enum Precios).
     * @param exp  La máquina expendedora donde se realizará la transacción.
     */
    public Comprador(Moneda m, Precios tipo, Expendedor exp) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        this.vuelto = 0;
        this.sonido = null;


        Producto p = exp.comprarProducto(m, tipo);

        if (p != null) {
            this.sonido = p.Consumir();
        }

        Moneda v = exp.getVuelto();
        while (v != null) {
            this.vuelto += v.getValor();
            v = exp.getVuelto();
        }
    }
    /**
     * Obtiene la cantidad total de dinero que el comprador recibió de vuelta.
     *
     * @return El vuelto total recolectado en formato numérico (entero).
     */
    public int cuantoVuelto() {
        return this.vuelto;
    }
    /**
     * Obtiene el sonido, sabor o tipo del producto que el comprador logró consumir.
     *
     * @return Un String que representa lo que se consumió (por ejemplo, "cocacola").
     *         Si no se logró realizar la compra, retornará null o vacío.
     */
    // Retorna el sonido/sabor del producto consumido
    public String queConsumiste() {
        return this.sonido;
    }
}