package org.usfirst.frc.team4902.robot.commands;

import org.usfirst.frc.team4902.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public Autonomous() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	
    	
    	
    	/*
    	 * DB String 1: Robot Max. Speed(metres / sec)
    	 * DB String 2: Width of field(metres)
    	 * DB String 3: Radius of Gear Deposit Area(metres)
    	 */
    	Double MaxSpeed = Double.parseDouble(SmartDashboard.getString("DB/String 1", "1.0"));
    	Double HalfDistance = Double.parseDouble(SmartDashboard.getString("DB/String 2", "20.0")) / 2.0;
    	Double DepositAreaRadius = Double.parseDouble(SmartDashboard.getString("DB/String 3", "2.0"));
    	
    	//Drive until we get to half distance - deposit area radius
    	//Maximum 15s allocated for driving, this leaves time for additional maneuvering
    	
    	addSequential(new Autodrive(1.0f, 1.0f, Math.min((HalfDistance - DepositAreaRadius) / MaxSpeed, 15.0)));
    }	
}
