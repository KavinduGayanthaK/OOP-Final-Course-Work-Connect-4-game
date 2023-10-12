package lk.ijse.dep.service;

import java.util.*;
import java.util.stream.Collectors;

public class AiPlayer extends Player {
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    public void movePiece(int col) {
        while (true) {
            col = minMax();
            if (board.isLegalMove(col)) {
                break;
            }
        }

        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);

        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.EMPTY) {
            boolean fl = board.existLegalMoves();
            if (!fl) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        } else {
            board.getBoardUI().notifyWinner(winner);
        }
    }

    public int minMax() {
        List<ReserveCollumn> collection = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            collection.add(new ReserveCollumn(i, 0));
        }
        for (int i = 0; i < collection.size(); i++) {
            int avs = board.findNextAvailableSpot(i);
            if (avs==-1)continue;
            board.updateMove(i, Piece.GREEN);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() == Piece.GREEN) {
                for (ReserveCollumn holder : collection) {
                    if (holder.getCol() == i) {
                        int score = holder.getScore() ;
                        score += 100;
                        holder.setScore(score);
                    }
                }
            }
            board.updateMove(i, avs, Piece.EMPTY);
        }

        for (int i = 0; i < 6; i++) {
            int avs = board.findNextAvailableSpot(i);
            if (avs==-1)continue;
            board.updateMove(i, Piece.BLUE);
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() == Piece.BLUE) {
                for (ReserveCollumn holder : collection) {
                    if (holder.getCol() == i) {
                        int score = holder.getScore();
                        score += 99;
                        holder.setScore(score);
                    }
                }
            }
            board.updateMove(i, avs, Piece.EMPTY);
        }
        return max(collection);
    }

    private int max(List<ReserveCollumn> list){
        int max = Integer.MIN_VALUE;
        int mi = -1;
        for (ReserveCollumn collection : list) {
            if (max<collection.getScore()){
                max=collection.getScore();
                mi=collection.getCol();
            }
        }
        if (max==0){
            return new Random().nextInt(6);
        }
        return mi;
    }
}

