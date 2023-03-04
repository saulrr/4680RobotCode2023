package frc.robot.subsystems;





import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
   protected CANSparkMax motor;
   protected CANSparkMax motor2;
    //private Encoder encoder = new Encoder(0, 1, true, EncodingType.k4X);
    public RelativeEncoder encoder;
    
    //public final double kDriveTick2Feet = 1.0/128 * 6 * Math.PI / 12;
    
    public final double kP = 0.5; // TODO - Tune this
    public final double kI = 0.0; // TODO - Tune this
    public final double kD = 0.15; // TODO - Tune this
    public double iLimit = 1;

    public double setpoint = 1;
    public double errorSum = 0;
    public double lastTimestamp = 0;
    public double lastError = 0;

   
   public ElevatorSubsystem() {
      motor = new CANSparkMax(9, MotorType.kBrushless);
     motor2 =  new CANSparkMax(10, MotorType.kBrushless);
      motor.setSmartCurrentLimit(5);
      motor.setIdleMode(IdleMode.kBrake);
      
      motor2.setSmartCurrentLimit(5);
      motor2.setIdleMode(IdleMode.kBrake);
      encoder = motor.getEncoder();
      stop();
   }
   @Override
   public void periodic(){
    SmartDashboard.putNumber("elevator encoder val: ", encoder.getPosition());
   }
   

   public void move(double speed) {
      motor.set(speed);
      motor2.set(-speed);
   }

   public void moveDown(double speed) {
      motor.set(-speed);
   }

   public void stop() {
      motor.set(0);
   }
}