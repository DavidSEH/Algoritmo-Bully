package AlgoritmoBully;

import java.util.ArrayList;

public class Procesos {
    private final ArrayList<Nodo> nodos;

    public Procesos(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public void listarNodos() {
        nodos.forEach(x -> System.out.println(x.datos()));
    }

    public Nodo elegirCoordinador() {
        ArrayList<Nodo> nodoCopia = (ArrayList<Nodo>) nodos.clone(); //Hago una copia de la lista para no alterar la lista principal
        nodoCopia.removeIf(nodo -> !nodo.isActive()); //Eliminamos los inactivos
        if (!nodoCopia.isEmpty()) { //Si la lista_copia no esta vacia
            Nodo nodoAleatorio; //Creamos un nodo aleatorio
            while (true) {
                //System.out.println("Tamanio de la copia: "+nodoCopia.size());
                int numAleatorio = (int) Math.floor(Math.random() * ((nodoCopia.size() - 1) - 0 + 0) + 0); //Esto solo para que el que comienze la Eleccion sea alguien aleatorio
                //System.out.println("Numero aleatorio: "+ numAleatorio);
                nodoAleatorio = nodoCopia.get(numAleatorio); //Encontramos el nodo aleatorio
                mensajes(2, nodoAleatorio); //Aqui convoca un mensaje "Eleccion" para elegir a un coordinador
                Nodo mayor = nodoMayor(nodoCopia); //Nodo de apoyo
                ArrayList<Nodo> menores = new ArrayList<>(); //Lista donde se almacenaran los nodos menores
                for (Nodo no : nodoCopia) {
                    if (no.getId() > nodoAleatorio.getId()) { // Aqui los nodos mayores al aleatorio responderan OK
                        mensajes(3, no);
                        mayor = no; //se actualiza el mayor
                    }else if(no.getId() == nodoAleatorio.getId()){
                    }
                    else {
                        menores.add(no); //se agregaran los que sean los valores menores
                    }
                }
                if (!menores.isEmpty()) { //Aqui se elimian los valores menores
                    for (Nodo n : menores) {
                        nodoCopia.remove(n);
                    }
                }
                if (mayor.getId() > nodoAleatorio.getId()) { //Si el nodo aleatorio no fue el mayor de los nodos, entonces se elimina
                    nodoCopia.remove(nodoAleatorio);
                } else {
                    nodoAleatorio.setCoordinador(true); // Sino quiere decir que es el mayor y por lo tanto es el Coordinador
                    mensajes(4, nodoAleatorio);
                    break;
                }
                /*Nodo max = nodoCopia.stream().max(Comparator.comparing(Nodo::getId)).get(); //Obtenemos el nodo con mayor Id
                max.setCoordinador(true);
                return max;*/
            }
            return nodoAleatorio;
        } else {
            System.out.println("La lista esta vacia o todos los nodos estan inactivos");
            return null;
        }
    }

    private Nodo nodoMayor(ArrayList<Nodo> nodoCopia) { //Esto solo para comparar y que nos aseguremos de tener siempre el mayor
        Nodo mayor = null;
        int i = 0;
        for (Nodo no: nodoCopia) {
            if (no.getId() > i){
                i = no.getId();
                mayor = no;
            }
        }
        return mayor;
    }

    public int encontrarNodo(Nodo nodo) {
        if (!nodos.isEmpty()) {
            return nodos.indexOf(nodo);
        } else {
            System.out.println("La lista esta vacia");
            return -1;
        }
    }

    public void mensajes(int opcion, Nodo nodo) {
        switch (opcion) {
            case 1 -> nodos.forEach(x -> {
                if (x.isActive()) {
                    System.out.println("El nodo con Id: " + x.getId() + " esta enviando mensajes");
                }else{
                    System.out.println("El nodo con Id: " + x.getId() + " no se encuentra activo");
                }
            });
            case 2 -> System.out.println("\nNodo " + nodo.getId() + " : Eleccion\n");
            case 3 -> System.out.println("Nodo " + nodo.getId() + " - OK: " + nodo.isActive());
            case 4 -> System.out.println("Nodo " + nodo.getId() + " - Coordinador: " + nodo.isCoordinador());
        }
    }

    public void iniciarSimulacion() {
        Nodo nodoCoordinador = elegirCoordinador();
        int nodoEncontrado = encontrarNodo(nodoCoordinador);
        nodos.set(nodoEncontrado, nodoCoordinador);
        nodos.forEach(n -> System.out.println(n.datos())); //Solo para verificar los datos
        System.out.println("");
        mensajes(1, null);

    }

    public void desactivarCoordinador() { //un lambda para encontrar al Coordinador y desactivarlo
        nodos.forEach(n -> {
            if (n.isCoordinador()) {
                n.setActive(false);
                n.setCoordinador(false);
            }
        });
    }
}
