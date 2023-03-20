package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.PIDController;

public class AutoBalance extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final SwerveSubsystem m_swerve;
    private double m_startTime = 0;
    PIDController balanceContoller;
    private double BalanceP = 1;
    private double BalanceI = 0;
    private double BalanceD = 0;
  
    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public AutoBalance(SwerveSubsystem swerve) {
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
        balanceContoller = new PIDController(BalanceP, BalanceI, BalanceD);
        balanceContoller.setP(BalanceP);
        balanceContoller.setI(BalanceI);
        balanceContoller.setD(BalanceD);
        balanceContoller.setTolerance(1.5);
    
       // This value is passed to our drive controls methods similar to holding forward on our joystick during teleop.
       double directionforce = balanceContoller.calculate(m_swerve.getPitch(), 0);
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
        return false;
    }
}
