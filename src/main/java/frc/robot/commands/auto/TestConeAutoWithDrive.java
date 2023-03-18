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

                new WaitCommand(1.7),

                    new InstantCommand(() -> m_arm.noArmPower()),
                    new InstantCommand(() -> m_intake.dropCone()),

                new WaitCommand(.5),

                    new InstantCommand(() -> m_intake.stopIntake()),

                new WaitCommand(.4),

                        new InstantCommand(() -> m_arm.lowerArm()),

                new WaitCommand(2),

                    new InstantCommand(() -> m_arm.noArmPower()),

                new WaitCommand(1),

                    new InstantCommand(() ->  {TrajectoryConfig config = new TrajectoryConfig(
                        AutoConstants.kMaxSpeedMetersPerSecond,
                        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                        // Add kinematics to ensure max speed is actually obeyed
                        .setKinematics(DriveConstants.kDriveKinematics);
                        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                        // Start at the origin facing the +X direction
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(new Translation2d(-1, 0), new Translation2d(-2, 0)),
                        // End 3 meters straight ahead of where we started, facing forward
                        new Pose2d(-3, 0, new Rotation2d(0)),
                        config);
                        var thetaController = new ProfiledPIDController(
                            AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
                        thetaController.enableContinuousInput(-Math.PI, Math.PI);

                        SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
                            exampleTrajectory,
                            swerve::getPose, // Functional interface to feed supplier
                            DriveConstants.kDriveKinematics,

                            new PIDController(AutoConstants.kPXController, 0, 0),
                            new PIDController(AutoConstants.kPYController, 0, 0),
                            thetaController,
                            swerve::setModuleStates,
                            swerve);

                            swerve.resetOdometry(exampleTrajectory.getInitialPose());

                            swerveControllerCommand.andThen(() -> swerve.drive(0, 0, 0, false, false));
                    })
             );

    }

    
}