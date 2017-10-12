package otello.model;

import otello.view.View;

public class Game {

    public static final int MAX_NR_OF_GAME_PIECES_PER_PLAYER = 32;
    
    private PlayingField playingField;
    private Player playerOne, playerTwo;
    private Player currentPlayer;

    public Game() {
        playingField = new PlayingField();
        playerOne = new Player(State.WHITE, MAX_NR_OF_GAME_PIECES_PER_PLAYER);
        playerTwo = new Player(State.BLACK, MAX_NR_OF_GAME_PIECES_PER_PLAYER);
        currentPlayer = playerOne;
    }

    public void updatePlayingField(int row, int column) {
        //Check if already taken
        if (playingField.getState(row, column) == State.EMPTY) {
            int tempRow = row;
            int tempColumn = column;
            //Up
            if (row > 1) {
                tempRow--;
                if (playingField.getState(tempRow, tempColumn) == flip(currentPlayer.getPlayerColor())) {
                    do {
                        tempRow--;
                        if (playingField.getState(tempRow, tempColumn) == currentPlayer.getPlayerColor()) {
                            for (int i = row; i > tempRow; i--) {
                                playingField.setState(i, tempColumn, currentPlayer.getPlayerColor());
                            }
                        } else if (playingField.getState(tempRow, tempColumn) == State.EMPTY) {
                            break;
                        }
                    } while (tempRow > 0);
                }
            }
            tempRow = row;
            
            //Down
            if(row < playingField.getRows() - 2){
                tempRow++;
                if (playingField.getState(tempRow, tempColumn) == flip(currentPlayer.getPlayerColor())) {
                    do {
                        tempRow++;
                        if (playingField.getState(tempRow, tempColumn) == currentPlayer.getPlayerColor()) {
                            for (int i = row; i < tempRow; i++) {
                                playingField.setState(i, tempColumn, currentPlayer.getPlayerColor());
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
                if (playingField.getState(tempRow, tempColumn) == flip(currentPlayer.getPlayerColor())) {
                    do {
                        tempColumn++;
                        if (playingField.getState(tempRow, tempColumn) == currentPlayer.getPlayerColor()) {
                            for (int i = column; i < tempColumn; i++) {
                                playingField.setState(tempRow, i, currentPlayer.getPlayerColor());
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
                if (playingField.getState(tempRow, tempColumn) == flip(currentPlayer.getPlayerColor())) {
                    do {
                        tempColumn--;
                        if (playingField.getState(tempRow, tempColumn) == currentPlayer.getPlayerColor()) {
                            for (int i = column; i > tempColumn; i--) {
                                playingField.setState(tempRow, i, currentPlayer.getPlayerColor());
                            }
                        } else if (playingField.getState(tempRow, tempColumn) == State.EMPTY) {
                            break;
                        }
                    } while (tempColumn > 0);
                }
            }
            tempColumn = column;

            if (currentPlayer == playerOne) {
                playingField.setState(row, column, State.WHITE);
            } else {
                playingField.setState(row, column, State.BLACK);
            }
        }

    }
    
    public void updateCurrentPlayer(){
        if(currentPlayer == playerOne){
            currentPlayer = playerTwo;
        }
        else{
            currentPlayer = playerOne;
        }
    }

    public PlayingField getPlayingField() {
        return playingField;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void removePieceFromCurrentPlayer(){
        currentPlayer.setGamePiecesRemaining(currentPlayer.getGamePiecesRemaining() - 1);
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
}
