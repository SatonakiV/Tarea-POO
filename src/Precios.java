public enum Precios {

    CocaCola(1000),
    Fanta(1000),
    Sprite(1000),
    Pepsi(1000),
    Kem(1000),
    Snickers(800),
    Super8(300),
    Kitkat(800),
    Chicle(200),
    Chocman(600);

    private final int precio;

    Precios(int precio) {
        this.precio = precio;

    }
    public int getPrecio() {
        return precio;
    }
}
