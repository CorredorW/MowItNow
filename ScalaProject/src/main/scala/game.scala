package main.scala

import scala.io.Source.fromResource
import java.io.{FileNotFoundException, IOException}

class Point(val xc:Int, val yc:Int){  //Create a point class in order to get the coordinates
    private var x = xc;
    private var y = yc;

    // Getters and setters
    // Getters and setters
    // Getter x coordinate
    def getX(): Int = {
        return x;
    }
    // Getter y coordinate
    def getY():Int = {
        return y;
    }
    // Setter x coordinate
    def setX(nx:Int){
        x = nx;
    }

    // Setter y coordinate
    def setY(ny:Int){
        y = ny;
    }
}

class Position(override val xc: Int, override val yc:Int, val directionc:Char)extends Point(xc,yc){ //Create a class named Position that inherits the properties from Point and includes a direction
    //that way it will be able to locate the lawnmower in the space.
    private var x = xc; //instance
    private var y = yc; //instance
    private var direction = directionc;

    // Getters and setters
    // Getter  direction
    def getDirection():Char = {
        return direction;
    }
    // Getters and setters
    // setter  direction
    def setDirection(nDirection:Char){
        direction = nDirection;
    }

    def print(){ // toString()
        println("("+x+","+y+","+direction+")")
    }
}

class Mower(val initialPosition:Position, val nombreTendeusec:Int){ //Class that contains the logic behind the game.
    private var position = initialPosition; // Creation of the coordinates of the lawnmower.
    private var nombreTendeuse = nombreTendeusec;
    /**
    Maps are implemented for changing the direction of the lawnmower. To each previous direction
    a new one will correspond depending if it turns to the left or to the right.
     **/

    private val gauche = Map('N'->'W','W'->'S','S'->'E','E'->'N') //Map to turn to the left
    private val droit = Map('N'->'E','E'->'S','S'->'W','W'->'N') //Map to turn to the right

    def move(movingDirection:Char, boardSize:Point){ //method for the movement of the lawnmower
        val actualDirection = position.getDirection();  //getting the direction of the lawnmower.
        val xValue = position.getX(); //getting the x value of the coordinate in the position of the lawnmower.
        val yValue = position.getY(); //getting the y value of the coordinate in the position of the lawnmower.
        val xBoundary = boardSize.getX(); //getting the  width of the board
        val yBoundary = boardSize.getY(); //getting the height of the board


        /**
            After creating the instances, we will focus on the movements of the lawnmower in the board.
            It's recalled that G is a 90 degree turn to the left, D, a 90 degree turn to the right and
            A moves the lawnmower forward one step. Taking into consideration the borders of the board.
         **/
        movingDirection match{
            case 'G'=>position.setDirection(gauche(actualDirection)) // In case that the the command is a turn to the left
            // the map previously created will be used to make a 90 degree turn.
            case 'D'=>position.setDirection(droit(actualDirection)) // In case that the the command is a turn to the right
            // the map previously created will be used to make a 90 degree turn.
            case 'A'=> { // Here we consider the case in which the command is to "advance", we will move in the board and update
                //the variables depending on the command.
                if(actualDirection == 'N' && yValue<yBoundary){ //if the lawnmower is facing to the north,
                    position.setY(yValue + 1);                  // it will move one step forward in the y coordinate.
                }else if(actualDirection == 'S' && yValue>0){   //if the lawnmower is facing to the south,
                    position.setY(yValue - 1);                 // it will move one step back in the y coordinate.
                }else if(actualDirection == 'E' && xValue<xBoundary){ //if the lawnmower is facing to the east,
                    position.setX(xValue + 1);                        // it will move one step forward in the x coordinate.
                }else if(actualDirection == 'W'&& xValue>0){ //if the lawnmower is facing to the south,
                    position.setX(xValue - 1);              // it will move one step back in the x coordinate.
                }
            }
        }
    }

    def print(){ //Method to print the final position of the lawnmower
        println("Tondeuse " +nombreTendeuse+": "+position.getX()+" "+position.getY()+" "+position.getDirection()+"")
    }

}
//We focus on running the game and creating a class dedicated to it where the command to run the game is found.
class Game (val boardSizec:Point, val commandsc:String, initialPositionc:Position,nombreTendeusec:Int){

        private var boardSize = boardSizec;
        private var commands = commandsc;
        private var initialPosition = initialPositionc;
        private var nombreTendeuse = nombreTendeusec;
        private var actualLawnMower = new Mower(initialPosition,nombreTendeuse);  //Create the lawnmower

