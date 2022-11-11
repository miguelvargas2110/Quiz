import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServidor {


    String host = "localhost";
    int puerto = 8081;
    ServerSocket server;

    Socket socketComunicacion;

    ObjectOutputStream flujoSalidaObjeto;

    String mensajeCliente;

    public AppServidor() {
        // TODO Auto-generated constructor stub
    }

    public void iniciarServidor() {


        try {
            // Se crea un socket servidor atendiendo a un determinado puerto.
            server = new ServerSocket(puerto);

            System.out.println("Esperando cliente");
            socketComunicacion = server.accept();

            //Se envian datos al cliente

            flujoSalidaObjeto = new ObjectOutputStream(socketComunicacion.getOutputStream());


            //Envio de objeto universidad
            enviarObjeto();

            flujoSalidaObjeto.close();

            // Se cierra el socket con el cliente.
            // La llamada anterior a setSoLinger() har�
            // que estos cierres esperen a que el cliente retire los datos.
            socketComunicacion.close();

            // Se cierra el socket encargado de aceptar clientes. Ya no
            // queremos m�s.
//            server.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void enviarObjeto() throws IOException {

        Programa programa = new Programa();
        programa.inicializar();
        System.out.println("Se envio el programa");
        flujoSalidaObjeto.writeObject(programa);
    }
}
