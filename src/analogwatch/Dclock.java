package analogwatch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * �̸� �˰��ô�
 * 
 * ���� = ���� �ѷ� (����)�� ������ ������ ��(��, ����/����=3.141592)
 * ==> �� ���� ���� �ѷ� ���̴� �׻� ���� ������*2*3.14�ϸ� ������ �ȴٴ� ��
 *     1414213
 *
 *  1����=���������� �������� ȣ�� ���̰� 1:1 ���� ���, �� 57.4��
 *  �̶� 180���� ���̶��� (90���� ����/2 360���� 2����)
 *  �׷��Ƿ� 1�� = ����/180 ���� ==> Math.PI/180 ����
 *  
 *  sin�� 0		~ 180  > 0 , 180 ~ 360 < 0
 *  cos�� 270 	~ 90   > 0 , 90  ~ 270 < 0
 */

class ClockGUI2 extends JFrame implements ActionListener{///
	private JButton b1 = new JButton("Start");
	
	static int r1,cX,cY,sooX,sooY,secX,secY,minX,minY,hourX,hourY;
    static int si,boon,cho;
    
    public ClockGUI2(){ //������ 
            this.setLayout(null);/////////
	        b1.setSize(100,70);//��ư ũ��
	        b1.setForeground(new Color(0,0,255));
	        b1.setBackground(new Color(255,180,100));
	        b1.setFont(new Font("����",Font.BOLD,15));
	        b1.setLocation(50,50); //��ư ������ġ 
	            this.add(b1);//��ư �߰� 
	        b1.addActionListener(this); ////////////////// 
            
            this.setBackground(new Color(200,255,100)); 
            this.setTitle("�ڹٴ� FUN FUN FUN !!!!!!");
            this.setSize(1100, 1000); //������ ũ��  //////////////////
            this.setVisible(true);
    
            addWindowListener(new WindowAdapter(){ //////////////////
                     public void windowClosing(WindowEvent e1){
                             dispose();
                             System.exit(0);
                     }
            });
            
            
    }//������-end     
    
    public void paint(Graphics g) {
    		//���� �޼ҵ�, ��ü
    	Date d1=new Date();
    	si=d1.getHours();//���⼭ �����нð���, �Ƴ��α׽ð���
    	boon=d1.getMinutes();
    	cho=d1.getSeconds();
    	
    	g.setColor(Color.pink);
    	g.fillOval(300, 300, 500, 500);//ä�� Ÿ�� //
    			//x,y�� �߽���
    	//g.drawOval(300, 300, 500, 500); // �ð� �� ũ�� (��ä�� Ÿ��)
    				//x , y, w, h (x,y) ���� ���
    	
    	//�ð��� ���ڴ� ������ ���ʿ� ���������ϹǷ�
    	r1=200;		 //���� �߽����κ����� ����
    	cX=300+500/2;//���� �߽� X 300+250 = 550
    	cY=300+500/2;//���� �߽� Y 300+250 = 550

        //���ѷ��ȿ� 12�����ڸ� ǥ���غ��� //12 ���� ����� 200���� �۰� �غ���
        g.setFont(new Font("����",Font.BOLD,42));
        g.setColor(Color.BLUE);
        
        for(int i = 1; i<=12; i++) {
        	sooX=cX+(int)(180*Math.sin(i*30*Math.PI/180));//��ǥ���� x�ప�� ���
        									//�� 1���� �ش��ϴ� ��
        									//30�� ���� ��ǥ�� ���ؾ� �Ѵ�.
        	sooY=cY-(int)(180*Math.cos(i*30*Math.PI/180));//��ǥ���� y�ప�� ���
        				// �� ���ڰ� ����� ��ġ�� �� ������
        	g.drawString(""+i,sooX-15,sooY+20);
        }//for-end 		//you know? ���� + ���� = ����
        				
        Graphics2D g2d = (Graphics2D)g;
    	g2d.setStroke(new BasicStroke(3));// ��ħ, ��ħ, ��ħ �����⸦ 3����
        
    	//��ħ���̴� 180������ //cho*6�� ���� (1 - 60���̹Ƿ�) *6 =360�� ����
    	
    	secX=cX+(int)(180*Math.sin(cho*6*Math.PI/180));
    	secY=cY-(int)(180*Math.cos(cho*6*Math.PI/180));
    	g.setColor(Color.BLUE);
    	g.drawLine(cX, cY, secX, secY); // (cX,cY) ���� (secX,secY)���� �����׸�
    	
    	//1���̸� 6�� 12�� �̸� �ǹǷ�
    	//1��*6 + 1��*6=12���� 2��
    	//1��*6 + (1~60��/10) = 12���� 2�е�
    	//--> �׷��� ���� �������ϰ� ���� �ϱ�
    		//��ħ���̴� 180���� ª���ϴ� 150����
    			//boon*6�� ���� (1-60)/*6=360������
    	minX=cX+(int)(150*Math.sin((boon*6+cho/10)*Math.PI/180));
    	minY=cY-(int)(150*Math.cos((boon*6+cho/10)*Math.PI/180));
    		g.setColor(Color.GREEN);
    		g.drawLine(cX, cY, minX, minY);
    		
    	/*
    	 * 1�ð�*30 + 1�ð�*30 	= 2�ð� 60
    	 * 1�ð�*30 + 1~60��/2 	= 2�ð� 60;
    	 */
    		
		hourX=cX+(int)(100*Math.sin((si*30+boon/2)*Math.PI/180));
    	hourY=cY-(int)(100*Math.cos((si*30+boon/2)*Math.PI/180));
    		g.setColor(Color.RED);
    		g.drawLine(cX, cY, hourX, hourY);
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b1) {
			b1.setEnabled(false);
			Timer t1=new Timer();
			t1.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					repaint();// 1�ʸ��� repaint
					//c++ c# (flutter������ widget = repaint)
				}
			}, 1000, 1000);//1���Ŀ� 1�ʰ�������
		}//if - end
	}//actionPerformed-end
	
}//class-end

	public class Dclock {
		
	
		public static void main(String[] args) {
			new ClockGUI2();
		}
	}
