package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    CANSparkMax motor;

   public ElevatorSubsystem() {
      motor = new CANSparkMax(12, MotorType.kBrushless);
      motor.setSmartCurrentLimit(20);
      motor.setIdleMode(IdleMode.kBrake);
      stop();
   }
   @Override
   public void periodic(){
    
   }

   public void moveUp(double speed) {
      motor.set(speed);
   }

   public void moveDown(double speed) {
      motor.set(-speed);
   }

   public void stop() {
      motor.set(0);
   }
}