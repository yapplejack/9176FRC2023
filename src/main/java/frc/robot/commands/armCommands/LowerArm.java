package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class LowerArm extends CommandBase {
    private ArmSubsystem m_arm;

    public LowerArm(ArmSubsystem arm){
        m_arm = arm;
    }

    @Override
    public void execute(){
        m_arm.lowerArm();
    }

    @Override
    public boolean isFinished(){
        // return m_arm.atLevel(m_targetPos);
        return false;
    }

    @Override
    public void end(boolean isInterrupted){
        m_arm.noArmPower();
    }
}
