#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <assert.h>
#include <stdbool.h>
#include "Graph.h"

struct arco{
    int st;
    int arr;
};

Graph initGraph(int nodes_count) {
    Graph G = malloc(sizeof(struct TGraph));
    G->adj = calloc(nodes_count, sizeof(List));
    G->nodes_count = nodes_count;
    return G;
}


Graph randomGraph(int nodes, int edges_min, int edges_max) {
    Graph G = initGraph(nodes);
    if (edges_max > nodes) {
        edges_max = nodes - 1;
    }
    int i = 0;
    int x = 0;
    int y = 0;
    for (i = 0; i < nodes; i++) {
        for (x = 0; x < edges_min; x ++) {
            addEdge(G, i, rand() % nodes, rand() % 50);
        }
        for (y = 0; y < rand() % (edges_max - edges_min); y++) {
            addEdge(G, i, rand() % nodes, rand() % 50);
        }
    }
    return G;
}


void freeGraph(Graph G) {
    if (G != NULL) {
        if (G->nodes_count > 0) {
            int i = 0;
            for (i = 0; i < G->nodes_count; i++) {
                freeList(G->adj[i]);
            }
        }
        free(G);
    }
}


void printGraph(Graph G) {
    if (G != NULL) {
        int x = 0;
        for (x = 0; x < G->nodes_count; x++) {
            printf("%d -> ", x);
            printList(G->adj[x]);
            printf("\n");
        }
    }
}

void addEdge(Graph G, int source, int target, int peso) {
    assert(G != NULL);
    assert(source < G->nodes_count);
    assert(target < G->nodes_count);
    if (source != target) {
        G->adj[source] = appendNodeList(G->adj[source], target, peso);
    }
}


void removeEdge(Graph G, int source, int target) {
    assert(G != NULL);
    assert(source < G->nodes_count);
    assert(target < G->nodes_count);
    if (source != target) {
        G->adj[source] = removeNodeList(G->adj[source], target);
    }
}


void addNode(Graph G) {
    if (G != NULL) {
        G->adj = realloc(G->adj, (G->nodes_count+1) * sizeof(List));
        G->nodes_count += 1;
        G->adj[G->nodes_count] = NULL;
    }
}


void removeNode(Graph G, int node_to_remove) {
    if (G != NULL) {
        int i = 0;
        int x = 0;
        List *tmp = G->adj;
        G->adj = calloc(G->nodes_count, sizeof(List));
        for (i = 0; i <= G->nodes_count; i++) {
            if (i != node_to_remove) {
                G->adj[x] = checkListRemoval(tmp[i], node_to_remove);
                x++;
            } else {
                freeList(G->adj[x]);
            }
        }
        free(*tmp);
        G->nodes_count -= 1;
    }
}


List checkListRemoval(List L, int node_to_remove) {
    if (L != NULL) {
        L->next = checkListRemoval(L->next, node_to_remove);
        if (L->target == node_to_remove) {
            List tmp = L->next;
            free(L);
            return tmp;
        } else if (L->target > node_to_remove) {
            L->target -= 1;
        }
    }
    return L;
}

void checkOrient(Graph G){
    int x;
    List top;
    List top2;
    struct arco arr[1000];
    int i=0;
    for(x=0;x<G->nodes_count;x++){
        //printf("Analizzo il vertice %d \n", x);
        top2=G->adj[x];
        while(top2!=NULL){
            int check=top2->target;
        //printf("Check è %d \n", check);
            top=G->adj[check];
            while(top!=NULL){
                //printf("Controllo che %d sia uguale a %d \n", top->target,x);
                if(top->target == x){
                    //printf("L'arco %d %d non è orientato. \n", x, check);
                    arr[i].st=x;
                    arr[i].arr=check;
                    i++;
                }
                top=top->next;
            }
        top2=top2->next;
        }
    }
    int fin=i;
    printf("Gli archi non orientati sono: \n");
    for(i=0;i<(fin);i++){
        if((i%2) == 0){
            printf("%d %d \n", arr[i].st, arr[i].arr);
            removeEdge(G, arr[i].st, arr[i].arr);
            removeEdge(G, arr[i].arr, arr[i].st);
        }    
    }
}

bool isCyc(Graph G,int v, bool visited[], bool *recStack){
    if(visited[v] == false){
        visited[v] = true;
        recStack[v] = true;
        List top;
        for(top=G->adj[v];top!=NULL;top=top->next){
            if( !visited[top->target] && isCyc(G,top->target, visited, recStack))
                return true;
            else if (recStack[top->target])
                return true;
        }
    }
    recStack[v] = false;
    return false;
}

bool isCyclicM(Graph G){
    bool visited[1000];
    bool recStack[1000];
    int i;
    for(i=0;i<G->nodes_count;i++){
        visited[i] = false;
        recStack[i] = false;
    }

    for(i=0;i<G->nodes_count;i++){
        if (isCyc(G, i, visited, recStack))
            return true;

    }
    return false;
}


