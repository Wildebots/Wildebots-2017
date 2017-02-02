package org.usfirst.frc.team4902.robot.subsystems;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
*The vision system controls camera input and image processing,
*Mainly for use in autonomous mode and driver aides.
**/
public class VisionSystem {

	private static CameraServer cServer;
	private static UsbCamera cam0;
	private static Mat matIn, matOut, matEdges;
	private static CvSink iStream;
	private static CvSource oStream;
	private static Thread thread;
	
	public static void start() {
		cServer = CameraServer.getInstance();
		cam0 = cServer.startAutomaticCapture();
					
		matIn = new Mat();
		matOut = new Mat();
		
		iStream = cServer.getVideo();
		oStream = cServer.putVideo("USB Camera 0", 640, 480);
		
		thread = new Thread(() -> {
			while (true) {
				iStream.grabFrame(matIn);
				
				Imgproc.GaussianBlur(matIn, matOut, new Size(5.0f, 5.0f), 0.0);
				Imgproc.Canny(matOut, matEdges, 100, 200);
				matOut = matEdges;
				
				oStream.putFrame(matOut);
			}
		});
		
		thread.start();
	}
}
