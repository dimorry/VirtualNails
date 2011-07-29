package com.wordpress.dimorry.VirtualNails;

/**
 * This class represents the condition that we cannot open the camera hardware
 * successfully. For example, another process is using the camera.
 */
public class CameraHardwareException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1625461354222766257L;

	public CameraHardwareException(Throwable t) {
        super(t);
    }
}
