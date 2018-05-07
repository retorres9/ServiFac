/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author rober
 */
public class Utilidades {
    String host = "unknown";
    public void getPcName() {
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            host = addr.getHostName();
            System.out.println(host);
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
    }
    
}
