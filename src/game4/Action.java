package game4;

public class Action {
    public int thrust;
    public int turn; // -1 for turn left, 1, for turn right
    public boolean shoot;

    public Action(int thrust, int turn, boolean shoot) {
        this.thrust = thrust;
        this.turn = turn;
        this.shoot = shoot;
    }

    public Action(){}

}
