package Chess;

import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class AliceChessBoard {

    public static void main(String[] args) {
        AliceChessBoard controller = new AliceChessBoard();
        controller.startGame();
    }

    public void startGame() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File selectedFile = chooser.getSelectedFile();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(selectedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // generate chess boards from the j-file content
        ArrayList<Board> chessBoards = generateBoards(fileScanner);
        
        // a loop through each board
        for (int boardIndex = 0; boardIndex < chessBoards.size(); boardIndex++) {
            //current board
            Board currentBoard = chessBoards.get(boardIndex);
            // print the current board
            currentBoard.print();
            Alice chessSolver = new Alice(currentBoard);
            List<Piece> solutionPath = chessSolver.solve();
            if (solutionPath == null) {
                //if no solution was found
                System.out.println("Board " + (boardIndex + 1) + ": Alice is stuck!");
            } else {
                // solution found, print order of captures. 
                System.out.print("Board " + (boardIndex + 1) + ": Alice should capture in this order: ");
                for (int i = 0; i < solutionPath.size(); i++) {
                    System.out.print(solutionPath.get(i).getSymbol());
                }
            }
            System.out.println();
        }
    }
    // list of chess boards
    ArrayList<Board> generateBoards(Scanner scanner) {
        ArrayList<Board> boardList = new ArrayList<>();
        int totalBoards = scanner.nextInt(); // first int is the total number of boards we want to read in and use in loop
        scanner.nextLine();
        for (int i = 0; i < totalBoards; i++) {
            Board boardInstance = new Board();
            for (int row = 0; row < boardInstance.getHeight(); row++) {
                String line = scanner.nextLine();
                String[] pieceCodes = line.split(" "); // split on spaces
          //note with updates these are the parameters for each of the Piece class' (int row, int col), PieceColor does not matter
                // here is how we will set each piece onto the board
                for (int col = 0; col < pieceCodes.length; col++) {
                    switch (pieceCodes[col]) {
                        case "P": boardInstance.setPiece(row, col, new Pawn(row, col)); 
                        break;
                        case "B": boardInstance.setPiece(row, col, new Bishop(row, col));
                        break;
                        case "R": boardInstance.setPiece(row, col, new Rook(row, col)); 
                        break;
                        case "N": boardInstance.setPiece(row, col, new Knight(row, col));
                        break;
                        case "Q": boardInstance.setPiece(row, col, new Queen(row, col));
                        break;
                        case "K": boardInstance.setPiece(row, col, new King(row, col));
                        break;
                        default: boardInstance.setPiece(row, col, null);
                    }
                }
            }
            boardList.add(boardInstance);
        }
        return boardList;
    }
}
