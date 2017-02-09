package org.usfirst.frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSystem extends Subsystem {

	Talon winchController = new Talon(4);

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void startWinch(double speed) {
		winchController.set(speed);
	}
	
	public void update() {
		
	}
}
