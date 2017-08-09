/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lizhinian
 */
public class MethodUtil {

    public static void sleepNMilliseconds(long millis) {
	try {
	    Thread.sleep(millis);
	} catch (InterruptedException ex) {
	    Logger.getLogger(MethodUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
