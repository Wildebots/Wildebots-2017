package org.usfirst.frc.team4902.robot;
import java.util.concurrent.atomic.AtomicBoolean;

import org.usfirst.frc.team4902.robot.EventSystem.HandlerType;
import org.usfirst.frc.team4902.robot.commands.Autonomous;
import org.usfirst.frc.team4902.robot.commands.Rotate;
import org.usfirst.frc.team4902.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4902.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static final boolean DEBUG = true;

//	public static OI oi;
	public static DriverStation driverStation = DriverStation.getInstance();
	public static DriveSystem driveSystem = new DriveSystem();
	
	public static AtomicBoolean enabled = new AtomicBoolean(true);
	
	public static ADXRS450_Gyro gyro;
	
//	DriveCommand driveCommand;
	
	@Override
	public void robotInit() {
//		oi = new OI();
		gyro = new ADXRS450_Gyro();
//		VisionSystem.start();
		EventSystem.getInstance().addHandler(() -> driveSystem.driveType.set(!driveSystem.driveType.get()),
				Input.getPrimaryInstance().getButtonY(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> {
			if (enabled.get()) {
				Scheduler.getInstance().disable();
			} else {
				Scheduler.getInstance().enable();
			}
			enabled.set(!enabled.get());
		}, Input.getPrimaryInstance().getButtonX(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> new Rotate(gyro.getAngle()+45).start(),
				Input.getPrimaryInstance().getButtonB(), HandlerType.OnPress);
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
		new Autonomous().start();
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
	public void teleopInit() 
	{
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
		if (DEBUG) SmartDashboard.putData("Gyro", gyro);
	}


	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}