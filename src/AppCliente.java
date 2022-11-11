import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//import javax.xml.ws.handler.MessageContext.Scope;

public class AppCliente {


    // puerto y host

    int puerto;
    String host;
    // socket cliente
    Socket socketComunicacion;
    // flujos de entrada y salida
    DataOutputStream flujoSalida;
    DataInputStream flujoEntrada;
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

//            flujoEntrada = new DataInputStream(socketComunicacion.getInputStream());
//            flujoSalida = new DataOutputStream(socketComunicacion.getOutputStream());
            flujoEntradaObject = new ObjectInputStream(socketComunicacion.getInputStream());
            flujoSalidaObject = new ObjectOutputStream(socketComunicacion.getOutputStream());

//            enviarDatos();

//            recibirDatosPrimitivos();

            recibirObjeto();

//            flujoEntrada.close();
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


    private void enviarDatos() throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escriba su correo:");

        String correo = sc.nextLine();

        System.out.println("Escriba su contraseña:");

        String contraseña = sc.nextLine();

        Usuario usuario = new Usuario(correo, contraseña);

        flujoSalidaObject.writeObject(usuario);
    }


    private void recibirDatosPrimitivos() throws IOException {

//		System.out.println("Datos recibidos del servidor: "+flujoEntrada.readInt());
        System.out.println("Datos recibidos del servidor: " + flujoEntrada.readUTF());
    }


    private void crearConexion() throws IOException {
        // TODO Auto-generated method stub
        socketComunicacion = new Socket(host, puerto);

    }


    private void recibirObjeto() throws IOException, ClassNotFoundException {

        this.programa = (Programa) flujoEntradaObject.readObject();

//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Escriba su correo: ");
//        String correo = sc.nextLine();
//        boolean flag = false;
//
//        System.out.println("Escriba su contraseña: ");
//        String contraseña = sc.nextLine();
//
//        for (Usuario usuario : programa.getListaUsuarios()) {
//            if(usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contraseña)){
//                System.out.println("Inicio Sesion");
//                flag = true;
//                break;
//            }
//        }
//        if(!flag){
//            System.out.println("Correo y/o contraseña no validos");
//        }

    }

    public void validarDatos() {

    }

    private void iniciarHiloClienteServidor() {

        HiloClienteServidor hiloClienteServidor = new HiloClienteServidor();

        hiloClienteServidor.inicializar(flujoEntradaObject, flujoSalidaObject, this, programa);

        hiloClienteServidor.start();
    }


}

