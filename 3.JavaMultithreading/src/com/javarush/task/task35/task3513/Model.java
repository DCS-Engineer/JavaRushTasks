package com.javarush.task.task35.task3513;

import java.io.File;
import java.util.*;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack<Tile[][]> previousStates;
    private Stack<Integer> previousScores;
    private boolean isSaveNeeded = true;
    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
        this.score = 0;
        this.maxTile = 2;
        previousStates = new Stack<Tile[][]>();
        previousScores = new Stack<Integer>();
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                if (gameTiles[i][j].value == 0){
                    result.add(gameTiles[i][j]);
                }
            }
        }
        return result;
    }

    void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles != null && !emptyTiles.isEmpty()){
            emptyTiles.get((int) (emptyTiles.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    protected void resetGameTiles(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles){
        boolean isChange = false;
        Tile tempTile;
        for (int i = 0; i < FIELD_WIDTH - 1; i++){
            for (int j = 0; j < FIELD_WIDTH - 1; j++){
                if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0){
                    tempTile = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = tempTile;
                    isChange = true;
                }
            }
        }
        return isChange;
    }

    private boolean mergeTiles(Tile[] tiles){
        boolean isChange = false;
        for (int i = 0; i < FIELD_WIDTH - 1; i++){
            if (tiles[i].getValue() != 0 && tiles[i].getValue() == tiles[i + 1].getValue()){
                tiles[i].setValue(tiles[i].getValue() * 2);
                tiles[i + 1].setValue(0);
                if (tiles[i].getValue() > maxTile){
                    maxTile = tiles[i].getValue();
                }
                score += tiles[i].getValue();
                isChange = true;
            }
        }
        if (isChange) {
            compressTiles(tiles);
        }
        return isChange;
    }

    public void left(){
        if (isSaveNeeded){
            saveState(this.gameTiles);
        }
        boolean isChange = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (mergeTiles(gameTiles[i]) | compressTiles(gameTiles[i])){
                isChange = true;
            }
        }
        if (isChange){
            addTile();
            isSaveNeeded = true;
        }
    }

    public void up(){
        saveState(this.gameTiles);
        rotateArray();
        rotateArray();
        rotateArray();
        left();
        rotateArray();
    }

    public void right(){
        saveState(this.gameTiles);
        rotateArray();
        rotateArray();
        left();
        rotateArray();
        rotateArray();
    }

    public void down(){
        saveState(this.gameTiles);
        rotateArray();
        left();
        rotateArray();
        rotateArray();
        rotateArray();
    }

    private void rotateArray(){
        Tile[][] tempArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        int k = 3;
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                tempArray[j][k] = gameTiles[i][j];
            }
            k--;
        }
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                gameTiles[i][j] = tempArray[i][j];
            }
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty()){
            return true;
        }
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH - 1; j++){
                if (gameTiles[i][j].getValue() == gameTiles[i][j + 1].getValue()){
                    return true;
                }
            }
        }
        for (int j = 0; j < FIELD_WIDTH; j++){
            for (int i = 0; i < FIELD_WIDTH - 1; i++){
                if (gameTiles[i][j].getValue() == gameTiles[i + 1][j].getValue()){
                    return true;
                }
            }
        }
        return false;
    }

    private void saveState(Tile[][] tiles){
        Tile[][] savedTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                savedTile[i][j] = new Tile(tiles[i][j].getValue());
            }
        }
        previousStates.push(savedTile);
        int savedScore = score;
        previousScores.push(savedScore);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousScores.empty() && !previousStates.empty()){
            this.gameTiles = previousStates.pop();
            this.score = previousScores.pop();
        }
    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged(){
        int sumCurrent = 0;
        int sumPrevious = 0;
        if (!previousStates.empty()) {
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    sumCurrent += gameTiles[i][j].getValue();
                    sumPrevious += previousStates.peek()[i][j].getValue();
                }
            }
        }
        return sumCurrent != sumPrevious;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()){
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        else {
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return moveEfficiency;
    }

    public void autoMove(){
        PriorityQueue<MoveEfficiency> movesQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        movesQueue.offer(getMoveEfficiency(this::left));
        movesQueue.offer(getMoveEfficiency(this::right));
        movesQueue.offer(getMoveEfficiency(this::up));
        movesQueue.offer(getMoveEfficiency(this::down));
        Move bestMove = movesQueue.peek().getMove();
        bestMove.move();
    }
}
