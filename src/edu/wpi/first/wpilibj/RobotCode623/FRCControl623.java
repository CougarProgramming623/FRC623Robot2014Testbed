/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

/**
 *
 * @author samwinkelstein
 */

import edu.wpi.first.wpilibj.templates.RobotHardware;
import edu.wpi.first.wpilibj.templates.SolController;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class FRCControl623 extends IterativeRobot {
    
    public DriverStationEnhancedIO DSIO;
    private RobotDrive myDrive;
    private Joystick driveStick;
      private Joystick controlStick;
    private RobotBase623 robotBase;
    private PnuematicsControl airControl;
    private AutonomousController623 autoControl;
    
    
    // init Robot 
    
    public void robotInit() {
        robotBase = new RobotBase623();
        airControl = new PnuematicsControl(robotBase);
        myDrive = robotBase.getDriveBase();
        driveStick = robotBase.getDriveStick();
        controlStick = robotBase.getControlStick();
        autoControl = new AutonomousController623(robotBase, airControl);
    }

    public void testInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
        autoControl.start = true;
    }

    public void autonomousPeriodic() {
    autoControl.AutoRunMain();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() 
    {

    }

    public void teleopPeriodic() {
        
    }
        
    // Drive and Joystick section *****************************

    private void drive() {
        double magnitude = applyMagnitudeSquared(applyMagnitudeDeadband(driveStick.getMagnitude()));
        double direction = driveStick.getDirectionDegrees();
        double rotation = applyRotationalDeadband(driveStick.getTwist());


        myDrive.mecanumDrive_Polar(magnitude, direction, rotation);
        printToDash(6, "mag: " + magnitude);
        printToDash(7, "joyD: " + direction);
        printToDash(7, "twist: " + rotation);
    }

    private double applyMagnitudeSquared(double magnitude) {
        return magnitude * magnitude;
    }

    // joystick Deadbands*****************************
    double applyMagnitudeDeadband(double magnitude) {
        double newMagnitude = magnitude;
        if (newMagnitude > .05) {
            newMagnitude = newMagnitude - .05;
        } else if (newMagnitude > 1) {
            newMagnitude = 1;
        }
        return newMagnitude;
    }

    double applyRotationalDeadband(double Twist) {
        double newTwist = Twist;

        if (newTwist > .1) {
            newTwist = newTwist - .1;
        } else if (newTwist < -.1) {
            newTwist = newTwist + .1;
        }
        return newTwist;
    }
    // accelerometer to try and get velocity
    // Gyrosection ***********************************

    private void printToDash(int line, String str) {
        DriverStationLCD dsLCD = DriverStationLCD.getInstance();
        switch (line) {
            case 1:
                dsLCD.println(DriverStationLCD.Line.kUser1, 1, str);
                break;
            case 2:
                dsLCD.println(DriverStationLCD.Line.kUser2, 1, str);
                break;
            case 3:
                dsLCD.println(DriverStationLCD.Line.kUser3, 1, str);
                break;
            case 4:
                dsLCD.println(DriverStationLCD.Line.kUser4, 1, str);
                break;
            case 5:
                dsLCD.println(DriverStationLCD.Line.kUser5, 1, str);
                break;
            case 6:
                dsLCD.println(DriverStationLCD.Line.kUser6, 1, str);
                break;
        }
        dsLCD.updateLCD();
    }
    public void DSInput() throws Exception
    {
        DSIO = DriverStation.getInstance().getEnhancedIO();
        if(DSIO.getDigital(1))
        {
            
        }
    }
    
 
    //*************************************************
    //*************************************************
    /* This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}

