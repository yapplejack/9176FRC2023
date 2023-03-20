package frc.robot.commands.auto;

//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.List;

import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class TestConeAutoWithDrive extends SequentialCommandGroup{
    public TestConeAutoWithDrive(SwerveSubsystem swerve, ArmSubsystem m_arm, IntakeSubsystem m_intake) {
             addCommands(
                    new InstantCommand(() -> m_arm.raiseArm()),
                    new InstantCommand(() -> m_intake.pickupCone()),
                
                new WaitCommand(.4),
                new InstantCommand(() -> m_intake.holdCone()),
                new WaitCommand(1.3),

                    new InstantCommand(() -> m_arm.noArmPower()),
                    new InstantCommand(() -> m_intake.dropCone()),

                new WaitCommand(.3),

                    new InstantCommand(() -> m_intake.stopIntake()),
                    new InstantCommand(() -> m_arm.lowerArm()),

                new WaitCommand(1.7),

                    new InstantCommand(() -> m_arm.noArmPower()),

                    new InstantCommand(() ->  {
                        swerve.drive(.5, 0, 0, true, true);
                    }),
                    new WaitCommand(1),
                    new InstantCommand(() ->  {
                        swerve.drive(0, 0, 0, true, true);
                    })
             );

    }

    
}