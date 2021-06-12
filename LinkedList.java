import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LinkedList
{
    String typed = "";

    Bag bag = new Bag();

    JFrame j = new JFrame();
    JPanel p = new JPanel();
    Graphics g = null;

    Node node;
    Node readOnlyCursor = new Node();
    Node cur = new Node();
    Node cursor = new Node();
    int i = 1;

    public class Strng {
        public Strng(String sentence) {
            this.sentence = sentence;
        }
        String sentence;
        int x;
        int y;
    }

    public class Bag {
    
        ArrayList<Strng> sentences = new ArrayList<Strng>();
        
        public Bag() {
            Strng sentence = new Strng("The cat is on the rug.");
            sentences.add(sentence);
            
            sentence = new Strng("The cat is on the grass.");
            sentences.add(sentence);
            
            sentence = new Strng("The spider is on the wall.");
            sentences.add(sentence);

            sentence = new Strng("The clock is on the wall in the bedroom.");
            sentences.add(sentence);

            sentence = new Strng("The rock broke.");
            sentences.add(sentence);

            sentence = new Strng("You fucked me.");
            sentences.add(sentence);

            sentence = new Strng("At is.");
            sentences.add(sentence);
        }
        
        public ArrayList<Strng> getSentences() {
            
            return this.sentences;
        }
    }

    class Node
    {
        Node node;
        Object obj;
    }


    public  LinkedList () 
    {

        j.setLayout(null);
        p.setLayout(null);
        j.setBounds(0, 0, 1000, 800);
        p.setBounds(j.getBounds());
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

        g = p.getGraphics();

        node = new Node();
        node.obj = bag.sentences.get(0);
        ((Strng)node.obj).x = 400;
        ((Strng)node.obj).y = 100;
        cur = node;
        
        node.node = new Node();
        node.node.obj = bag.sentences.get(1);
        ((Strng)node.node.obj).x = 400;
        ((Strng)node.node.obj).y = 140;
        
        node.node.node = new Node();
        node.node.node.obj = bag.sentences.get(2);
        ((Strng)node.node.node.obj).x = 400;
        ((Strng)node.node.node.obj).y = 180;
        
        node.node.node.node = new Node();
        node.node.node.node.obj = bag.sentences.get(3);
        ((Strng)node.node.node.node.obj).x = 400;
        ((Strng)node.node.node.node.obj).y = 220;
        
        node.node.node.node.node = new Node();
        node.node.node.node.node.obj = bag.sentences.get(5);
        ((Strng)node.node.node.node.node.obj).x = 400;
        ((Strng)node.node.node.node.node.obj).y = 260;

        node.node.node.node.node.node = new Node();
        node.node.node.node.node.node.obj = bag.sentences.get(6);
        ((Strng)node.node.node.node.node.node.obj).x = 400;
        ((Strng)node.node.node.node.node.node.obj).y = 300;

        move();
    }

    public void move() {
        j.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                typed += String.valueOf(e.getKeyChar()).toUpperCase();
                j.setTitle(typed);
                System.out.println(typed);
                System.out.println(((Strng)node.node.node.node.node.node.obj).sentence.toUpperCase());
                if(typed.equals(((Strng)node.obj).sentence.toUpperCase())) {
                    ((Strng)node.obj).sentence = "";
                    typed = "";
                }
                if(typed.equals(((Strng)node.node.obj).sentence.toUpperCase())) {
                    ((Strng)node.node.obj).sentence = "";
                    typed = "";
                }
                if(typed.equals(((Strng)node.node.node.obj).sentence.toUpperCase())) {
                    ((Strng)node.node.node.obj).sentence = "";
                    typed = "";
                }
                if(typed.equals(((Strng)node.node.node.node.obj).sentence.toUpperCase())) {
                    ((Strng)node.node.node.node.obj).sentence = "";
                    typed = "";
                }
                if(typed.equals(((Strng)node.node.node.node.node.obj).sentence.toUpperCase())) {
                    ((Strng)node.node.node.node.node.obj).sentence = "";
                    typed = "";
                }
                if(typed.equals(((Strng)node.node.node.node.node.node.obj).sentence.toUpperCase())) {
                    ((Strng)node.node.node.node.node.node.obj).sentence = "";
                    typed = "";
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });

        Thread t = new Thread() {
            public void run() {
                while(true) {
                    //Clear Screen
                    g.setColor(Color.green);
                    g.fillRect(0, 0, 1000, 800);;
                    //Operateion .
                    traverseLinkedList(node);
                    try {
                        Thread.sleep(1000);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t.start();
    }

    public Node update(int iii, int ii, Node node) {
        if(iii < ii)
            return update(iii+1, ii, node.node);
        i = iii;
        return node;
    }

    public Node updateDil(int iii, int ii, Node node) {
        if(iii < ii-1)
            return updateDil(iii+1, ii, node.node);
        i = iii;
        return node;
    }

    public void deleteNode(int ii) {

        Node nBefore = node;
        cursor.node = updateDil(i, ii, cur.node);
        if(i-1 >= ii-1) {
            cursor.node = cursor.node.node;
            nBefore.node = null;
        } else if(i-1 == ii - 2) {
            if(cursor.node.node.node != null) {
                cursor.node.node = cursor.node.node.node;
                cursor.node = null;
            } else {
                cursor.node.node = null;
            }
        }
    }

    public void addNode(int ii, String g) {

        Node nod = new Node();
        nod.obj = g;
        Node nBefore = node;
        if(ii == 0) {
            nod.node = cur.node;
            node.node = nod;
        }
        if(ii != 0) {
            cursor.node = update(i, ii, cur.node);
            if(i-1 >= ii) {
                nod.node = cursor.node;
                cursor = nod;
            } else if(i-1 == ii - 1) {
                if(cursor.node.node != null) {
                    nod.node = cursor.node.node;
                    cursor.node.node = nod;
                } else {
                    nod.node = null;
                    cursor.node.node = nod;
                }
            }
        }
    }

    public Node traverseLinkedList(Node n)
    {

        if(n != null) {
            try
            {
                g.setFont(new Font("arial", Font.PLAIN, 20));

                g.setColor(new Color(255, 255, 255));
                
                g.drawString(((Strng)n.obj).sentence, ((Strng)n.obj).x, ((Strng)n.obj).y);

            } catch(Exception e)
            {
                e.printStackTrace();
            }

            this.traverseLinkedList(n.node);

            return null;
        }

        return n;
    }

    public static void main(String[] args) {

        LinkedList ll = new LinkedList();
    }
}