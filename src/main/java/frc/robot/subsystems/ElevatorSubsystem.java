package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ElevatorSubsystem extends SubsystemBase {
   protected CANSparkMax leftMotor;
   protected CANSparkMax rightMotor;
   public RelativeEncoder leftEncoder;
   public RelativeEncoder rightEncoder;
       
   public final double kP = 0.5; // TODO - Tune this
   public final double kI = 0.0; // TODO - Tune this
   public final double kD = 0.15; // TODO - Tune this
   public double iLimit = 1; 

   public double setpoint = 1;
   public double errorSum = 0;
   public double lastTimestamp = 0;
   public double lastError = 0;

   public int selectGamepiece = 1; // 1 = Cube, 2 = Cone, defaults to cube
   
   public ElevatorSubsystem() {
      leftMotor = new CANSparkMax(9, MotorType.kBrushless);
      rightMotor =  new CANSparkMax(10, MotorType.kBrushless);
      
      leftMotor.setSmartCurrentLimit(5);
      leftMotor.setIdleMode(IdleMode.kBrake);
      
      rightMotor.setSmartCurrentLimit(5);
      rightMotor.setIdleMode(IdleMode.kBrake);
      
      leftEncoder = leftMotor.getEncoder();
      
      //stop(); //wasn't sure if this was needed
   }
   
   @Override
   public void periodic(){
      SmartDashboard.putNumber("elevator encoder: ", leftEncoder.getPosition());
      SmartDashboard.putNumber("Selected Gampeiece", selectGamepiece);

      if(RobotContainer.shootController.getBackButtonPressed()){
         selectGamepiece = 1;
      }
      
      if(RobotContainer.shootController.getStartButtonPressed()){
         selectGamepiece = 2;
      }
   }

   public void move(double speed) {
      leftMotor.set(speed);
      rightMotor.set(-speed);
   }

   //

   public void stop() {
      leftMotor.set(0);
      rightMotor.set(0);
   }
}