import java.io.*;
import java.util.Scanner;

public class HiloClienteServidor extends Thread {


    AppCliente cliente;
    private ObjectOutputStream flujoSalida;
    private ObjectInputStream flujoEntrada;
    private Programa programa;


    public void inicializar(ObjectInputStream flujoEntrada, ObjectOutputStream flujoSalida,
                            AppCliente cliente, Programa programa) {

        this.flujoEntrada = flujoEntrada;
        this.flujoSalida = flujoSalida;
        this.cliente = cliente;
        this.programa = programa;
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escriba su correo: ");
        String correo = sc.nextLine();
        boolean flag = false;

        System.out.println("Escriba su contraseña: ");
        String contraseña = sc.nextLine();

        for (Usuario usuario : programa.getListaUsuarios()) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contraseña)) {
                System.out.println("Inicio Sesion");
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Correo y/o contraseña no validos");
        }
        System.out.println("Se ejecuto el hilo");


    }
}

