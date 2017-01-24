package org.usfirst.frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4902.robot.subsystems.*;
/**
 *
 */
public class DriveCommand extends Command {
	Joystick joystick = new Joystick(0);
	
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		DriveSystem.setMotorSpeeds(joystick.getY() - joystick.getX(), joystick.getY() + joystick.getX());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}