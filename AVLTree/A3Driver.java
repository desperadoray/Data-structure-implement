package csciA3;

//File:        AVLTree.java
//Author:      Geoffrey Tien
//             Rui Zhang
//Date:        2016-10-30(revised 2016/10/31)
//Description: Partial implementation of an AVL Tree class.
//           Some methods given, remainder to be completed by you

class AVLNode {
	public Comparable data;
	public AVLNode left;
	public AVLNode right;
	public int height;

	// default constructor
	public AVLNode(Comparable value) {
		data = value;
		left = null;
		right = null;
		height = 0;
	}

	// parameterized constructor
	public AVLNode(Comparable value, AVLNode left1, AVLNode right1) {
		data = value;
		left = left1;
		right = right1;
		height = 0;
	}

	// The ResetHeight method recomputes height if the
	// left or right subtrees have changed.
	void ResetHeight() {
		int leftheight = -1;
		int rightheight = -1;
		if (left != null)
			leftheight = left.height;
		if (right != null)
			rightheight = right.height;
		height = 1 + Math.max(leftheight, rightheight);
	}
}

public class AVLTree {
	// member attributes
	AVLNode root;
	private int size;

	// private methods
	
	//remove predecessor node
	private void clear(AVLNode nd){
		nd = null;
	}
	
	// recursive contains
	private Boolean Contains(AVLNode nd, Comparable item) {
		if (item.compareTo(nd.data) == 0)
			return true;
		if (item.compareTo(nd.data) < 0 && nd.left != null)
			return Contains(nd.left, item);
		if (item.compareTo(nd.data) > 0 && nd.right != null)
			return Contains(nd.right, item);
		return false;
	}

	// check height
	private boolean isImba(AVLNode nd) {
		int lh = -1;
		int rh = -1;
		if (nd == null)
			return false;
		if (nd.left != null)
			lh = nd.left.height;
		if (nd.right != null)
			rh = nd.right.height;
		if (Math.abs(lh - rh) >= 2)
			return true;
		return false;
	}

	// recursive helper function for deep copy
	// creates a new node based on sourcend's contents,
	// recurses to create left and right children.
	// returns a reference to the newly created node.
	// The new tree should have the same structure as the source tree
	private AVLNode CopyNode(AVLNode sourcend) {
		// to be completed
		AVLNode nd = new AVLNode(sourcend.data);
		AVLNode left = null;
		AVLNode right = null;
		if (sourcend.left != null)
			left = CopyNode(sourcend.left);
		if (sourcend.right != null)
			right = CopyNode(sourcend.right);
		nd.left = left;
		nd.right = right;
		nd.ResetHeight();
		return nd;
	}

	// recursive insertion
	// returns the root of the augmented tree
	// (nd may or may not have been rotated)
	// Performs ordinary (recursive) BST insertion,
	// then computes left/right subtree heights and
	// rebalance if necessary, otherwise reset nd's
	// height and return.
	// Does not affect size of the tree.
	private AVLNode Insert(AVLNode nd, Comparable item) {
		// to be completed
		if (nd == null)
			nd = new AVLNode(item);
		else if (item.compareTo(nd.data) <= 0) {
			nd.left = Insert(nd.left, item);
		} else {
			nd.right = Insert(nd.right, item);
		}
		if (isImba(nd))
			return Balance(nd);
		else {
			nd.ResetHeight();
			return nd;
		}

	}

	// recursive remove
	// returns root of the supplied (possibly rebalanced) tree
	// with the key value removed (if found)
	// return null if not found
	private AVLNode Remove(AVLNode nd, Comparable item) {
		// to be completed
		if (nd == null)
			return null;
		else if (item.compareTo(nd.data) < 0)
			nd.left = Remove(nd.left, item);

		else if (item.compareTo(nd.data) > 0)
			nd.right = Remove(nd.right, item);

		else if (nd.left != null && nd.right != null) {
			Comparable tmp = nd.data;
			nd.data = Predecessor(nd).data;
			Predecessor(nd).data = tmp;
			nd.left = Remove(nd.left, item);
		} 
		else if(nd.left == null && nd.right == null){
			nd = null;
			size--;
		}
		else{
			nd = (nd.left != null) ? nd.left : nd.right;
			size--;
		}
		if(nd!= null)
			nd.ResetHeight();
		return nd == null? null : (isImba(nd)?Balance(nd):nd);
	}

	// Finds and returns the predecessor of the supplied subtree root node
	private AVLNode Predecessor(AVLNode nd) {
		// to be completed
		if (nd == null)
			return null;
		if (nd.left != null) {
			nd = nd.left;
			while (nd.right != null)
				nd = nd.right;
		}
		return nd;
	}

