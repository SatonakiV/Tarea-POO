abstract class Dulce extends Producto {
    public abstract String Consumir();
}

class Snickers  extends Dulce {

    @Override
    public String Consumir() {
        return "Snickers";
    }
}

class Super8 extends Dulce {

    @Override
    public String Consumir() {
        return "Super8";
    }
}

class Kitkat extends Dulce {

    @Override
    public String Consumir() {
        return "Kitkat";
    }
}

class Chicle extends Dulce {

    @Override
    public String Consumir() {
        return "Chicle";
    }
}

class Chocman extends Dulce {

    @Override
    public String Consumir() {
        return "Chocman";
    }
}

