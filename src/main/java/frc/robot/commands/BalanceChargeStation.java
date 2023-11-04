package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class BalanceChargeStation extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveSubsystem m_swerve;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public BalanceChargeStation(DriveSubsystem swerve) {
        m_swerve = swerve;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(swerve);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    //Speed should be slowed down
    @Override
    public void execute() {
        m_swerve.drive(-.09, 0, 0, true, true);
    }
  
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_swerve.drive(0, 0, 0, true, true);
    }
  
    // Returns true when the command should end.
    // isFinished waits for the charge station to be balanced
    @Override
    public boolean isFinished() {
        if(Math.abs(m_swerve.getPitch()) <= 5.0d){
            return true;
          }
          return false;
    }
}
