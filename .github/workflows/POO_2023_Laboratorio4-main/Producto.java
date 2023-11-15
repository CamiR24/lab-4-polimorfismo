public abstract class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

/*public class Libro extends Producto {
    public Libro(String nombre, double precio) {
        super(nombre, precio);
    }
}

public class Revista extends Producto {
    public Revista(String nombre, double precio) {
        super(nombre, precio);
    }
}*/
