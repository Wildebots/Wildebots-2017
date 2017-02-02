package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Autodrive extends Command {
	
	private double leftspeed, rightspeed, duration;
	
	/**
	 * @param speed the speed of movement
	 * @param duration the amount of time in seconds to drive
	 */
	public Autodrive(double leftspeed, double rightspeed, double duration) {
		requires(Robot.driveSystem);
		this.leftspeed = leftspeed;
		this.rightspeed = rightspeed;
		this.duration = duration;
	}
	
	@Override
	protected void initialize() {
		setTimeout(duration);
		Robot.driveSystem.getDrive().tankDrive(leftspeed, rightspeed);
	}
	
	@Override
	protected void end() {
		Robot.driveSystem.getDrive().tankDrive(0, 0);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
