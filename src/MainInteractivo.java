import java.util.Scanner;

/**
 * Interfaz de usuario por consola para el sistema de la Máquina Expendedora.
 * Permite a un usuario realizar múltiples compras a través de un menú interactivo,
 * seleccionando productos y métodos de pago de forma dinámica.
 */
public class MainInteractivo {

    /**
     * Método principal que inicia el bucle del menú interactivo.
     * Despliega las opciones de productos y pagos, lee la entrada del usuario mediante Scanner,
     * intenta ejecutar la transacción creando un Comprador y gestiona las excepciones
     * en caso de fallos (pago insuficiente, falta de stock, etc.) para mantener el programa en ejecución.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Creamos el expendedor con 10 unidades de cada producto inicialmente
        Expendedor exp = new Expendedor(10);
        boolean continuar = true;

        System.out.println("=== BIENVENIDO A LA MÁQUINA EXPENDEDORA ===");

        while (continuar) {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. CocaCola ($1500)");
            System.out.println("2. Fanta ($1200)");
            System.out.println("3. Sprite ($1200)");
            System.out.println("4. Pepsi ($1500)");
            System.out.println("5. Kem ($1000)");
            System.out.println("6. Snickers ($800)");
            System.out.println("7. Super8 ($300)");
            System.out.println("8. Kitkat ($800)");
            System.out.println("9. Chicle ($200)");
            System.out.println("10. Chocman ($600)");
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
                case 2: tipoSeleccionado = Precios.Fanta; break;
                case 3: tipoSeleccionado = Precios.Sprite; break;
                case 4: tipoSeleccionado = Precios.Pepsi; break;
                case 5: tipoSeleccionado = Precios.Kem; break;
                case 6: tipoSeleccionado = Precios.Snickers; break;
                case 7: tipoSeleccionado = Precios.Super8; break;
                case 8: tipoSeleccionado = Precios.Kitkat; break;
                case 9: tipoSeleccionado = Precios.Chicle; break;
                case 10: tipoSeleccionado = Precios.Chocman; break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;
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
                default:
                    System.out.println("Pago inválido. Intente nuevamente.");
                    continue;
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