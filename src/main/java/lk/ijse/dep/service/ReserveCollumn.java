package lk.ijse.dep.service;

public class ReserveCollumn implements Comparable<ReserveCollumn>{
    private int col;
    private Integer score;

    public ReserveCollumn() {
    }

    public ReserveCollumn(int col, int score) {
        this.col = col;
        this.score = score;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getScore() {
        return score ;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "col=" + col +
                ", mark=" + score +
                '}';
    }

    @Override
    public int compareTo(ReserveCollumn o) {
        return this.score.compareTo(o.getScore());
    }
}
