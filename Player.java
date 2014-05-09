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
    public Room plaza, zapateria, tiendaRopa, peluqueria, descansillo, servicios, salida;
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
        plaza = new Room("in plaza,una amplia plaza redonda en el medio del centro comercial");
        zapateria= new Room("in zapateria, una tienda de zapatos");
        tiendaRopa = new Room("in tienda ropa, una tienda de ropa");
        peluqueria = new Room("in peluqueria, la peluqueria del centro comercial");
        descansillo = new Room("in descansillo, un espacio amplio al sur del centro comercial");
        servicios = new Room("in ba�o, los WC del centro comercial");
        salida = new Room("in la salida, encontraste la salida del centro comercial!");
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
        plaza.addItem("una gran estatua de bronce dentro de una fuente decorativa.", 3000.0F, false);
        plaza.addItem("una cartera perdida.", 0.05F, true);
        zapateria.addItem("una moneda.", 0.005F, true);
        peluqueria.addItem("una revista.",0.2F, true);
        servicios.addItem("una llave.", 0.03F, true);
        descansillo.addItem("una maquina de refrescos.", 150.0F, false);// asta aki estaba en createrooms
        mochila = new ArrayList<Objeto>();
        camino = new Stack<Room>();
        currentRoom = plaza;
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
        }

       
    }

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
