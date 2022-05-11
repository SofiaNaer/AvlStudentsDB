import java.io.IOException;
import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Student> DS = new ArrayList<>();
		StudentsDS Students = new StudentsDS(DS);
		Students.initDs("file.txt");
		Student s = new Student("Haim Levi" , "7780" , 3 , 90.8);
		
		Students.addStudent(s);
		//Students.delStudent("5590");
		//Students.searchById("5590");
		//System.out.println(Students.searchByAvg(72.2));
		//Students.printByYear(2);
		//Students.printLast();
		//Students.printDS();
		double [] arr = Students.search(64.6, 90.8);
		for (int i = 0; i <arr.length ;i++) {
			System.out.println(arr[i]+ "*");
		}
	}

}
