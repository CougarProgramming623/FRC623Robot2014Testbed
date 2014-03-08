
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class SolController {
    
    private Compressor compressor;
    private DoubleSolenoid doubleSol;
    private Solenoid sol1;
    
    public SolController(RobotHardware hardware) {
        compressor = new Compressor(1,1);
        doubleSol = hardware.getDoubleSol();
        sol1 = hardware.getSol1();
        this.Init();
    }
    
    public void Init() {
        compressor.start();
    }
    
    public void setDoubleSol(int dir) {
        switch (dir) {
            case 1: doubleSol.set(DoubleSolenoid.Value.kForward); break;
            case -1: doubleSol.set(DoubleSolenoid.Value.kReverse); break;
            case 0: doubleSol.set(DoubleSolenoid.Value.kOff); break;
        }
    }
    
    public void setSol1(boolean on) {
        sol1.set(on);
    }
    
}