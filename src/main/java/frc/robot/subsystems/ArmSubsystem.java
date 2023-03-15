package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax arm = new CANSparkMax(9, MotorType.kBrushless);
    
    
    
    
    
    
    
    
    
    public ArmSubsystem() {
    
    }

    public void raiseArm(double trigger) {
        arm.set(ArmConstants.ARM_OUTPUT_POWER * trigger);
    }

    public void lowerArm(double trigger) {
        arm.set(-ArmConstants.ARM_OUTPUT_POWER * trigger);
    }
    
}