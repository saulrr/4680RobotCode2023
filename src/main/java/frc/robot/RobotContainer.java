package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.ZeroHeading;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveDrive;



public class RobotContainer {
    
    public static final ElevatorSubsystem elevator = new ElevatorSubsystem();
    public static final Intake intake = new Intake();
    public static final SwerveDrive swerveDrive = new SwerveDrive(); // was final static?
    
    // Non-setpoint elevator controls
    // private final ElevatorCommand elevatorcomup = new ElevatorCommand(RobotContainer.elevator, 1);
    // private final ElevatorCommand elevatorcomdown = new ElevatorCommand(RobotContainer.elevator, 0);

    // Setpoint elevator controls
    private final ElevatorCommand elevatorHigh = new ElevatorCommand(elevator, 4, elevator.selectGamepiece);
    private final ElevatorCommand elevatorMiddle = new ElevatorCommand(elevator, 3, elevator.selectGamepiece);
    private final ElevatorCommand elevatorLow = new ElevatorCommand(elevator, 2, elevator.selectGamepiece);
    private final ElevatorCommand elevatorGroundIntake = new ElevatorCommand(elevator, 1, elevator.selectGamepiece);
    private final ElevatorCommand elevatorShelfIntake = new ElevatorCommand(elevator, 5, elevator.selectGamepiece);


    private final Command m_zeroHeading = new ZeroHeading();
    
    private final IntakeCommand intakeStop = new IntakeCommand(intake, 0);

    private final IntakeCommand intakeCommand = new IntakeCommand(intake,1);
    private final IntakeCommand holdCommand = new IntakeCommand(intake, 2);
    private final IntakeCommand scoreCommand = new IntakeCommand(intake, 3);

    // private final IntakeCommand cubeIntakeCommand = new IntakeCommand(intake,1);
    // private final IntakeCommand cubeHoldCommand = new IntakeCommand(intake, 2);
    // private final IntakeCommand cubeScoreCommand = new IntakeCommand(intake, 3);

    // private final IntakeCommand coneIntakeCommand = new IntakeCommand(intake, 1);
    // private final IntakeCommand coneHoldCommand = new IntakeCommand(intake, 2);
    // private final IntakeCommand coneScoreCommand = new IntakeCommand(intake, 3);

    

    //private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
    public static XboxController driveController = new XboxController(0);
    public static CommandXboxController operatorController = new CommandXboxController(1);
    
    //public static XboxController operatorController = new XboxController(1);

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
        
        elevator.setDefaultCommand(elevatorGroundIntake);
        
        configureButtonBindings();

    }

    private void configureButtonBindings(){
      
        new JoystickButton(driveController, XboxController.Button.kLeftBumper.value).onTrue(m_zeroHeading);

        //Elevator positioning
        operatorController.a().onTrue(elevatorGroundIntake); //pos 1
        operatorController.x().onTrue(elevatorLow); //pos 2
        operatorController.y().onTrue(elevatorMiddle); //pos 3
        operatorController.b().onTrue(elevatorHigh); //pos 4
        operatorController.rightBumper().onTrue(elevatorShelfIntake); //pos 5

        //Intake logic
        operatorController.rightTrigger().whileTrue(intakeCommand).onFalse(intakeStop);
        operatorController.leftTrigger().whileTrue(scoreCommand).onFalse(intakeStop);

        // operatorController.rightTrigger().onTrue(intakeCommand.andThen(holdCommand));
        // operatorController.leftTrigger().whileTrue(scoreCommand).onFalse(intakeStop);
        
        //intake/score command logic
        // if(elevator.selectGamepiece == 1){
        //     operatorController.rightTrigger().whileTrue(cubeIntakeCommand); //simple intake command
        //     //operatorController.rightTrigger().whileTrue(cubeIntakeCommand.andThen(cubeHoldCommand));
        // }
        // if(elevator.selectGamepiece == 2){
        //     coneIntakeCommand.andThen(coneHoldCommand);
        // }

        // operatorController.leftTrigger().whileTrue(
        //     if(){

        //     }
        //     if(){

        //     }
        // );
        
        // new JoystickButton(operatorController, XboxController).onTrue(elevatorGroundIntake);
        // new JoystickButton(operatorController, XboxController.Button.kX.value).onTrue(elevatorLow);
        // new JoystickButton(operatorController, XboxController.Button.kY.value).onTrue(elevatorMiddle);
        // new JoystickButton(operatorController, XboxController.Button.kB.value).onTrue(elevatorHigh);
        // new JoystickButton(operatorController, XboxController.Button.kRightBumper.value).onTrue(elevatorShelfIntake);
        // new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value).whileTrue(intakeCommand)

    //TODO Fix intake commands?
    //new JoystickButton(operatorController, XboxController.Button.kRightBumper.value).onTrue(intakeCommand());
    //new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value).onTrue(elevatorcom);
    
    // new JoystickButton(operatorController, XboxController.Button.kStart.value).onTrue(new InstantCommand(() -> {
    //     elevatorCommandHigh.SetMode(1, "Cone");
    //     elevatorCommandMiddle.SetMode(1, "Cone");
    //     elevatorCommandLow.SetMode(1, "Cone");
    // }));
    // new JoystickButton(operatorController, XboxController.Button.kBack.value).onTrue(new InstantCommand(() -> {
    //     elevatorCommandHigh.SetMode(0, "Box");
    //     elevatorCommandMiddle.SetMode(0, "Box");
    //     elevatorCommandLow.SetMode(0, "Box");
    // }));
    
    // new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value).onTrue(intakecommand);
    // new JoystickButton(operatorController, XboxController.Button.kRightBumper.value).onTrue(intakecommand);
    }
}
