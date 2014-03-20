/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;
import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 *
 * @author samwinkelstein
 */
public class DoubleActSol {
    
    private DoubleSolenoid Sol;
    public DoubleActSol(int portF, int PortR)
    {
        Sol = new DoubleSolenoid(portF, PortR);
    }
    
    public void setState(int action) {
        switch (action) {
            case 1: Sol.set(DoubleSolenoid.Value.kForward); break;
            case -1: Sol.set(DoubleSolenoid.Value.kReverse); break;
            case 0: Sol.set(DoubleSolenoid.Value.kOff); break;
        }
    }   
}
