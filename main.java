
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.event.*;
import pkg.*;
import java.text.DecimalFormat;

public class main extends JFrame{
	JPanel control;
	JPanel buttons;
	JPanel sliders;
	JLabel zoomlabel;
	JLabel speedlabel;
	JButton AStar;
	JButton snode;
	JButton enode;
	JButton reset;
	JButton BFirst;
	JButton Dijkstra;
	DecimalFormat f = new DecimalFormat("##.000");

	static int rerun = 0;
	main(int w,int h){
		this.setSize(w,h);

		display dis = new display(w,h-90);
		dis.setBounds(0,90,w,h-90);
		dis.setLayout(null);
		dis.setVisible(true);

		// buttons			
		snode = new JButton("Start Node");
		snode.setBounds(0,10,95,40);
		snode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.colorvalue = 6;
			}
		});

		enode = new JButton("End Node");
		enode.setBounds(95,10,90,40);
		enode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.colorvalue = 5;
			}
		});			

		AStar = new JButton("A*");
		AStar.setBounds(185,10,60,40);
		AStar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				long startTime = System.nanoTime();
				if(rerun ==0){
					if(dis.astar()==0){
						rerun = 1;
						((JButton)e.getSource()).setText("Clean");
						long endTime = System.nanoTime();
						long duration = (endTime - startTime);
						double durationSeconds = (double) duration / 1_000_000_000;
						//System.out.println(algo.counter2);
						//System.out.println("A* found path in " + durationSeconds + " Seconds");
						JOptionPane.showMessageDialog(null, 
	                              "This algorithm has found the quickest path in " + f.format(durationSeconds) + " seconds. \n"+
	                              "The path it took was "+ algo.counter2 +" boxes long \n"+
	                              " It also searched " + algo.counter + " boxes in order to find this route", 
	                              "A* Algorithm", 
	                              JOptionPane.WARNING_MESSAGE);
					}
				}else{
					dis.clean();
					rerun = 0;
					algo.counter = 0;
					algo.counter2 = 0;
					((JButton)e.getSource()).setText("A*");

				}
			}
		});	
		BFirst = new JButton("B First");

		BFirst.setBounds(245,10,90,40);
		BFirst.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				long startTime = System.nanoTime();
				if(rerun ==0){
					if(dis.bfirst()==0){
						rerun = 1;
						((JButton)e.getSource()).setText("Clean");
						long endTime = System.nanoTime();
						long duration = (endTime - startTime);
						double durationSeconds = (double) duration / 1_000_000_000;
						//System.out.println("BFS found path in " + durationSeconds + " seconds");
						JOptionPane.showMessageDialog(null, 
	                              "This algorithm has found the quickest path in " + f.format(durationSeconds) + " seconds.", 
	                              "Breeadth First Search Algorithm", 
	                              JOptionPane.WARNING_MESSAGE);
					}
				}else{
					dis.clean();
					rerun = 0;
					((JButton)e.getSource()).setText("Breadth First");

				}
			}
		});	

		Dijkstra = new JButton("Dijkstra");
		Dijkstra.setBounds(335,10,90,40);
		Dijkstra.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				long startTime = System.nanoTime();
				if(rerun ==0){
					if(dis.dijk()==0){
						rerun = 1;
						((JButton)e.getSource()).setText("Clean");
						long endTime = System.nanoTime();
						long duration = (endTime - startTime);
						double durationSeconds = (double) duration / 1_000_000_000;
						 
						//System.out.println("Dijkstras found path in " + f.format(durationSeconds) + " seconds");
						JOptionPane.showMessageDialog(null, 
	                              "This algorithm has found the quickest path in " + f.format(durationSeconds) + " seconds."+
	                            		  "\n The path it took was "+ algodijk.counter4 +" boxes long"+
	    	                              "\n  It also searched " + algodijk.counter3 + " boxes in order to find this route", 
	                              "Dijkstra's Algorithm", 
	                              JOptionPane.WARNING_MESSAGE);
					}
				}else{
					dis.clean();
					rerun = 0;
					algodijk.counter3 = 0;
					algodijk.counter4 = 0;
					((JButton)e.getSource()).setText("Dijkstra");

				}
			}
		});	
		reset = new JButton("Reset");
		reset.setBounds(425,10,80,40);
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dis.reset();
				AStar.setText("A*");
			}
		});




		/*JSlider zoom = new JSlider(JSlider.HORIZONTAL,10,50,20);
		zoom.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int val = ((JSlider)e.getSource()).getValue();
				dis.setboxno(val);

			}
		});*/
		JSlider speed = new JSlider(JSlider.HORIZONTAL,0,20,0);
		speed.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				int val = ((JSlider)e.getSource()).getValue();
				dis.setspeed(20-val);
				System.out.println(val);
			}
		});




		//zoom.setBounds(50,0,160,30);
		speed.setBounds(50,40,160,30);

		//zoomlabel = new JLabel("Zoom");
		speedlabel = new JLabel("Speed");

		//zoomlabel.setBounds(0,0,50,30);
		speedlabel.setBounds(0,30,50,30);

		control = new JPanel();
		buttons = new JPanel();
		sliders = new JPanel();
		control.setBounds(5,0,w,60);
		control.setLayout(null);
		control.setVisible(true);

		buttons.setBounds(0,0,w,60);
		buttons.setLayout(null);
		buttons.setVisible(true);

		sliders.setBounds(0,30,w,60);
		sliders.setLayout(null);
		sliders.setVisible(true);

		buttons.add(enode);
		buttons.add(snode);
		buttons.add(AStar);
		buttons.add(reset);
		buttons.add(BFirst);
		buttons.add(Dijkstra);

		//sliders.add(zoomlabel);
		sliders.add(speedlabel);
		//sliders.add(zoom);
		sliders.add(speed);


		control.add(buttons);
		control.add(sliders);


		this.add(dis);
		this.add(control);
	}
	protected void round(double durationSeconds, int i) {
		// TODO Auto-generated method stub
		
	}
	protected void round(double durationSeconds) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		main a = new main(600,660);
		a.setLayout(null);
		a.setVisible(true);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}