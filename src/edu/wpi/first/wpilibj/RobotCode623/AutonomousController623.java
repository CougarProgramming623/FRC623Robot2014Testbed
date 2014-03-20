/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author samwinkelstein
 */
public class AutonomousController623 {
    
    private RobotDrive mechDrive;
    private PnuematicsControl airControl;
    private double drivemagnitude;
    private double twistmagnitude;
    private double direction;
    private final int servoRight = 1;
    private final int servoLeft = -1;
    private int servo;
     private VisionController visControl;
     public boolean start;
     public boolean rightScore;
     public boolean leftScore;
    
    public AutonomousController623(RobotBase623 robotBase, PnuematicsControl airControl)
    {
        mechDrive = robotBase.getDriveBase();
        this.airControl = airControl;
        visControl = VisionController.getInstance();
        start = true;
    }
    public void AutoRunMain()
    {
        visControl.processImage();
        if(start)
        {
        if(RightHot() && ServoRight())
        {
            rightScore = true;
        }
        else if(RightHot() && ServoRight())
        {
            leftScore = true;
        }
        start = false;
        }
        if(rightScore)
            RightScore();
        else if(leftScore)
        {
            LefttScore();
        }
    }
    public boolean RightHot()
    { 
        if(visControl.isHot)   
            return true;
       
        return false;
    }
     public boolean LeftHot()
    { 
        if(visControl.isHot)   
            return true;
       
        return false;
    }
    public boolean ServoRight()
    {
         if(servo == servoRight)
             return true;
         
         return false;
    }
    public boolean ServoLeft()
    {
         if(servo == servoLeft)
             return true;
         
         return false;
    }
    public void RightScore()
    {
        if(!InRange() && visControl.Distance > RC.Max)
        {
            drivemagnitude = 1;
            twistmagnitude = 0;
            direction = 0;
            mechDrive.mecanumDrive_Polar(drivemagnitude, direction, twistmagnitude);
        }
        else if(!InRange() && visControl.Distance < RC.Min)
        {
            drivemagnitude = -1;
            twistmagnitude = 0;
            direction = 0;
            mechDrive.mecanumDrive_Polar(drivemagnitude, direction, twistmagnitude);
        }
        else
        {
            
            drivemagnitude = 0;
            twistmagnitude = 0;
            direction = 0;
            mechDrive.mecanumDrive_Polar(drivemagnitude, direction, twistmagnitude);
        if(PnuematicsControl.charged)
        {
            airControl.Fire();
        }
        }
        
    }
    public void LefttScore()
    {
        if(!InRange() && visControl.Distance > RC.Max)
        {
            drivemagnitude = 1;
            twistmagnitude = 0;
            direction = 0;
            mechDrive.mecanumDrive_Polar(drivemagnitude, direction, twistmagnitude);
        }
        else if(!InRange() && visControl.Distance < RC.Min)
        {
            drivemagnitude = -1;
            twistmagnitude = 0;
            direction = 0;
            mechDrive.mecanumDrive_Polar(drivemagnitude, direction, twistmagnitude);
        }
        else
        {
            
            drivemagnitude = 0;
            twistmagnitude = 0;
            direction = 0;
            mechDrive.mecanumDrive_Polar(drivemagnitude, direction, twistmagnitude);
        if(PnuematicsControl.charged)
        {
            airControl.Fire();
        }
        }
        
    }
    public boolean InRange()
    {
        if(visControl.Distance > RC.Min && visControl.Distance < RC.Max)
            return true;
        return false;
    }
}
