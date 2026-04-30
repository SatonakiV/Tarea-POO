import java.util.Scanner;

public class MainInteractivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Creamos el expendedor con 10 unidades de cada producto inicialmente
        Expendedor exp = new Expendedor(10);
        boolean continuar = true;

        System.out.println("=== BIENVENIDO A LA MÁQUINA EXPENDEDORA ===");

        while (continuar) {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. Coca Cola");
            System.out.println("2. Sprite");
            System.out.println("3. Fanta");
            System.out.println("4. Snickers");
            System.out.println("5. Super 8");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcionProducto = scanner.nextInt();

            if (opcionProducto == 0) {
                continuar = false;
                System.out.println("¡Gracias por su compra!");
                continue;
            }
            
            Precios tipoSeleccionado = null;
            switch (opcionProducto) {
                case 1: tipoSeleccionado = Precios.CocaCola; break;
                case 2: tipoSeleccionado = Precios.Sprite; break;
                case 3: tipoSeleccionado = Precios.Fanta; break;
                case 4: tipoSeleccionado = Precios.Snickers; break;
                case 5: tipoSeleccionado = Precios.Super8; break;
                default: System.out.println("Opción inválida."); continue;
            }

            System.out.println("\n--- Pago ---");
            System.out.println("1. Moneda de $100");
            System.out.println("2. Moneda de $500");
            System.out.println("3. Moneda de $1000");
            System.out.println("4. Ingresar sin moneda (null)");
            System.out.print("Seleccione con qué va a pagar: ");

            int opcionPago = scanner.nextInt();
            Moneda monedaPago = null;

            switch (opcionPago) {
                case 1: monedaPago = new Moneda100(); break;
                case 2: monedaPago = new Moneda500(); break;
                case 3: monedaPago = new Moneda1000(); break;
                case 4: monedaPago = null; break;
                default: System.out.println("Pago inválido."); continue;
            }

            // Bloque donde el Main atrapa las excepciones lanzadas por el Comprador/Expendedor
            try {
                Comprador c = new Comprador(monedaPago, tipoSeleccionado, exp);
                System.out.println("\n✅ ¡COMPRA EXITOSA!");
                System.out.println("Consumiste: " + c.queConsumiste());
                System.out.println("Tu vuelto total es de: $" + c.cuantoVuelto());

            } catch (PagoIncorrectoException | PagoInsuficienteException | NoHayProductoException e) {
                System.out.println("\n❌ ERROR EN LA COMPRA: " + e.getMessage());
                System.out.println("Recoja su moneda de rechazo en el depósito de vuelto.");

            } catch (Exception e) {
                System.out.println("\n❌ ERROR INESPERADO: " + e.getMessage());
            }
        }
        scanner.close();
    }
}