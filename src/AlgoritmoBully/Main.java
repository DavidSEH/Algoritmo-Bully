package AlgoritmoBully;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos nodos tendra la simulacion?: ");
        int numNodos = sc.nextInt();

        ArrayList<Nodo> nodos = new ArrayList<>();
        for (int i = 0; i < numNodos; i++) {
            int numRandom = (int) (Math.random() * (200 - 10 + 1) + 10);
            nodos.add(new Nodo(numRandom, true, false));
        }
        Procesos procesos = new Procesos(nodos);
        System.out.println("\n********** SIMULACION DEL ALGORITMO BULLY **********");
        while (true) {
            System.out.println("\n1) Listar los nodos");
            System.out.println("2) Iniciar simulacion");
            System.out.println("3) Desactivar Coordinador");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> procesos.listarNodos();
                case 2 -> procesos.iniciarSimulacion();
                case 3 -> {
                    procesos.desactivarCoordinador();
                    procesos.iniciarSimulacion();
                }
            }
            sc.nextLine();
            System.out.println("\nDesea continuar? S/N: ");
            String salir = sc.nextLine();
            if (salir.equalsIgnoreCase("n")) {
                System.exit(0);
            }
        }

    }
}
