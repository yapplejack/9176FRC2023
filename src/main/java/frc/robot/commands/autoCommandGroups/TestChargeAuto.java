package frc.robot.commands.autoCommandGroups;

//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.ApproachChargeStation;
import frc.robot.commands.BalanceChargeStation;
import frc.robot.commands.XonChargeStation;

public class TestChargeAuto extends SequentialCommandGroup{
    ApproachChargeStation drive;
    BalanceChargeStation station_balance;
    XonChargeStation parkingBrake;
    public TestChargeAuto(DriveSubsystem swerve) {
        addCommands(
            drive = new ApproachChargeStation(swerve),
            station_balance = new BalanceChargeStation(swerve),
            parkingBrake = new XonChargeStation(swerve)
            
        );

    }

    
}