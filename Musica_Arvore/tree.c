
#include "tree.h"

aNo* new_folha(Musica musica)
{
    aNo* folha = (aNo*)malloc(sizeof(aNo));
    folha->maior = NULL;
    folha->menor = NULL;
    folha->musica = musica;

    if(folha == NULL)
        return NULL;

    return folha;
}

void add_folha(aNo** raiz, Musica musica)
{
    if(*raiz == NULL)
    {
        *raiz = new_folha(n);
    }
    else
    {
        if(strcmp(musica.titulo,(*raiz)->musica.titulo) < 0)
            add_folha(&(*raiz)->menor, n);
        if(strcmp(musica.titulo,(*raiz)->musica.titulo) > 0)
            add_folha(&(*raiz)->maior, n);
    }
}
