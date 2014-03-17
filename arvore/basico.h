#ifndef BASICO_H_INCLUDED
#define BASICO_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX 255

typedef struct data
{
    int dia;
    int mes;
    int ano;
    int hora;
    int minuto;
    int segundo;
}Data;

char* lerDado(char* texto);
int lestring(char s[], int max);
int confirmar(char* texto);
char* replace(char* c);

#endif // BASICO_H_INCLUDED
