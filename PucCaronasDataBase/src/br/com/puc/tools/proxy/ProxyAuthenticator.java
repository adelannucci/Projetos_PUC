/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.puc.tools.proxy;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author adelannucci
 */
public class ProxyAuthenticator {

    public static String proxyHost = "http.proxyHost";
    public static String proxyPort = "http.proxyPort";
    public static String proxyUser = "http.proxyUser";
    public static String proxyPassword = "http.proxyPassword";
    public static String proxyHostPUC = "proxy1.acd.puc-campinas.edu.br";
    public static String proxyPortPUC = "3128";
    public static String proxyUserPUC = "lab-inf\\";

    
    public static void authenticator(String user, String password)
    {
        Authenticator.setDefault(new Proxy(user, password));
    }
    
    public static void startProxy(String user, String password, String proxyHost, String port) {
        Authenticator.setDefault(new Proxy(user, password));
        System.setProperty(ProxyAuthenticator.proxyHost, proxyHost);
        System.setProperty(ProxyAuthenticator.proxyPort, port);
    }

    public static void startProxy(String user, String password) {
        startProxy(ProxyAuthenticator.proxyUserPUC + user, password, ProxyAuthenticator.proxyHost, ProxyAuthenticator.proxyPortPUC);
    }

    public static void setProxy(String user, String password, String proxyHost, String port) {
        
        
        System.setProperty(ProxyAuthenticator.proxyHost, proxyHost);
        System.setProperty(ProxyAuthenticator.proxyPort, port);
        System.setProperty(ProxyAuthenticator.proxyUser, user);
        System.setProperty(ProxyAuthenticator.proxyPassword, password);
    }

    public static void setProxy(String user, String password) {
        setProxy(ProxyAuthenticator.proxyUserPUC + user, password, ProxyAuthenticator.proxyHostPUC, ProxyAuthenticator.proxyPortPUC);
    }

    private static class Proxy extends Authenticator {

        private String user, password;

        public Proxy(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
}
