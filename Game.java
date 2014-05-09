
import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!  .
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    
    private Parser parser;
   
    private Stack<Room> pila;
    private Player jugador;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        pila = new Stack<Room>();
        jugador = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

        // create the rooms
       
         // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        jugador.printLocationInfo();//codigo remplazado por metodo
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            jugador.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            jugador.printLocationInfo();
        } 
        else if (commandWord.equals("eat")) {
            System.out.println( "You have eaten now and you are not hungry any more");
        } 
        else if (commandWord.equals("back")) {
            backRoom();
        } 
        else if (commandWord.equals("take")) {
            coge(command);
        } 
        else if (commandWord.equals("items")) {
            jugador.imprimeMochila() ;
        } 
        else if (commandWord.equals("drop")) {
            deja(command);
        } 

        return wantToQuit;
    }

    public void deja(Command command)
    {

        //se desarrollara entre room y player.

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("que dejo?");
            return;
        }
        int indx = Integer.parseInt(command.getSecondWord());
        Objeto tiro =  jugador.tiro(indx);
        if (tiro!=null){
            currentRoom.dejaObjeto(tiro);
        }

        printLocationInfo();
    }

    public void coge(Command command)
    {

        //se desarrollara entre room y player.

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("que cojo?");
            return;
        }
        int indx = Integer.parseInt(command.getSecondWord());

        Objeto recojo =  currentRoom.recogeObjeto(indx);
        if (recojo!=null){
            jugador.cogeobjeto(recojo);
        }

        printLocationInfo();
    }

    public void backRoom()
    {
        if(!pila.empty()){
            currentRoom = pila.pop();
            printLocationInfo();
        }
        else{
            System.out.println( "estas en la primera habitacion, no puedes retroceder mas");
        }
    }

    public void validComandsA()
    {
        parser.getCommands().showAll();
    }

    public void validComandsB()
    {
        parser.imprimeComandos();
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the centro comercial.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help look eat back take items drop ");
    }

    

    // ejercicio 0108, cambiar codigo repetido por petodo privado
   

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
