import java.util.Scanner;

class TicTacToeApp{   
    // Class attributes 
    public char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    Scanner myObj = new Scanner(System.in);

    final char player1 = 'x';
    final char player2 = 'o';

    public int curPlayer = 1;

    public int winner = -1;
    public boolean isFull = false;

    // Main method
    public static void main(String[] args){

        TicTacToeApp game = new TicTacToeApp();

        // Call directions method - introduces Tic Tac Toe game and how to play
        game.directions();

        // If game not over... repeat
        while(!game.isOver()){

            // Shows board to console
            game.showBoard();

            // Asks current player where they would like to play till input is valid. Update board
            game.askPlayer();

            // Update curPlayer
            game.updateCurPlayer();
        } 

        // If game over because a player won... end game by showing final board and printing who won.
        game.showBoard();
        game.over();
    }

    public void over(){
        if (winner != -1){
            System.out.println("Player " + winner + " won! Game is over.");
        } else if(isFull){
            System.out.println("Board is full. Players have tied.");
        }
    }

    public boolean isOver(){
        // Check if a player has 3 in a row, column or diagonally - if so update winner
        if(playerWon('x') || playerWon('o')){
            return true;
        }

        // Check if board is full, if so update isFull
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }

        isFull = true;
        return true;
    }

    public int translateTokenToPlayerNum(char token){
        return token == player1 ? 1 : 2;
    }

    public char translatePlayerNumToToken(int playerNum){
        return playerNum == 1 ? player1 : player2;
    }

    public boolean playerWon(char token){
        // Check if token found 3 in a row
        for(int i = 0; i < 3; i++){
            if(board[i][0] == token && board[i][1] == token && board[i][2] == token){
                winner = translateTokenToPlayerNum(token);
                return true;
            }
        }

        // Check if token found 3 in a column
        for(int i = 0; i < 3; i++){
            if(board[0][i] == token && board[1][i] == token && board[2][i] == token){
                winner = translateTokenToPlayerNum(token);
                return true;
            }
        }

        // Check if token found 3 in a diagonal
        if((board[0][0] == token && board[1][1] == token && board[2][2] == token) || (board[0][2] == token && board[1][1] == token && board[2][0] == token)){
            winner = translateTokenToPlayerNum(token);
            return true;
        }

        return false;
    }

    public void showBoard(){
        System.out.println();
        System.out.println("  0 1 2");
        for(int i = 0; i < 3; i++){
            System.out.print(i + " ");
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j]);
                if(j != 2){
                    System.out.print("|");
                }
            }
            System.out.println(" ");
            
            if(i != 2){
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    public void updateCurPlayer(){
        if(curPlayer == 1){
            curPlayer = 2;
        } else{
            curPlayer = 1;
        }
    }

    public void askPlayer(){
        System.out.println("Current player to place a token on board is: Player " + curPlayer + " (" + translatePlayerNumToToken(curPlayer) + ")");

        boolean placed = false;

        while(!placed){
            System.out.println("Enter where would you like to place a token?");
            System.out.print("Respond in format: ROW# COLUMN#. Rows and columns start with number 0: ");
            int inputRow = myObj.nextInt();  
            int inputColumn = myObj.nextInt(); 
        
            if(board[inputRow][inputColumn] != ' '){
                System.out.println("That position is already filled.");
                showBoard();
            } else{
                board[inputRow][inputColumn] = translatePlayerNumToToken(curPlayer);
                placed = true;
            }
        }

    }

    public void directions(){
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("This game is a 2 player game. One player has x pieces. The other player has o pieces.");
        System.out.println("The two players will alternate placing a piece on a 3x3 board till either:");
        System.out.println("1. A player wins by getting 3 of their pieces in a row, column or diagonally first.");
        System.out.println("2. Players tie by filling up the board without any of them winning");
        System.out.println("Good luck!");
    }
    
}