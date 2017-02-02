package org.usfirst.frc.team4902.robot.subsystems;
import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
*The vision system controls camera input and image processing,
*Mainly for use in autonomous mode and driver aides.
**/
public class VisionSystem {

	private static CameraServer cServer;
	private static UsbCamera cam0;
	private static Mat matIn, matOut;
	private static CvSink iStream;
	private static CvSource oStream;
	private static Thread thread;
	
	public static void start() {
		cServer = CameraServer.getInstance();
		cam0 = cServer.startAutomaticCapture();
//		cam0.setResolution(640, 480);
					
//		matIn = new Mat();
//		matOut = new Mat();
//		
//		iStream = cServer.getVideo();
//		oStream = cServer.putVideo("USB Camera 0", 640, 480);
//		 thread = new Thread(() -> {
//			while (true) {
//				iStream.grabFrame(matIn);
//				oStream.putFrame(matOut);
//			}
//		});
//		 thread.start();
	}
	
}
