package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax arm = new CANSparkMax(9, MotorType.kBrushless);
    
    public ArmSubsystem() {
        arm.setInverted(true);
        arm.setIdleMode(IdleMode.kBrake);
        arm.setSmartCurrentLimit(ArmConstants.ARM_CURRENT_LIMIT_A);
    }

    public void raiseArm() {
        arm.set(ArmConstants.ARM_OUTPUT_POWER );
    }

    public void lowerArm() {
        arm.set(-ArmConstants.ARM_OUTPUT_POWER);
    }

    public void noArmPower()
    {
        arm.set(0);
    }
    
}