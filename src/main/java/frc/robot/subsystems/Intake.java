package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private CANSparkMax spinny;

    public Intake(){
        spinny = new CANSparkMax(11, MotorType.kBrushless);
        spinny.setSmartCurrentLimit(20);
        spinny.setIdleMode(IdleMode.kCoast);
        spinny.setInverted(true);
    }

    public void setIntakeSpeed(double intakeSpeed) {
        spinny.set(intakeSpeed);
      }

}
