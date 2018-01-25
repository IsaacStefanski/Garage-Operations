package ics.parkinggarageapp;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * This ExternalSpecFactory reads in the specifications for building objects
 * from an external text file that has key=value pairs providing the
 * specifications. Objects are built using Java Reflection.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-12-12: Initial version</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.00
 * @since 1.8
 */
public abstract class ExternalSpecFactory {

    public static Camera getCameraInstance() {
        Camera cam = null;

        File file = new File("src" + File.separatorChar + "config.properties");
        Properties props = new Properties();
        FileInputStream inFile;
        try {
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();
        
            String className = props.getProperty("camera");
            Class clazz = Class.forName(className);
            cam = (Camera)clazz.newInstance();
            
        } catch (Exception ex) {
            System.out.println("Error reading config.properties");
        }
        
        return cam;
    }
    
    public static TicketDatabase getTicketDatabaseInstance() {
        TicketDatabase db = null;

        File file = new File("src" + File.separatorChar + "config.properties");
        Properties props = new Properties();
        FileInputStream inFile;
        try {
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();
        
            String className = props.getProperty("ticketdb");
            Class clazz = Class.forName(className);
            db = (TicketDatabase)clazz.newInstance();
            
        } catch (Exception ex) {
            System.out.println("Error reading config.properties");
        }
        
        return db;
    }
}