
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
    private boolean seCoge;

    /**
     * Constructor for objects of class Objeto
     */
    public Objeto(String objeto, float pesoObjeto, boolean seCoge)
    {
        this.objeto = objeto;
        this.pesoObjeto = pesoObjeto;
        this.seCoge = seCoge;
    }

    public boolean getSeCoge()
    {
        return seCoge;
    }

    public void describeObjeto()
    {
        if(objeto != null){
            System.out.println(objeto+". Pesa "+pesoObjeto+" kg." );
        }
    }

    public float getPeso()
    {
        return pesoObjeto;
    }

}