package org.usfirst.frc.team4902.robot.subsystems;
import java.util.concurrent.atomic.AtomicBoolean;

import org.usfirst.frc.team4902.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *The drive system controls the motors responsible for driving the robot
 *Interacts with move commands and any additional commands that
 *Affect movement
 */
public class DriveSystem extends Subsystem implements PIDOutput {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Talon leftfront = new Talon(0),
			leftback = new Talon(1),
			rightfront = new Talon(4),
			rightback = new Talon(3);
	
	//true = tank
	//false = false
	//arcade by default
	public AtomicBoolean driveType = new AtomicBoolean(false);
	
	private RobotDrive drive = new RobotDrive(leftback, leftfront, rightback, rightfront);
	
	public DriveSystem() {
		leftfront.setInverted(true);
		leftback.setInverted(true);
		rightfront.setInverted(true);
		rightback.setInverted(true);
	}
	
	public void stop() {
		drive.tankDrive(0, 0);
	}
	
	public RobotDrive getDrive() {
		return drive;
	}
		
	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	@Override
	public void pidWrite(double output) {
		drive.tankDrive(-output, output);
	}
}
