package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.IntakeConstants;

public class RunIntake extends CommandBase {
    private IntakeSubsystem m_intake;
    private int object;
    private double intakePower;
    //private double m_startTime = 0;

    public RunIntake(IntakeSubsystem intake, int gameObject){
        m_intake = intake;
        // 1 = intake cone, 2 = intake cube
        object = gameObject;
        if(gameObject == 1)
        {
            intakePower = -IntakeConstants.INTAKE_OUTPUT_POWER;
        }
        else if(gameObject == 2)
        {
            intakePower = IntakeConstants.INTAKE_OUTPUT_POWER;
        }
        addRequirements(m_intake);
    }

    @Override
    public void execute(){
        m_intake.setIntakeMotor(intakePower, IntakeConstants.INTAKE_CURRENT_LIMIT_A);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean isInterrupted){
        if(object == 1)
        {
            m_intake.setIntakeMotor(-IntakeConstants.INTAKE_HOLD_POWER, IntakeConstants.INTAKE_HOLD_CURRENT_LIMIT_A);
        }
        else
        {
            m_intake.setIntakeMotor(IntakeConstants.INTAKE_HOLD_POWER, IntakeConstants.INTAKE_HOLD_CURRENT_LIMIT_A);
        }
    }
}