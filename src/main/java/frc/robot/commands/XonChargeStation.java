package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Timer;


public class XonChargeStation extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_swerve;
    private double m_startTime = 0;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public XonChargeStation(DriveSubsystem swerve) {
        m_swerve = swerve;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(swerve);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      m_startTime = Timer.getFPGATimestamp();
    }

    public double getTime() {
        return Timer.getFPGATimestamp() - m_startTime;
      }
  
    // Called every time the scheduler runs while the command is scheduled.
    //Speed should be slowed down
    @Override
    public void execute() {
        m_swerve.setX();
    }
  
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    // isFinished waits for the charge station to be balanced
    @Override
    public boolean isFinished() {
        if(getTime() >= 15.0f){
            return true;
          }
          return false;
    }
}
