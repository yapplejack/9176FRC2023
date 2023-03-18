package frc.robot.commands.auto;

//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class TestConeAutoWithDrive extends SequentialCommandGroup{
    public TestConeAutoWithDrive(SwerveSubsystem swerve, ArmSubsystem m_arm, IntakeSubsystem m_intake) {
             addCommands(
                 
                new WaitCommand(1),
                    new InstantCommand(() -> m_arm.raiseArm()),

                new WaitCommand(2),
                    new InstantCommand(() -> m_arm.noArmPower()),
                    new InstantCommand(() -> m_intake.dropCone()),
        
                new WaitCommand(.5),
        
                new InstantCommand(() -> m_intake.stopIntake()),
                new WaitCommand(.4),
                        new InstantCommand(() -> m_arm.lowerArm()),
        
                new WaitCommand(2),
                    new InstantCommand(() -> m_arm.noArmPower()),

                new WaitCommand(1),
                    new InstantCommand(() -> swerve.drive(-1, 0, 0, true, true)),
                new WaitCommand(1)
             );

    }

    
}