	// Rebalances an AVL tree, returns the root of the balanced
	// tree (formerly rooted at nd)
	private AVLNode Balance(AVLNode nd) {
		int lheight = -1;
		if (nd.left != null)
			lheight = nd.left.height;
		int rheight = -1;
		if (nd.right != null)
			rheight = nd.right.height;

		if (rheight > lheight) {
			AVLNode rchild = nd.right;
			int rrheight = -1;
			if (rchild.right != null)
				rrheight = rchild.right.height;
			int rlheight = -1;
			if (rchild.left != null)
				rlheight = rchild.left.height;
			if (rrheight > rlheight)
				return RRBalance(nd);
			else
				return RLBalance(nd);
		} else {
			AVLNode lchild = nd.left;
			int llheight = -1;
			if (lchild.left != null)
				llheight = lchild.left.height;
			int lrheight = -1;
			if (lchild.right != null)
				lrheight = lchild.right.height;
			if (llheight > lrheight)
				return LLBalance(nd);
			else
				return LRBalance(nd);
		}
	}

	// corrects an RR imbalance rooted at nd
	// returns the balanced AVL tree
	private AVLNode RRBalance(AVLNode nd) {
		AVLNode rchild = nd.right;
		AVLNode rlchild = rchild.left;
		rchild.left = nd;
		nd.right = rlchild;
		nd.ResetHeight();
		rchild.ResetHeight();
		return rchild;
	}

	// corrects an RL imbalance rooted at nd
	// returns the balanced AVL tree
	private AVLNode RLBalance(AVLNode nd) {
		AVLNode subroot = nd;
		AVLNode rnode = subroot.right;
		AVLNode rlnode = rnode.left;
		AVLNode rlrtree = rlnode.right;
		AVLNode rlltree = rlnode.left;

		// build the restructured tree
		rnode.left = rlrtree;
		subroot.right = rlltree;
		rlnode.left = subroot;
		rlnode.right = rnode;

		// adjust heights
		rnode.ResetHeight();
		subroot.ResetHeight();
		rlnode.ResetHeight();

		return rlnode;
	}

	// corrects an LL imbalance rooted at nd
	// returns the balanced AVL tree
	private AVLNode LLBalance(AVLNode nd) {
		AVLNode lchild = nd.left;
		AVLNode lrchild = lchild.right;
		lchild.right = nd;
		nd.left = lrchild;
		nd.ResetHeight();
		lchild.ResetHeight();
		return lchild;
	}

	// corrects an LR imbalance rooted at nd
	// returns the balanced AVL tree
	private AVLNode LRBalance(AVLNode nd) {
		AVLNode subroot = nd;
		AVLNode lnode = subroot.left;
		AVLNode lrnode = lnode.right;
		AVLNode lrltree = lrnode.left;
		AVLNode lrrtree = lrnode.right;

		// build the restructured tree
		lnode.right = lrltree;
		subroot.left = lrrtree;
		lrnode.left = lnode;
		lrnode.right = subroot;

		// adjust heights
		lnode.ResetHeight();
		subroot.ResetHeight();
		lrnode.ResetHeight();

		return lrnode;
	}

	// default constructor
	public AVLTree() {
		root = null;
		size = 0;
	}

	// copy constructor
	public AVLTree(AVLTree tree) {
		// to be completed
		// should set member attributes and perform deep copy
		if (tree.root == null) {
			root = null;
			size = 0;
		} else {
			root = CopyNode(tree.root);
			size = tree.size;
		}

	}

	// gets the number of items in the tree
	public int Size() {
		return size;
	}

	// gets the height of the tree (root)
	public int Height() {
		if (root == null)
			return -1;
		return root.height;
	}

	// searches the tree for a key
	public Boolean Contains(Comparable item) {
		// to be completed
		return root == null ? false : Contains(root, item);
	}

	// Inserts an item into the tree.
	public boolean Insert(Comparable item) {
		root = Insert(root, item);// to be completed
		return ++size > 0 ? true : false;
	}

	// for test purposes only
	public void Insert(Comparable... items) {
		for (Comparable item : items)
			Insert(item);
	}

	// Removes an item from the tree.
	// Returns true if the item is successfully removed
	// Returns false if the item cannot be removed (i.e. not found)
	public boolean Remove(Comparable item) {
		// to be completed
		if (root == null)
			return false;
		if (!Contains(item))
			return false;
		else {
			root = Remove(root, item);
			return true;
		}
	}

	// removes all items in the tree
	public void RemoveAll() {
		// to be completed
		root = null;
		size = 0;
	}

	// returns a formatted string, reverse in-order traversal
	// (as done in lab / code sample)
	public String PrintTree() {
		return PrintTree(root, 0, new StringBuilder()).toString();
	}

	// recursive helper for PrintTree
	private StringBuilder PrintTree(AVLNode nd, int level, StringBuilder tree) {
		if (nd == null) {
			return tree;
		} else {
			PrintTree(nd.right, 1 + level, tree);
			for (int i = 0; i < level; i++) {
				tree.append("\t");
			}
			tree.append(nd.data + "\n");
			PrintTree(nd.left, 1 + level, tree);
			return tree;
		}
	}
}
