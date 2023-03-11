package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends CommandBase {
    private ElevatorSubsystem Elevator;
    private Timer timer = new Timer();
    private int position; //Symbolic elevator position where 1 = ground intake, etc.
    private int setpoint;  //Encoder value related to position   
    private int gamepiece; //1 = Cube, 2 = Cone
    
    //private SparkMaxPIDController m_elevatorPID;
    //public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    private PIDController m_elevatorPID = new PIDController(Elevator.kP, Elevator.kI, Elevator.kD);
    
    public ElevatorCommand(ElevatorSubsystem elevator, int m_position, int selectedGamepiece) {
        this.Elevator = elevator;
        position = m_position;
        gamepiece = selectedGamepiece;
        addRequirements(Elevator);
        setName(String.format("ElevatorCommand(%d , %d)", position, gamepiece));
    }


    @Override
    public void initialize(){
        timer.reset();
        //SwitchControls = 0;
        Elevator.errorSum = 0;
        Elevator.lastTimestamp = Timer.getFPGATimestamp();
        Elevator.lastError = 0;
        
        // Cube Setpoints - ground intake, low goal, mid goal, high goal, shelf intake
        if (position == 1 && gamepiece == 1) {
            setpoint = 1; // TODO Encoder Value
        }
        if (position == 2 && gamepiece == 1) {
            setpoint = 5; // TODO
        }
        if (position == 3 && gamepiece == 1) {
            setpoint = 6; // TODO
        }
        if (position == 4 && gamepiece == 1) {
            setpoint = 7; // TODO
        }
        if (position == 5 && gamepiece == 1) {
            setpoint = 3; // TODO
        }

        //Cone Setpoints - see above order
        if (position == 1 && gamepiece == 2) {
            setpoint = 2; // TODO
        }
        if (position == 2 && gamepiece == 2) {
            setpoint = 6; // TODO
        }
        if (position == 3 && gamepiece == 2) {
            setpoint = 7; // TODO
        }
        if (position == 4 && gamepiece == 2) {
            setpoint = 8; // TODO
        }
        if (position == 5 && gamepiece == 2) {
            setpoint = 4; // TODO
        }
    }

    //
   /*  public void SetMode(int gameObject, String mode) {
        this.SwitchControls = gameObject;
        this.mode = mode;
        SmartDashboard.putString(getName() + " mode", mode);
    }
    */

    @Override
    public void execute() {
        
        Elevator.move(Elevator.arbFF + m_elevatorPID.calculate(Elevator.leftEncoder.getPosition(), setpoint)); // adds FF input to fight gravity
               
        SmartDashboard.putNumber("Target Setpoint", setpoint);
        
        // // if(RobotContainer.operatorController.getBButtonPressed()){
        // //     if(SwitchControls == 1){
        // //         setpoint = 4.2;
        // //     }
        // //     else if(SwitchControls == 0){
        // //         setpoint = 4;
        // //         }
        // // }
        // //  else if(RobotContainer.operatorController.getYButtonPressed()){
        // //     if(SwitchControls == 1){
        // //         setpoint = 2.2;
        // //     }else if(SwitchControls == 0){
        // //     setpoint = 2;
        // //     }
        // // } else if(RobotContainer.operatorController.getXButtonPressed()){
        // //     if(SwitchControls == 1){
        // //         setpoint = 0.9 ;
        // //     } else if(SwitchControls == 0){
        // //     setpoint = 0.7;
        // //     }
        // // }

        // // if(RobotContainer.operatorController.getStartButtonPressed()){
            
        // //     SwitchControls = 1;
        // //     mode = "Cone";
        // // }
        // // if(RobotContainer.operatorController.getBackButtonPressed()){
        // //     SwitchControls = 0;
        // //     mode = "Box";
        // // }

        // double sensorPosition = Elevator.leftEncoder.getPosition();

        // // calculations
        // double error = setpoint - sensorPosition;
        // double dt = Timer.getFPGATimestamp() - Elevator.lastTimestamp;

        // if(Math.abs(error) < Elevator.iLimit){
        //     Elevator.errorSum += error * dt;
        // }

        // double errorRate = (error - Elevator.lastError) / dt;
    
        // double outputSpeed = Elevator.kP * error + Elevator.kI * Elevator.errorSum + Elevator.kD * errorRate;
        
        // Elevator.move(outputSpeed);
        
        // Elevator.lastTimestamp = Timer.getFPGATimestamp();
        // Elevator.lastError = error;
        
        //SmartDashboard.putString("Control Type:", mode);
 
     /*   if(RobotContainer.operatorController.getBButtonPressed()){
            if(Num == 3){
                Elevator.moveDown(Climb_Speed);
                timer.start();
            
                if (timer.hasElapsed(2.0)) {
                    Elevator.stop();
                    timer.stop();
                    timer.reset();
                }
                Num = 1;
            } if(Num == 2){
            Elevator.moveDown(Climb_Speed);
          
            timer.start();
            
            if (timer.hasElapsed(1.0)) {
                Elevator.stop();
                timer.stop();
                timer.reset();
            }
                
                Num = 1;
            }

            
        }  if(RobotContainer.operatorController.getAButtonPressed()){
            if(Num == 3){
                Elevator.moveDown(Climb_Speed);
            timer.start();
            
            if (timer.hasElapsed(1.0)) {
                Elevator.stop();
                timer.stop();
                timer.reset();
            }
                Num = 2;
            } if(Num == 1){
            Elevator.moveUp(Climb_Speed);
            timer.start();
            
            if (timer.hasElapsed(1.0)) {
                Elevator.stop();
                timer.stop();
                timer.reset();
            }
            //FIXME: the timer doesnt really work, after 1 second has passed, all the other commands with a scheduler wont work
            //need to find a way to implement a new timer so it only spins for a certain amount of time
            Num = 2;
        }
        }
        if(RobotContainer.operatorController.getXButtonPressed()){
            if(Num == 1){
                Elevator.moveUp(Climb_Speed);
                timer.start();
            
                if (timer.hasElapsed(2.0)) {
                    Elevator.stop();
                    timer.stop();
                    timer.reset();
                }
                Num = 3;
            } if(Num == 2){
            Elevator.moveUp(Climb_Speed);
            timer.start();
            
            if (timer.hasElapsed(1.0)) {
                Elevator.stop();
                timer.stop();
                timer.reset();
            }
            Num = 3;
        }
        }
    

    if (timer.hasElapsed(1)) {
        timer.reset();
    }
    */
    
}



@Override
public boolean isFinished(){
return false;
}
}