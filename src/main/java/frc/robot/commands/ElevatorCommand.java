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
    

    

    public ElevatorCommand(ElevatorSubsystem elevator) {
        this.Elevator = elevator;
        addRequirements(Elevator);
        }
        

    @Override
    public void initialize(){

    }

    @Override
    public void execute() {
        if(RobotContainer.shootController.getBButtonPressed()){
            
                Elevator.moveUp(-Climb_Speed);
                
                Num = 1;
            

            
        }  if(RobotContainer.shootController.getAButtonPressed()){
            if(Num == 3){
                new InstantCommand(() -> Elevator.moveDown(Climb_Speed), Elevator );
                new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
            }
            Elevator.moveUp(Climb_Speed);
            new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
            //FIXME: the timer doesnt really work, after 1 second has passed, all the other commands with a scheduler wont work
            //need to find a way to implement a new timer so it only spins for a certain amount of time
            Num = 2;
        }
        if(RobotContainer.shootController.getRawButton(3)){
            if(Num == 1){
                new InstantCommand(() -> Elevator.moveDown(Climb_Speed), Elevator );
                new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(2).schedule();
            }
            new InstantCommand(() -> Elevator.moveUp(Climb_Speed), Elevator );
            new InstantCommand(() -> Elevator.stop(), Elevator).withTimeout(1).schedule();
            Num = 3;
        }
    }


@Override
public boolean isFinished(){
return false;
}
}