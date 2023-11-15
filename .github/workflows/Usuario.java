import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private Plan plan;
    private List<Producto> productosSeleccionados;

    public Usuario(String nombreUsuario, String contrasena, Plan plan) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.plan = plan;
        this.productosSeleccionados = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        if (puedeAgregarProducto(libro)) {
            productosSeleccionados.add(libro);
            System.out.println("Libro agregado correctamente.");
        } else {
            System.out.println("No puedes agregar más libros.");
        }
    }

    public void agregarRevista(Revista revista) {
        productosSeleccionados.add(revista);
        System.out.println("Revista agregada correctamente.");
    }

    public void vaciarListaSeleccion() {
        productosSeleccionados.clear();
        System.out.println("Lista de selección vaciada.");
    }

    private boolean puedeAgregarProducto(Producto producto) {
        if (producto instanceof Libro && plan == Plan.PREMIUM && productosSeleccionados.size() < 5) {
            return true;
        } else if (producto instanceof Libro && plan == Plan.BASE && productosSeleccionados.size() < 3) {
            return true;
        }
        return false;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Plan getPlan() {
        return plan;
    }

    public List<Producto> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setProductosSeleccionados(List<Producto> productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }
}
