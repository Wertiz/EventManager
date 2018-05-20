#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include "Graph.c"

int main(int argc, const char * argv[]) {
    srand((unsigned int)time(NULL));
    
    // Creo un grafo in modo random,
    // specificando che deve avere 20 nodi e almeno 5 archi
    // con un massimo di 25
    //Graph G = randomGraph(5, 2, 5);
    Graph G = initGraph(4);
    addEdge(G,0,1,0);
    addEdge(G,1,0,0);
    addEdge(G,1,2,0);
    addEdge(G,2,3,0);
    addEdge(G,3,0,0);
    /*addEdge(G,2,3,0);
    addEdge(G,3,3,0);*/
    printGraph(G);
    printf("\n");
    checkOrient(G);
    printGraph(G);
    // insert code here...
    bool check=isCyclicM(G);
    if(check)
    	printf("\nIl grafo presenta un ciclo.\n");
    else
    	printf("\nIl grafo non presenta un ciclo.\n");
    //printf("Hello, World!\n");
    return 0;
}

