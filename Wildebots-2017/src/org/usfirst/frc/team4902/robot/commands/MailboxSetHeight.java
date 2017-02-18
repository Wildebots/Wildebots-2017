package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MailboxSetHeight extends Command {
	
	double target;
	
	public MailboxSetHeight(double target) {
		requires(Robot.mailbox);
		this.target = target;
	}
	
	@Override
	protected void initialize() {
		Robot.mailbox.setTarget(target);
	}
	
	@Override
	protected void end() {
		Robot.mailbox.stop();
	}

	@Override
	protected boolean isFinished() {
		return Robot.mailbox.isPIDDone();
	}

}
