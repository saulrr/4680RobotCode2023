package frc.robot.subsystems;





import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
   protected CANSparkMax motor;
    //private Encoder encoder = new Encoder(0, 1, true, EncodingType.k4X);
    //public RelativeEncoder encoder = motor.getEncoder();
    //private final double kDriveTick2Feet = 1.0/128 * 6 * Math.PI / 12;

   public ElevatorSubsystem() {
      motor = new CANSparkMax(12, MotorType.kBrushless);
      motor.setSmartCurrentLimit(20);
      motor.setIdleMode(IdleMode.kBrake);
      
      stop();
   }
   @Override
   public void periodic(){
    
   }
   public void getEncode(){
      motor.getEncoder();
   }

   public void move(double speed) {
      motor.set(speed);
   }

   public void moveDown(double speed) {
      motor.set(-speed);
   }

   public void stop() {
      motor.set(0);
   }
}