/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

/**
 *
 * @author samwinkelstein
 */
// Robot control system

public class RC {
    // Drive Base with Talons use PWM at end
   public static final int Right_Front_Motor_PWM = 1;
   public static final int Right_Back_Motor_PWM = 2;
   public static final int Left_Front_Motor_PWM = 3;
   public static final int Left_Back_Motor_PWM = 4;
   
   //control Joysticks
    public static final int DRIVER_JOYSTICK_PORT = 1;
    public static final int Control_JOYSTICK_PORT = 2;
   
    //Joystick 1 buttons
    public static final int JSB1_FIRE_IN_RAnge = 1;
    public static final int JSB1_Spin_Right = 3;
    public static final int JSB1_Spin_Left = 4;
    
    // enhanced IO buttons
    public static final int DIO_Fire = 1;
    public static final int DIO_Charge = 2;
    public static final int DIO_Juke_shoot_right = 3;
    public static final int DIO_Juke_shoot_Left = 4;
    public static final int DIO_lock_FB_and_Load = 5;
    
    //Puematics System pnuematics ports 
    //compressor
    public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL_DIO = 1;
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    
    //Catch values
    public static final int Catch_Lock = 1;
    public static final int Catch_Release = -1;
    public static final int Catch_off = 0;
     
    //pickup values
      public static final int Pickup_extend = 1;
      public static final int Pickup_retract = -1;
      public static final int Pickup_off = 0;
      //pickup values
      public static final int Holder_release = 1;
      public static final int Holder_clamp = -1;
      public static final int Holder_off = 0;
    //solinoid single
    public static final int Launch_Cylenders_Sol = 1;
    // solinoid double
       // catch sol
    public static final int Catch_Lock_Port = 2;
    public static final int Catch_release_Port = 3;
        // pickup sol
    public static final int Pickup_Extend_port = 4;
    public static final int Pickup_Retract_port = 5;
        // holding sol
    public static final int holder_Release_port = 5;
    public static final int holder_Clamp_port = 6;
    //anolog sensors use ALog at end 1, 8
    public static final int Gyro_ALog = 1;

    
    // Digital inputs use Alog at end 1,9
    
    
    //distance inRange;
    public static final int Max = 16;
     public static final int Min = 13;
    
}
