package electronicclock;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class ClockGUI2 extends Frame implements ActionListener {
	// GUI
	private Button b1 = new Button("������~");
	private TextField tf1 = new TextField();
	private Label lab1 = new Label("H");
	private TextField tf3 = new TextField();
	private Label lab3 = new Label("M");
	private TextField tf5 = new TextField();
	private Label lab5 = new Label("S");
	
	public ClockGUI2(){
        
    this.setLayout(null);
        
    b1.setSize(100,70);//��ư ũ��
    b1.setForeground(new Color(0,0,255));
    b1.setBackground(new Color(255,180,100));
    b1.setFont(new Font("����",Font.BOLD,20));
    b1.setLocation(170,250); //��ư ������ġ 
    this.add(b1);//��ư �߰� 
    b1.addActionListener(this); ////////////////// 

    tf1.setSize(50,50);
    tf1.setForeground(new Color(0,0,255));
    tf1.setBackground(new Color(255,255,0));
    tf1.setFont(new Font("����",Font.BOLD,35));
    tf1.setLocation(30, 70);//�ؽ�Ʈ ������ġ 
    this.add(tf1); 
    
    lab1.setSize(50,50);
    lab1.setForeground(new Color(0,0,255));
    lab1.setBackground(new Color(255,255,0));
    lab1.setFont(new Font("����",Font.BOLD,35));
    lab1.setLocation(100, 70);//�ؽ�Ʈ ������ġ 
    this.add(lab1); 

    tf3.setSize(50,50);
    tf3.setForeground(new Color(0,0,255));
    tf3.setBackground(new Color(255,255,0));
    tf3.setFont(new Font("����",Font.BOLD,35));
    tf3.setLocation(170,70);//�ؽ�Ʈ ������ġ 
    this.add(tf3);
        
    lab3.setSize(50,50);
    lab3.setForeground(new Color(0,0,255));
    lab3.setBackground(new Color(255,255,0));
    lab3.setFont(new Font("����",Font.BOLD,35));
    lab3.setLocation(240,70);//�ؽ�Ʈ ������ġ 
    this.add(lab3);
        
    tf5.setSize(50,50);
    tf5.setForeground(new Color(0,0,255));
    tf5.setBackground(new Color(255,255,0));
    tf5.setFont(new Font("����",Font.BOLD,35));
    tf5.setLocation(310,70);//�ؽ�Ʈ ������ġ 
    this.add(tf5);
        
    lab5.setSize(50,50);
    lab5.setForeground(new Color(0,0,255));
    lab5.setBackground(new Color(255,255,0));
    lab5.setFont(new Font("����",Font.BOLD,35));
    lab5.setLocation(380,70);//�ؽ�Ʈ ������ġ 
    this.add(lab5);
        
    this.setBackground(new Color(0,200,0)); 
    this.setTitle("�ڹٴ� FUN FUN FUN !!!!!!");
    this.setSize(460, 400); //������ ũ�� 
    this.setVisible(true);
        
      
    addWindowListener(new WindowAdapter(){ //////////////////
        public void windowClosing(WindowEvent e1){        		        		
            dispose();
            System.exit(0);
                         
        }                 
    });
}//������-end        

	@Override
	public void actionPerformed(ActionEvent e1) {
		if(e1.getSource() == b1) { //��ư�� ����������(�̺�Ʈ)
			b1.setEnabled(false); //disable //��Ȱ��ȭ
			Timer t1 = new Timer(); //Thread
			t1.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					Date d1 = new Date();
					tf1.setText(String.valueOf(d1.getHours()));
					tf3.setText(String.valueOf(d1.getMinutes()));
					tf5.setText(String.valueOf(d1.getSeconds()));
				}//run-end
			}, 1000, 1000); //1���Ŀ� 1�ʰ������� //t1�޼ҵ�-end
		}//if-end
	}//actionevent-end
}//ClockGUI2 class-end

public class ClockGUI {

	public static void main(String[] args) {
		ClockGUI2 bb = new ClockGUI2();

	}

}
