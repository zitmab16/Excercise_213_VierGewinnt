package BuiLo;

public class BL {

    private Value[][] field = new Value[7][7];
    private Value currentPlayer;

    public BL() {
        reset();
    }

    public void reset() {
        for (int i = 0; i < field.length; i++) {
            Value[] row = field[i];

            for (int j = 0; j < row.length; j++) {
                field[i][j] = Value.EMPTY;
            }
        }
        currentPlayer = Value.X;
    }

    public int makeMove(int column) {
        for (int i = field.length - 1; i > 0; i--) {
            if (field[i][column] == BuiLo.Value.EMPTY) {
                field[i][column] = currentPlayer;
                currentPlayer = (currentPlayer == Value.X) ? Value.O : Value.X;
                return i;

            }
        }

        return -1;
    }

    public Value testWinner() {

        for (int i = 4; i < field.length; i++) {
            for (int j = 0; j < field.length - 3; j++) {
                if (field[i][j] == Value.O
                        && field[i - 1][j + 1] == Value.O
                        && field[i - 2][j + 2] == Value.O
                        && field[i - 3][j + 3] == Value.O) {
                    return Value.O;
                } else if (field[i][j] == Value.X
                        && field[i - 1][j + 1] == Value.X
                        && field[i - 2][j + 2] == Value.X
                        && field[i - 3][j + 3] == Value.X) {
                    return Value.X;
                }
            }
        }

        for (int i = 4; i < field.length; i++) {
            for (int j = 3; j < field.length; j++) {
                if (field[i][j] == Value.O
                        && field[i - 1][j - 1] == Value.O
                        && field[i - 2][j - 2] == Value.O
                        && field[i - 3][j - 3] == Value.O) {
                    return Value.O;
                } else if (field[i][j] == Value.X
                        && field[i - 1][j - 1] == Value.X
                        && field[i - 2][j - 2] == Value.X
                        && field[i - 3][j - 3] == Value.X) {
                    return Value.X;
                }
            }
        }

        for (int i = 1; i < field.length - 3; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == Value.X
                        && field[i + 1][j] == Value.X
                        && field[i + 2][j] == Value.X
                        && field[i + 3][j] == Value.X) {
                    return Value.X;
                } else if (field[i][j] == Value.O
                        && field[i + 1][j] == Value.O
                        && field[i + 2][j] == Value.O
                        && field[i + 3][j] == Value.O) {
                    return Value.O;
                }
            }
        }

        for (int j = 0; j < field.length - 3; j++) {
            for (int i = 1; i < field.length; i++) {
                if (field[i][j] == Value.O
                        && field[i][j + 1] == Value.O
                        && field[i][j + 2] == Value.O
                        && field[i][j + 3] == Value.O) {
                    return Value.O;
                } else if (field[i][j] == Value.X
                        && field[i][j + 1] == Value.X
                        && field[i][j + 2] == Value.X
                        && field[i][j + 3] == Value.X) {
                    return Value.X;
                }
            }
        }

        return BuiLo.Value.DRAW;
    }

    public Value getValueAt(int row, int col) {
        return field[row][col];
    }
}
