/* Nomes: Alexandre Almeida Souza / Larissa Coelho de Castro Roberto
RAs: 12651154 / 12560520
Opcionais funcionando:
Valor do Projeto:  _______ pontos
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>

typedef struct server{
   long ip;
   int porta;
   unsigned long qtd_sol;
   struct server *proxServer;
}Server;

typedef struct service{
   char s_name[30];
   struct service *proxService;
   Server *priServer;
}Service;

Service* new_service();
Server* new_server();
int find_server(char* name, long ip, int port, Service *head);
int add_new_service(char* name, long ip, int port, unsigned long qtd, Service * head);
int add_new_server(long ip, int port, unsigned long qtd, Server *head);
void lista(Service * head);
void list_service_type(Service * head);
int consult_service(char* service, Service * head);
int remove_service(char* name, Service * head);
void writeFile(Service *head);
void readFile(FILE *arq, Service *head);
Server* solicitar_servidor(char* name, Service *head);
void menu();
int menu_func(int op, FILE *arq, Service *head);
int le_string(char s[], int max);



//returna um novo Service para uma lista de servicos
Service* new_service()
{
    Service *no;
    no = (Service*)malloc(sizeof(Service));

    if(no != NULL)
    {
        no->priServer = new_server();
        no->proxService = NULL;
        return no;
    }
    else
    {
        printf("\nESTOURO DE MEMORIA!");
		exit(0);
    }
}

//retorna um novo servidor
Server* new_server()
{
    Server *no;
    no = (Server*)malloc(sizeof(Server));

    if(no != NULL)
    {
        no->proxServer = NULL;
        return no;
    }
    else
    {
        printf("\nESTOURO DE MEMORIA!");
		exit(0);
    }
}

int find_server(char* name, long ip, int port, Service *head)
{

    Service *no;
    no = head->proxService;
    Server *noS;

    while(no != NULL)
    {
        if(strcmp(no->s_name, name) == 0)
        {
            noS = no->priServer->proxServer;
            while(noS != NULL)
            {

                if(noS->ip == ip && noS->porta == port)
                {
                    //Achou o servico com a porta e o ip
                    return 1;
                }
                noS = noS->proxServer;
            }
            //achou somente o servico
            return 0;
        }
        no = no->proxService;
    }
    //nao achou nada
    return -1;
}

int add_new_service(char* name, long ip, int port, unsigned long qtd, Service * head)
{
    int out = 0;
    int find = find_server(name, ip, port, head);

    // O servidor foi localizado nao add
    if(find == 1)
    {
        return 0;
    }
    //como o servico existe devemos localizalo e add o server
    else if(find == 0)
    {
        while(head != NULL)
        {
            if(strcmp(head->s_name,name)==0)
            {
                out = add_new_server(ip,port,qtd,head->priServer);
                return out;
            }

            head = head->proxService;
        }
    }
    //O service nao existe localizar o final e add
    else if(find == -1)
    {
       while(head != NULL)
        {
            if(head->proxService == NULL)
            {
                Service *no = new_service();
                strcpy(no->s_name,name);
                head->proxService = no;
                out = add_new_server(ip,port,qtd, head->proxService->priServer);
                return out;
            }

            head = head->proxService;
        }
    }

    return 0;
}

int add_new_server(long ip, int port, unsigned long qtd, Server *head)
{
    //lista vazia
    while(head != NULL)
    {
        if(head->proxServer == NULL)
        {
            Server *no = new_server();
            no->ip = ip;
            no->porta = port;
            no->qtd_sol = qtd;
            head->proxServer = no;
            return 1;
        }

        head = head->proxServer;
    }

    return 0;
}

void listar(Service * head)
{
    Service *no;
    no = head->proxService;

    if(no != NULL)
    {
        printf("\nService: %s", no->s_name);
        listar(no);
    }

}

void list_service_type(Service * head)
{
    Service *no;
    no = head->proxService;
    Server *noS;

    if(no != NULL)
    {
        printf("\nService: %s", no->s_name);
        noS = no->priServer->proxServer;
        while(noS != NULL)
        {
           printf("\nServer - ip: %ld - port: %d", noS->ip, noS->porta);
           noS = noS->proxServer;
        }

        list_service_type(no);
    }

}

int consult_service(char* service, Service * head)
{
    int out = 0;
    Service *no;
    no = head->proxService;
    Server *noS;

    if(no != NULL)
    {
        if(strcmp(no->s_name, service) == 0)
        {
            printf("\nService: %s", no->s_name);
            noS = no->priServer->proxServer;
            while(noS != NULL)
            {
                printf("\nServer - ip: %ld - port: %d", noS->ip, noS->porta);
                noS = noS->proxServer;
            }
            return 1;
        }

        out = consult_service(service, no);
    }

    return out;

}

int remove_service(char* name, Service * head)
{
	Service *no;
    no = head->proxService;

    Server *noS;
    //lista vazia
    if(head->proxService != NULL)
    {
        if(strcmp(no->s_name,name) == 0)
        {
			noS = no->priServer->proxServer;

            while(noS != NULL)
            {
                Server *aux = noS;
				noS = noS->proxServer;
				free(aux);
            }
            head->proxService = no->proxService;
            free(no);
            return 1;
        }
        else
        {
            return remove_service(name,head->proxService);
        }

    }
   return 0;
}

void writeFile(Service *head)
{
    FILE *arq;
    arq = fopen("datalog.log", "w");
    Service *aux = head->proxService;
    while(aux != NULL)
    {
        Server *auxServer = aux->priServer->proxServer;
        while(auxServer != NULL)
        {
           fprintf(arq,"%s %ld %d %lu\n", aux->s_name,auxServer->ip,auxServer->porta,auxServer->qtd_sol);
           auxServer = auxServer->proxServer;
        }
        aux = aux->proxService;
    }

    fclose(arq);
}

void readFile(FILE *arq, Service *head)
{
    char name[30];
    long ip;
    int port;
    unsigned long qtd;
    int out = 0;

    while(!feof(arq))
    {

        fscanf(arq,"%s %ld %d %lu ", name, &ip, &port, &qtd);
        out = add_new_service(name, ip, port, qtd, head);
    }
    fclose(arq);
}

Server* solicitar_servidor(char* name, Service *head)
{
    Server *out = NULL;
    Service *no;
    no = head->proxService;
    Server *noS;

    if(no != NULL)
    {
        if(strcmp(no->s_name, name) == 0)
        {
            noS = no->priServer->proxServer;
            out = noS;

            while(noS != NULL)
            {
                //printf("\n");
                //printf("\nnoS - qtd: %lu - ip: %ld - port: %d", noS->qtd_sol, noS->ip, noS->porta);
                //printf("\nout - qtd: %lu - ip: %ld - port: %d", out->qtd_sol, out->ip, out->porta);
                if(out->qtd_sol > noS->qtd_sol)
                {
                    out = noS;
                }
                noS = noS->proxServer;
            }
            out->qtd_sol = out->qtd_sol + 1;
            return out;
        }

        out = solicitar_servidor(name, no);
    }

    return out;
}

void menu()
{
    system("cls");
    printf("\n*----------------Menu----------------*\n\n");
    printf("1- Inclusao de um novo servico (IP e porta do servidor) \n");
    printf("2- Remocao de um servico existente \n");
    printf("3- Consulta de um servico \n");
    printf("4- Listagem de todos os servicos \n");
    printf("5- Listagem de servidores por tipo \n");
    printf("6- Armazenar dados \n");
    printf("7- Recuperar dados \n");
    printf("8- Solicitar Servico \n");
    printf("9- Sair \n");
}

int menu_func(int op, FILE *arq, Service *head)
{
    int r_add = 0;
    int r_consult = 0;
    int r_remove = 0;
    long ip = 0;
    int port = 0;
    char n_service[30];

    system("cls");
    fflush(stdout);
    fflush(stdin);

    switch(op)
    {
        case 1:
            printf("Inclusao de um novo servico\n");
            printf("Digite o nome do servico\n");
            le_string(n_service,30);
            fflush(stdin);

            printf("Digite o ip: ");
            scanf(" %ld", &ip);
            fflush(stdin);
            printf("Digite a porta: ");
            scanf(" %d", &port);

            r_add = add_new_service(n_service,ip,port,0,head);

            if(r_add==0)
            {
                printf("\nErro ao adicionar porta (e/ou) ip ja existente nesse servico!");
            }
            else
            {
                printf("\nServico adicionado!");
            }

            break;
        case 2:
            printf("Remocao de um servico existente \n");
            printf("Digite o nome do servico\n");
            le_string(n_service,30);
            fflush(stdin);
            r_remove = remove_service(n_service, head);
            if(r_remove == 0)
            {
                printf("\nServico nao encontrado!");
            }
            else
            {
               printf("\nServico removido");
            }
			break;
        case 3:
            printf("Consulta de um servico \n");
            printf("Digite o nome do servico\n");
            le_string(n_service,30);
            fflush(stdin);
            r_consult = consult_service(n_service, head);
            if(r_consult == 0)
            {
                printf("\nServico nao encontrado!");
            }
			break;
        case 4:
            printf("Listagem de todos os servicos \n");
            listar(head);
			break;
        case 5:
            printf("Listagem de servidores por tipo \n");
            list_service_type(head);
			break;
        case 6:
            printf("Armazenar dados \n");
            writeFile(head);
			break;
        case 7:
            printf("Recuperar dados \n");
            arq = fopen("datalog.log", "r");
            if(arq==NULL)
            {
                printf("Não existe nenhum arquivo gravado!");
            }
            else
            {
                readFile(arq, head);
                printf("Dados recuperados \n");
            }
			break;
        case 8:
            printf("Solicitar Servico \n");
            printf("Digite o nome do servico\n");
            le_string(n_service,30);
            fflush(stdin);

            Server *s = solicitar_servidor(n_service, head);
            if(s!=NULL)
            {
                printf("\nSolicitacao - %s - ip: %ld - port: %d", n_service, s->ip, s->porta);
            }
            else
            {
                printf("Servico nao encontrado\n");
            }
			break;
        case 9:
            printf("Sair\n");
            writeFile(head);
            return 0;
			break;
        default:
            printf("Opcao invalida\n");
        break;
    }

    printf("\n");
    fflush(stdout);
    return 1;
}

int le_string(char s[], int max)
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

int main(){
    //cabeca da lista de servicos
    Service *head;
    head = new_service();
    int op = 0;
    int cont = 1;

    FILE *arq;
    arq = fopen("datalog.log", "r");
    if(arq!=NULL)
        readFile(arq, head);
    fclose(arq);

    while(cont == 1)
    {
        menu();
        printf("Escolha a opcao desejada: ");
        fflush(stdin);
        scanf(" %d", &op);
        fflush(stdout);
        cont = menu_func(op, arq, head);
        system("pause");
    }

    return 0;

}
