package org.usfirst.frc.team4902.robot.subsystems;

import org.usfirst.frc.team4902.robot.Input;
import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSystem extends Subsystem {
	
	Talon winchController = new Talon(2);

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new Command() {
//			{
//				requires(Robot.winchsystem);
//			}
//			
//			
//			@Override
//			protected void execute() {
//				Robot.winchsystem.setSpeed(Input.getPrimaryInstance().getRightY());
//				System.out.println(Input.getPrimaryInstance().getRightY() + " : " + Robot.winchsystem.getCurrent());
//			}
//			
//			@Override
//			protected boolean isFinished() {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});
	}
	
	public void setSpeed(double speed) {
		winchController.set(speed);
	}
	
	public double getCurrent() {
		return Robot.pdp.getCurrent(0);
	}
	
	public void stop() {
		winchController.set(0);
	}
}
