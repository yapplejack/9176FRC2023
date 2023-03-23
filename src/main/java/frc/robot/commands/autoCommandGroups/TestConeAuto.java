package frc.robot.commands.autoCommandGroups;

//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class TestConeAuto extends SequentialCommandGroup{
    public TestConeAuto(ArmSubsystem m_arm, IntakeSubsystem m_intake) {
             addCommands(
                new InstantCommand(() -> m_arm.raiseArm()),

                new WaitCommand(2),
                    new InstantCommand(() -> m_arm.noArmPower()),
                    new InstantCommand(() -> m_intake.dropCone()),
        
                new WaitCommand(2),
                new InstantCommand(() -> m_intake.dropCone()),
        
                new WaitCommand(2),
                new InstantCommand(() -> m_intake.stopIntake()),
                new WaitCommand(.4),
                new InstantCommand(() -> m_arm.lowerArm()),
        
                new WaitCommand(2),
                new InstantCommand(() -> m_arm.noArmPower())
             );

    }

    
}