package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import pabeles.concurrency.ConcurrencyOps;

public class Intake extends SubsystemBase {
    private CANSparkMax intakeMotor1; //determine if right or left motor
    private CANSparkMax intakeMotor2; //determine if right or left motor

    public double cubeIntakeSpeed= 0.5;
    public int cubeIntakeLimit; //current limit
    public double cubeHoldSpeed;
    public int cubeHoldLimit; 

    public double coneIntakeSpeed= 0.2;
    public int coneIntakeLimit = 2;
    public double coneHoldSpeed = .01;
    public int coneHoldLimit = 1;

    public Intake(){
        intakeMotor1 = new CANSparkMax(11, MotorType.kBrushless);
        intakeMotor1.setSmartCurrentLimit(20);
        intakeMotor1.setIdleMode(IdleMode.kCoast);
        intakeMotor2 = new CANSparkMax(12, MotorType.kBrushless);
        intakeMotor2.setSmartCurrentLimit(20);
        intakeMotor2.setIdleMode(IdleMode.kCoast);
    }

    public void intake() {
        intakeMotor1.setSmartCurrentLimit(RobotContainer.elevator.selectGamepiece == 1 ? cubeIntakeLimit : coneIntakeLimit);
        intakeMotor2.setSmartCurrentLimit(RobotContainer.elevator.selectGamepiece == 1 ? cubeIntakeLimit : coneIntakeLimit);
        intakeMotor1.set(RobotContainer.elevator.selectGamepiece == 1 ? cubeIntakeSpeed : coneIntakeSpeed);
        intakeMotor2.set(-RobotContainer.elevator.selectGamepiece == 1 ? cubeIntakeSpeed : coneIntakeSpeed);
    }

    public void hold(){
        intakeMotor1.setSmartCurrentLimit(RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldLimit : coneHoldLimit);
        intakeMotor2.setSmartCurrentLimit(RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldLimit : coneHoldLimit);
        intakeMotor1.set(RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldSpeed : coneHoldSpeed);
        intakeMotor2.set(-RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldSpeed : coneHoldSpeed);
    }
    public void score(){
        intakeMotor1.setSmartCurrentLimit(RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldLimit : coneHoldLimit);
        intakeMotor2.setSmartCurrentLimit(RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldLimit : coneHoldLimit);
        intakeMotor1.set(RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldSpeed : coneHoldSpeed);
        intakeMotor2.set(-RobotContainer.elevator.selectGamepiece == 1 ? cubeHoldSpeed : coneHoldSpeed);
    }

    public void stop(){
        intakeMotor1.set(0);
        intakeMotor2.set(0);
    }

}


