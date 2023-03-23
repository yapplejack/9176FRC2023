package frc.robot.commands.autoCommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.TestIntakeApp;
import frc.robot.commands.TestIntakeBal;

public class TestBalanceWithIntake extends SequentialCommandGroup{
    TestIntakeApp drive;
    TestIntakeBal test;
    public TestBalanceWithIntake(IntakeSubsystem intake, DriveSubsystem swerve) {
        addCommands(
            drive = new TestIntakeApp(intake, swerve),
            test = new TestIntakeBal(intake, swerve)
        );

    }

    
}