package uet.oop.bomberman.entities.movingEntities.enemy.BFS;

public class pair {
    public int fi;
    public int se;

    public pair(int fi, int se) {
        this.fi = fi;
        this.se = se;
    }

    public pair(pair other) {
        fi = other.fi;
        se = other.se;
    }
}
