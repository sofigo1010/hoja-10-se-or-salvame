/**
 * @author Sofia Garcia, Julio García Salas
 * Folder: HDT10
 * Archivo: SupportFunctions.java
 * Fecha: 22/05/2023
 */

import java.util.HashMap;
import java.util.Map;

public class SupportFunctions {
    public static String getKeyfromValue(HashMap<String,Integer> kValue, Integer value){
        for (Map.Entry<String, Integer> entry : kValue.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
