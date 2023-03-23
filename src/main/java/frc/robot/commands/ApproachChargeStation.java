package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import java.lang.Math;

public class ApproachChargeStation extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_swerve;
    private double m_startTime = 0;
    private final double pitch = 8.0d;  
    /**
     * Intializes when the constructor is called.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ApproachChargeStation(DriveSubsystem swerve) {
        m_swerve = swerve;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(swerve);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_swerve.drive(-.1, 0, 0, true, true);
    }
  
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //after we find the robot has been titled we will call another commandBase that drives until level
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //instead of end condition being time, end condition 
        //should be pitch > than a value
        if(Math.abs(m_swerve.getPitch()) >= pitch){
          return true;
        }
        return false;
    }
}
