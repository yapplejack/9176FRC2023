package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.TestIntakeBal;
import java.lang.Math;

public class TestIntakeBal extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final IntakeSubsystem m_intake;
    private final DriveSubsystem m_swerve;
    private double m_startTime = 0;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TestIntakeBal(IntakeSubsystem intake, DriveSubsystem swerve) {
        m_intake = intake;
        m_swerve = swerve;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(intake, swerve);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      m_startTime = Timer.getFPGATimestamp();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_intake.setIntakeMotor(-.30, 5);

    }
  
    public double getTime() {
      return Timer.getFPGATimestamp() - m_startTime;
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //after we find the robot has been titled we will call another commandBase that drives until level
        m_intake.setIntakeMotor(0, 25);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //instead of end condition being time, end condition 
        //should be pitch > than a value
        if(m_swerve.getPitch() <= Math.abs(4)){
          return true;
        }
        return false;
    }
}
