#include "principal.h"

int main ()
{
    int opcao;
    No *head;

    //Criar lista
	head = novoNo();

	FILE *arq;
    arq = fopen("datalog.txt", "r");
    if(arq != NULL)
        readFile(arq, head);

	do
	{
		menu();
		printf("\nDigite uma opcao: ");
		fflush(stdout);
		scanf("%d",&opcao);
		fflush(stdin);
		executaOpcao(opcao, head);
	}while(opcao != -1);


	return(0);
}

void menu()
{
    system("cls");
    printf("\n1  - Inserir Musica");
	printf("\n2  - Navegar");
	printf("\n3  - Listar");
	printf("\n4  - Buscar por titulo");
	printf("\n5  - Remover por titulo");
	printf("\n6  - Alterar");
	printf("\n7  - Recuperar");
	printf("\n-1 - Sair");
}

void executaOpcao(int opcao, No *head)
{
    FILE *arq;

	switch(opcao)
	{
	    Musica musica;
	    char titulo[MAX];
		case 1:
            fflush(stdin);
            musica = scanMusica(0, musica.dataCadastro);
            int res = inserirOrdenado(musica,head);
            if( res == 1)
                printf("Musica adicionada\n");
            else if (res == 0)
            {
                printf("A musica dublicada. Nao adicionada\n");
            }
            else
            {
                printf("Falha ao adicionar\n");
            }
            system("pause");
		break;
		case 2:
            executaOpcaoNavegar(head);
        break;
		case 3:
            printf("Listar\n");
            listar(head);
            system("pause");
        break;
        case 4:
            printf("Digite o titulo da musica\n");
            lestring(titulo, MAX);
            fflush(stdin);
            Musica *m = buscarPorTitulo(titulo,head->prox);
            if(m!=NULL)
                printMusica(*m);
            system("pause");
        break;
        case 5:
            printf("Digite o titulo da musica\n");
            lestring(titulo, MAX);
            fflush(stdin);
            if(removerPorTitulo(titulo,head,1))
            {
                printf("Musica Removida\n");
            }
            else
            {
                printf("Musica nao localizada\n");
            }
            system("pause");
        break;
        case 6:
            printf("Digite o titulo da musica\n");
            lestring(titulo, MAX);
            fflush(stdin);
            if(alterar(titulo,head))
            {
                printf("Musica Alterada\n");
            }
            else
            {
                printf("Musica nao localizada\n");
            }
            system("pause");
        break;
        case 7:
            arq = fopen("log.txt", "r");
            if(arq != NULL)
                readFile(arq, head);
            arq = fopen("log.txt", "w");
            fclose(arq);
        break;
		case -1:
            writeFile(head, 0);
		break;
	}
}

void menuNavegar()
{
    system("cls");
    printf("\n1 - Anterior - 2 - Proxima - 0 - Sair\n");
}

void executaOpcaoNavegar(No* head)
{
    No* no = head->prox;
    int opc = 0;
    do
	{
		menuNavegar();
		printMusica(no->musica);
		printf("\nDigite uma opcao: ");
		fflush(stdout);
		scanf("%d",&opc);
		fflush(stdin);
		no = navegar(no,opc);
	}while(no != NULL);
}

