package otello.model;

public class PlayingField {
    
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;
    
    private State[][] playingFieldState;
    
    public PlayingField(){
        playingFieldState = new State[ROWS][COLUMNS];
        initPlayingField();
    }
    
    public void initPlayingField(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if((i == 3 && j == 3) || (i == 4 && j == 4)){
                    playingFieldState[i][j] = State.WHITE;
                }
                else if((i == 3 && j == 4) || (i == 4 && j == 3)){
                    playingFieldState[i][j] = State.BLACK;
                }
                else{
                    playingFieldState[i][j] = State.EMPTY;
                }
            }
        }
    }
    
    public State getState(int row, int column){
        if(row < 0 || row >= ROWS || column < 0 || column >= COLUMNS){
            throw new IllegalArgumentException("Invalid argument");
        }
        return playingFieldState[row][column];
    }
    
    public void setState(int row, int column, State state){
        if(row < 0 || row >= ROWS || column < 0 || column >= COLUMNS || state == null){
            throw new IllegalArgumentException("Invalid argument");
        }
        playingFieldState[row][column] = state;
    }
    
    public int getRows(){
        return ROWS;
    }
    
    public int getColumns(){
        return COLUMNS;
    }
    
    public void reset(){
        initPlayingField();
    }
}
