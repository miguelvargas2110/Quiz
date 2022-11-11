import java.io.*;
import java.net.Socket;

public class AppCliente {


    // puerto y host

    int puerto;
    String host;
    // socket cliente
    Socket socketComunicacion;
    // flujos de entrada y salida
    ObjectInputStream flujoEntradaObject;
    ObjectOutputStream flujoSalidaObject;
    private Programa programa;


    public AppCliente(String host, int puerto) {
        this.puerto = puerto;
        this.host = host;
    }


    public void iniciarCliente() {


        try {
            crearConexion();

            flujoEntradaObject = new ObjectInputStream(socketComunicacion.getInputStream());
            flujoSalidaObject = new ObjectOutputStream(socketComunicacion.getOutputStream());

            recibirObjeto();

            flujoSalidaObject.close();
            socketComunicacion.close();

            iniciarHiloClienteServidor();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    private void crearConexion() throws IOException {
        // TODO Auto-generated method stub
        socketComunicacion = new Socket(host, puerto);

    }


    private void recibirObjeto() throws IOException, ClassNotFoundException {

        this.programa = (Programa) flujoEntradaObject.readObject();
    }

    private void iniciarHiloClienteServidor() {

        HiloClienteServidor hiloClienteServidor = new HiloClienteServidor();

        hiloClienteServidor.inicializar(flujoEntradaObject, flujoSalidaObject, this, programa);

        hiloClienteServidor.start();
    }
}

