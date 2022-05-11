// AVL tree implementation in Java

// Create nod

// Tree class
class AvlTree {
	public Node root;

	int height(Node N) {
		if (N == null)
			return 0;
		return N.height;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		x.right = y;
		y.left = T2;
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;
		int val = (T2 != null) ? T2.desc : -1;
	    y.desc = y.desc - (x.desc + 1) + (val + 1);
	    x.desc = x.desc - (val + 1) + (y.desc + 1);
		return x;
	}

	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		y.left = x;
		x.right = T2;
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		int val = (T2 != null) ? T2.desc : -1;
	    x.desc = x.desc - (y.desc + 1) + (val + 1);
	    y.desc = y.desc - (val + 1) + (x.desc + 1);
		return y;
	}

	// Get balance factor of a node
	int getBalanceFactor(Node N) {
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	// Insert a node
	Node insertNode(Node node, Student student) {

		// Find the position and insert the node
		if (node == null)
			return (new Node(student));
		if (student.getAvg() < node.student.getAvg())
		{	node.desc ++;
			node.left = insertNode(node.left, student);
			
		
		}
		else if (student.getAvg() >= node.student.getAvg())
		{	node.desc ++;
			node.right = insertNode(node.right, student);
			
		
		}
		else
			return node;

		// Update the balance factor of each node
		// And, balance the tree
		node.height = 1 + max(height(node.left), height(node.right));
		int balanceFactor = getBalanceFactor(node);
		if (balanceFactor > 1) {
			if (student.getAvg() < node.left.student.getAvg()) {
				return rightRotate(node);
			} else if (student.getAvg() > node.left.student.getAvg()) {
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}
		}
		if (balanceFactor < -1) {
			if (student.getAvg() > node.right.student.getAvg()) {
				return leftRotate(node);
			} else if (student.getAvg() < node.right.student.getAvg()) {
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}
		}
		return node;
	}

	Node nodeWithMimumValue(Node node) {
		Node current = node;
		while (current.left != null)
			current = current.left;
		return current;
	}

	// Delete a node
	Node deleteNode(Node root, Student student) {

		// Find the node to be deleted and remove it
		if (root == null)
			return root;
		if (student.getAvg() < root.student.getAvg())
			root.left = deleteNode(root.left, student);
		else if (student.getAvg() > root.student.getAvg())
			root.right = deleteNode(root.right, student);
		else {
			if ((root.left == null) || (root.right == null)) {
				Node temp = null;
				if (temp == root.left)
					temp = root.right;
				else
					temp = root.left;
				if (temp == null) {
					temp = root;
					root = null;
				} else
					root = temp;
			} else {
				Node temp = nodeWithMimumValue(root.right);
				root.student = temp.student;
				root.right = deleteNode(root.right, temp.student);
			}
		}
		if (root == null)
			return root;

		// Update the balance factor of each node and balance the tree
		root.height = max(height(root.left), height(root.right)) + 1;
		int balanceFactor = getBalanceFactor(root);
		if (balanceFactor > 1) {
			if (getBalanceFactor(root.left) >= 0) {
				return rightRotate(root);
			} else {
				root.left = leftRotate(root.left);
				return rightRotate(root);
			}
		}
		if (balanceFactor < -1) {
			if (getBalanceFactor(root.right) <= 0) {
				return leftRotate(root);
			} else {
				root.right = rightRotate(root.right);
				return leftRotate(root);
			}
		}
		return root;
	}

}

