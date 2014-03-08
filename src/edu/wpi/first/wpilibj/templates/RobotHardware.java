/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Kostyantyn
 */
public class RobotHardware {

    private RobotDrive driveControl;    // class that controls the four driving motors

    private AnalogChannel sonar;

    private Compressor compressor; // Compressor
    private DoubleSolenoid doubleSol;
    private Solenoid sol1; // TODO name this better
    private Solenoid sol2; // TODO name this better

    private Joystick driverJoystick; // Represents the right joystick (for driving)
    private Joystick secondJoystick; // Represents the left joystick (for non-driving tasks)

    public RobotHardware() {
 
        doubleSol = new DoubleSolenoid(Constants.DOUBLE_SOLENOID_FORWARD_CHANNEL, Constants.DOUBLE_SOLENOID_REVERSE_CHANNEL);
        sol1 = new Solenoid(Constants.SOLENOID_PORT_1);
       
    }

    public RobotDrive getDrive() {
        return driveControl;
    }

    public Compressor getCompressor() {
        return compressor;
    }

    public DoubleSolenoid getDoubleSol() {
        return doubleSol;
    }

    public Solenoid getSol1() {
        return sol1;
    }
}