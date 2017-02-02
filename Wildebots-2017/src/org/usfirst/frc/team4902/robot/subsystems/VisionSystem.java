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
public class VisionSystem extends Subsystem {

	private CameraServer cServer;
	private UsbCamera cam0;
	private Mat matIn, matOut;
	private CvSink iStream;
	private CvSource oStream;
	
	public VisionSystem() {
		
		new Thread(() -> {
			cServer = CameraServer.getInstance();
			cam0 = cServer.startAutomaticCapture();
			cam0.setResolution(1280, 720);
			
			matIn = new Mat();
			matOut = new Mat();
			
			iStream = cServer.getVideo();
			oStream = cServer.putVideo("cam0", 1280, 720);
			
			while (true) {
				iStream.grabFrame(matIn);
				oStream.putFrame(matOut);
			}
			
		}).start();
		
	}
	
	public void initDefaultCommand() {
//		setDefaultCommand(new Command() {
//			
//			@Override
//			protected void execute() {
//				iStream.grabFrame(matIn);
//				oStream.putFrame(matOut);
//			}
//
//			@Override
//			protected boolean isFinished() {
//				return false;
//			}
//			
//		});
	}
}
