package org.usfirst.frc.team4902.robot.commands;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;

public class WinchSystem extends Command {

	Talon winchController = new Talon(4);
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
