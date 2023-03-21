package frc.robot.commands.auto;

//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.commands.TestAutoDriver;

public class TestDriveAuto extends SequentialCommandGroup{
    TestAutoDriver drive;
    public TestDriveAuto(SwerveSubsystem swerve) {
             addCommands(
                drive = new TestAutoDriver(swerve)
             );

    }

    
}