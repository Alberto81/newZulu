import java.util.ArrayList;
import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Objeto> mochila;
    public static final float PESO_MAX = 50.0F;
    private Room currentRoom;

    private Stack<Room> camino;
    //habitacion en la que est�
    //habitaciones pasadas
    //metodos
    //goroom, back look eat
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {

        mochila = new ArrayList<Objeto>();
        camino = new Stack<Room>();

    }

    public void setCurrentRoom(Room inicio)
    {
        currentRoom = inicio;
    }

    public void printLocationInfo()
    {
        currentRoom.getLongDescription();

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
        Objeto alSuelo =  tiro(indx);
        if (alSuelo!=null){
            currentRoom.dejaObjeto(alSuelo);
            printLocationInfo();
        }

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
            cogeobjeto(recojo);
            printLocationInfo();
        }else if (recojo!=null){
         System.out.println("no lo puedes coger, pesa demasiado");
        }

    }

    // public boolean puedoCon()
    //{
    // boolean puedo = true;
    // if (PESO_MAX < pesoMochila)
    // {

    //  }
    //  }

    public void backRoom()
    {
        if(!camino.empty()){
            currentRoom = camino.pop();
            printLocationInfo();
        }
        else{
            System.out.println( "estas en la primera habitacion, no puedes retroceder mas");
        }
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direccion = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direccion);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            //    pila.push(currentRoom);//esto aun no va.
            currentRoom = nextRoom;//cambio de habitacion

        }
        printLocationInfo();
    }

    public void come()
    {
        System.out.println( "You have eaten now and you are not hungry any more");
    }

    public float pesoMochila()
    {
        float pesoTotal = 0;
        for(Objeto objeto: mochila ){
            pesoTotal += objeto.getPeso();
        }
        return pesoTotal;
    }

    public void cogeobjeto(Objeto objeto)
    {
        if ((pesoMochila() + objeto.getPeso()) > PESO_MAX){
            System.out.println("no puedes coger el objeto, llevas demasiado peso.");
            System.out.println("");
        }
        else{
            mochila.add(objeto); 

        }
    }

    public Objeto tiro(int indx){
        Objeto devuelve = null;
        if (indx >= 0 && indx < mochila.size()){
            devuelve = mochila.get(indx);
            mochila.remove(indx);
        }
        else{
            System.out.println("ese numero no se corresponde con ningun objeto");
        }
        return devuelve;
    }

    public void imprimeMochila()
    {
        int pos = 0;
        System.out.println("en la mochila tienes:");
        boolean hayAlgo = false;
        for(Objeto objeto: mochila ){
            hayAlgo = true;
            System.out.println("objeto n�: "+pos); 
            objeto.describeObjeto();
            pos++;
        }
        if (!hayAlgo){
            System.out.println("nada.");
        }
        System.out.println("peso total de la mochila: "+pesoMochila()+" Kg.");
        System.out.println("");
    }
}
