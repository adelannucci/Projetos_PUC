#include "musicas.h"

char* musicaToString(Musica musica)
{
    char* out = (char*) malloc(strlen(musica.album) + strlen(musica.cantor) + strlen(musica.formato) + strlen(musica.genero) + strlen(musica.titulo) + 1);

    sprintf(out,"Titulo: %s \nCantor: %s \nAlbum: %s \nGenero: %s \nFormato: %s \n", musica.titulo, musica.cantor, musica.album, musica.genero, musica.formato);

    return out;
}

void printMusica(Musica musica)
{
    printf("\nTitulo: %s \nCantor: %s \nAlbum: %s \nGenero: %s \nFormato: %s \n", musica.titulo, musica.cantor, musica.album, musica.genero, musica.formato);
    printf("Data de Criacao: %d/%d/%d ",musica.dataCadastro.dia, musica.dataCadastro.mes, musica.dataCadastro.ano);
    printf("%d:%d:%d\n",musica.dataCadastro.hora, musica.dataCadastro.minuto, musica.dataCadastro.segundo);
    printf("Data de Alteracao: %d/%d/%d ",musica.dataAlteracao.dia, musica.dataAlteracao.mes, musica.dataAlteracao.ano);
    printf("%d:%d:%d\n",musica.dataAlteracao.hora, musica.dataAlteracao.minuto, musica.dataAlteracao.segundo);
}

Musica scanMusica(int type, Data dataC)
{
    Musica musica;

    time_t now;
    struct tm* tm;
    now = time(0);
    tm = localtime(&now);

    if(type == 0)
    {
        musica.dataCadastro.ano = tm->tm_year+1900;
        musica.dataCadastro.mes = tm->tm_mon+1;
        musica.dataCadastro.dia = tm->tm_mday;
        musica.dataCadastro.hora = tm->tm_hour;
        musica.dataCadastro.minuto = tm->tm_min;
        musica.dataCadastro.segundo = tm->tm_sec;
        musica.dataAlteracao = musica.dataCadastro;
    }
    else if(type == 1)
    {
        musica.dataCadastro = dataC;
        musica.dataAlteracao.ano = tm->tm_year+1900;
        musica.dataAlteracao.mes = tm->tm_mon+1;
        musica.dataAlteracao.dia = tm->tm_mday;
        musica.dataAlteracao.hora = tm->tm_hour;
        musica.dataAlteracao.minuto = tm->tm_min;
        musica.dataAlteracao.segundo = tm->tm_sec;
    }


    strcpy(musica.titulo,lerDado("Digite o Titulo: "));
    strcpy(musica.cantor,lerDado("Digite o Cantor: "));
    strcpy(musica.album,lerDado("Digite o Album: "));
    strcpy(musica.genero,lerDado("Digite o Genero: "));
    strcpy(musica.formato,lerDado("Digite o Formato: "));

    return musica;
}

int isMusicaEqual(Musica m1, Musica m2)
{
    int out = 0;
    if( (strcmp(m1.titulo,m2.titulo) == 0) && ( strcmp(m1.album,m2.album) == 0) &&
        (strcmp(m1.cantor,m2.cantor) == 0) && (strcmp(m1.formato,m2.formato) == 0) &&
        (strcmp(m1.genero,m2.genero) == 0) )
    {
            out = 1;
    }

    return out;
}

Musica musicReplace(Musica m)
{
    Musica out;
    out.dataAlteracao = m.dataAlteracao;
    out.dataCadastro = m.dataCadastro;
    strcpy(out.titulo,replace(m.titulo));
    strcpy(out.cantor,replace(m.cantor));
    strcpy(out.album,replace(m.album));
    strcpy(out.genero,replace(m.genero));
    strcpy(out.formato,replace(m.formato));

    return out;

}


