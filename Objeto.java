
/**
 * Write a description of class Objeto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objeto
{
    // instance variables - replace the example below with your own
    private String objeto;
    private float pesoObjeto;

    /**
     * Constructor for objects of class Objeto
     */
    public Objeto(String objeto, float pesoObjeto)
    {
        this.objeto = objeto;
        this.pesoObjeto = pesoObjeto;
    }

    public void describeObjeto()
    {
        if(objeto != null){
            System.out.println("There are "+objeto+". Pesa "+pesoObjeto+" kg." );
        }
    }

}