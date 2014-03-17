#ifndef MUSICAS_H_INCLUDED
#define MUSICAS_H_INCLUDED

#include "basico.h"

typedef struct musica
{
	char titulo[MAX];
	char cantor[MAX];
	char genero[MAX];
	char album[MAX];
	char formato[MAX];
	Data dataCadastro;
	Data dataAlteracao;
}Musica;

char* musicaToString(Musica musica);
void printMusica(Musica musica);
Musica scanMusica(int type, Data dataC);
int isMusicaEqual(Musica m1, Musica m2);
Musica musicReplace(Musica m);

#endif // MUSICAS_H_INCLUDED
