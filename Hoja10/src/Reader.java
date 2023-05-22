import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Reader {
    Vector<Vector<Object>> initialVector = new Vector<>();
    HashMap<String, Integer> kValue = new HashMap<>();

    public Reader(Vector<Vector<Object>> initialVector, HashMap<String, Integer> kValue) {
        this.initialVector = initialVector;
        this.kValue = kValue;
        readFile(true,null);
    }

    public Reader(){
        Vector<Vector<Object>> initialVector =  new Vector<Vector<Object>>();
        HashMap<String,Integer> kValue = new HashMap<>();
    }

    public Vector<Vector<Object>> readFile(boolean flag, String newNode) {
        FileReader archivo;
        BufferedReader lector;
        if(flag){
            try {
                archivo = new FileReader("logistica.txt");
                if (archivo.ready()) {
                    lector = new BufferedReader(archivo);
                    String cadena;
                    while ((cadena = lector.readLine()) != null) {

                        String[] data = cadena.split(" "); //El tamaño tiene que ser 6

                        if (data.length != 6) {
                            System.out.println("Hay un problema en una de las lecturas");
                        } else {

                            //Preguntar si pongo lowerCase
                            if (!(kValue.containsKey(data[0]))) {
                                //Si no existe la ciudad la crea y le agrega un valor de ubicacion
                                //Crea el vector de destinos de esa ciudad
                                kValue.put(data[0], kValue.size());
                                initialVector.add(new Vector<>());
                            }
                            if (!(kValue.containsKey(data[1]))) {
                                //Si no existe la ciudad la crea y le agrega un valor de ubicacion
                                //Crea el vector de destinos de esa ciudad
                                kValue.put(data[1], kValue.size());
                                initialVector.add(new Vector<>());
                            }
                            //Llena todos los vectores al numero de ciudad que hay
                            for (int i = 0; i < kValue.size(); i++) {
                                Vector<Object> partida = initialVector.get(i);
                                int cont = 0;
                                while (partida.size() < kValue.size()) {
                                    if (cont == i) {
                                        partida.add(0);
                                    } else {
                                        partida.add("-");
                                    }
                                    cont++;
                                }
                            }

                            //Encuentra la posicion que tiene la ciudad de partida y de destino en la matriz
                            Integer ciudadPartida = kValue.get(data[0]);
                            Integer ciudadDestino = kValue.get(data[1]);

                            //Crea valores
                            Vector<String> keys = new Vector<>();
                            for (int j = 0; j < kValue.size(); j++) {
                                keys.add(SupportFunctions.getKeyfromValue(kValue, j));
                            }
                            Vector<Object> partida = initialVector.get(ciudadPartida);
                            partida.set(ciudadDestino, Arrays.asList(data[2], data[3], data[4], data[5]));
                            //Mandaremos a la cadena.
                        }
                    }
                    System.out.println("-----------------------------------------------");
                    return initialVector;
                } else {
                    System.out.println("El archivo no se encuentra");
                }
            } catch (Exception e) {
                throw new RuntimeException("Unexpected Error: " + e);
            }
        }else{
            String[] data = newNode.split(" ");
            //Preguntar si pongo lowerCase
            if (!(kValue.containsKey(data[0]))) {
                //Si no existe la ciudad la crea y le agrega un valor de ubicacion
                //Crea el vector de destinos de esa ciudad
                kValue.put(data[0], kValue.size());
                initialVector.add(new Vector<>());
            }
            if (!(kValue.containsKey(data[1]))) {
                //Si no existe la ciudad la crea y le agrega un valor de ubicacion
                //Crea el vector de destinos de esa ciudad
                kValue.put(data[1], kValue.size());
                initialVector.add(new Vector<>());
            }
            //Llena todos los vectores al numero de ciudad que hay
            for (int i = 0; i < kValue.size(); i++) {
                Vector<Object> partida = initialVector.get(i);
                int cont = 0;
                while (partida.size() < kValue.size()) {
                    if (cont == i) {
                        partida.add(0);
                    } else {
                        partida.add("-");
                    }
                    cont++;
                }
            }


            //Encuentra la posicion que tiene la ciudad de partida y de destino en la matriz
            Integer ciudadPartida = kValue.get(data[0]);
            Integer ciudadDestino = kValue.get(data[1]);
            System.out.println("Values " + initialVector.size());

            //Crea valores
            Vector<String> keys = new Vector<>();
            for (int j = 0; j < kValue.size(); j++) {
                keys.add(SupportFunctions.getKeyfromValue(kValue, j));
            }

            System.out.println(keys);
            Vector<Object> partida = initialVector.get(ciudadPartida);
            System.out.println("Estamos en vector: " + SupportFunctions.getKeyfromValue(kValue, ciudadPartida));
            partida.set(ciudadDestino, Arrays.asList(data[2], data[3], data[4], data[5]));
            System.out.println(partida);

            System.out.println("-----------------------------------------------");
            //Mandaremos a la cadena.
            return initialVector;
        }
        return null;
    }
}
