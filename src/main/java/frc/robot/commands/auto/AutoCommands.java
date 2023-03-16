package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;

public class AutoCommands {
    public static void finishConeIntake() {
        IntakeCommands.intake()
                .alongWith(ElevatorCommands.hopElevator(), FourBarCommands.home())
                .withTimeout(0.75)
                .schedule();
    }
    public static void 
}
