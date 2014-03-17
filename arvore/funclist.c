#include "funclist.h"

No* novoNo()
{
    No *no;
    no = (No*)malloc(sizeof(No));

    if(no != NULL)
    {
        no->prev = NULL;
        no->prox = NULL;
        return no;
    }
    else
    {
        printf("\nESTOURO DE MEMORIA!");
		exit(0);
    }
}

void listar(No * head)
{
    No *no;
    no = head->prox;

    if(no != NULL)
    {
        printMusica(no->musica);
        //printf("%s", musicaToString(no->musica));
        listar(no);
    }

}

void inseriNoInicio(Musica musica, No *head)
{
    No *no = novoNo();
    no->musica = musica;

    No *aux;
    aux = NULL;
    //lista vazia
    if(head->prox == NULL)
    {
       head->prox = no;
    }
    else
    {
        aux=head->prox;
        head->prox = no;
        no->prox = aux;
        no->prev = NULL;
    }
}

void inserirNoFim(Musica musica, No *head)
{

    //lista vazia
    if(head->prox == NULL)
    {
        No *no = novoNo();
        no->musica = musica;
        head->prox = no;
        no->prev = head;
    }
    else
    {
        inserirNoFim(musica, head->prox);
    }
}

int inserirOrdenado(Musica musica, No *head)
{
    No *no = novoNo();

    No *aux = head->prox;
    No *prev = NULL;

    no->musica = musica;

    /*procura posicao para insercao*/
	while(aux != NULL && ( strcmp(aux->musica.titulo,musica.titulo) <= 0) )
	{
		if( strcmp(aux->musica.titulo,musica.titulo) == 0)
		//if( isMusicaEqual(aux->musica, musica) == 1)
        {
            return 0;
        }
		prev = aux;
		aux = aux->prox;
	}

    if(head->prox == NULL)//lista vazia
    {
        head->prox = no;
        no->prev = NULL;
        return 1;
    }
	else if(aux == NULL)//pos fim
    {
        prev->prox = no;
        no->prev = prev;
        return 1;
    }
	else if(prev == NULL)//pos ini
    {
        head->prox = no;
        no->prev = NULL;
        no->prox = aux;
        return 1;
    }
	else//pos meio
	{
		prev->prox = no;
		no->prox = aux;
		no->prev = prev;
		return 1;
	}

	return-1;


}

Musica* buscarPorTitulo(char* titulo, No *head)
{
    Musica* m;
    m=NULL;
    if(head == NULL)
    {
        return m;
    }
    else
    {
        //char * tituloMusica = (char*) malloc(strlen(head->musica.titulo)+1);
        //sprintf(tituloMusica,"%s",head->musica.titulo);
        if(strcmp(head->musica.titulo,titulo)==0)
        {
            m = &(head->musica);
            return m;
        }
        else
        {
            m = buscarPorTitulo(titulo, head->prox);
        }
    }

    return m;
}

int removerPorTitulo(char* titulo, No *head, int type)
{

    No *h = head->prox;

    No *ant = NULL;
    No *pro = NULL;

    while(h != NULL)
    {
       if(strcmp(h->musica.titulo,titulo)==0)
        {
            printMusica(h->musica);
            if(confirmar("Deseja mesmo Remover\n") == 0)
            {
                return 0;
            }

            ant = h->prev;
            pro = h->prox;

            if(ant == NULL)//primeiro elemento
            {
               head->prox = pro;
                if(pro != NULL)
                    pro->prev = NULL;
            }
            else if(pro != NULL)//Meio
            {
                ant->prox = pro;
                pro->prev = ant;
            }
            else
            {
              ant->prox = NULL;
            }

            if(type == 1)
                writeFile(h, 1);
            free(h);

            return 1;
        }
        h = h->prox;
    }

    return 0;
}

int alterar(char* titulo, No *head)
{
    Musica m, m1;
    No* aux = head;

    while(head != NULL)
    {
        if(strcmp(head->musica.titulo,titulo)==0)
        {
            m = head->musica;
            printMusica(m);
            if(confirmar("Deseja mesmo Alterar\n"))
            {
                removerPorTitulo(m.titulo,aux, 0);
                m1 = scanMusica(1, m.dataCadastro);
                if(inserirOrdenado(m1, aux) == 0)
                {
                    inserirOrdenado(m, aux);
                }
            }

            return 1;
        }

        head = head->prox;
    }

    return 0;
}

No* navegar(No* no, int opc)
{
    switch(opc)
    {
        case 1:
            if(no->prev == NULL)
                return NULL;
            return no->prev;
            break;
        case 0:
            return NULL;
            break;
        case 2:
            if(no->prox == NULL)
                return NULL;
            return no->prox;
            break;
    }

    return NULL;
}



void writeFile(No *head, int type)
{
    FILE *arq;
    No *aux = NULL;
    if( type == 0) // Salvar arquivo
    {
        arq = fopen("datalog.txt", "w");
        if(arq != NULL)
            aux = head->prox;
    }
    else if(type == 1)//Salvar Log recuperacao
    {
        arq = fopen("log.txt", "a");

        if(arq == NULL)
            arq = fopen("log.txt", "w");
        aux = head;
        aux->prox = NULL;
    }

    while(aux != NULL)
    {
        Musica m = aux->musica;

        m = musicReplace(m);

        fprintf(arq,"%s %s %s %s %s %d %d %d %d %d %d %d %d %d %d %d %d\n",
            m.titulo,
            m.cantor,
            m.album,
            m.genero,
            m.formato,
            m.dataCadastro.ano,
            m.dataCadastro.mes,
            m.dataCadastro.dia,
            m.dataCadastro.hora,
            m.dataCadastro.minuto,
            m.dataCadastro.segundo,
            m.dataAlteracao.ano,
            m.dataAlteracao.mes,
            m.dataAlteracao.dia,
            m.dataAlteracao.hora,
            m.dataAlteracao.minuto,
            m.dataAlteracao.segundo
            );
        aux = aux->prox;
    }

    fclose(arq);
}

void readFile(FILE *arq, No *head)
{

    while(!feof(arq))
    {
        //No *aux = novoNo();
        Musica m;
        fscanf(arq,"%s %s %s %s %s %d %d %d %d %d %d %d %d %d %d %d %d ",
            m.titulo,
            m.cantor,
            m.album,
            m.genero,
            m.formato,
            &m.dataCadastro.ano,
            &m.dataCadastro.mes,
            &m.dataCadastro.dia,
            &m.dataCadastro.hora,
            &m.dataCadastro.minuto,
            &m.dataCadastro.segundo,
            &m.dataAlteracao.ano,
            &m.dataAlteracao.mes,
            &m.dataAlteracao.dia,
            &m.dataAlteracao.hora,
            &m.dataAlteracao.minuto,
            &m.dataAlteracao.segundo
            );

        m = musicReplace(m);
        inserirOrdenado(m, head);

    }
}


