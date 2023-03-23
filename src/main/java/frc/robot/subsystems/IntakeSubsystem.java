package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax.IdleMode;
//import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax intake = new CANSparkMax(10, MotorType.kBrushless);
    
    public IntakeSubsystem() {
        intake.setInverted(true);
        intake.setIdleMode(IdleMode.kBrake);
        intake.setSmartCurrentLimit(IntakeConstants.INTAKE_CURRENT_LIMIT_A);
    }

    public void pickupCone() {
        intake.setSmartCurrentLimit(IntakeConstants.INTAKE_CURRENT_LIMIT_A);
        intake.set(IntakeConstants.INTAKE_OUTPUT_POWER);
    }

    public void dropCone() {
        intake.setSmartCurrentLimit(IntakeConstants.INTAKE_CURRENT_LIMIT_A);
        intake.set(-IntakeConstants.INTAKE_OUTPUT_POWER);
    }

    public void stopIntake() {
        intake.set(0);
    }

    public void holdCone(){
        intake.set(-IntakeConstants.INTAKE_HOLD_POWER);
    }

    public void holdCube() {
        intake.set(IntakeConstants.INTAKE_HOLD_POWER);
    }

    public void setInverted(boolean set){
        intake.setInverted(set);
    }

    public void setIdleMode(IdleMode mode){
        intake.setIdleMode(mode);
    }

    public void setIntakeMotor(double percent, int amps) {
        intake.setSmartCurrentLimit(amps);
        intake.set(percent);
    }
}