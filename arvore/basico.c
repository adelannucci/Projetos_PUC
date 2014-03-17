#include "basico.h"

char* lerDado(char* texto)
{
    char *aux;
    aux =(char*)malloc(MAX);
    //int confirmar = 1;
    //do
    //{
        system("cls");
        fflush(stdout);
        printf(texto);
        lestring(aux,MAX);
        fflush(stdin);
        printf("\n");
        fflush(stdout);

       /* system("cls");
        printf("O valor lido foi: ");
        printf("%s", aux);
        printf("\nDigite 1 para confirmar: ");
        fflush(stdout);
        scanf("%d", &confirmar);
        fflush(stdin);
        printf("\n");
        fflush(stdout);*/

   // }while(confirmar != 1);

    return aux;
}

int lestring(char s[], int max)
{
    int i = 0;
    char letra;

    /* No caso o max é o tamanho que s pode receber. Deve ser passado o tamanho
     * mesmo, ou seja, se for passado 100 a função já se encarrega de não deixar
     * passar de 99 cars (+1 do finalizador)
     */
    for (i = 0; i < (max - 1); i++)
    {
        letra = fgetc(stdin);

        /* Veja que se encontrou um ENTER (\n) mas não leu nenhum caractere válido,
         * não aceita. Precisa ler algo. Decrementa o i para anular o efeito do i++
         * do laço e volta no laço com continue
         */
        if ((letra == '\n') && (i == 0))
        {
            i = i - 1;
            continue;
        }
        /* Agora se leu um enter já tendo lido caracteres válidos, então
         * o usuário terminou de digitar sua string e ela possui ao menos
         * um caractere válido
         */
         if (letra == '\n')
            break;
         s[i] = letra;
    }

    /* Finaliza a string */
    s[i] = 0;

    /* retorna a quantidade de cars lidos (pode ser útil). Então, esta função
     * lê uma string e retorna o seu tamanho
     */

     return (i);
}

int confirmar(char* texto)
{

    int c;
    printf("%s", texto);
    printf("\nDigite 1 para confirmar ou 0 para cancelar.\n");
    fflush(stdout);
    scanf("%d", &c);
    fflush(stdin);
    printf("\n");
    fflush(stdout);

    if(c!=0 && c!=1)
        c = confirmar(texto);

    return c;
}

char* replace(char* c)
{
    char *out;
    out = (char*)malloc(MAX);

    if(strlen(c)!= 0)
        strcpy(out, c);

    int i = 0;
    while(c[i] != '\0')
    {
        if(c[i] == ' ')
            out[i] = '@';
        else if(c[i] == '@')
            out[i] = ' ';
        else
            out[i] = c[i];

        i++;
    }

    return out;
}
