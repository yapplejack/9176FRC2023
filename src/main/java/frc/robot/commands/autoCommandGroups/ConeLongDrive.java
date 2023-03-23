package frc.robot.commands.autoCommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.CrossLong;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ConeLongDrive extends SequentialCommandGroup{
    CrossLong drive;
    public ConeLongDrive(DriveSubsystem swerve, ArmSubsystem m_arm, IntakeSubsystem m_intake) {
             addCommands(

             //new InstantCommand(() -> m_intake.pickupCone()),
             new WaitCommand(.4),
                    new InstantCommand(() -> m_arm.raiseArm()),
                
                new WaitCommand(0),
                //new InstantCommand(() -> m_intake.holdCone()),
                new WaitCommand(1.3),

                    new InstantCommand(() -> m_arm.noArmPower()),
                    new WaitCommand(0),
                    new InstantCommand(() -> m_intake.dropCone()),

                new WaitCommand(.3),

                    new InstantCommand(() -> m_intake.stopIntake()),
                    new WaitCommand(0),
                    new InstantCommand(() -> m_arm.lowerArm()),

                new WaitCommand(1.7),

                    new InstantCommand(() -> m_arm.noArmPower()),
                    drive = new CrossLong(swerve));

    }
}
