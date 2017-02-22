package org.usfirst.frc.team4902.robot.subsystems;

import org.usfirst.frc.team4902.robot.Input;
import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSystem extends Subsystem {
	
	private Spark winchController = new Spark(1);

	@Override	
	protected void initDefaultCommand() {
		
		//For testing and manual control of winch with joystick
		
		setDefaultCommand(new Command() {
			{
				requires(Robot.winchsystem);
			}
			
			
			@Override
			protected void execute() {
				Robot.winchsystem.setSpeed(Input.getSecondaryInstance().getLeftY());
				if (Robot.winchsystem.getCurrent() > 0) 
					System.out.println(Input.getSecondaryInstance().getLeftY() + " : " + Robot.winchsystem.getCurrent());
			}
			
			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	
	public void setSpeed(double speed) {
		winchController.set(speed);
	}
	
	public double getCurrent() {
		return Robot.pdp.getCurrent(6);
	}
	
	public void stop() {
		winchController.set(0);
	}
}
