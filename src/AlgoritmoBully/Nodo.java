package AlgoritmoBully;

public class Nodo {
    private int id; //El identificador con el que se evaluara el mayor
    private boolean active; //Si esta activo o no
    private boolean coordinador; //Si es o no un Coordinador

    public Nodo(int id, boolean active, boolean coordinador) {
        this.id = id;
        this.active = active;
        this.coordinador = coordinador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCoordinador() {
        return coordinador;
    }

    public void setCoordinador(boolean coordinador) {
        this.coordinador = coordinador;
    }

    public String datos() {
        return "\nId: " + getId() +
                "   |   Activo: " + isActive() +
                "   |   Coordinador: " + isCoordinador();
    }
}
