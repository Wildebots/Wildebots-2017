package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Input;
import org.usfirst.frc.team4902.robot.Robot;
import org.usfirst.frc.team4902.robot.subsystems.MailboxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class MailboxCommand extends Command {
	
	public MailboxCommand() {
		requires(Robot.mailbox);
	}
	
	@Override
	protected void execute() {
//		Robot.mailbox.setSpeed(Input.getSecondaryInstance().getRightY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
