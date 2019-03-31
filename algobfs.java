package pkg;

import java.util.*;
import java.awt.*;
import pkg.*;
import java.util.concurrent.TimeUnit;

public class algobfs{
	node start,end,current;
	static int speed=20;
	HashSet<node> openset  = new HashSet<node>();
	HashSet<node> closeset = new HashSet<node>();
	HashMap<node, node> camefrom = new HashMap<>();
	
	Graphics g;
	int b;
	public static int counter5;
	public static int counter6;
	public algobfs(node start,node end,Graphics g,int b){
		this.start = start;
		this.end   = end;
		this.g     = g;
		this.b     = b;
		
		
		// start();
	}

	


	
	public void findminneighbor(node a){
	
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				int skip = 0;
				// if(i == 0 && j == 0){
				// 	continue;
				// }		
				
				node neighbor = new node(a);
				neighbor.x = a.x + i;
				neighbor.y = a.y + j;

				
				if(neighbor.isoutofbound()|| display.arr[neighbor.x][neighbor.y] == 1){
					continue;
				}
				Iterator<node>it = closeset.iterator();
				while(it.hasNext()){
					if(it.next().isequal(neighbor)){
						skip = 1;
						break;
					}
				}
				if(skip == 1){
					continue;
				}
				if(i == 0 || j == 0){
					neighbor.g = a.g +20;
				}else{
					neighbor.g = a.g +20.1;
				}
				
				it = openset.iterator();
				node min = null;
				while(it.hasNext()){
					node temp = it.next();
					if(temp.isequal(neighbor)){
						if(temp.g > neighbor.g){
							min = temp;
						}
							skip = 1;
					}

				}
				if(min != null){
					camefrom.put(neighbor,a);
					openset.remove(min);
					openset.add(neighbor);
				}
				if(skip == 1){
					continue;
				}
				camefrom.put(neighbor,a);
				openset.add(neighbor);
				counter5++;
				drawbox(neighbor,6);
			} 
		}
		// System.out.println("choosen:"+min.x+" "+min.y+" "+min.f+" "+min.h );
		// return min;
	}


	public int bfirst(){
		boolean path = true;
		int count = 0;
		openset.add(start);
		current = start;

		while(!openset.isEmpty()){

			if(current.isequal(end)){
				buildpath(current);
				path = false;
				break;
			}
			Iterator<node>it = openset.iterator();
			current = null;
			while(it.hasNext()){
				node temp = it.next();
				if(current == null || current.f > temp.f) {
					current = temp;
				}else if(current.f == temp.f && current.g > temp.g){
					current = temp;
				}
			}	
			openset.remove(current);
			closeset.add(current);

			findminneighbor(current);
			
			drawbox(current,5);			
		}
		if(path){
			return 1;//if path not found return 1 so calling function know that pathis not available
		}
		return 0;
	}

	Color setcolor(int i,int j,int c){
		Color color = Color.WHITE;
		switch(c){
				case 0: color = Color.WHITE;break;
				case 1: color = Color.BLACK;break;
				case 2: color = Color.YELLOW;break;
				case 3: color = Color.ORANGE;break;
				case 4: color = Color.YELLOW; break;
				case 5: color = Color.GREEN;break;
				case 6: color = Color.CYAN;  break;
		}
		display.arr[i][j] = c;
		return color;
	}

	public void drawbox(node current,int c){
		if(current.isequal(start)){
			return;
		}else if(current.isequal(end)){
			return;
		}
		int i = current.x;
		int j = current.y;
		Color color = setcolor(i,j,c);

		g.setColor(color);
		g.fillRect(i*b,j*b,b,b);
		g.setColor(Color.BLACK);
		g.drawRect(i*b,j*b,b,b);

		if(display.boxno == 10){
			String s = String.format("%d %d" ,(int)current.g,(int)current.h);
			String s1 = String.format("%d" ,(int)current.f);

			g.drawString(s,i*b+2,j*b+b/3);
			g.drawString(s1,i*b+2,j*b+b*2/3);
		}
		try{	
			TimeUnit.MILLISECONDS.sleep(speed);
		}catch(Exception e){}

	}
	void buildpath(node current){
		while(camefrom.containsKey(current)){
			current = camefrom.get(current);
			counter6++;
			drawbox(current,4);
		}
		// display.refresh = 1;
	}
}