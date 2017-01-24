package org.usfirst.frc.team4902.robot.subsystems;
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
	private static Talon leftMotorController = new Talon(0);
	private static Talon rightMotorController = new Talon(0);
	
	public static void rotateRobot(double degrees)
	{
		
	}
	
	/*
	 * Drive the robot forward and back.
	 * Mainly used for testing as the actual
	 * Driving is done using the move() function
	 * 
	 * Speed - Speed of movement from -1 to 1
	 * -1 moves back at full speed, while 1 moves
	 * forward at full speed.
	 */
	public static void driveForwardBack(double speed)
	{
		leftMotorController.setSpeed(speed);
		rightMotorController.setSpeed(speed);
	}
	
	/*
	 * Debug: Sets the speed of the left
	 * and right motors
	 * 
	 * left = left speed, -1 to 1
	 * right = right motor speed, -1 to 1
	 */
	public static void setMotorSpeeds(double left, double right) 
	{
		leftMotorController.set(left);
		rightMotorController.set(right);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
