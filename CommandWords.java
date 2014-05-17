import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private HashMap <String, Option> comandosMap; 
    // a constant array that holds all valid command words

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        comandosMap = new HashMap<>();

        comandosMap.put("go",Option.GO);
        comandosMap.put("quit",Option.QUIT);
        comandosMap.put("help",Option.HELP);
        comandosMap.put("look",Option.LOOK);
        comandosMap.put("eat",Option.EAT);
        comandosMap.put("back",Option.BACK);
        comandosMap.put("take",Option.TAKE);
        comandosMap.put("items",Option.ITEMS);
        comandosMap.put("drop",Option.DROP);
        // nothing to do at the moment...
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String comandos = "Los comandos validos son: ";
        System.out.println(comandosMap.keySet());
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(Option comando)
    {
        return comandosMap.containsValue(comando);
    }

    /**
     * Return the Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The Option correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Option getCommandWord(String commandWord)
    {
        Option returnedValue;
        if (comandosMap.containsKey(commandWord)){
            returnedValue = comandosMap.get(commandWord);

        }else{
            returnedValue = Option.UNKNOWN;
        }
        return returnedValue;
    }
}
