package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
   private CANSparkMax motor;

   public ElevatorSubsystem() {
      motor = new CANSparkMax(1, MotorType.kBrushless);
      stop();
   }

   public void moveUp() {
      motor.set(0.5);
   }

   public void moveDown() {
      motor.set(-0.5);
   }

   public void stop() {
      motor.set(0);
   }
}