public enum Precios {

    CocaCola(1500),
    Fanta(1200),
    Sprite(1200),
    Pepsi(1500),
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
