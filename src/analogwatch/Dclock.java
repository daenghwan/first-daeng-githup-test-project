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
 * 미리 알고갑시다
 * 
 * 파이 = 원의 둘레 (원주)와 지름의 길이의 비(즉, 원주/지름=3.141592)
 * ==> 이 말은 원의 둘레 길이는 항상 원의 반지름*2*3.14하면 나오게 된다는 것
 *     1414213
 *
 *  1라디안=단위원에서 반지름과 호의 길이가 1:1 같은 경우, 약 57.4도
 *  이때 180도는 파이라디안 (90도는 파이/2 360도는 2파이)
 *  그러므로 1도 = 파이/180 라디안 ==> Math.PI/180 라디안
 *  
 *  sin값 0		~ 180  > 0 , 180 ~ 360 < 0
 *  cos값 270 	~ 90   > 0 , 90  ~ 270 < 0
 */

class ClockGUI2 extends JFrame implements ActionListener{///
	private JButton b1 = new JButton("Start");
	
	static int r1,cX,cY,sooX,sooY,secX,secY,minX,minY,hourX,hourY;
    static int si,boon,cho;
    
    public ClockGUI2(){ //생성자 
            this.setLayout(null);/////////
	        b1.setSize(100,70);//버튼 크기
	        b1.setForeground(new Color(0,0,255));
	        b1.setBackground(new Color(255,180,100));
	        b1.setFont(new Font("돋움",Font.BOLD,15));
	        b1.setLocation(50,50); //버튼 내부위치 
	            this.add(b1);//버튼 추가 
	        b1.addActionListener(this); ////////////////// 
            
            this.setBackground(new Color(200,255,100)); 
            this.setTitle("자바는 FUN FUN FUN !!!!!!");
            this.setSize(1100, 1000); //프레임 크기  //////////////////
            this.setVisible(true);
    
            addWindowListener(new WindowAdapter(){ //////////////////
                     public void windowClosing(WindowEvent e1){
                             dispose();
                             System.exit(0);
                     }
            });
            
            
    }//생성자-end     
    
    public void paint(Graphics g) {
    		//내장 메소드, 객체
    	Date d1=new Date();
    	si=d1.getHours();//여기서 디지털시계라면, 아날로그시계라면
    	boon=d1.getMinutes();
    	cho=d1.getSeconds();
    	
    	g.setColor(Color.pink);
    	g.fillOval(300, 300, 500, 500);//채운 타원 //
    			//x,y는 중심축
    	//g.drawOval(300, 300, 500, 500); // 시계 원 크기 (안채운 타원)
    				//x , y, w, h (x,y) 왼쪽 상단
    	
    	//시계의 숫자는 원보다 안쪽에 쓰여져야하므로
    	r1=200;		 //실제 중심으로부터의 길이
    	cX=300+500/2;//실제 중심 X 300+250 = 550
    	cY=300+500/2;//실제 중심 Y 300+250 = 550

        //원둘레안에 12개숫자만 표시해볼까 //12 숫자 출력을 200보다 작게 해보자
        g.setFont(new Font("굴림",Font.BOLD,42));
        g.setColor(Color.BLUE);
        
        for(int i = 1; i<=12; i++) {
        	sooX=cX+(int)(180*Math.sin(i*30*Math.PI/180));//좌표에서 x축값을 담당
        									//ㄴ 1도에 해당하는 값
        									//30도 마다 좌표가 변해야 한다.
        	sooY=cY-(int)(180*Math.cos(i*30*Math.PI/180));//좌표에서 y축값을 담당
        				// ㄴ 글자가 생기는 위치의 원 반지름
        	g.drawString(""+i,sooX-15,sooY+20);
        }//for-end 		//you know? 문자 + 숫자 = 문자
        				
        Graphics2D g2d = (Graphics2D)g;
    	g2d.setStroke(new BasicStroke(3));// 초침, 분침, 시침 선굵기를 3으로
        
    	//초침길이는 180정도로 //cho*6은 각도 (1 - 60초이므로) *6 =360도 까찌
    	
    	secX=cX+(int)(180*Math.sin(cho*6*Math.PI/180));
    	secY=cY-(int)(180*Math.cos(cho*6*Math.PI/180));
    	g.setColor(Color.BLUE);
    	g.drawLine(cX, cY, secX, secY); // (cX,cY) 부터 (secX,secY)까지 직선그림
    	
    	//1분이면 6도 12도 이면 되므로
    	//1분*6 + 1분*6=12도로 2분
    	//1분*6 + (1~60초/10) = 12도로 2분됨
    	//--> 그래서 분을 스무스하게 가기 하기
    		//분침길이는 180보다 짧게하는 150으로
    			//boon*6은 각도 (1-60)/*6=360도까지
    	minX=cX+(int)(150*Math.sin((boon*6+cho/10)*Math.PI/180));
    	minY=cY-(int)(150*Math.cos((boon*6+cho/10)*Math.PI/180));
    		g.setColor(Color.GREEN);
    		g.drawLine(cX, cY, minX, minY);
    		
    	/*
    	 * 1시간*30 + 1시간*30 	= 2시간 60
    	 * 1시간*30 + 1~60분/2 	= 2시간 60;
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
					repaint();// 1초마다 repaint
					//c++ c# (flutter에서는 widget = repaint)
				}
			}, 1000, 1000);//1초후에 1초간격으로
		}//if - end
	}//actionPerformed-end
	
}//class-end

	public class Dclock {
		
	
		public static void main(String[] args) {
			new ClockGUI2();
		}
	}
