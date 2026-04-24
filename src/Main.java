import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        // 1. Crear el expendedor con 1 unidad de cada producto para probar stock
        Expendedor exp = new Expendedor(1);

        System.out.println("=== INICIO DE TEST DE COMPRA Y EXCEPCIONES ===");

        // CASO 1: Compra exitosa (CocaCola con moneda de 1000)
        System.out.println("\n[Test 1]: Compra exitosa");
        try {
            Moneda m = new Moneda1000();
            Comprador c = new Comprador(m, Precios.CocaCola, exp);
            System.out.println("Respuesta del comprador: " + c.queConsumiste());
            System.out.println("Vuelto total: $" + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        // CASO 2: Pago insuficiente (Intentar comprar Sprite de 1000 con moneda de 100)
        System.out.println("\n[Test 2]: Forzando PagoInsuficienteException");
        try {
            Moneda m = new Moneda100();
            Comprador c = new Comprador(m, Precios.Sprite, exp);
        } catch (PagoInsuficienteException e) {
            System.out.println("Éxito: Se capturó PagoInsuficienteException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error distinto al esperado: " + e.getClass().getName());
        }

        // CASO 3: No hay producto (La CocaCola se agotó en el Test 1)
        System.out.println("\n[Test 3]: Forzando NoHayProductoException");
        try {
            Moneda m = new Moneda1000();
            Comprador c = new Comprador(m, Precios.CocaCola, exp);
        } catch (NoHayProductoException e) {
            System.out.println("Éxito: Se capturó NoHayProductoException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // CASO 4: Pago incorrecto (Moneda null)
        System.out.println("\n[Test 4]: Forzando PagoIncorrectoException");
        try {
            Comprador c = new Comprador(null, Precios.Snickers, exp);
        } catch (PagoIncorrectoException e) {
            System.out.println("Éxito: Se capturó PagoIncorrectoException -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- PRUEBA DE ORDENAMIENTO ---
        System.out.println("\n=== PRUEBA DE ORDENAMIENTO DE MONEDAS ===");
        ArrayList<Moneda> bolsaDeMonedas = new ArrayList<>();
        bolsaDeMonedas.add(new Moneda1000());
        bolsaDeMonedas.add(new Moneda100());
        bolsaDeMonedas.add(new Moneda500());

        System.out.println("Antes de ordenar:");
        for (Moneda m : bolsaDeMonedas) System.out.println(m.toString());

        // *** Esto requiere que la clase Moneda implemente Comparable ***
        Collections.sort(bolsaDeMonedas);

        System.out.println("\nDespués de ordenar (menor a mayor):");
        for (Moneda m : bolsaDeMonedas) System.out.println(m.toString());
    }
}