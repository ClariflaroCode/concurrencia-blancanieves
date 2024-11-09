import java.util.Timer;

public class Main {
    public static volatile boolean running = true;
    public static void main(String[] args) {
        System.out.println("Programa de Blancanieves");
        //IMPORTANTE: el sleep no libera el monitor, el wait sí. Siempre que usamos wait y notify usamos synchorinize.
        Blancanieves blancanieves = new Blancanieves(null);
        Casa casa = new Casa(blancanieves);
        blancanieves.SetCasa(casa);
        Thread hiloBlancanieves = new Thread(blancanieves);
        hiloBlancanieves.start();

        for (int i = 0; i < 7; i++) {
            Thread hiloEnanito = new Thread(new Enanitos(casa));
            hiloEnanito.setName("Enanito"+i);
            hiloEnanito.start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;

        System.out.println("Fin de la ejecución del programa.");

    }
}