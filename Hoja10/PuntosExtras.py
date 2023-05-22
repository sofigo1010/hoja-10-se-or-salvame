#
 # @author Sofia Garcia, Julio García Salas
 # Folder: HDT10
 # Archivo: Main.java
 # Fecha: 22/05/2023


import networkx as nx
import numpy as np
import re
# To create an empty undirected graph
G = nx.Graph()

kValue = {}
initialVector = []
finalVector = []

def readFile():
    f = open("logistica.txt","r")
    for x in f:
        arr = x.strip("\n").split(" ")
        if(not(arr[0] in kValue)):
            G.add_node(arr[0])
            kValue[arr[0]] = len(kValue)
            initialVector.append([])
        if(not(arr[1] in kValue)):
            G.add_node(arr[1])
            kValue[arr[1]] = len(kValue)
            initialVector.append([])
        
        for i in range(len(kValue)):
            city = initialVector[i]
            cont = 0
            while len(city) < len(kValue):
                if(cont == i):
                    city.append(0)
                else:
                    city.append("-")
                cont+=1
            
        startCity = kValue[arr[0]]
        arrivedCity = kValue[arr[1]]

        city = initialVector[startCity]
        city[arrivedCity] = [arr[2],arr[3],arr[4],arr[5]]



def makeTheFistVector():
    print("Ingrese los siguentes climas")
    keys = list(kValue.keys())
    for i in range(len(initialVector)):
        startCity = initialVector[i]
        NstartCity = []
        cont = 0
        for j in range(len(startCity)):
            if cont != i:
                element = startCity[j]
                if(isinstance(element,list)):
                    typeW = 0
                    print("De "+keys[i]+" a "+keys[j]+" que clima hay?")
                    opc = input("0) Clima normal\n1) Con lluvia\n2) Con nieve\n3) Con Tormenta\nSeleccione la opcion:")
                    if(opc == "0" or opc == "1" or opc == "2" or opc == "3"):
                        typeW = int(opc)
                    G.add_edge(keys[i], keys[j], weight =int(element[typeW]))
                    print("--------------------------------------")
            cont+=1
        finalVector.append(NstartCity)


def centerGraph():
    shortest_paths = nx.floyd_warshall(G, weight='weight')

    # Calculate the eccentricity for each node
    eccentricities = {}
    for node, paths in shortest_paths.items():
        eccentricity = max(paths.values())
        eccentricities[node] = eccentricity

    # Find the center of the graph (nodes with minimum eccentricity)
    min_eccentricity = min(eccentricities.values())
    center = [node for node, eccentricity in eccentricities.items() if eccentricity == min_eccentricity]

    print("Center of the graph:", center)

def floydAlgorithm(source, target):
    predecessors, shortRoute = nx.floyd_warshall_predecessor_and_distance(G)
    print("-----------------------------------------------")
    print("La ruta mas corta es de: "+str(shortRoute[source][target]))
    print("Las cidades que tiene que seguir son:")
    print(nx.reconstruct_path(source, target, predecessors))

def removeEdge(source, target):
    G.remove_edge(source, target)
    
def addNewEdge(source, target,sourceInt, targetInt):
    print("Ingrese el tiempo entre las dos ciudades con los siguentes climas")
    normal = int(input("Clima Normal:\n"))
    rain = int(input("Clima con lluvia:\n"))
    snow = int(input("Clima con nueve:\n"))
    storm = int(input("Clima con tormenta:\n"))
    
    initialVector[sourceInt][targetInt] = [normal, rain, snow, storm]
    if(G.has_edge(source, target)):
        G.remove_edge(source,target)
        G.add_edge(source,target, weight=normal)
    else:
        G.add_edge(source,target, weight=normal)


def changeWeather(source, target,sourceInt, targetInt):
    if(isinstance(initialVector[sourceInt][targetInt],list) ):
        weather = initialVector[sourceInt][targetInt]
        print("De "+source+" a "+target+" que clima hay?")
        opc = input("0) Clima Normal\n1) Con lluvia\n2) Con Nieve\n3) Con Tormenta\nSeleccione la opcion: ")
        typeW = 0
        if(opc == "0" or opc == "1" or opc == "2" or opc == "3"):
            typeW = int(opc)
        if(G.has_edge(source, target)):
            G.remove_edge(source,target)
            G.add_edge(source,target, weight=weather[typeW])
        else:
            G.add_edge(source,target, weight=weather[typeW]) 
    else:
        print("No existe relacion entre esas ciudades")


    




print("Bienvenido al programa")
readFile()
makeTheFistVector()
flag = True

while flag:
    print("Ingrese la opcion que quiere")
    opc = input("0) Salir\n1) Distancia mas corta entre dos ciudades\n2) Ciudad centro del grafo" +
                    "\n3) Modificar un grafo\n4) Indicar un clima entre ciudades\nSeleccione la opcion: ")
    print("-----------------------------------------------")
    if(opc == "0"):
        flag = False
    if(opc == "1"):
        print("Ingrese la ciudad de la que va a partir")
        keys = list(kValue.keys())
        for i in range(len(kValue)):
            print(str(i)+") "+keys[i])
        c1 = input("Seleccione la ciudad de la que parte: ")
        for i in range(len(kValue)):
            print(str(i)+") "+keys[i])
        c2 = input("Selecciona la ciudad a la que va a llegar: ")
        if(re.findall("\d",c1) and re.findall("\d",c2)):
            c1 = int(c1)
            c2 = int(c2)
            if(c1 < len(kValue) and c2 < len(kValue)):
                floydAlgorithm(keys[c1],keys[c2])
    if(opc == "2"):
        centerGraph()
    if(opc == "3"):
        opc = input("Ingrese la opcion que desee\n0) Borrar la conexion entre dos ciudades\n"+
                            "1) Establacer conexion entre dos ciudades\nSeleccione opcion: ")
        if(opc == "0" or opc == "1"):
            print("Ingrese la ciudad de la que va a partir")
            keys = list(kValue.keys())
            for i in range(len(kValue)):
                print(str(i)+") "+keys[i])
            c1 = input("Seleccione la ciudad de la que parte: ")
            for i in range(len(kValue)):
                print(str(i)+") "+keys[i])
            c2 = input("Selecciona la ciudad a la que va a llegar: ")
            if(re.findall("\d",c1) and re.findall("\d",c2)):
                c1 = int(c1)
                c2 = int(c2)
                if(c1 < len(kValue) and c2 < len(kValue)):
                    if(opc == "0"):
                        removeEdge(keys[c1],keys[c2])
                    else:
                        addNewEdge(keys[c1],keys[c2], c1, c2)
    if(opc == "4"):
        print("Ingrese la ciudad de la que va a partir")
        keys = list(kValue.keys())
        for i in range(len(kValue)):
            print(str(i)+") "+keys[i])
        c1 = input("Seleccione la ciudad de la que parte: ")
        for i in range(len(kValue)):
            print(str(i)+") "+keys[i])
        c2 = input("Selecciona la ciudad a la que va a llegar: ")
        if(re.findall("\d",c1) and re.findall("\d",c2)):
            c1 = int(c1)
            c2 = int(c2)
            if(c1 < len(kValue) and c2 < len(kValue)):         
                changeWeather(keys[c1],keys[c2],c1,c2)
                    

        
    print("-----------------------------------------------")



'''

'''