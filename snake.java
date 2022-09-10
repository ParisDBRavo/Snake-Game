import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;



public class snake extends JFrame implements KeyListener,Runnable
//le agregué el jframe para usar repaint
{
    Lista A;
    int pos[];
    int k;
    Boolean en, bandera, comienzo;
//q y w son las posiciones de la fruta que come la viborita
    int x0,y0,incrX, incrY, q, w,cont;
    Thread myThread;  

  public snake()
  {
//Declaro las variables que voy a usar.
    addKeyListener(this);
    setVisible(true);
    setLayout(null);
    setTitle("Snake");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(500,500));
    setBounds(400,150,504,504);
    bandera = false;
    comienzo = false;
    en = true;
    myThread = new Thread(this);
    x0 = y0 = 150;
    
    incrX = 1;
    
    incrY = 0;
    A = new Lista();
  }

  public void paint(Graphics g)
  { 
    if(!bandera)
    {
      g.setColor(Color.green);
      g.fillRect(0,0,504,504);
      g.setColor(Color.black);
      g.drawString("Snake", 100, 70);
      g.drawString("Presione enter para iniciar", 100, 100);
      g.drawString("Ignorar los cuadrados negros que no se mueven.", 100, 130);
      g.drawString("Por: Paris Alejandro.", 100, 160);
    }
    if(bandera&&en)
    {
        g.setColor(Color.green);
 //   g.clearRect(x-1, y-1, x+1, y+1);
        g.fillRect(0,0,500,500);

//Para iniciar con una snake
        A.agregar(new Nodo(144,144));
        A.agregar(new Nodo(144-12,144));
        g.setColor(Color.black);
        g.fillRect(144,144,10,10);
        g.fillRect(144-12,144,10,10);
        k=1;
        fruta(g);
        en =false;
    }
    else
    {   

        x0=x0+(incrX*12);
        y0=y0+(incrY*12);
//por si la cola ya esta en x0,y0... casos en donde llegas a game over
        if(A.tenerfin().esta(x0,y0)||x0<12||x0>=492||y0<36||y0>=492)
        {   
            g.setColor(Color.green);
            g.fillRect(0,0,504,504);
            g.setColor(Color.black);
            g.drawString("GAME OVER",190,100);
            bandera=false;
            en=true;
            A = new Lista();
            x0=150;
            y0=150;
            incrX=1;
            incrY=0;
        }
        else
        {
//Para mover nomás lo muevo y pinto la parte donde pasó :v
            pos = A.avanzar();
            A.tenerini().Colo(x0,y0);
            g.setColor(Color.black);
            g.fillRect(x0,y0,10,10);
            g.fillRect(x0,y0,10,10);

//Revisa si hay fruta
            if(q==x0&&w==y0)
            {
                A.agregar(new Nodo(pos[0],pos[1]));
                fruta(g);

                cont=cont+1;

            }
            else
            {
                g.setColor(Color.green);
                g.fillRect(pos[0],pos[1],10,10);
                        
            }
        }
    }
  }
  

  
  public void destroy()
  
  {
    
    myThread = null;
    
  }
  
  public void interrupt()
  
  {
    
    myThread = null;
    
  }
  



 public static void main(String[] args)
  {
    snake js = new snake();
  }


//No entendí el otro archivo por lo que hice esto.
    public void run()
    {
        while(comienzo)
        {
//cambia el punto inmediato al de jugar lo deje igual porque me gustó como se veía, no influye en el juego
            x0=144;
            y0=144;
            while(bandera)
            {
                repaint();
                try
                {
                    myThread.sleep(400-A.tam());
                }
                catch(InterruptedException e)
                {
                    System.out.println(e);
                }
            }
        en=true;
        repaint();
        while(!bandera)
        {
            try
            {
                myThread.sleep(10);
            }
            catch(InterruptedException e)
            {
            System.out.println(e);
            }
        }
        }
    }
  public void keyTyped(KeyEvent e) 
  {
      displayInfo(e, "KEY TYPED: ");
  }

  public void keyPressed(KeyEvent e) {
      displayInfo(e, "KEY PRESSED: ");
  }

  public void keyReleased(KeyEvent e) {
      displayInfo(e, "KEY RELEASED: ");
  }

  private void displayInfo(KeyEvent e, String keyStatus){
    switch(e.getKeyCode()){
      case KeyEvent.VK_ESCAPE:
        System.exit(0);
      case KeyEvent.VK_ENTER:
        if(keyStatus.equals("KEY PRESSED: ")){
          bandera=!bandera;
        if(!comienzo){
            myThread.start();
            comienzo=true;
        }}
        break;

      case 38:
        incrX=0;
        incrY=-1;
        break;
      case 40:
        incrX=0;
        incrY=1;
        break;
      case 37:
        incrX=-1;
        incrY=0;
        break;
      case KeyEvent.VK_RIGHT:
        incrX=1;
        incrY=0;
        break;
      default:
        System.out.println(e.getKeyCode());
        break;
    }
  }
      public void fruta(Graphics g)
    {
        int t;
        int r;
        int aux;
        do
        {
            t=12*(1+(int)(Math.random()*39.1));
            r=12*(3+(int)(Math.random()*37.1));
        }
        while(A.tenerfin().esta(t,r));
        g.setColor(Color.blue);
        g.fillRect(t,r,10,10);
        if(k>1)
        {
            aux=k-1;
            g.setColor(Color.green);
            g.drawString("Puntuacion :" +aux,190,100);
        }
        g.setColor(Color.blue);
        g.drawString("Puntuacion :" +k,190,100);
        k=k+1;        
        q=t;
        w=r;   
    }
}
