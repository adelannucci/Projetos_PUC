#ifndef FUNCLIST_H_INCLUDED
#define FUNCLIST_H_INCLUDED

#include "musicas.h"

typedef struct no
{
    Musica musica;
    struct no* prev;
    struct no* prox;
} No;

No* novoNo();
void listar(No * head);
void inseriNoInicio(Musica musica, No *head);
void inserirNoFim(Musica musica, No *head);
int inserirOrdenado(Musica musica, No *head);
Musica* buscarPorTitulo(char* titulo, No *head);
int removerPorTitulo(char* titulo, No *head, int type);
int alterar(char* titulo, No *head);
No* navegar(No* no, int opc);
void writeFile(No *head, int type);
void readFile(FILE *arq, No *head);

#endif // FUNCLIST_H_INCLUDED
