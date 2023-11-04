package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NewArmSubsystem;

public class RaiseArm extends CommandBase {
    private NewArmSubsystem m_arm;

    public RaiseArm(NewArmSubsystem arm){
        m_arm = arm;
    }

    @Override
    public void execute(){
        m_arm.armRaise();
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