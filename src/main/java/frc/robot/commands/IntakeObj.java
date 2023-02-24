package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class IntakeObj extends CommandBase{
    private final Intake m_intake;
    private String collecting;

    public IntakeObj(Intake m_Intake){
        m_intake = m_Intake;
        addRequirements(m_intake);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotContainer.shootController.getLeftBumperPressed()){
        m_intake.setIntakeSpeed(0.75);
            collecting = "Releasing";
        } else if (RobotContainer.shootController.getLeftBumperReleased()){
            m_intake.setIntakeSpeed(0);
            collecting = "Not moving";
        }
        if(RobotContainer.shootController.getRightBumperPressed()){
            m_intake.setIntakeSpeed(-0.75);
            collecting = "Collecting";
            } else if (RobotContainer.shootController.getRightBumperReleased()){
                m_intake.setIntakeSpeed(0);
                collecting = "Not moving";
            }
        SmartDashboard.putString("Intake", collecting);
    }

    @Override
    public boolean isFinished() {
      return false;
    }
}
