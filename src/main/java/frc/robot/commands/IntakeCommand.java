package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class IntakeCommand extends CommandBase{
    private final Intake intake;
    //private ElevatorSubsystem elevator;
    private String stopped, intaking, holding, scoring;
    private int action; //0 = stop, 1 = intake, 2 = hold, 3 = score

    public IntakeCommand(Intake m_intake, int m_action){
        intake = m_intake;
        action = m_action;
        addRequirements(intake);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute(){
        
        //Cube logic
        if(action == 0 && RobotContainer.elevator.selectGamepiece == 1){
            //SmartDashboard.putString("Intake Status", stopped);
            intake.stop();
        }
        if(action == 1 && RobotContainer.elevator.selectGamepiece == 1){
            //SmartDashboard.putString("Intake Status", intaking);
            intake.intake();
        }
        if(action == 2 && RobotContainer.elevator.selectGamepiece == 1){
            //SmartDashboard.putString("Intake Status", holding);
        }
        if(action == 3 && RobotContainer.elevator.selectGamepiece == 1){
            //SmartDashboard.putString("Intake Status", scoring);
            intake.score();
        } 

        //Cone Logic
        if(action == 0 && RobotContainer.elevator.selectGamepiece == 2){
            //SmartDashboard.putString("Intake Status", stopped);
            intake.stop();
        }
        if(action == 1 && RobotContainer.elevator.selectGamepiece == 2){
            //SmartDashboard.putString("Intake Status", intaking);
            intake.intake();
        }
        if(action == 2 && RobotContainer.elevator.selectGamepiece == 2){
            //SmartDashboard.putString("Intake Status", holding);

        }
        if(action == 3 && RobotContainer.elevator.selectGamepiece == 2){
            //SmartDashboard.putString("Intake Status", scoring);
            intake.score();
        } 

        
        // if(RobotContainer.operatorController.getLeftBumperPressed()){
        // m_intake.setIntakeSpeed(0.75);
        //     collecting = "Releasing";
        // } else if (RobotContainer.operatorController.getLeftBumperReleased()){
        //     m_intake.setIntakeSpeed(0);
        //     collecting = "Not moving";
        // }
        // if(RobotContainer.operatorController.getRightBumperPressed()){
        //     m_intake.setIntakeSpeed(-0.75);
        //     collecting = "Collecting";
        //     } else if (RobotContainer.operatorController.getRightBumperReleased()){
        //         m_intake.setIntakeSpeed(0);
        //         collecting = "Not moving";
        //     }
        //SmartDashboard.putString("Intake", collecting);
    }

    @Override
    public boolean isFinished() {
      return false;
    }
}
