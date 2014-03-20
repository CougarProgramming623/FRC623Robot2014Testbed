/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;
import edu.wpi.first.wpilibj.Solenoid;
/**
 *
 * @author samwinkelstein
 */
public class SingleActSol {
    
    private Solenoid Sol;
    
    public SingleActSol(int port)
    {
        Sol = new Solenoid(port);
    }
    public void setState(boolean on) {
        Sol.set(on);
    }
}
