/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.gui;

/**
 *
 * @author 12647202
 */
public enum EnumMsg {
    
    Cadastrar("Cadastrar"),
    CadastrarSucesso("Cadastrado com Sucesso"),
    CadastrarErro("Erro ao cadastrar"),
    Alterar("Alterar"),
    AlterarSucesso("Alterado com Sucesso"),
    AlterarErro("Erro ao alterar"),
    Excluir("Excluir"),
    ConfirmarExclusao("Deseja realmente excluir?"),
    ExcluirSucesso("Excluido com Sucesso"),
    ExcluirErro("Erro ao excluir");
    
    private String msg;
    
    EnumMsg(String msg)
    {
        this.msg = msg;
    }
    
    public String getmsg()
    {
        return msg;
    }
}
