package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.AbsoluteEncoder;

import java.util.EnumMap;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase{
    public enum armPositions{
        LVLONE, 
        LVLTWO, 
        LVLTRE,
        HOME
    }
        
    private final CANSparkMax arm = new CANSparkMax(9, MotorType.kBrushless);
    
    private final AbsoluteEncoder armAbsEncoder = arm.getAbsoluteEncoder(Type.kDutyCycle);

    double m_speed = 0.0;

    EnumMap<armPositions, Double> mapAbs = new EnumMap<>(armPositions.class);

    private final PIDController m_AbsPidController = new PIDController(0.0, 0.0, 0.0); //i was 0.2 | p is set below

    public ArmSubsystem(){
        arm.setInverted(true);

        mapAbs.put(armPositions.HOME, ArmConstants.kOffset);
        mapAbs.put(armPositions.LVLONE, ArmConstants.kLVLONE);
        mapAbs.put(armPositions.LVLTWO, ArmConstants.kLVLTWO);
        mapAbs.put(armPositions.LVLTRE, ArmConstants.kLVLTRE); //At hard stop:
        
        arm.setIdleMode(IdleMode.kBrake);

        arm.setSmartCurrentLimit(Constants.ArmConstants.ARM_CURRENT_LIMIT_A);

        m_AbsPidController.enableContinuousInput(0, 1);
    /**
     * Smart Motion coefficients are set on a SparkMaxPIDController object
     * 
     * - setSmartMotionMaxVelocity() will limit the velocity in RPM of
     * the pid controller in Smart Motion mode
     * - setSmartMotionMinOutputVelocity() will put a lower bound in
     * RPM of the pid controller in Smart Motion mode
     * - setSmartMotionMaxAccel() will limit the acceleration in RPM^2
     * of the pid controller in Smart Motion mode
     * - setSmartMotionAllowedClosedLoopError() will set the max allowed
     * error for the pid controller in Smart Motion mode
     */
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm Relative Enc", arm.getEncoder().getPosition());
        SmartDashboard.putNumber("ArmABS Absolute", armAbsEncoder.getPosition());
        SmartDashboard.putNumber("Arm oCurrent", arm.getOutputCurrent());
        SmartDashboard.putNumber("Arm Motor Speed", m_speed);
        // SmartDashboard.putNumber("ArmABS Offset", armAbsEncoder.getPositionOffset());  
        //   Might use global that is set by drive periodic to indicate if driving too fast.

        m_speed = arm.getEncoder().getVelocity();

        if (((armAbsEncoder.getPosition() < ArmConstants.kMinHeightAbs) && (m_speed < 0)) ||
            ((armAbsEncoder.getPosition() > ArmConstants.kMaxHeightAbs) && (m_speed > 0))) {
            arm.set(0);
            }

    }

    public void raiseArmAbs(armPositions position){
        if (((armAbsEncoder.getPosition() < ArmConstants.kMinHeightAbs) && (position == armPositions.HOME)) ||
            ((armAbsEncoder.getPosition() > ArmConstants.kMaxHeightAbs) && (position == armPositions.LVLTRE))) {
            arm.set(0);
            return;
        }

        // For LVLTRE, LVLTWO, and HOME
        switch (position) {
            case LVLTRE:
            case LVLTWO:
            case HOME:
                m_AbsPidController.setP(2.5);
                m_AbsPidController.setI(.5);
                m_AbsPidController.setD(.1);
                break;
        // For LVLONE and CONESTOW
            case LVLONE:
            default:
                m_AbsPidController.setP(2.5);
                m_AbsPidController.setI(.5);
                m_AbsPidController.setD(.1);
                break;
        }
        double ref = mapAbs.get(position);

        double pidOut = MathUtil.clamp(
            m_AbsPidController.calculate(armAbsEncoder.getPosition(),ref),
            Constants.ArmConstants.kArmMinOutput, Constants.ArmConstants.kArmMaxOutput);
            
        SmartDashboard.putNumber("Arm Abs Target Pos", ref);
        arm.set(pidOut);
        if(atPosition(position))
        {
            noArmPower();
            return;
        }
        // TODO: Add a new armPosition that reads a value from the smart dashboard and moves arm to that position.
    }

    public void raiseArm(double speed){
        if (((armAbsEncoder.getPosition() < ArmConstants.kOffset) && (speed < 0)) ||
            ((armAbsEncoder.getPosition() > ArmConstants.kLVLTRE) && (speed > 0))) {
            arm.set(0);
            return;
        }
        arm.set(speed);
    }

    public void raiseArm(double raiseSpeed, double lowerSpeed){
        double speed = raiseSpeed - lowerSpeed; //positive output to raise arm
        if (((armAbsEncoder.getPosition() < ArmConstants.kOffset) && (speed < 0)) ||
            ((armAbsEncoder.getPosition() > ArmConstants.kLVLTRE) && (speed > 0))) {
            arm.set(0);
            return;
        }
        arm.set(speed);
    }

    public boolean atPosition(armPositions pos){
        double currentEncoderPosition = armAbsEncoder.getPosition();
        return (Math.abs(currentEncoderPosition - mapAbs.get(pos)) < Constants.ArmConstants.kAllowedErrAbs);
    }

    public void noArmPower()
    {
        arm.set(0);
    }

    public void raiseArm() {
        arm.set(ArmConstants.ARM_OUTPUT_POWER );
    }

    public void lowerArm() {
        arm.set(-ArmConstants.ARM_OUTPUT_POWER);
    }

}