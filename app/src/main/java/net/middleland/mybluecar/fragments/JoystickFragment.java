package net.middleland.mybluecar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.middleland.mybluecar.R;


public class JoystickFragment extends Fragment {

    protected static final String TAG = "JoystickFragment";
    private TextView angleTextView = null;
    private TextView powerTextView = null;
    private TextView directionTextView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joystick, container,
                false);

        @SuppressWarnings("unused")
        LinearLayout relativeLayout = (LinearLayout) rootView
                .findViewById(R.id.joystick_layout);

        angleTextView = (TextView) rootView.findViewById(R.id.angleTextView);
        powerTextView = (TextView) rootView.findViewById(R.id.powerTextView);
        directionTextView = (TextView) rootView.findViewById(R.id.directionTextView);


        JoystickView joystickView = (JoystickView) rootView.findViewById(R.id.joystickView);


        // Movement event Listener
        joystickView.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {
            private String btCommand;

            @Override
            public void onValueChanged(int angle, int power, int direction) {
                angleTextView.setText(" " + String.valueOf(angle) + "º");
                powerTextView.setText(" " + String.valueOf(power) + "%");
                btCommand = angle + ":" + power;

                Log.d(TAG, "BT command:" + btCommand);
                switch (direction) {
                    case JoystickView.FRONT:
                        directionTextView.setText(R.string.front);
                        break;

                    case JoystickView.FRONT_RIGHT:
                        directionTextView.setText(R.string.front_right);
                        break;

                    case JoystickView.RIGHT:
                        directionTextView.setText(R.string.right);
                        break;

                    case JoystickView.BOTTOM_RIGHT:
                        directionTextView.setText(R.string.bottom_right);
                        break;

                    case JoystickView.BOTTOM:
                        directionTextView.setText(R.string.bottom);
                        break;

                    case JoystickView.BOTTOM_LEFT:
                        directionTextView.setText(R.string.bottom_left);
                        break;

                    case JoystickView.LEFT:
                        directionTextView.setText(R.string.left);
                        break;

                    case JoystickView.FRONT_LEFT:
                        directionTextView.setText(R.string.front_left);
                        break;

                    default:
                        directionTextView.setText(R.string.center);
                }
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);

        return rootView;
    }

}
