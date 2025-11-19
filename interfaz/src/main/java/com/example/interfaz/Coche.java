package com.example.interfaz;

public class Coche extends Thread {
    private String nombre;
    private int distanciaRecorrida;
    private int velocidadMaxima;
    private int meta = 800;
    private int fila;
    private static int llegada = 0;
    private HelloController controller;
    private int posicionllegada = 0;

    public Coche(String nombre, int velocidadMaxima,int fila,HelloController controller) {
        this.nombre = nombre;
        this.distanciaRecorrida = 0;
        this.velocidadMaxima = velocidadMaxima;
        this.fila = fila;
        this.controller = controller;
    }

    public static void reiniciarCarrera() {
        llegada = 0;
    }

    @Override
    public void run() {
        //Arrancan los coches
        System.out.println(this.nombre + " ha arrancado!");

        //Mientras la distancia que recorra los coches sea menos que la meta
        while(this.distanciaRecorrida < this.meta) {
            try {
                int avance = (int)(Math.random() * (velocidadMaxima + 1));
                distanciaRecorrida += avance;

                //Si se pasa la distancia es la misma que la máxima
                if(distanciaRecorrida > meta){
                    distanciaRecorrida = meta;
                }

                int posicion = distanciaRecorrida/100;
                if(posicion>7){
                    posicion = 7;
                }

                boolean hallegado = (distanciaRecorrida >=meta);
                controller.posicionCoche(fila,posicion,nombre,hallegado,posicionllegada);


                System.out.println(this.nombre + ".".repeat(distanciaRecorrida) + " 🚓 " + distanciaRecorrida + "/" + meta + " m");

                //Tiempo de descanso
                Thread.sleep((int)(Math.random()*200));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Si la distancia es igual que la distancia de la meta, hha acabado la carrera
            if (this.distanciaRecorrida >= this.meta) {
                synchronized (Coche.class) {
                    if(posicionllegada ==0){
                        llegada++;
                        posicionllegada = llegada;

                        System.out.println(this.nombre + " ha acabado la carrera! de " + llegada);
                        controller.posicionCoche(fila, 7, nombre, true, posicionllegada);
                    }
                }
                break;
            }

        }

    }
}
