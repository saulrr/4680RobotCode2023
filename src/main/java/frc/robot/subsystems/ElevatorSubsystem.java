package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ElevatorSubsystem extends SubsystemBase {
   private CANSparkMax leftMotor;
   private CANSparkMax rightMotor;
   public RelativeEncoder leftEncoder;
   public RelativeEncoder rightEncoder;
       
   public final double kP = 0.0; // TODO - Tune this second
   public final double kI = 0.0; // TODO - Tune this fourth
   public final double kD = 0.0; // TODO - Tune this third
   public final double arbFF = 0.0; //TODO - Tune this first
   //public double iLimit = 1; //don't need for wpilib

   public double setpoint = 1;
   public double errorSum = 0;
   public double lastTimestamp = 0;
   public double lastError = 0;

   private float kUpperLimitLeftMotor = 3;   //TODO confirm which was is up or down and set
   private float kLowerLimitRightMotor = 0;  //TODO confirm which way is up or down and set

   public int selectGamepiece = 1; // Initialize to 1 for Cube, 2 = Cone, defaults to cube
   
   public ElevatorSubsystem() {
      leftMotor = new CANSparkMax(9, MotorType.kBrushless);
      rightMotor =  new CANSparkMax(10, MotorType.kBrushless);
      
      leftMotor.setSmartCurrentLimit(5);
      leftMotor.setIdleMode(IdleMode.kBrake);
      leftMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
      leftMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
      leftMotor.setSoftLimit(SoftLimitDirection.kForward, kUpperLimitLeftMotor); 
      leftMotor.setSoftLimit(SoftLimitDirection.kReverse, kLowerLimitRightMotor);    
      
      
      rightMotor.setSmartCurrentLimit(5);
      rightMotor.setIdleMode(IdleMode.kBrake);
      
      leftEncoder = leftMotor.getEncoder();
      
      //stop(); //wasn't sure if this was needed
   }
   
   @Override
   public void periodic(){
      SmartDashboard.putNumber("elevator encoder: ", leftEncoder.getPosition());
      SmartDashboard.putNumber("Selected Gampeiece", selectGamepiece);

      if(RobotContainer.operatorController.back().getAsBoolean()){
         selectGamepiece = 1;
      }
      
      if(RobotContainer.operatorController.start().getAsBoolean()){
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