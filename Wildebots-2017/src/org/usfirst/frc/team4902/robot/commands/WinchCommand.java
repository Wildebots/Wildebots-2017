package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchCommand extends Command {
	
	public WinchCommand() {
		requires(Robot.winchsystem);
	}

	@Override
	protected boolean isFinished() {
//		return isTimedOut();
		return isTimedOut() && Robot.winchsystem.getCurrent() > 2;
	}
	
	@Override
	protected void initialize() {
		Robot.winchsystem.setSpeed(Double.parseDouble(SmartDashboard.getString("DB/String 1", "0")));
		setTimeout(1);
	}
	
	@Override
	protected void execute() {
		System.out.println(Robot.winchsystem.getCurrent());
	}
	
	@Override
	protected void end() {
		Robot.winchsystem.setSpeed(0);
	}
}
