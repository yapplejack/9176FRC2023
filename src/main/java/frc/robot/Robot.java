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
import frc.robot.commands.autoCommandGroups.*;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  
  //CANSparkMax arm = new CANSparkMax(9, MotorType.kBrushless);
  ArmSubsystem arm;
  //CANSparkMax intake = new CANSparkMax(10, MotorType.kBrushless);
  IntakeSubsystem intake;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  static final int ARM_CURRENT_LIMIT_A = 20;

  /**
   * Percent output to run the arm up/down at
   */
  static final double ARM_OUTPUT_POWER = 0.55;

  /**
   * How many amps the intake can use while picking up
   */
  static final int INTAKE_CURRENT_LIMIT_A = 25;

  /**
   * How many amps the intake can use while holding
   */
  static final int INTAKE_HOLD_CURRENT_LIMIT_A = 5;

  /**
   * Percent output for intaking
   */
  static final double INTAKE_OUTPUT_POWER = 1.0;

  /**
   * Percent output for holding
   */
  static final double INTAKE_HOLD_POWER = 0.07;

  /**
   * Time to extend or retract arm in auto
   */
  static final double ARM_EXTEND_TIME_S = 2.0;

  /**
   * Time to throw game piece in auto
   */
  static final double AUTO_THROW_TIME_S = 0.375;

  /**
   * Time to drive back in auto
   */
  static final double AUTO_DRIVE_TIME = 6.0;

  /**
   * Speed to drive backwards in auto
   */
  static final double AUTO_DRIVE_SPEED = -0.25;

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
    arm = new ArmSubsystem();
    intake = new IntakeSubsystem();
    CameraServer.startAutomaticCapture();
    //arm.setInverted(true);
    //arm.setIdleMode(IdleMode.kBrake);
    //arm.setSmartCurrentLimit(ARM_CURRENT_LIMIT_A);
    //intake.setInverted(false);
    //intake.setIdleMode(IdleMode.kBrake);

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
      = new TestBalanceWithIntake(intake, m_robotContainer.m_robotDrive); break;

      case kAutoBalance: m_autonomousCommand =
      new TestChargeAuto(m_robotContainer.m_robotDrive); break;

      case kConeDriveBack :
      m_autonomousCommand = new ConeLongDrive(m_robotContainer.m_robotDrive, arm, intake); break;
    
      case kCubeDriveBack : 
      m_autonomousCommand = new CubeLongDrive(m_robotContainer.m_robotDrive, arm, intake); break;

      case kConeAutoBalance :
      m_autonomousCommand = new BalanceWithCone(m_robotContainer.m_robotDrive, arm, intake); break;

      case kCubeAutoBalance :
      m_autonomousCommand = new BalanceWithCube(m_robotContainer.m_robotDrive, arm, intake); break;

      case kConeOnly : default :
      m_autonomousCommand = new SoloCone(arm, intake); break;

      case kCubeOnly :
      m_autonomousCommand = new SoloCube(arm, intake); break;
    }

      

     

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}


  public void setArmMotor(double percent) {
    //arm.set(percent);
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    lastGamePiece = NOTHING;
  }
  static final int CONE = 1;
  static final int CUBE = 2;
  static final int NOTHING = 3;
  int lastGamePiece;
  double armPower = 0;


  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //double armPower; // 1 and 4 for buttons
    if (m_robotContainer.m_manipController.getL2Axis() > .1) {
      // lower the arm
     // armPower = -ARM_OUTPUT_POWER * m_manipController.getLeftTriggerAxis();
      arm.lowerArm();
      armPower = -.55;
    } else if (m_robotContainer.m_manipController.getR2Axis() > .1) {
      // raise the arm
      //armPower = ARM_OUTPUT_POWER * m_manipController.getRightTriggerAxis();
      arm.raiseArm();
      armPower = .55;
    } else {
      // do nothing and let it sit where it is
      //armPower = 0.0;
      arm.noArmPower();
      armPower = 0;
    }
    SmartDashboard.putNumber("Arm Power", armPower);
    //setArmMotor(armPower);
  
    double intakePower;
    int intakeAmps;
    if (m_robotContainer.m_manipController.getRawButton(5)) {
      // cube in or cone out
      intakePower = INTAKE_OUTPUT_POWER;
      //intake.dropCone();
      intakeAmps = INTAKE_CURRENT_LIMIT_A;
      lastGamePiece = CUBE;
    } else if (m_robotContainer.m_manipController.getRawButton(6)) {
      // cone in or cube out
      intakePower = -INTAKE_OUTPUT_POWER;
      //intake.pickupCone();
      intakeAmps = INTAKE_CURRENT_LIMIT_A;
      lastGamePiece = CONE;
    } else if (lastGamePiece == CUBE) {
      //intake.holdCube();
      intakePower = INTAKE_HOLD_POWER;
      intakeAmps = INTAKE_HOLD_CURRENT_LIMIT_A;
    } else if (lastGamePiece == CONE) {
      //intake.holdCone();
      intakePower = -INTAKE_HOLD_POWER;
      intakeAmps = INTAKE_HOLD_CURRENT_LIMIT_A;
    } else {
      intakePower = 0.0;

      intakeAmps = 0;
    }
    intake.setIntakeMotor(intakePower, intakeAmps);
    SmartDashboard.putNumber("Intake Power", intakePower);
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
