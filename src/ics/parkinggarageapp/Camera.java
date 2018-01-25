package ics.parkinggarageapp;

import java.util.Objects;

/**
 * A Camera can be used to capture an image and share that data.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-11-30: Initial version of Camera class</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.00
 * @since 1.8
 */
public class Camera {
    private static int cameraNum;
    private String cameraID;
    private String data;
    
    /**
     * Constructor to create a Camera object
     */
    public Camera(){
        cameraNum++;
        setCameraID("CAM" + cameraNum);
    }
    
    /**
     * Captures the image/data
     * 
     * @param data a <code>String</code> of data
     */
    public final void capture(String data){
        setData(data);
    }

    public final static int getCameraNum() {
        return cameraNum;
    }

    public final static void setCameraNum(int cameraNum) throws IllegalArgumentException {
        if(cameraNum >= 0){
            Camera.cameraNum = cameraNum;
        } else {
            throw new IllegalArgumentException("Invalid ID number");
        }
    }

    public final String getCameraID() {
        return cameraID;
    }

    public final void setCameraID(String cameraID) throws IllegalArgumentException {
        if(cameraID != null && cameraID.length() > 0 && cameraID.startsWith("CAM")){
            this.cameraID = cameraID;
        } else {
            throw new IllegalArgumentException("Sorry, that ID is invalid");
        }
    }

    public final String getData() {
        return data;
    }

    public final void setData(String data) {
        if(data != null && data.length() > 0){
            this.data = data;
        } else {
            throw new IllegalArgumentException("Sorry, invalid data");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.cameraID);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Camera other = (Camera) obj;
        if (!Objects.equals(this.cameraID, other.cameraID)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Camera ID = " + cameraID;
    }
}