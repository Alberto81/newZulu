import java.util.ArrayList;
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

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        mochila = new ArrayList<Objeto>();
    }

    public float pesoMochila()
    {
        float pesoTotal = 0;
        for(Objeto objeto: mochila ){
            pesoTotal =+ objeto.getPeso();
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

    public void imprimeMochila()
    {
        int pos = 0;
        System.out.println("en la mochila tienes:");
        boolean hayAlgo = false;
        for(Objeto objeto: mochila ){
            hayAlgo = true;
            System.out.println("objeto nº: "+pos); 
            objeto.describeObjeto();
            pos++;
        }
        if (!hayAlgo){
            System.out.println("nada.");
        }
        System.out.println("peso total de la mochila: "+pesoMochila()+".");
        System.out.println("");
    }
}
