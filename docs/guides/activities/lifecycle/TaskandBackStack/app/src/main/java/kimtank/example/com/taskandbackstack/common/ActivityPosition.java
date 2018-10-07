package kimtank.example.com.taskandbackstack.common;

import java.lang.reflect.Type;

public enum  ActivityPosition {
    A_ACTIVITY_POSITION(0),
    B_ACTIVITY_POSITION(1),
    C_ACTIVITY_POSITION(2),
    D_ACTIVITY_POSITION(3),
    E_ACTIVITY_POSITION(4);

    private int position;
    ActivityPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
}
