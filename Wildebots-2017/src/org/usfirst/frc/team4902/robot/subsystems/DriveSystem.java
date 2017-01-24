package org.usfirst.frc.team4902.robot.subsystems;
import org.usfirst.frc.team4902.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *The drive system controls the motors responsible for driving the robot
 *Interacts with move commands and any additional commands that
 *Affect movement
 */
public class DriveSystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Talon leftMotorController = new Talon(0);
	private Talon rightMotorController = new Talon(1);
	
	private RobotDrive drive = new RobotDrive(leftMotorController, null, rightMotorController, null);
	
	public RobotDrive getDrive() {
		return drive;
	}
		
	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}
}
