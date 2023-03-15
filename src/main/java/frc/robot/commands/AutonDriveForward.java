package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.commands.ElevatorCommand;

public class AutonDriveForward extends SequentialCommandGroup {
    private final SwerveDrive m_swerveDrive;
    private final ElevatorSubsystem m_elevator;

    public AutonDriveForward(SwerveDrive swerveDrive, ElevatorSubsystem elevator){
m_swerveDrive = swerveDrive;
m_elevator = elevator;
addCommands(new AutoSwerveJoystickCmd(m_swerveDrive, 0.25, 0.0, 0.0, true).withTimeout(1));
addCommands(new ElevatorCommand(m_elevator, 3, 1));   
}

}