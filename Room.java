import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{  //ejercicio0111 para que los atributos de room sean privados
    private HashMap<String, Room> direccion;
    private String description;
    private ArrayList<Objeto> objetos;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)  
    {
        this.description = description;
        direccion = new HashMap<String, Room>();//creamos el hashmap y lo llenamos(ej0112)
        objetos = new ArrayList<Objeto>();

    }

    public void getLongDescription()
    {

        System.out.println("You are " + getDescription());
        for(Objeto objeto: objetos ){
          objeto.describeObjeto();
        }
        System.out.print(getExitString());
        System.out.println(); 
    }
    
    public void addItem (String objeto, float pesoObjeto)
    {
        objetos.add(new Objeto(objeto, pesoObjeto));
    }
        
    public String getExitString()
    {
        String conjuntoSalidas="salidas por: ";
        Set <String> salidas = direccion.keySet();
        for(String dir:salidas){
            conjuntoSalidas = conjuntoSalidas + dir+" ";
        }

        return conjuntoSalidas;
    }

    public Room getExit(String direction )
    {        

        //funcionando con hashmap(ej 0112)
        Room rumbo = direccion.get(direction);

        return rumbo;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    //lo dejo para resumir, pero las direcciones no tipicas se ponen por setExit.
    public void setExits(Room north, Room east, Room south, Room west, Room sureste, Room noroeste) 
    {
        if(north != null)
            direccion.put("north",north) ;
        if(east != null)
            direccion.put("east", east) ;
        if(south != null)
            direccion.put("south", south) ;
        if(west != null)
            direccion.put("west", west) ;
        if(sureste != null)
            direccion.put("sureste", sureste);    
        if(noroeste != null)
            direccion.put("noroeste", noroeste) ;   
    }

    //del ejercicio 13
    public void setExit(String direction, Room neighbor)
    {
        if(neighbor!= null){
            direccion.put(direction, neighbor);
        }
    }

    // create the rooms

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
