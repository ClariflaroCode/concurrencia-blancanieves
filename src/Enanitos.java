public class Enanitos implements Runnable{
    public Casa casa;

    public Enanitos(Casa casa) {
        this.casa = casa;
    }

    @Override
    public void run() {



        while (Main.running) {
            if (casa.EnanitoAgregadoEsperandoComer(this)) {
                //si pude hacer esto, me agregué a esperar la comida.
                System.out.print("Me senté a comer");
                casa.BlancanievesDespertate();
                setSentado();
                System.out.print("Terminé de comer");
                casa.EliminarEnanitoDeListaDeEspera(this);
            } else {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public synchronized void setSentado()  {

        //Está sentado esperando que le sirvan la comida.
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void DespertateYaTeDieronLaComida(){
        this.notify();

    }

}
