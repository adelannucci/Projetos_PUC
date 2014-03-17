#ifndef TREE_H_INCLUDED
#define TREE_H_INCLUDED

#include "musicas.h"

#include "musicas.h"

typedef struct arvore
{
    Musica musica;
    struct arvore* maior;
    struct arvore* menor;
}aNo;

aNo* new_folha(int n);
void add_folha(aNo** raiz, Musica musica);


int inserirOrdenado(Musica musica, No *head);
Musica* buscarPorTitulo(char* titulo, No *head);
int removerPorTitulo(char* titulo, No *head, int type);
int alterar(char* titulo, No *head);
void writeFile(No *head, int type);
void readFile(FILE *arq, No *head);

#endif // TREE_H_INCLUDED
