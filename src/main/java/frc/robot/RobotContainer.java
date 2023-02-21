package frc.robot;

import java.util.List;

import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.ZeroHeading;



public class RobotContainer {
    
public static final ElevatorSubsystem Elevator = new ElevatorSubsystem();
public final static SwerveDrive swerveDrive = new SwerveDrive();



private final ElevatorCommand elevatorCommand = new ElevatorCommand(RobotContainer.Elevator);
private final Command m_zeroHeading = new ZeroHeading();

private final Joystick driverJoytick = new Joystick(OIConstants.kDriverControllerPort);
public static XboxController driveController = new XboxController(0);

    public static XboxController shootController = new XboxController(1);

public RobotContainer(){
    
swerveDrive.setDefaultCommand(new SwerveJoystickCmd(swerveDrive, 
()-> -driverJoytick.getRawAxis(OIConstants.kDriverYAxis), 
()-> driverJoytick.getRawAxis(OIConstants.kDriverXAxis), 
()-> driverJoytick.getRawAxis(OIConstants.kDriverRotAxis), 
()-> !driverJoytick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

configureButtonBindings();
}



    //controllers
    


private void configureButtonBindings(){
    
   
    new JoystickButton(driveController, XboxController.Button.kLeftBumper.value).onTrue(m_zeroHeading);
 new JoystickButton(shootController, XboxController.Button.kA.value).onTrue(elevatorCommand);
 new JoystickButton(shootController, XboxController.Button.kB.value).onTrue(elevatorCommand);
 //new JoystickButton(shootController, XboxController.Button.kX.value).whileTrue(elevatorCommand);
}
}
