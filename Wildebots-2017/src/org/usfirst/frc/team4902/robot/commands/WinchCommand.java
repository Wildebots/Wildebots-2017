package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchCommand extends Command {
	
	double amp_break;
	
	public WinchCommand() {
//		requires(Robot.winchsystem);
		amp_break = Double.parseDouble(SmartDashboard.getString("DB/String 2", "2"));
	}

//	@Override
//	protected boolean isFinished() {
//		return isTimedOut();
//		return isTimedOut() && Robot.winchsystem.getCurrent() > amp_break;
//	}
	
	@Override
	protected void initialize() {
//		Robot.winchsystem.setSpeed(Double.parseDouble(SmartDashboard.getString("DB/String 1", "1")));
		setTimeout(1);
	}
	
	@Override
	protected void execute() {
//		System.out.println(Robot.winchsystem.getCurrent());
	}
	
	@Override
	protected void end() {
//		Robot.winchsystem.stop();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
