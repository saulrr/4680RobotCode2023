package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase{
    private final Intake intake;
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
        if(action == 0){

            SmartDashboard.putString("Intake Status", stopped);
        }
        if(action == 1){
            SmartDashboard.putString("Intake Status", intaking);
        }
        if(action == 2){
            SmartDashboard.putString("Intake Status", holding);
        }
        if(action == 3){
            SmartDashboard.putString("Intake Status", scoring);
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
