/**
 * Representa la máquina expendedora de productos.
 * Gestiona el almacenamiento interno de bebidas y dulces en sus respectivos depósitos,
 * así como el depósito para almacenar y entregar el vuelto de las transacciones.
 */
public class Expendedor {


    //Inicializa cada uno de los depositos de cada tipo de producto para evitar problemas
    private Deposito<Producto> depositoCoca;
    private Deposito<Producto> depositoSprite;
    private Deposito<Producto> depositoPepsi;
    private Deposito<Producto> depositoKem;
    private Deposito<Producto> depositoFanta;
    private Deposito<Producto> depositoSuper8;
    private Deposito<Producto> depositoKitkat;
    private Deposito<Producto> depositoSnickers;
    private Deposito<Producto> depositoChicle;
    private Deposito<Producto> depositoChocman;
    private Deposito<Moneda> depositoVuelto;

    /**
     * Constructor que inicializa todos los depósitos de la máquina expendedora
     * y los llena con una cantidad específica inicial de cada producto.
     *
     * @param cantidad La cantidad de unidades de cada producto con la que se rellenará la máquina.
     */
    public Expendedor(int cantidad) {
        this.depositoCoca = new Deposito<>();
        this.depositoSprite = new Deposito<>();
        this.depositoPepsi = new Deposito<>();
        this.depositoKem = new Deposito<>();
        this.depositoFanta = new Deposito<>();
        this.depositoSuper8 = new Deposito<>();
        this.depositoKitkat = new Deposito<>();
        this.depositoSnickers = new Deposito<>();
        this.depositoChicle = new Deposito<>();
        this.depositoChocman = new Deposito<>();
        this.depositoVuelto = new Deposito<>();


        //For para instanciar hasta x cantidad de acuerdo lo pedido
        for(int i = 0; i < cantidad; i++) {
            depositoCoca.addElemento(new Cocacola());
            depositoSprite.addElemento(new Sprite());
            depositoPepsi.addElemento(new Pepsi());
            depositoKem.addElemento(new Kem());
            depositoFanta.addElemento(new Fanta());
            depositoSnickers.addElemento(new Snickers());
            depositoSuper8.addElemento(new Super8());
            depositoKitkat.addElemento(new Kitkat());
            depositoChicle.addElemento(new Chicle());
            depositoChocman.addElemento(new Chocman());
        }
    }

    /**
     * Intenta realizar la compra de un producto en la máquina expendedora.
     * Verifica que la moneda sea válida, que el pago sea suficiente y que exista stock.
     * Si la compra es exitosa, calcula el vuelto en monedas de 100 y lo almacena en el depósito de vuelto.
     *
     * @param m            La moneda ingresada por el comprador para pagar.
     * @param tipoProducto El producto específico que se desea comprar (del enum Precios).
     * @return El objeto Producto que fue extraído exitosamente del depósito correspondiente.
     * @throws PagoIncorrectoException Si la moneda ingresada es nula.
     * @throws PagoInsuficienteException Si el valor de la moneda es menor al precio del producto solicitado.
     * @throws NoHayProductoException Si el depósito del producto solicitado está vacío o el producto no existe.
     */
    public Producto comprarProducto(Moneda m, Precios tipoProducto) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException{
        Producto p = null; //Varible para comprobar si el producto es valido luego


        //Verificamos si la moneda existe
        if(m == null){
            throw new PagoIncorrectoException("Moneda no puede ser nula");
        }

        //Verificamos que el valor de lo ingresado sea mayor al precio del producto
        if(m.getValor() < tipoProducto.getPrecio()){
            depositoVuelto.addElemento(m); //Guardamos la moneda si no es suficiente para luego devolverla
            throw new PagoInsuficienteException("Ingresar el pago suficiente");
        }

        //Switch para verificar que el producto este, y de serlo saca el objeto del deposito respectivo
        switch (tipoProducto){
            case CocaCola -> p = depositoCoca.getElemento();
            case Sprite -> p = depositoSprite.getElemento();
            case Pepsi -> p = depositoPepsi.getElemento();
            case Fanta -> p = depositoFanta.getElemento();
            case Kem -> p = depositoKem.getElemento();
            case Chicle -> p = depositoChicle.getElemento();
            case Chocman -> p = depositoChocman.getElemento();
            case Super8 -> p = depositoSuper8.getElemento();
            case Kitkat -> p = depositoKitkat.getElemento();
            case Snickers -> p = depositoSnickers.getElemento();
        }

        //Si el producto no existe o no hay stock guarda la moneda para devolverla y lanza exception
        if(p == null){
            depositoVuelto.addElemento(m);
            throw new NoHayProductoException("No existe este producto o no hay stock de el");
        }

        //Si todo funciona, entonces devolvemos el producto a la vez que el vuelto en monedas de 100 respectivamente
        else {
            int getVuelto = m.getValor() -  tipoProducto.getPrecio();
            for (int i = 0; i < getVuelto/100; i++) {
                depositoVuelto.addElemento(new Moneda100());
            }
        }

        return p;
    }


    /**
     * Extrae una moneda del depósito de vuelto de la máquina.
     * Este método debe llamarse repetidamente por el comprador para retirar todo su dinero acumulado.
     *
     * @return Una instancia de Moneda (ya sea una moneda de vuelto o la moneda original rechazada),
     *         o null si el depósito de vuelto ya se encuentra vacío.
     */
    public Moneda getVuelto() {
        return depositoVuelto.getElemento();
    }



}
