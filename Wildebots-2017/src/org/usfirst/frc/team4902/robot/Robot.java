package org.usfirst.frc.team4902.robot;
import java.util.concurrent.atomic.AtomicBoolean;

import org.usfirst.frc.team4902.robot.EventSystem.HandlerType;
import org.usfirst.frc.team4902.robot.commands.DriveCommand;
import org.usfirst.frc.team4902.robot.commands.ExampleCommand;
import org.usfirst.frc.team4902.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4902.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriverStation driverStation = DriverStation.getInstance();
	public static DriveSystem driveSystem = new DriveSystem();
	
	public static AtomicBoolean driveType = new AtomicBoolean(false);
	
	DriveCommand driveCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putBoolean("DB/Button 1", false);
//		chooser.addDefault("Default Auto", new ExampleCommand());
//		SmartDashboard.putData("Auto mode", chooser);
		EventSystem.getInstance().addHandler(() -> driveType.set(!driveType.get()),
				Input.getPrimaryInstance().getButtonY(), HandlerType.OnPress);
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
		Scheduler.getInstance().add(driveCommand);
	}


	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
	}


	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}