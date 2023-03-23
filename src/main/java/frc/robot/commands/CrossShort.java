package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class CrossShort extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_swerve;
    private double m_startTime = 0;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public CrossShort(DriveSubsystem swerve) {
        m_swerve = swerve;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(swerve);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      m_startTime = Timer.getFPGATimestamp();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_swerve.drive(-.1, 0, 0, true, true);
    }
  
    public double getTime() {
      return Timer.getFPGATimestamp() - m_startTime;
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if(getTime() > 2.0)
        {
          return true;
        }
        return false;
    }
}
