package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NewArmSubsystem;

public class LowerArm extends CommandBase {
    private NewArmSubsystem m_arm;

    public LowerArm(NewArmSubsystem arm){
        m_arm = arm;
    }

    @Override
    public void execute(){
        m_arm.armLower();
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
