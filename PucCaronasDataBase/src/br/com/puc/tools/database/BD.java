/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.tools.database;
/*
 Classe....:	BD
 Descrição.:	Classe com métodos para:
 - conexão ao banco de dados
 - execução de consulta SQL 
 - comandos SQL (insert, update, delete) 
 */

import java.sql.*;

public class BD {
    //declaração das variáveis de conexão e de execução dos comandos

    private Connection conexao;
    private Statement comando;
    //declaração de constantes para conexão com banco de dados Oracle
    private String DRIVER       = "oracle.jdbc.driver.OracleDriver";
    
//    private String STRCON 	= "jdbc:oracle:thin:@fs1-ceatec:1521:ORCL";   
//    private String USER 	= "tpw45";
//    private String PASS 	= "bgw85fw";
    
    private String STRCON = "jdbc:oracle:thin:@localhost:1521:XE";
    private String USER = "tpw";
    private String PASS = "tpw";

    //construtor para conexão com parâmetros
    public BD(String drv, String strCon, String usr, String senha) throws Exception {
        try {
            Class.forName(drv);
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver de conexão JDBC não encontrado!");
        }

        try {
            conexao = DriverManager.getConnection(strCon, usr, senha);
        } catch (SQLException e) {
            throw new Exception("String de conexão, usuário ou senha incorretos!");
        }

        try {
            comando = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new Exception("Falha na execução do comando de conexão!");
        }
    }

    //construtor para conexão sem parâmetros (utiliza as constantes criadas)
    public BD() throws Exception {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver de conexão JDBC não encontrado!");
        }

        try {
            Thread.sleep(25);
            conexao = DriverManager.getConnection(STRCON, USER, PASS);
        } catch (SQLException e) {
            throw new Exception("String de conexão, usuário ou senha incorretos!");
        }

        try {
            comando = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new Exception("Falha na execução do comando de conexão!");
        }
    }

    //método para execução dos comandos: INSERT, UPDATE, DELETE
    public boolean execComando(String cmdSQL) throws Exception {
        try {
            this.comando.executeUpdate(cmdSQL);
            Thread.sleep(25);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //método para execução de consultas (comando SELECT)
    public ResultSet execConsulta(String qrySQL) throws Exception {
        ResultSet resultado = null;

        try {
            resultado = this.comando.executeQuery(qrySQL);
        } catch (SQLException e) {
            throw new Exception("Falha na execução do comando de consulta!");
        }
        Thread.sleep(25);
        return resultado;
    }

    //método para fechar a conexão
    public void fechar() throws Exception {
        try {
            this.comando.close();
            this.comando = null;

            this.conexao.close();
            this.conexao = null;
        } catch (SQLException e) {
            throw new Exception("Falha ao fechar conexão!");
        }
    }
}