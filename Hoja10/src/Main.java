/**
 * @author Sofia Garcia, Julio García Salas
 * Folder: HDT10
 * Archivo: Main.java
 * Fecha: 22/05/2023
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al programa");
        //---------------
        Scanner sc = new Scanner(System.in);
        Vector<Vector<Object>> initialVector =  new Vector<Vector<Object>>();
        Vector<Vector<Object>> finalVector = new Vector<>();
        HashMap<String,Integer> kValue = new HashMap<>();
        Graph graph = new Graph();
        boolean flag = true;
        String regex = "\\d+";
        //-----Read and create variables---------
        Reader reader = new Reader(initialVector,kValue);
        //--------------------------------------
        finalVector = graph.makeTheFistVector(initialVector,kValue);
        graph.floydAlgorithm(finalVector,kValue);
        //--------
        while(flag){
            System.out.println("---------------------------");
            System.out.println("Vector de adyacencia: ");
            List<String> list = new ArrayList<>();
            for(int i=0;i<kValue.size();i++){
                list.add(SupportFunctions.getKeyfromValue(kValue, i));
            }
            System.out.println(list);
            for(int i=0;i<finalVector.size();i++){
                System.out.println(finalVector.get(i));
            }
            System.out.println("---------------------------");
            //-------------------OPC---------------------
            System.out.println("Ingrese la opcion que quiere");
            System.out.println("0) Salir\n1) Distancia mas corta entre dos ciudades\n2) Ciudad centro del grafo" +
                    "\n3) Modificar un grafo\n4) Indicar un clima entre ciudades");
            String opc = sc.nextLine();
            System.out.println("-----------------------------------------------");
            //--------------------------------------------
            if(opc.equals("0")){
                flag = false;
            } else if (opc.equals("1")) {
                //------------start City----------------------
                System.out.println("Ingrese de la ciudad de la que sale");
                for(int i=0;i<kValue.size();i++){
                    System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                }
                String sCity = sc.nextLine();
                //------------final City---------------------
                System.out.println("Ingrese a la ciudad a la que quiere llegar");
                for(int i=0;i<kValue.size();i++){
                    System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                }
                String fCity = sc.nextLine();
                //------------------------------------------
                if(sCity.matches(regex)){
                    if(fCity.matches(regex)){
                        int sCityN = Integer.parseInt(sCity);
                        int fCityN = Integer.parseInt(fCity);
                        //------------------------------------------------
                        if((sCityN >= 0 && sCityN<kValue.size()) && (fCityN >= 0 && fCityN<kValue.size())){
                            if(graph.getFloydVector().get(sCityN).get(fCityN).equals("NE")){
                                System.out.println("No hay manera de llegar a el destino");
                            }else{
                                System.out.println("-----------------------------------------------");
                                System.out.println("Valor de la ruta mas corta: "+graph.getFloydVector().get(sCityN).get(fCityN));
                                System.out.println("Recorrido de las ciudades");
                                System.out.println(SupportFunctions.getKeyfromValue(kValue,sCityN));
                                System.out.println(graph.arrivalRoutes.get(sCityN).get(fCityN));
                                System.out.println(SupportFunctions.getKeyfromValue(kValue,fCityN));
                            }
                        }
                        //------------------------------------------------------
                    }
                }
                //------------------------------------------
            }else if(opc.equals("2")){
                System.out.println("La ciudad centro es: "+graph.centerCity(kValue));
            }else if(opc.equals("3")){
                System.out.println("Que desea modificar?\n0) Hay interrumpcion entre dos ciudades" +
                        "\n1) Establecer conexion entre dos ciudades");
                opc = sc.nextLine();
                if(opc.equals("0") || opc.equals("1")){
                    //------------start City----------------------
                    System.out.println("Ingrese de la ciudad de la que sale");
                    for(int i=0;i<kValue.size();i++){
                        System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                    }
                    String sCity = sc.nextLine();
                    //------------final City---------------------
                    System.out.println("Ingrese a la ciudad a la que quiere llegar");
                    for(int i=0;i<kValue.size();i++){
                        System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                    }
                    String fCity = sc.nextLine();
                    //--------------------------------------------
                    if(sCity.matches(regex)){
                        if(fCity.matches(regex)){
                            int sCityN = Integer.parseInt(sCity);
                            int fCityN = Integer.parseInt(fCity);
                            //------------------------------------------------
                            if((sCityN >= 0 && sCityN<kValue.size()) && (fCityN >= 0 && fCityN<kValue.size())){
                                if(opc.equals("0")){
                                    graph.deleteConnection(finalVector,sCityN,fCityN,kValue);
                                }else {
                                    graph.createNewConnection(initialVector,finalVector,sCityN,fCityN,kValue,true);
                                }
                            }
                            //------------------------------------------------------
                        }
                    }
                    //------------------------------------------
                }
            }else if(opc.equals("4")){
                //------------start City----------------------
                System.out.println("Ingrese de la ciudad de la que sale");
                for(int i=0;i<kValue.size();i++){
                    System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                }
                String sCity = sc.nextLine();
                //------------final City---------------------
                System.out.println("Ingrese a la ciudad a la que quiere llegar");
                for(int i=0;i<kValue.size();i++){
                    System.out.println(i+") "+SupportFunctions.getKeyfromValue(kValue,i));
                }
                String fCity = sc.nextLine();
                //--------------------------------------------
                if(sCity.matches(regex)){
                    if(fCity.matches(regex)){
                        int sCityN = Integer.parseInt(sCity);
                        int fCityN = Integer.parseInt(fCity);
                        //------------------------------------------------
                        if((sCityN >= 0 && sCityN<kValue.size()) && (fCityN >= 0 && fCityN<kValue.size())){
                            if(initialVector.get(sCityN).get(fCityN) instanceof List){
                                //--------------------------------------------------
                                List<String> weather = (List<String>) initialVector.get(sCityN).get(fCityN) ;
                                System.out.println("De "+SupportFunctions.getKeyfromValue(kValue,sCityN)+" a "+SupportFunctions.getKeyfromValue(kValue,fCityN)+" que clima hay?");
                                System.out.println("0) Clima Normal\n1) Con lluvia\n2) Con Nieve\n3) Con Tormenta");
                                opc = sc.nextLine();
                                int typeW = 0;
                                if(opc.equals("0") || opc.equals("1") || opc.equals("2") || opc.equals("3")){
                                    typeW = Integer.parseInt(opc);
                                }
                                finalVector.get(sCityN).set(fCityN,Integer.parseInt(weather.get(typeW)));
                                graph.floydAlgorithm(finalVector,kValue);
                                //--------------------------------------------------
                            }else{
                                System.out.println("No existe relacion entre esas ciudades");
                            }
                        }
                        //------------------------------------------------------
                    }
                }
                //------------------------------------------
            }
            System.out.println("-----------------------------------------------");
            System.out.println("Presione ENTER para continuar");
            sc.nextLine();
        }
    }
}