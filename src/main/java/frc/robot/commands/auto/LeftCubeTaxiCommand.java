
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.commands.auto.AutoCommands;


// Wait 5 seconds before driving out of tarmac
public class LeftCubeTaxiCommand extends SequentialCommandGroup {
    /** Creates a new TestPathFollowing. */
    public LeftCubeTaxiCommand() {
        addCommands(
            AutoCommands.simpleLaunchCube(),
                new SwerveDrive(() -> -0.25, () -> -1, () -> 0.0, false).withTimeout(0.5),
                new SwerveDrive(() -> -2.0, () -> 0.0, () -> 0.0, false).withTimeout(3));
    }

    Rotation2d finalRotation() {
        return new Rotation2d(Math.PI);
    }
}