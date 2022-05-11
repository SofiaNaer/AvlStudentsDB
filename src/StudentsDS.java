import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StudentsDS {
	private AvlTree StudentsDS;
	private ArrayList <Student> DS;
	
	public StudentsDS(ArrayList <Student> DS) {
		this.DS = DS;
		this.StudentsDS = new AvlTree();
	}
	//-------------------------------initDs---------------------------------------------
	public   void initDs (String fileName) throws IOException {
		
		FileReader fw = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fw);
		String s1 = br.readLine();
		while (s1!= null) {
			String name = s1;
			s1 = br.readLine();
			String id = s1;
			s1 = br.readLine();
			int year = Integer.parseInt(s1);
			s1 = br.readLine();
			double avg = Double.parseDouble(s1);
			s1 = br.readLine();
			s1 = br.readLine();
			
			DS.add(new Student(name, id, year, avg));
			StudentsDS.root =StudentsDS.insertNode(StudentsDS.root, new Student(name, id, year, avg));	
		}
		fw.close();
		br.close();
	}
	
//------------------------------addStudent------------------------------------------
	 public void addStudent (Student st) {
		 DS.add(st);
		 StudentsDS.root =StudentsDS.insertNode(StudentsDS.root, st);
		 
	 }
//------------------------------searchByid------------------------------------	 
		public void searchById (String id) {
			boolean flag = false;
			for (int i = 0; i< DS.size(); i++) {
			  if( DS.get(i).getId().equals(id)) {
				  flag = true;
				  System.out.println(DS.get(i).toString());
				  break;
			}
			}
			if (flag == false) {
				System.out.println( "Student doesn't exist");
			}		
	}
	 
//----------------------------searchBYAvg--------------------------------------------
		public int searchByAvg (double avg) {
			return FountGreater(avg, StudentsDS.root);
		}
		
		private int FountGreater (double avg, Node root) {
			int count = 0; 
			
			if (root.student.getAvg() > avg ) {
				count =  root.right.desc +1 +1;
				root = root.left;
				while ( root!= null && root.student.getAvg() > avg) {
					if (root.right != null) {
						count += root.right.desc +2;
						root = root.left;	
					}
					else {
						count++;
						root = root.left ;
					}
			
				}
					
				return count;
		}
			if (root.student.getAvg() < avg) {
				while (root.student.getAvg() < avg && root != null) {
					root = root.right;
				}
				if (root.student.getAvg() > avg) {
					count = root.right.desc +2;
				}
				else {
					count = root.right.desc +1;
				}
				return count;
			}
			if (root.student.getAvg() == avg){
				count = root.right.desc;
			}
			return count;
		
		}
//--------------------------------------PrintByYear----------------------------------
		
		public void printByYear (int y) {
			if (y<1 || y> 3) {
				System.out.println( "Wrong Parameters");
			}
			else {
				for (int i = 0; i <DS.size(); i++) {
					if (DS.get(i).getYear() == y) {
						System.out.println(DS.get(i).toString());
					}
				}
			}
		}
		
//--------------------------------------PrintLast---------------------------------------
		public void printLast () {
			System.out.println(DS.get(DS.size()-1).toString());
		}
//--------------------------------------DelStudent----------------------------------------		
		public void delStudent (String ID) {
			boolean flag = false;
			int i = 0;
			for (; i<DS.size(); i++) {
				if (DS.get(i).getId().equals(ID)) {
					flag = true;
					break;
				}
			}
			if(flag) {
				
				StudentsDS.deleteNode(StudentsDS.root, DS.get(i));
				DS.remove(i);
			}
			else {
				System.out.println("Student with this ID dosen't exist");
			}
			
		}
		public double[] search (double a, double b) {
			double [] ret = new double [StudentsDS.root.desc +1];
			return searchAB(a, b, StudentsDS.root, ret, 0);
		}
		
		
		private double[] searchAB (double a, double b, Node root, double[] arr, int i) {
				
				if (root == null ) 
					return null;
				
				if(a< root.student.getAvg() ) 
					//System.out.println(root.student.getAvg());
					searchAB(a, b, root.left, arr, i);
				
				
				if (root.student.getAvg()>= a && root.student.getAvg() <= b) {
					//System.out.println(root.student.getAvg());
					arr[i] = root.student.getAvg();
					i++; 
					
				}
				if(a< root.student.getAvg() ) 
					//System.out.println(root.student.getAvg());
					searchAB(a, b, root.left, arr, i);
				if (root.student.getAvg() <b) {
					//System.out.println(root.student.getAvg());
					searchAB(a, b, root.right, arr, i);
				}
				//searchAB(a, b, root.left, arr, i);
					
				
				
				
			
			
			
			
				return arr;
			}
		
			
			

	
		
//----------------------------------printDs----------------------------------------		
		public void printDS () throws IOException {
			FileWriter fw = new FileWriter("studentDS.txt");
			PrintWriter pw = new PrintWriter(fw);
			inOrder(StudentsDS.root, pw);
		    

	    	
	    	pw.close();
	    	fw.close();
		}
		
		private void inOrder(Node node, PrintWriter pw) {
			    if (node != null) {
			    	inOrder(node.left,pw);
			    	pw.println(node.student);
			    	inOrder(node.right,pw);
			}

		
	}
	
		
}
