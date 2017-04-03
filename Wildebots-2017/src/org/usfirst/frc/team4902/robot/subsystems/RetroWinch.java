package org.usfirst.frc.team4902.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RetroWinch extends Subsystem {
	
	private Talon one = new Talon(6), two = new Talon(7);

	@Override
	protected void initDefaultCommand() {}
	
	public void setSpeed(double speed) {
		one.set(speed);
		two.set(speed);
	}
	
	public void stop() {
		one.set(0);
		two.set(0);
	}

}
