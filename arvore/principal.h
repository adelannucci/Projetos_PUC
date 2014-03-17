/********************************************************/
/*     Projeto de Estrutura de Dados PUC - Campinas     */
/*                Gerenciador de Musicas                */
/*                                                      */
/********************************************************/
/*             André Luiz Vannucci 12647202             */
/*        Opcionais: 2(+1); 4(+1); 5(+1); 6(+3)         */
/*        Valor do Projeto: VP MAX = 11 Pontos          */
/*                          VP = NP + OPC;              */
/*                          if(VP>10)                   */
/*                             VP = 10;                 */
/********************************************************/
/*              Data de Entrega: 09/05/2013             */
/********************************************************/

#ifndef PRINCIPAL_H_INCLUDED
#define PRINCIPAL_H_INCLUDED

#include "funclist.h"

void menu();
void executaOpcao(int opcao, No *head);
void menuNavegar();
void executaOpcaoNavegar(No* head);
int main ();

#endif // PRINCIPAL_H_INCLUDED
