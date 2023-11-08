package frc.robot.commands.autoCommandGroups.testAutos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.testCommands.TestIntakeApp;
import frc.robot.commands.testCommands.TestIntakeBal;
import frc.robot.subsystems.DriveSubsystem;

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