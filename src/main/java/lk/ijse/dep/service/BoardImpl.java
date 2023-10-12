package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private BoardUI boardUI;

    private Piece[][] pieces = new Piece[6][5];


    public BoardImpl(BoardUI boardUI) {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }

        }
        this.boardUI = boardUI;
    }


    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            if (isLegalMove(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int avs = findNextAvailableSpot(col);
        pieces[col][avs] = move;
    }

    @Override
    public void updateMove(int col, int raw, Piece move) {
        pieces[col][raw] = move;
    }

    @Override
    public Winner findWinner() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {

                Piece collWinner = pieces[j][i];
                if (collWinner != Piece.EMPTY && collWinner == pieces[j+1][i] && collWinner == pieces[j+2][i] &&
                        collWinner == pieces[j+3][i]){
                    return new Winner(collWinner,j,j+3,i,i);
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                Piece rowWinner = pieces[i][j];
                if (rowWinner != Piece.EMPTY && rowWinner == pieces[i][j+1] && rowWinner ==pieces[i][j+2] &&
                        rowWinner == pieces[i][j+3]) {
                    return new Winner(rowWinner, i, i, j, j + 3);
                }
            }
        }
        return new Winner(Piece.EMPTY );

    }
}
