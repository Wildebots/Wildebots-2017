package org.usfirst.frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSystem extends Subsystem {

	private double cutoff = 0.0;
	Talon winchController = new Talon(4);

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void setSpeed(double speed) {
		winchController.set(speed);
	}
	
	public void stop() {
		winchController.set(0);
	}
	
}
