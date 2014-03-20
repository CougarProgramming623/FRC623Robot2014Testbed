/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author samwinkelstein
 */
public class PnuematicsControl {
    
    private DoubleActSol Catch;
    private DoubleActSol pickup;
    private DoubleActSol holder;
    private SingleActSol LaunchCylenders;
    public static boolean charged;
    public static boolean fire;
    public PnuematicsControl(RobotBase623 robotBase)
    {
        charged = false;
        Catch = robotBase.getCatch();
        pickup = robotBase.getPickup();
        holder = robotBase.getHolder();
        LaunchCylenders = robotBase.getLaunchCylenders();
    }
    public void Charge()
    {
        Catch.setState(RC.Catch_Lock);
        Timer.delay(1.5);
        LaunchCylenders.setState(true);
        Timer.delay(10.0);
        charged = true;
    }
    public void Fire()
    {
        Catch.setState(RC.Catch_Release);
        Timer.delay(2);
        LaunchCylenders.setState(false);
    }
    public void extendPickup()
    {
        pickup.setState(RC.Pickup_extend);
    }
    public void retractPickup()
    {
        pickup.setState(RC.Pickup_retract);
    }
    public void ClampHolder()
    {
        holder.setState(RC.Holder_clamp);
    }
     public void releaseHolder()
    {
        holder.setState(RC.Holder_release);
    }
}
