package Chess;

import java.util.*;

public class Alice {
    private Board chessBoard;
    private Piece activePiece = null;

    public Alice(Board board) {
        this.chessBoard = board; //current provided board to this object
    }

    public List<Piece> solve() {
    	//initialize variables to start from the bottom being the first position
        int row = chessBoard.getHeight() - 1;
        int col = 0;
        Piece piece;
        //finding the piece in the bottom row (non-null)
        while (col < chessBoard.getWidth()) {
            piece = chessBoard.getPiece(row, col);
            if (piece != null) {
                activePiece = piece; // set the piece if there is one on the bottom row
                break;
            }
            col++;
        }
        //No starting piece found
        if (activePiece == null) {
            return null;
        }

        //use this as a queue for all of the possible paths we could take 
        Queue<List<Piece>> possiblePaths = new LinkedList<>();
        // start with the active piece from bottom row
        List<Piece> initialPath = new ArrayList<>();
        initialPath.add(activePiece);
        possiblePaths.offer(initialPath);

        //check every path possible
        while (!possiblePaths.isEmpty()) {
            List<Piece> currentPath = possiblePaths.poll(); 
            //get and remove the path from the queue
            Piece lastPiece = currentPath.get(currentPath.size() - 1);
            // check if the path is a solution
            if (lastPiece.getRow() == 0 && currentPath.size() == chessBoard.countPieces()) {
                return currentPath; //valid path 
            }

            //all possible moves for the last piece
            List<Piece> nextMoves = possibleMoves(lastPiece, currentPath);
            // goes through each move and creates a new path with that move, then adds it to the queue
            for (int i = 0; i < nextMoves.size(); i++) {
                Piece nextMove = nextMoves.get(i);
                List<Piece> newPath = new ArrayList<>(currentPath);
                newPath.add(nextMove);
                possiblePaths.offer(newPath);
            }
        }
        return null;
    }

    private List<Piece> possibleMoves(Piece piece, List<Piece> currentPath) {
        List<Piece> possibleMoves = new ArrayList<>();
        int currentRow = piece.getRow();
        int currentCol = piece.getCol();
        // using to check all board positions for valid moves
        for (int i = 0; i < chessBoard.getHeight(); i++) {
            for (int j = 0; j < chessBoard.getWidth(); j++) {
                //IF the move is valid AND the piece is not alredy in the path, add it to the possible moves - carter
                if (chessBoard.isValidMove(currentRow, currentCol, i, j) && !currentPath.contains(chessBoard.getPiece(i, j))) {
                    possibleMoves.add(chessBoard.getPiece(i, j));
                }
            }
        }
        return possibleMoves;
    }
}
