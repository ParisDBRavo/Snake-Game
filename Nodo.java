//Aquí creo los nodos y todas las funciones que se requieren de ellos
public class Nodo
{
//posicion para (x,y) como un arreglo
    int[] Pos;
    Nodo sig;
    
    Nodo(int a, int b)
    {
        Pos = new int[2];
        Pos[0] = b;
        Pos[1] = a;
    }
//Inicializo
    Nodo(int a, int b, Nodo x)
    {
        this(a,b);
        sig = x;
    }
//para obtener las coordenadas
    public int obtPos1()
    {
        return Pos[0];
    }

    public int obtPos2()
    {
        return Pos[1];
    }

    public void Colo(int a,int b)
    {
        Pos[0]=a;
        Pos[1]=b;
    }

    public boolean coincid(int a,int b)
    {
        if(a!=Pos[0]||b!=Pos[1])
          return false;
        else
          return true;
    }

    public void ponersig(Nodo s)
    {
        sig = s;
    }

    public Nodo tenersig()
    {
        return sig;
    }
//ver si en alguna coor, hay un elemento de la lista
    public boolean esta(int a,int b)
    {
        if(coincid(a,b))
          return true;
        if(sig!=null)
        {
          if(sig.esta(a,b))
            return true;
          else
            return false;
        }
        else
          return false;
    }

    public void recorre()
    {
        if(sig!=null)
        {

          Colo(sig.obtPos1(),sig.obtPos2());
          sig.recorre();
        }
    }


}


