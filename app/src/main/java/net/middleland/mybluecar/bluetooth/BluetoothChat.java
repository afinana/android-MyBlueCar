package net.middleland.mybluecar.bluetooth;

public class BluetoothChat {

    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "device_toast";

    public static final String BT_COMMAND_ID="BT";
    private static final char BT_PARAMETER_SEPARATOR ='|' ;

    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_DEVICE_NAME = 2;
    public static final int MESSAGE_TOAST = 3;
    public static final int MESSAGE_READ = 4;
    public static final int MESSAGE_WRITE = 5;



    public String getBtCommand(int angle, int power, int direction ){
        StringBuffer sb = new StringBuffer();
        sb.append(BT_COMMAND_ID);
        sb.append(BT_PARAMETER_SEPARATOR);
        sb.append(angle);
        sb.append(BT_PARAMETER_SEPARATOR);
        sb.append(power);
        sb.append(BT_PARAMETER_SEPARATOR);
        sb.append(direction);

        return sb.toString();

    }

}
