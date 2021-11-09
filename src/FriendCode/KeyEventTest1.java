package FriendCode;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyEventTest1 extends JFrame {
    JLabel lbl=new JLabel("Hi~~!");
    JPanel pnl=new JPanel();
    public KeyEventTest1(){
        setTitle("Key Test");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnl.addKeyListener(new KeyHandler());
        pnl.setLayout(null);
        lbl.setBounds(30,30,50,20);
        pnl.add(lbl);
        add(pnl);
        setVisible(true);
        pnl.requestFocus();
    }
    
    class KeyHandler implements KeyListener{

		@Override
		
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		
		public void keyPressed(KeyEvent e) {
			setTitle("Key Test("+e.getKeyChar()+","+e.getKeyCode()+")");
            switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                lbl.setLocation(lbl.getX(), lbl.getY()-5);
                break;
            case KeyEvent.VK_DOWN:
                lbl.setLocation(lbl.getX(), lbl.getY()+5);
                break;
            case KeyEvent.VK_LEFT:
                lbl.setLocation(lbl.getX()-5, lbl.getY());
                break;
            case KeyEvent.VK_RIGHT:
                lbl.setLocation(lbl.getX()+5, lbl.getY());
                break;
		}
		}

		@Override
		
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

 
    }
    
    public static void main(String[] args){
        new KeyEventTest1();
    }
}
