package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
   private CANSparkMax motor;
   private static final int CLIMBER_CAN_ID = 14;
   TalonFX motor2;

   public ElevatorSubsystem() {
    motor2 = new TalonFX(CLIMBER_CAN_ID);
    motor2.setNeutralMode(NeutralMode.Brake);
      motor = new CANSparkMax(1, MotorType.kBrushless);
      stop();
   }

   public void moveUp(double speed) {
      motor2.set(ControlMode.PercentOutput  ,speed);
   }

   public void moveDown(double speed) {
      motor2.set(ControlMode.PercentOutput, -speed);
   }

   public void stop() {
      motor2.set(ControlMode.PercentOutput, 0);
   }
}