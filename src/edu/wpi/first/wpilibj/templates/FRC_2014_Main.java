/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. 
 /*
 * Sams code V2;
 /*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class FRC_2014_Main extends IterativeRobot {

    // define Variables
    private Talon frontLeftTalon;
    private Talon backLeftTalon;
    private Talon frontRightTalon;
    private Talon backRightTalon;
    private RobotDrive myDrive;
    private Joystick driveStick;
    private Joystick ControlStick;
    public Servo yaw;
    public Servo tilt;
    float timeinseconds;
    public NetworkTable table;
    public boolean initShoot;
    public double camX;
    public double CamY;
    private RobotHardware robotHardware;
    private SolController solControl;
    boolean fire;
    boolean charged;
    public DriverStationEnhancedIO DSIO;
    
    
    
    Gyro gyro;
    // aheading is the key variable in storing angular data and heading from the gyro
    double aheading;
    // target heading stores the variable of the heading in degrees for the direction of the target position 
    double targetHeading;
    boolean oneturn = true;
    
    // init Robot 
    
    public void robotInit() {
        
        frontRightTalon = new Talon(1);
        backRightTalon = new Talon(2);
        frontLeftTalon = new Talon(3);
        backLeftTalon = new Talon(4);
        gyro = new Gyro(1); 
        ControlStick = new Joystick(2);
        driveStick = new Joystick(1);
        robotHardware = new RobotHardware();
        solControl = new SolController(robotHardware);
        myDrive = new RobotDrive(frontLeftTalon, backLeftTalon, frontRightTalon, backRightTalon);
        myDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true); myDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        myDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        myDrive.setExpiration(0.1);
        fire = false;
        charged = false;
        
        
    }

    public void testInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
        printToDash(1, "gyroReset!");
        gyro.reset();
        targetHeading = 45;
    }

    public void autonomousPeriodic() {
          
        double autorotation = 0;
        double automagnitude = 0;
        double autoDirection = 0;
       aheading = gyro.getAngle();
        autorotation = table.getNumber("AutoRotation", 0.00);
       
            myDrive.mecanumDrive_Polar(automagnitude, autoDirection, autorotation);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() 
    {

    }

    public void teleopPeriodic() {
        
        if(driveStick.getRawButton(5))
        {
            gyro.reset();
            shootLeft();
        }
         if(driveStick.getRawButton(6))
        {
            gyro.reset();
            shootRight();
        }
         if(driveStick.getRawButton(4))
         {
             gyro.reset();
             spin1();
         }
         try{
              DSInput();
         }
         catch(Exception e){
                 
                 }
        
        drive();
        
        

    }
    
    public void spin1()
    {
        
        while(gyro.getAngle() < 360)
        {
            printToDash(1, "gyroAngle " + gyro.getAngle());
        double magnitude = applyMagnitudeSquared(applyMagnitudeDeadband(driveStick.getMagnitude()));
        double direction = driveStick.getDirectionDegrees();
        
         myDrive.mecanumDrive_Polar(magnitude, 0, .6);
         if(driveStick.getRawButton(1))
         {
              myDrive.mecanumDrive_Polar(magnitude, 0, 0);
             break;
         }
        }
        double magnitude = applyMagnitudeSquared(applyMagnitudeDeadband(driveStick.getMagnitude()));
         myDrive.mecanumDrive_Polar(magnitude, 0,0);
    }
    public void shootLeft()
    {
        while(gyro.getAngle() > -20)
        {
            myDrive.mecanumDrive_Polar(0, 0, -.7);
            printToDash(1, "gyroAngle " + gyro.getAngle());
        }
        myDrive.mecanumDrive_Polar(0, 0, 0);
    }
        public void shootRight()
    {
        while(gyro.getAngle() < 20)
        {
            myDrive.mecanumDrive_Polar(0, 0, .7);
            printToDash(1, "gyroAngle " + gyro.getAngle());
        }
        myDrive.mecanumDrive_Polar(0, 0, 0);
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
    
    public void Charge()
    {
        solControl.setDoubleSol(1);
            Timer.delay(1);
            solControl.setSol1(true);
            Timer.delay(10);
            charged = true;
    }
    public void FIRE()
    {
        solControl.setDoubleSol(1);
        Timer.delay(.5);
        solControl.setSol1(true);
        
        Timer.delay(3);
        solControl.setDoubleSol(-1);
        Timer.delay(3);
         solControl.setSol1(false);
    }
    public void FIRETeliOp()
    {
        fire = true;
        solControl.setDoubleSol(-1);
        Timer.delay(4);
         solControl.setSol1(false);
          Timer.delay(5);
          charged = false;
          fire = false;

    }
    // squares driveStick magnitude 

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
        if(DSIO.getDigital(1) && charged && !fire)
        {
            FIRETeliOp();
        }
        if(DSIO.getDigital(2) && !charged && !fire)
        {
           Charge();
        }
    }
    
 
    //*************************************************
    //*************************************************
    /* This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}
