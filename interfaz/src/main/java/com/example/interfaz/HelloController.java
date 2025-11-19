package com.example.interfaz;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML
    //Para los nombres de los coches
    private TextField nombreCoche1,nombreCoche2,nombreCoche3,nombreCoche4,nombreCoche5;
    private ArrayList<TextField> nombreCoches = new ArrayList<>();
    @FXML
    //Para cada posición que es cada 100 metros porque hay 8 textfield y la meta son 800 m
    private TextField pista01,pista02,pista03,pista04,pista05, pista06,pista07,pista08;
    @FXML
    private TextField pista11,pista12,pista13,pista14,pista15, pista16,pista17,pista18;
    @FXML
    private TextField pista21,pista22,pista23,pista24,pista25, pista26,pista27,pista28;
    @FXML
    private TextField pista31,pista32,pista33,pista34,pista35, pista36,pista37,pista38;
    @FXML
    private TextField pista41,pista42,pista43,pista44,pista45, pista46,pista47,pista48;

    private ArrayList<Coche> coches = new ArrayList<>();
    //lista de cada fila con sus pistas
    private ArrayList<TextField[]> pistaCoches = new ArrayList<>();


    @FXML
    public void initialize(){
        //añadimos a la lista de nombre de coches los textviews de los nombres para que luego le creamos uno
        if (nombreCoche1 != null) {
            nombreCoches.add(nombreCoche1);
            nombreCoche1.setEditable(false);
        }

        if (nombreCoche2 != null){
            nombreCoches.add(nombreCoche2);
            nombreCoche2.setEditable(false);
        }
        if (nombreCoche3 != null) {
            nombreCoches.add(nombreCoche3);
            nombreCoche3.setEditable(false);
        }
        if (nombreCoche4 != null){
            nombreCoches.add(nombreCoche4);
            nombreCoche4.setEditable(false);
        }
        if (nombreCoche5 != null){
            nombreCoches.add(nombreCoche5);
            nombreCoche5.setEditable(false);
        }

        //nombrar a los coches
        for(int i = 0; i < nombreCoches.size(); i++){
            nombreCoches.get(i).setText("Coche " + (i+1));
        }

        //Creamos las filas con cada huevo de pista para luego añadir los coches
        crearPista();

    }

    public void crearPista(){
        TextField[] fila1 ={pista01,pista02,pista03,pista04,pista05, pista06,pista07,pista08};
        TextField[] fila2 ={pista11,pista12,pista13,pista14,pista15, pista16,pista17,pista18};
        TextField[] fila3 ={pista21,pista22,pista23,pista24,pista25, pista26,pista27,pista28};
        TextField[] fila4 ={pista31,pista32,pista33,pista34,pista35, pista36,pista37,pista38};
        TextField[] fila5 ={pista41,pista42,pista43,pista44,pista45, pista46,pista47,pista48};

        pistaCoches.add(fila1);
        pistaCoches.add(fila2);
        pistaCoches.add(fila3);
        pistaCoches.add(fila4);
        pistaCoches.add(fila5);
    }

    @FXML
    public void iniciar(){
        //limpiamos antes de iniciar
        Coche.reiniciarCarrera();
        coches.clear();
        limpiarpistas();

        //Creamos un nuevo coche
        for(int i = 0; i < nombreCoches.size(); i++){
            String nombre = nombreCoches.get(i).getText();
            if(!nombre.isEmpty()){
                coches.add(new Coche(nombre,(30+i),i,this));

                //le pasamos los datos para ver como van y poder verlo luego visualmente
                posicionCoche(i,0,nombre,false,0);
            }
        }
        for(Coche coche: coches){
            coche.start();

        }
    }

    public void limpiarpistas(){
        for(TextField[] fila: pistaCoches){
            for(TextField campo : fila){
                if(campo !=null){
                    campo.setText("");
                }
            }
        }
    }

    public void posicionCoche(int fila,int posicion,String nombre,boolean llegada,int posicionllegada){
        Platform.runLater(()->{
            if(fila < pistaCoches.size() && posicion < 8){
                if(posicion > 0) {
                    //Y en la posicion anterior a la que estaba la borra
                    int posicionAnterior = posicion - 1;
                    pistaCoches.get(fila)[posicionAnterior].setText("");
                }
                if(llegada) {
                    //si llegas a la meta te enseña la posición
                    pistaCoches.get(fila)[7].setText(ordenLlegada(posicionllegada));
                }
                else {
                    //Si estás en la posición recorriendo esos metros te enseña el coche
                    pistaCoches.get(fila)[posicion].setText("🚗");
                }
            }
        });
    }

    public String ordenLlegada(int posicion){
        switch (posicion){
            case 1: return "1º Puesto";
            case 2: return "2º Puesto";
            case 3: return "3º Puesto";
            case 4: return "4º Puesto";
            case 5: return "5º Puesto";
            default: return "";
        }
    }

}
