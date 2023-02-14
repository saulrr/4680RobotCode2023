package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends CommandBase {
    private static final double Climb_Speed = 1.0;
    private ElevatorSubsystem Elevator;
    private int Num = 1;

    public void ClimbCommand(){
        addRequirements(RobotContainer.Elevator);
    }


    @Override
    public void initialize(){

    }

    @Override
    public void execute() {
        if(RobotContainer.shootController.getXButtonPressed()){
            if(Num == 2){
                new InstantCommand(() -> Elevator.moveDown(Climb_Speed), Elevator );
                new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
                Num = 1;
            }

            
        }  if(RobotContainer.shootController.getYButtonPressed()){
            if(Num == 3){
                new InstantCommand(() -> Elevator.moveDown(Climb_Speed), Elevator );
                new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
            }
            new InstantCommand(() -> Elevator.moveUp(Climb_Speed), Elevator );
            new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
            Num = 2;
        }
        if(RobotContainer.shootController.getBButtonPressed()){
            if(Num == 1){
                new InstantCommand(() -> Elevator.moveDown(Climb_Speed), Elevator );
                new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(2).schedule();
            }
            new InstantCommand(() -> Elevator.moveUp(Climb_Speed), Elevator );
            new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
            Num = 3;
        }
    }
}
