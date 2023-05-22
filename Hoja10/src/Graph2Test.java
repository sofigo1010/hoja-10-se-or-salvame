/**
 * @author Sofia Garcia, Julio García Salas
 * Folder: HDT10
 * Archivo: Graph2Test.java
 * Fecha: 22/05/2023
 */

import java.security.spec.ECField;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.SpinnerNumberModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Graph2Test {

    @Test
    public void testCreateNewNodesFromFile() {
        Reader reader = new Reader();
        Vector<Vector<Object>> expResult = new Vector<>();
        Vector<Object> BA = new Vector<>();
        BA.add(0);
        BA.add(Arrays.asList("10","15","20","50"));
        Vector<Object> SP = new Vector<>();
        SP.add("-");
        SP.add(0);
        expResult.add(BA);
        expResult.add(SP);
        System.out.println(BA);
        Vector<Vector<Object>> result = reader.readFile(false, "BuenosAires SaoPaulo 10 15 20 50");
        assertEquals(expResult,result);
    }

    @Test
    public void testRemoveConnection(){
        HashMap<String,Integer> kValue = new HashMap<>();
        Graph graph = new Graph();
        kValue.put("BA", 0);
        kValue.put("SP", 1);
        Vector<Vector<Object>> vector = new Vector<>();
        Vector<Object> BA = new Vector<>();
        BA.add(0);
        BA.add(10);
        Vector<Object> SP = new Vector<>();
        SP.add(15);
        SP.add(0);
        vector.add(BA);
        vector.add(SP);
        Vector<Vector<Object>> expResult = new Vector<>();
        Vector<Object> SP2 = new Vector<>();
        SP2.add("NE");
        SP2.add(0);
        expResult.add(BA);
        expResult.add(SP2);

        Vector<Vector<Object>> result = graph.deleteConnection(vector, 1, 0, kValue);
        assertEquals(expResult,result);
    }

    @Test
    public void testAddConnection(){
        HashMap<String,Integer> kValue = new HashMap<>();
        Graph graph = new Graph();
        kValue.put("BA", 0);
        kValue.put("SP", 1);
        Vector<Vector<Object>> expResult = new Vector<>();
        Vector<Object> BA = new Vector<>();
        BA.add(0);
        BA.add(Arrays.asList("10","15","20","50"));
        Vector<Object> SP2 = new Vector<>();
        SP2.add(Arrays.asList("10","15","20","50"));
        SP2.add(0);
        expResult.add(BA);
        expResult.add(SP2);
        Vector<Vector<Object>> vector = new Vector<>();
        Vector<Object> SP = new Vector<>();
        SP.add(Arrays.asList("-"));
        SP.add(0);
        vector.add(BA);
        vector.add(SP);

        Vector<Vector<Object>> result = graph.createNewConnection(vector, vector, 1, 0, kValue, false);
        assertEquals(expResult,result);
    }
}