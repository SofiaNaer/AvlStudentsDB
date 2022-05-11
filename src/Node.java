
public class Node {
	int  height;
	Student student;
	Node left, right;
	public int desc;
	public int rightCount;
	public int leftCount;

	Node(Student s) {
		student = s;
		height = 1;
	}
}
