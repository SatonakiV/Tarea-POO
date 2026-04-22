abstract class Bebida extends Producto {
}

class Cocacola extends Bebida {

    @Override
    public String Consumir() {
        return "Cocacola";
    }
}

class Sprite extends Bebida {

    @Override
    public String Consumir() {
        return "Sprite";
    }
}

class Fanta extends Bebida {

    @Override
    public String Consumir() {
        return "Fanta";
    }
}

class Pepsi extends Bebida {

    @Override
    public String Consumir() {
        return "Pepsi";
    }
}

class Kem extends Bebida {

    @Override
    public String Consumir() {
        return "Kem";
    }
}