        def startGame(){  //Method to run the game.
            for (i<-0 to commands.length-1){
                var movingDirection = commands.charAt(i);
                actualLawnMower.move(movingDirection,boardSize);
            }
            actualLawnMower.print();
        }
}


object Demo {


    /** From now on, we will handle the exceptions of the possible errors to find while running the game
     * creating a class for each of the exceptions and conditions which will be validated.
     * Custom exceptions
    * */
    // Custom Exceptions
    class InputException(s:String) extends Exception(s){}  

    class ExceptionCommandsInput{  
        @throws(classOf[InputException])  
        def validate(commands:String){  
            if(!commands.matches("(G|A|D)+")){  
                throw new InputException("Invalid input: "+commands+" use only commands allowed: G D A ")  
            }
        }  
    }  

    class ExceptionPositionInput{  
        @throws(classOf[InputException])  
        def validate(pos:Array[String]){
            var strPos = ""
            for (i <- 0 to pos.length-1){
                strPos+=pos(i)+" ";
            } 
            if(pos.length!=3){  
                throw new InputException("Invalid input: "+strPos+" Only accept 3 arguments\n. Example: 1 2 N");  
            }else if (!(pos(0).matches("[0-9]+")) || !(pos(1).matches("[0-9]+")) || !(pos(2).matches("(N|S|E|W)+"))){
                throw new InputException("Invalid input: "+strPos+" Argument type exception\n. Example: 1 2 N");  
            }
        }
    }  

    class ExceptionSizeInput{  
        @throws(classOf[InputException])  
        def validate(pos:Array[String]){  
            var strPos = ""
            for (i <- 0 to pos.length-1){
                strPos+=pos(i)+" ";
            } 
            if(pos.length!=2){  
                throw new InputException("Invalid input: "+strPos+" Only accept 2 arguments\n. Example: 5 5");  
            }else if (!(pos(0).matches("[0-9]+")) || !(pos(1).matches("[0-9]+"))){
                throw new InputException("Invalid input: "+strPos+" Argument type exception\n. Example: 5 5");  
            }
        }
    }  

    def main(args: Array[String]){ //Main to run our code.

        var eSizeInput = new ExceptionSizeInput();
        val eCommandsInput = new ExceptionCommandsInput();
        val ePositionInput = new ExceptionPositionInput();

    //As the commands are given in a external file, it is necessary to read it.
        try{
            val filename= "input.txt"
            val src = fromResource(filename) //read the file
            val inputContent = src.getLines().toList // check what can happen (non empty and potentially odd size)
            src.close
            val (inputSize,instructions)= inputContent.splitAt(1); // inputsize saves the size of the board, and instructions
            //is a reference to the commands given by the user.
            val size = inputSize(0).toString().split(" "); // create variable what saves the information from inputsize

            //handling exceptions about the size of the board
            try{
                eSizeInput.validate(size); //Validates if the conditions meet to be considered as a size Exception.
            }catch{
                case eSizeInput: Exception => println("ExceptionSize occured "+eSizeInput);
            }

            //setting the board size based on what was read from the file.
            val boardSize = new Point(xc = size(0).toInt,yc = size(1).toInt);
            var instruction:Int  = 0

            while(instruction < (instructions.length)){

                var coordinate = instructions(instruction).toString().split(" ");

                //handling exceptions about the position in the board
                try{
                    ePositionInput.validate(coordinate); //Validates if the conditions meet to be considered as a position Exception.
                }catch{
                    case ePositionInput: Exception => println("ExceptionPosition occured "+ePositionInput);
                }

                val initialPosition  = new Position(xc = coordinate(0).toInt, yc = coordinate(1).toInt, directionc = coordinate(2).charAt(0));
                val commands = instructions(instruction +1).toString();


                //handling exceptions about the commands.
                try{
                    eCommandsInput.validate(commands);
                }catch{
                    case eCommandsInput: Exception => println("ExceptionCommands occured "+eCommandsInput);
                    //Validates if the conditions meet to be considered as a command Exception.
                }
                val nombreTendeuse = instruction/2+1;  //saves the number of the lawnmower.
                val game = new Game(boardSizec = boardSize,commandsc = commands , initialPositionc = initialPosition, nombreTendeusec = nombreTendeuse );

                game.startGame();
                instruction+=2;
            }
        }
        catch{
            case fnfe : FileNotFoundException => println(s"Couldn't find that file : $fnfe")  //to be printed in the given case that the command file isn't found.
        }
    }
}