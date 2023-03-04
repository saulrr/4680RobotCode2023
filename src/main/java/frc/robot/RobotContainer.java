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
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IntakeObj;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.ElevatorCommand;
//import frc.robot.commands.ElevatorState;
import frc.robot.commands.ZeroHeading;



public class RobotContainer {
    
public static final ElevatorSubsystem elevator = new ElevatorSubsystem();
public final static SwerveDrive swerveDrive = new SwerveDrive();
public final static Intake intake = new Intake();

private final ElevatorCommand elevatorcomup = new ElevatorCommand(RobotContainer.elevator, 1);
private final ElevatorCommand elevatorcomdown = new ElevatorCommand(RobotContainer.elevator, 0);
//private final ElevatorState elevatorState = new ElevatorState();
//private final ElevatorCommand elevatorCommandHigh = new ElevatorCommand(RobotContainer.Elevator, 3);
//private final ElevatorCommand elevatorCommandMiddle = new ElevatorCommand(RobotContainer.Elevator, 2);
//private final ElevatorCommand elevatorCommandLow = new ElevatorCommand(RobotContainer.Elevator, 1);
// private final ElevatorCommand elevatorCommandLowCone = new ElevatorCommand(RobotContainer.Elevator, 1);
private final Command m_zeroHeading = new ZeroHeading();
private final IntakeObj intakecommand = new IntakeObj(RobotContainer.intake);

//private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
public static XboxController driveController = new XboxController(0);
public static XboxController shootController = new XboxController(1);

public RobotContainer(){
    
    swerveDrive.setDefaultCommand(new SwerveJoystickCmd(swerveDrive, 
        // ()-> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis), 
        // ()-> driverJoystick.getRawAxis(OIConstants.kDriverXAxis), 
        // ()-> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis), 
        // ()-> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

        ()-> -driveController.getRawAxis(OIConstants.kDriverYAxis), //Y axis left stick drive controller 
        ()-> driveController.getRawAxis(OIConstants.kDriverXAxis), //x axis left stick drive controller
        ()-> driveController.getRawAxis(OIConstants.kDriverRotAxis), //x axis right stick drive controller
        ()-> !driveController.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));


    configureButtonBindings();
}



    //controllers
    


private void configureButtonBindings(){
    
   
    new JoystickButton(driveController, XboxController.Button.kLeftBumper.value).onTrue(m_zeroHeading);
    //new JoystickButton(shootController, XboxController.Button.kY.value).onTrue(elevatorcom);
    new JoystickButton(shootController, XboxController.Button.kB.value).onTrue(elevatorcomdown);
    new JoystickButton(shootController, XboxController.Button.kX.value).onTrue(elevatorcomup);
   // new JoystickButton(shootController, XboxController.Button.kBack.value).onTrue(elevatorcom);
   // new JoystickButton(shootController, XboxController.Button.kStart.value).onTrue(elevatorcom);
   /* new JoystickButton(shootController, XboxController.Button.kStart.value).onTrue(new InstantCommand(() -> {
        elevatorCommandHigh.SetMode(1, "Cone");
        elevatorCommandMiddle.SetMode(1, "Cone");
        elevatorCommandLow.SetMode(1, "Cone");
    }));
    new JoystickButton(shootController, XboxController.Button.kBack.value).onTrue(new InstantCommand(() -> {
        elevatorCommandHigh.SetMode(0, "Box");
        elevatorCommandMiddle.SetMode(0, "Box");
        elevatorCommandLow.SetMode(0, "Box");
    }));
    */
    new JoystickButton(shootController, XboxController.Button.kLeftBumper.value).onTrue(intakecommand);
    new JoystickButton(shootController, XboxController.Button.kRightBumper.value).onTrue(intakecommand);
}
}
