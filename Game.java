
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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{

    private Parser parser;

    private Player jugador;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        jugador = new Player();
        createRooms();
    }

    private void createRooms()
    {
        Room zapateria= new Room("una tienda donde venden todo tipo de calzado");
        Room plaza = new Room("in plaza,una amplia plaza redonda en el medio del centro comercial");
        Room tiendaRopa = new Room("in tienda ropa, una tienda de ropa");
        Room peluqueria = new Room("in peluqueria, la peluqueria del centro comercial");
        Room descansillo = new Room("in descansillo, un espacio amplio al sur del centro comercial");
        Room servicios = new Room("in baño, los WC del centro comercial");
        Room salida = new Room("in la salida, encontraste la salida del centro comercial!");
        // initialise room exits***modificado para la 0110
        plaza.setExits(zapateria, peluqueria, descansillo, tiendaRopa, null, null);
        zapateria.setExits(null, null, plaza, null, peluqueria, null);
        tiendaRopa.setExits(null, plaza, null, null, null, null);
        peluqueria.setExits(null, null, null, plaza, null, zapateria);
        descansillo.setExits(plaza, servicios, salida, null, null, null);
        servicios.setExits(null, null, null, descansillo, null, null);
        salida.setExits(descansillo, null, null, null, null, null);
        plaza.setExit("escala", servicios);
        servicios.setExit("bucea", plaza);
        zapateria.setExit("salta", salida);
        salida.setExit("atlas", zapateria);
        tiendaRopa.setExit("rie", salida);
        descansillo.setExit("vuela", salida);
        //creo objetos en las habitaciones.
        plaza.addItem("una gran estatua de bronce dentro de una fuente decorativa.", 49.950F, true);
        plaza.addItem("una cartera perdida.", 0.05F, true);
        zapateria.addItem("una moneda.", 0.005F, true);
        peluqueria.addItem("una revista.",0.2F, true);
        servicios.addItem("una llave.", 0.03F, true);
        descansillo.addItem("una maquina de refrescos.", 150.0F, false);// asta aki estaba en createrooms
        
        jugador.setCurrentRoom(plaza);
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {   
        createRooms();
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
            jugador.come();
        } 
        else if (commandWord.equals("back")) {
            jugador.backRoom();
        } 
        else if (commandWord.equals("take")) {
            jugador.coge(command);
        } 
        else if (commandWord.equals("items")) {
            jugador.imprimeMochila() ;
        } 
        else if (commandWord.equals("drop")) {
            jugador.deja(command);
        } 

        return wantToQuit;
    }

    
    
    private void validComandsA()
    {
        parser.getCommands().showAll();
    }

    private void validComandsB()
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
