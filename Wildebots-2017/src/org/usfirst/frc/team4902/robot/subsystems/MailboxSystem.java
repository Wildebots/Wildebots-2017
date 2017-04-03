package org.usfirst.frc.team4902.robot.subsystems;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MailboxSystem extends Subsystem {
	
	private Spark motor = new Spark(0);

	private Encoder encoder = new Encoder(0, 1);
	
	public static final double MAX_ENCODER_VALUE = 800;
	
	public static final double PID_TOLERANCE = 5;

	//0.1 0 0.02
	
	private PIDController controller = new PIDController(0.1, 0, 0.02, encoder, motor); //ADJUST PID VALUES	
	public MailboxSystem() {
		controller.setInputRange(0, MAX_ENCODER_VALUE);
		encoder.setReverseDirection(true);
		controller.setOutputRange(-0.5, 0.5);
//		motor.setInverted(true);
	}
	
	public PIDController getPIDController() {
		return controller;
	}
	
	//Positive: up
	//Negative: down
	public void setSpeed(double speed) {
		//Ensure we're not going past our limits
//		if (encoder.get() > MAX_ENCODER_VALUE && speed > 0) {
//			motor.set(0);
//		} else if (encoder.get() < 0 && speed < 0) {
//			motor.set(0);
//		} else {
//			motor.set(speed);
//		}
		motor.set(speed);
	}
	
	public void resetEncoder() {
		encoder.reset();
		controller.reset();
		
	}
	
	public void setTarget(double val) {
		controller.reset();
		controller.setSetpoint(val);
		controller.enable();
	}
	
	public boolean isPIDDone() {
		return controller.getError() < PID_TOLERANCE;
	}
	
	public void stop() {
		controller.disable();
		motor.set(0);
	}
	
	public int getEncoderValue() {
		return encoder.get();
	}
	
	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new MailboxCommand());
	}

}
