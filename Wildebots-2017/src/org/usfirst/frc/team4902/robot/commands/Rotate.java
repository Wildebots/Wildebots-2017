package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class Rotate extends Command {
	
	private static final double speed = 0.1;
	
	private double target;
	
	private boolean finished = false;
	
	private PIDController controller = new PIDController(2, 0.5, 0.5, Robot.gyro, Robot.driveSystem);
	
	public Rotate(double target) {
		requires(Robot.driveSystem);
		this.target = target%360;
		controller.setOutputRange(0, 0.75);
	}
	
	@Override
	protected void initialize() {
		controller.setSetpoint(target);
		controller.enable();
	}
	
	@Override
	protected void execute() {
//		double angle = Robot.gyro.getAngle() % 360;
//		double diff = target-angle;
//		System.out.println("angle: "+angle+" diff: "+diff +" target: "+target);
//		if (diff > 30 && diff < 30) {
//			finished = true;
//		} else if (diff < 0) {
//			Robot.driveSystem.getDrive().tankDrive(speed, -speed);
//		} else {
//			Robot.driveSystem.getDrive().tankDrive(-speed, speed);
//		}
	}
	
	@Override
	protected void end() {
		controller.disable();
		Robot.driveSystem.stop();
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
