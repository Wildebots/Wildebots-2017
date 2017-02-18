package org.usfirst.frc.team4902.robot.subsystems;

import org.usfirst.frc.team4902.robot.commands.MailboxCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MailboxSystem extends Subsystem {
	
	private Talon motor = new Talon(16/*TEMPORARY_CHANNEL*/);

	private Encoder encoder = new Encoder(0, 0); //TEMPORARY_VALUES
	
	public static final double MAX_ENCODER_VALUE = Double.MAX_VALUE; //TEMPORARY_VALUE
	
	public static final double PID_TOLERANCE = 0.05;
	
	private PIDController controller = new PIDController(1, 1, 1, encoder, motor); //ADJUST PID VALUES
	
	public MailboxSystem() {
		controller.setInputRange(0, MAX_ENCODER_VALUE);
	}
	
	public PIDController getPIDController() {
		return controller;
	}
	
	public void setSpeed(double speed) {
		motor.set(speed);
	}
	
	public void setTarget(double val) {
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
		setDefaultCommand(new MailboxCommand());
	}

}
