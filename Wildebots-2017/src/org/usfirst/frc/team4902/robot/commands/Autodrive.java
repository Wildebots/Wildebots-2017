package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Autodrive extends Command {
	
	private double leftspeed, rightspeed, duration, startTime;
	
	/**
	 * @param speed the speed of movement
	 * @param duration the amount of time in seconds to drive
	 */
	public Autodrive(double leftspeed, double rightspeed, double duration) {
		requires(Robot.driveSystem);
		this.leftspeed = leftspeed;
		this.rightspeed = rightspeed;
		this.duration = duration;
		this.startTime = System.currentTimeMillis();
	}
	
	private double QuickCosineInterpolation(double val1, double val2, double mu)
	{
		return ((val1 * (1.0 - ((1.0 - Math.cos(mu * Math.PI)) / 2.0))) + (val2 * ((1.0 - Math.cos(mu * Math.PI)) / 2.0)));
	}
	
	
	@Override
	protected void initialize() {
		setTimeout(duration);
	}
	
	@Override
	protected void execute() {
		Robot.driveSystem.getDrive().tankDrive(QuickCosineInterpolation(0.2, leftspeed, (System.currentTimeMillis() - startTime) / (duration * 500)), QuickCosineInterpolation(0.2, rightspeed, (System.currentTimeMillis() - startTime) / (duration * 500)));
	}
	
	@Override
	protected void end() {
		Robot.driveSystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
