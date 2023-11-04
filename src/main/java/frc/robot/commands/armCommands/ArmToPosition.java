package frc.robot.commands.armCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NewArmSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class ArmToPosition extends CommandBase {
    private NewArmSubsystem m_arm;
    private NewArmSubsystem.armPositions m_targetPos;
    private boolean m_keepRunning;
    //private double m_startTime = 0;

    public ArmToPosition(NewArmSubsystem arm, NewArmSubsystem.armPositions pos){
        m_arm = arm;
        m_targetPos = pos;
        m_keepRunning = false;
        addRequirements(m_arm);
    }

    public ArmToPosition(NewArmSubsystem arm, NewArmSubsystem.armPositions pos, boolean keepRunning){
        m_arm = arm;
        m_targetPos = pos;
        m_keepRunning = keepRunning;
        addRequirements(m_arm);
    }

    @Override
    public void initialize() {
      //m_startTime = Timer.getFPGATimestamp();
    }
    /* 
    public double getTime() {
        return Timer.getFPGATimestamp() - m_startTime;
    }*/

    @Override
    public void execute(){
        m_arm.raiseArmAbs(m_targetPos);
    }

    @Override
    public boolean isFinished(){
        //if(getTime() >= 1.0f){
        //    return true;
        //  }
        return m_arm.atPosition(m_targetPos);
        //return false;
    }

    @Override
    public void end(boolean isInterrupted){
        if (!m_keepRunning) m_arm.raiseArm(0);
    }
}