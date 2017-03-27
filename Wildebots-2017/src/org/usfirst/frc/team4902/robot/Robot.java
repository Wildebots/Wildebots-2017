package org.usfirst.frc.team4902.robot;
import java.util.concurrent.atomic.AtomicBoolean;

import org.usfirst.frc.team4902.robot.EventSystem.HandlerType;
import org.usfirst.frc.team4902.robot.commands.Autodrive;
import org.usfirst.frc.team4902.robot.commands.Autonomous;
import org.usfirst.frc.team4902.robot.commands.WinchCommand;
import org.usfirst.frc.team4902.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4902.robot.subsystems.MailboxSystem;
import org.usfirst.frc.team4902.robot.subsystems.VisionSystem;
import org.usfirst.frc.team4902.robot.subsystems.WinchSystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriverStation driverStation = DriverStation.getInstance();
	public static DriveSystem driveSystem = new DriveSystem();
	public static MailboxSystem mailbox = new MailboxSystem();
	public static WinchSystem winchsystem = new WinchSystem();

	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	public static AtomicBoolean enabled = new AtomicBoolean(true), currentCount = new AtomicBoolean(false);

	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	@Override
	public void robotInit() {
//		VisionSystem.start();
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setVideoMode(new VideoMode(PixelFormat.kMJPEG, 640, 480, 30));
		SmartDashboard.putString("DB/String 1", "1");
		SmartDashboard.putString("DB/String 0", "Robot Enabled");
		SmartDashboard.putString("DB/String 2", "2");
		EventSystem.getInstance().addHandler(() -> driveSystem.driveType.set(!driveSystem.driveType.get()),
				Input.getPrimaryInstance().getRightBumper(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> {
			if (!this.isDisabled()) {
				if (enabled.get()) {
					Scheduler.getInstance().disable();
					SmartDashboard.putString("DB/String 0", "Robot Disabled");
				} else {
					Scheduler.getInstance().enable();
					SmartDashboard.putString("DB/String 0", "Robot Enabled");
				}
				enabled.set(!enabled.get());
			}
		}, Input.getPrimaryInstance().getLeftBumper(), HandlerType.OnPress);
//		EventSystem.getInstance().addHandler(() -> Robot.mailbox.resetEncoder(),
//				Input.getSecondaryInstance().getButtonX(), HandlerType.OnPress);
		//		EventSystem.getInstance().addHandler(() -> new Rotate(gyro.getAngle()+45).start(),
		//				Input.getPrimaryInstance().getButtonB(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> new WinchCommand().start(),
				Input.getPrimaryInstance().getButtonX(), HandlerType.OnPress);

		EventSystem.getInstance().addHandler(() -> mailbox.setTarget(735/*Top*/),
				Input.getPrimaryInstance().getButtonY(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> mailbox.setTarget(300/*Middle*/),
				Input.getPrimaryInstance().getButtonB(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> mailbox.setTarget(0/*Bottom*/),
				Input.getPrimaryInstance().getButtonA(), HandlerType.OnPress);

	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		//		autonomousCommand = chooser.getSelected();
		//
		//		// schedule the autonomous command (example)
		//		if (autonomousCommand != null)
		//			autonomousCommand.start();
//		new Autonomous().start();
		new Autodrive(0.75, 0.75, 5).start();;
		//		Scheduler.getInstance().add(new Autonomous());
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//		if (autonomousCommand != null)
		//		{
		//			autonomousCommand.cancel();
		//		}
		//		
		//		Scheduler.getInstance().add(driveCommand);
	}


	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	/**
	 * @return if the robot is in debug mode or not, defaults to false
	 * enable debug mode by pressing the first button in the basic tab
	 */
	public static boolean isDebug() {
		return SmartDashboard.getBoolean("DB/Button 0", false);
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}