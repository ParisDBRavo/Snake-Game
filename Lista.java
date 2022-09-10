//Creo las listas con los nodos utilizados
public class Lista
{
//Creo el final e inicio para poder eliminar más fácil y así mover la serpiente
    Nodo inicio, fin;
    int tam;
    Lista(Nodo n)
    {
        tam=1;
        inicio = n;
        fin = n;
    }

    Lista()
    {
        tam=0;
    }
    
    public void agregar(Nodo n)
    {
        if(tam==0)
        {
            fin = n;
            inicio = n;
            tam++;
        }
        else
        {
//Se pone antes del final para ahorrar y no usar un auxiliar
            n.ponersig(fin);
            fin = n;
            tam++;
        }
    }
    
    public Nodo tenerini()
    {
        return inicio;
    }

    public Nodo tenerfin()
    {
        return fin;
    }
    
    public int tam()
    {
        return tam;
    }

    public int[] avanzar()
    {
        int[] r = new int[2];
        r[0]=fin.obtPos1();
        r[1]=fin.obtPos2();
        fin.recorre();
        return r;
    }





}

