package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ElevatorCommand extends CommandBase {
    private static final double Climb_Speed = 1.0;

    public void ClimbCommand(){
        addRequirements(RobotContainer.Elevator);
    }


    @Override
    public void initialize(){

    }

    @Override
    public void execute() {
        if(driveController.getPOV() == 0){

        }
    }
}
