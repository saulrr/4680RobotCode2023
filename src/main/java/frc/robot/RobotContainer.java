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
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;



public class RobotContainer {
    
private final static SwerveDrive swerveDrive = new SwerveDrive();

private final Joystick driverJoytick = new Joystick(OIConstants.kDriverControllerPort);
public static XboxController drivecController = new XboxController(0);
    public static XboxController shooController = new XboxController(1);

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
    
    if(driverJoytick.getRawButtonPressed(XboxController.Button.kLeftBumper.value)){
        swerveDrive.zeroHeading();
    }
    
}
}
