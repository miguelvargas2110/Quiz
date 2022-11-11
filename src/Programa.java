import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Programa implements Serializable {
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Programa(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Programa() {
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void inicializar() {

        Usuario usuario = new Usuario();

        usuario.setCorreo("miguela.vargasn@uqvirtual.edu.co");
        usuario.setContrasena("Zeus2110");

        getListaUsuarios().add(usuario);

        usuario = new Usuario();

        usuario.setCorreo("juans.ortizr@uqvirtual.edu.co");
        usuario.setContrasena("Jm14042327");

        getListaUsuarios().add(usuario);

        usuario = new Usuario();

        usuario.setCorreo("juansebastianortiz8@gmail.com");
        usuario.setContrasena("Jm14042327");

        getListaUsuarios().add(usuario);

    }
}
