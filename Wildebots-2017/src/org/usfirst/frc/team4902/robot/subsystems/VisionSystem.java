package org.usfirst.frc.team4902.robot.subsystems;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionPipeline;

/**
*The vision system controls camera input and image processing,
*Mainly for use in autonomous mode and driver aides.
**/
public class VisionSystem {

	private static CameraServer cServer;
	private static UsbCamera cam0;
	
	public static class VPipeline implements VisionPipeline {

		private Mat result;
		
		@Override
		public void process(Mat image) {
			//Processing
//			Imgproc.line(image, new Point(0, 0), new Point(image.width(), image.height()), new Scalar(255));
//			Imgproc.GaussianBlur(image, image, new Size(5, 5), 0);
//			Imgproc.Canny(image, image, 100, 200);
			Mat thresh = new Mat(), hierarchy = new Mat();
			List<MatOfPoint> points = new ArrayList<>();
			Imgproc.threshold(image, thresh, 200, 255, Imgproc.THRESH_BINARY);
			Imgproc.findContours(thresh, points, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
//			List<MatOfInt> ints = new ArrayList<>();
//			Imgproc.convexHull(points.get(0), ints.get(0));
			Imgproc.drawContours(image, points, 1, new Scalar(255, 0, 0));
			result = image;
			try {
				Thread.currentThread().sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void start() {
		cServer = CameraServer.getInstance();
		cam0 = cServer.startAutomaticCapture();
		
		CvSink sink = cServer.getVideo(cam0);
		
		CvSource source = cServer.putVideo("Final Image", 640, 480);
		
		cam0.setFPS(16);
		
		
		
//		sink.grabFrame(img);
		
//		source.putFrame(img);
		
		new Thread(() -> {
			Mat img = new Mat();
			sink.grabFrame(img);
			source.putFrame(img);
		}).start();
		
//		VisionThread tr = new VisionThread(cam0, new VPipeline(), new Listener<VPipeline>() {
//			CvSource out = cServer.putVideo("Final Image", 640, 480);
//			
//			@Override
//			public void copyPipelineOutputs(VPipeline pipeline) {
//				out.putFrame(pipeline.result);
//			}
//			
//		});
//				
//		tr.start();
//		
	}
}
