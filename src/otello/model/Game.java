package otello.model;

public class Game {

    private static final int MAX_NR_OF_GAME_PIECES_PER_PLAYER = 32;

    private PlayingField playingField;
    private Player playerOne, playerTwo;
    private State currentColorPlaying;

    public Game() {
        currentColorPlaying = State.BLACK;
        playingField = new PlayingField();
        playerOne = new Player(State.BLACK, MAX_NR_OF_GAME_PIECES_PER_PLAYER);
        playerTwo = new Player(State.WHITE, MAX_NR_OF_GAME_PIECES_PER_PLAYER);
    }

    public void updatePlayingField(int row, int column) {
        //Check if already taken
        if (playingField.getState(row, column) == State.EMPTY) {
            int tempRow = row;
            int tempColumn = column;
            //Up
            if (row > 1) {
                tempRow--;
                if (playingField.getState(tempRow, tempColumn) == flip(currentColorPlaying)) {
                    do {
                        tempRow--;
                        if (playingField.getState(tempRow, tempColumn) == currentColorPlaying) {
                            for (int i = row; i > tempRow; i--) {
                                playingField.setState(i, tempColumn, currentColorPlaying);
                            }
                        } else if (playingField.getState(tempRow, tempColumn) == State.EMPTY) {
                            break;
                        }
                    } while (tempRow > 0);
                }
            }
            tempRow = row;

            //Down
            if (row < playingField.getRows() - 2) {
                tempRow++;
                if (playingField.getState(tempRow, tempColumn) == flip(currentColorPlaying)) {
                    do {
                        tempRow++;
                        if (playingField.getState(tempRow, tempColumn) == currentColorPlaying) {
                            for (int i = row; i < tempRow; i++) {
                                playingField.setState(i, tempColumn, currentColorPlaying);
                            }
                        } else if (playingField.getState(tempRow, tempColumn) == State.EMPTY) {
                            break;
                        }
                    } while (tempRow < playingField.getRows() - 1);
                }
            }

            tempRow = row;

            //Right
            if (column < playingField.getColumns() - 2) {
                tempColumn++;
                if (playingField.getState(tempRow, tempColumn) == flip(currentColorPlaying)) {
                    do {
                        tempColumn++;
                        if (playingField.getState(tempRow, tempColumn) == currentColorPlaying) {
                            for (int i = column; i < tempColumn; i++) {
                                playingField.setState(tempRow, i, currentColorPlaying);
                            }
                        } else if (playingField.getState(tempRow, tempColumn) == State.EMPTY) {
                            break;
                        }
                    } while (tempColumn < playingField.getColumns() - 1);
                }
            }
            tempColumn = column;

            //Left
            if (column > 1) {
                tempColumn--;
                if (playingField.getState(tempRow, tempColumn) == flip(currentColorPlaying)) {
                    do {
                        tempColumn--;
                        if (playingField.getState(tempRow, tempColumn) == currentColorPlaying) {
                            for (int i = column; i > tempColumn; i--) {
                                playingField.setState(tempRow, i, currentColorPlaying);
                            }
                        } else if (playingField.getState(tempRow, tempColumn) == State.EMPTY) {
                            break;
                        }
                    } while (tempColumn > 0);
                }
            }
            tempColumn = column;

            if (currentColorPlaying == State.WHITE) {
                playingField.setState(row, column, State.WHITE);
            } else {
                playingField.setState(row, column, State.BLACK);
            }

            updateCurrentPlayer();
        }
    }

    public void updateCurrentPlayer() {
        if (currentColorPlaying == State.WHITE) {
            currentColorPlaying = State.BLACK;
        } else {
            currentColorPlaying = State.WHITE;
        }
    }

    public PlayingField getPlayingField() {
        return playingField;
    }

    public State getCurrentColorPlaying() {
        return currentColorPlaying;
    }

    private State flip(State state) {
        State newState = State.EMPTY;
        if (state == State.WHITE) {
            newState = State.BLACK;
        }
        if (state == State.BLACK) {
            newState = State.WHITE;
        }
        return newState;
    }

    public int getMaxNrOfGamePiecesPerPlayer() {
        return MAX_NR_OF_GAME_PIECES_PER_PLAYER;
    }

    public int getGamePiecesRemaining(State color) {
        switch(color){
            case WHITE:{
                return playerOne.getGamePiecesRemaining();
            }
            case BLACK:{
                return playerTwo.getGamePiecesRemaining();
            }
            default:{
                throw new IllegalPlayerColorException("Illegal Player color.");
            }
            
        }
    }
    
    public void reset(){
        currentColorPlaying = State.BLACK;
        playingField.reset();
        playerOne = new Player(State.BLACK, MAX_NR_OF_GAME_PIECES_PER_PLAYER);
        playerTwo = new Player(State.WHITE, MAX_NR_OF_GAME_PIECES_PER_PLAYER);
    }
}
