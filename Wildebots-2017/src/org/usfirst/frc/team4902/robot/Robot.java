package org.usfirst.frc.team4902.robot;
import java.util.concurrent.atomic.AtomicBoolean;

import org.usfirst.frc.team4902.robot.EventSystem.HandlerType;
import org.usfirst.frc.team4902.robot.commands.Autonomous;
import org.usfirst.frc.team4902.robot.commands.Rotate;
import org.usfirst.frc.team4902.robot.commands.WinchCommand;
import org.usfirst.frc.team4902.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4902.robot.subsystems.VisionSystem;
import org.usfirst.frc.team4902.robot.subsystems.WinchSystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	//	public static OI oi;
	public static DriverStation driverStation = DriverStation.getInstance();
	public static DriveSystem driveSystem = new DriveSystem();

	public static AtomicBoolean enabled = new AtomicBoolean(true), currentCount = new AtomicBoolean(false);

	public static ADXRS450_Gyro gyro;
	
	public static PowerDistributionPanel pdp;
	public static WinchSystem winchsystem = new WinchSystem();

	//	DriveCommand driveCommand;

	@Override
	public void robotInit() {
		//		oi = new OI();
		pdp = new PowerDistributionPanel();
		gyro = new ADXRS450_Gyro();
		//		VisionSystem.start();
		SmartDashboard.putString("DB/String 0", "Robot Enabled");
		EventSystem.getInstance().addHandler(() -> driveSystem.driveType.set(!driveSystem.driveType.get()),
				Input.getPrimaryInstance().getButtonY(), HandlerType.OnPress);
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
		}, Input.getPrimaryInstance().getButtonX(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> new Rotate(gyro.getAngle()+45).start(),
				Input.getPrimaryInstance().getButtonB(), HandlerType.OnPress);
		EventSystem.getInstance().addHandler(() -> new WinchCommand().start(),
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