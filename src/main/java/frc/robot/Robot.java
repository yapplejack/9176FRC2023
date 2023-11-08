// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cameraserver.CameraServer;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.armCommands.ArmToPosition;
import frc.robot.commands.autoCommandGroups.*;
import frc.robot.commands.autoCommandGroups.testAutos.TestBalanceWithIntake;
import frc.robot.commands.autoCommandGroups.testAutos.TestChargeAuto;
//import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.armPositions;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.Constants;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //XboxController m_manipController = new XboxController(OIConstants.kManipControllerPort);
  //comment out the top two choices
  private static final String kTestBal = "Test Balance with Intake";
  private static final String kAutoBalance = "Raw Autobalance";

  private static final String kConeDriveBack = "Cone drive";
  private static final String kCubeDriveBack = "Cube drive";
  
  private static final String kConeAutoBalance = "Cone Autobalance";
  private static final String kCubeAutoBalance = "Cube Autobalance";

  private static final String kConeOnly = "Cone Only";
  private static final String kCubeOnly = "Cube Only";
  



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();

    //m_chooser.addOption("Test Balance", kTestBal);
    //m_chooser.addOption("Raw Autobalance", kAutoBalance);

    m_chooser.addOption(kConeDriveBack, kConeDriveBack);
    m_chooser.addOption(kCubeDriveBack, kCubeDriveBack);

    m_chooser.addOption(kConeAutoBalance, kConeAutoBalance);
    m_chooser.addOption(kCubeAutoBalance, kCubeAutoBalance);
    
    m_chooser.addOption(kConeOnly, kConeOnly);
    m_chooser.addOption(kCubeOnly, kCubeOnly);



    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = new TestConeAuto(arm, intake);
    //m_autonomousCommand = new TestCubeAuto(arm, intake);
    //m_autonomousCommand = new TestConeAutoWithDrive(m_robotContainer.m_robotDrive, arm, intake);

    String autoSelected = m_chooser.getSelected();

      switch(autoSelected) { case kTestBal : m_autonomousCommand 
      = new TestBalanceWithIntake(m_robotContainer.m_intake, m_robotContainer.m_robotDrive); break;

      case kAutoBalance: m_autonomousCommand =
      new TestChargeAuto(m_robotContainer.m_robotDrive); break;

      case kConeDriveBack :
      m_autonomousCommand = 
      new ConeLongDrive(m_robotContainer.m_robotDrive, m_robotContainer.m_arm, m_robotContainer.m_intake); break;
    
      case kCubeDriveBack : 
      m_autonomousCommand =
      new CubeLongDrive(m_robotContainer.m_robotDrive, m_robotContainer.m_arm, m_robotContainer.m_intake); break;

      case kConeAutoBalance :
      m_autonomousCommand =
      new BalanceWithCone(m_robotContainer.m_robotDrive, m_robotContainer.m_arm, m_robotContainer.m_intake); break;

      case kCubeAutoBalance :
      m_autonomousCommand = 
      new BalanceWithCube(m_robotContainer.m_robotDrive, m_robotContainer.m_arm, m_robotContainer.m_intake); break;

      case kConeOnly : default :
      m_autonomousCommand = new SoloCone(m_robotContainer.m_arm, m_robotContainer.m_intake); break;

      case kCubeOnly :
      m_autonomousCommand = new SoloCube(m_robotContainer.m_arm, m_robotContainer.m_intake); break;
    }

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }


  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
