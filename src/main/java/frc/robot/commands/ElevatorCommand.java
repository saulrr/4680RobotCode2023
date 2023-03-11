package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends CommandBase {
    private final ElevatorSubsystem elevator;
    //private Timer timer = new Timer();
    private int position; //Symbolic elevator position where 1 = ground intake, etc.
    private double setpoint;  //Encoder value related to position   
    private int gamepiece; //1 = Cube, 2 = Cone
    
    //private SparkMaxPIDController m_elevatorPID;
    //public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    private PIDController m_elevatorPID;
    
    public ElevatorCommand(ElevatorSubsystem elevator, int m_position, int selectedGamepiece) {
        this.elevator = elevator;
        m_elevatorPID = new PIDController(elevator.kP, elevator.kI, elevator.kD);
        position = m_position;
        gamepiece = selectedGamepiece;
        addRequirements(RobotContainer.elevator);
        //setName(String.format("ElevatorCommand(%d , %d)", position, gamepiece));
    }


    @Override
    public void initialize(){
        //timer.reset();
        //SwitchControls = 0;
        // elevator.errorSum = 0;
        // elevator.lastTimestamp = Timer.getFPGATimestamp();
        // elevator.lastError = 0;

        // Cube Setpoints - ground intake, low goal, mid goal, high goal, shelf intake
        if (position == 1 && gamepiece == 1) {
            setpoint = 0.7; // Should be about .7 for actual position
        }
        if (position == 2 && gamepiece == 1) {
            setpoint = 15; // TODO
        }
        // if (position == 3 && gamepiece == 1) {
        //     setpoint = 12; // TODO
        // }
        // if (position == 4 && gamepiece == 1) {
        //     setpoint = 15; // TODO
        // }
        // if (position == 5 && gamepiece == 1) {
        //     setpoint = 15; // TODO
        // }

        //Cone Setpoints - see above order
        if (position == 1 && gamepiece == 2) {
            setpoint = 1.3; // should be about 1.2 for actual position
        }
        if (position == 2 && gamepiece == 2) {
            setpoint = 10; // TODO
        }
        // if (position == 3 && gamepiece == 2) {
        //     setpoint = 15; // TODO
        // }
        // if (position == 4 && gamepiece == 2) {
        //     setpoint = 15; // TODO
        // }
        // if (position == 5 && gamepiece == 2) {
        //     setpoint = 15; // TODO
        // }
    }

    @Override
    public void execute() {
        double controlEffort = elevator.arbFF + m_elevatorPID.calculate(elevator.leftEncoder.getPosition(), setpoint); // adds FF input to fight gravity

        elevator.move(controlEffort); 
        //elevator.move(elevator.arbFF);
        //elevator.move(.8);

        SmartDashboard.putNumber("Target Position", position);
        SmartDashboard.putNumber("Target Setpoint", setpoint);
        SmartDashboard.putNumber("PID Setpoint", m_elevatorPID.getSetpoint());
        SmartDashboard.putNumber("PID Error", m_elevatorPID.getPositionError());
        SmartDashboard.putNumber("Control Effort", controlEffort);
                    
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}