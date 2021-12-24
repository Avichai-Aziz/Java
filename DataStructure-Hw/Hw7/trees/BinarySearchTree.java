package il.ac.telhai.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	private BinarySearchTree<T> prevNode;

	public BinarySearchTree(T value, BinarySearchTree<T> left, BinarySearchTree<T> right) {
		super(value, left, right);
		if(((left.getValue().compareTo(value) < 0) && (right.getValue().compareTo(value) < 0)) ||
				((left.getValue().compareTo(value) > 0) && (right.getValue().compareTo(value) > 0)) ||
				(left.getValue().compareTo(right.getValue()) > 0))
		{
			throw new RuntimeException();
		}
		prevNode = null;
	}

	public BinarySearchTree(T value) {
		super(value, null, null);
		prevNode = null;
	};

	/**
	 * Adds the object value to the tree as a leaf according to the parameter. If
	 * the object was in the tree before, then a RuntimeException is thrown.
	 * 
	 * @param val
	 */
	public void add(T val) {
		if(val == null)
		{
			throw new RuntimeException();
		}
		BinarySearchTree<T> temp = this;
		BinarySearchTree<T> newNode = new BinarySearchTree<T>(val);
		if(temp.isLeaf())
		{
			if((temp.getValue().compareTo(val) > 0))
			{
					temp.setLeft(newNode);
			}

			if((temp.getValue().compareTo(val) < 0))
			{
					temp.setRight(newNode);
			}
			newNode.prevNode = temp;
			return;
		}
		while(temp != null)
		{
			if(temp.getValue().compareTo(val) == 0)
			{
				throw new RuntimeException();
			}
			else if((temp.nextFromLeft() == null) && (temp.getValue().compareTo(val) > 0))
			{
				temp.setLeft(newNode);
				break;
			}
			else if((temp.nextFromLeft() != null) && (temp.getValue().compareTo(val) > 0))
			{
				temp = temp.nextFromLeft();
			}

			else if((temp.nextFromRight() == null) && (temp.getValue().compareTo(val) < 0))
			{
				temp.setRight(newNode);
				break;
			}
			else if((temp.nextFromRight() != null) && (temp.getValue().compareTo(val) < 0))
			{
				temp = temp.nextFromRight();
			}

		}
		newNode.prevNode = temp;
	}

	/**
	 * Looks for an object which is equal to the parameter.
	 * 
	 * @param value: the object to be looked for in the tree
	 * @return true if the tree contains this object. Otherwise, return false.
	 */
	public boolean contains(T value) {
		if(value == null)
		{
			throw new RuntimeException();
		}
		BinarySearchTree<T> temp = this;
		while(temp != null)
		{
			if(temp.getValue().compareTo(value) == 0)
			{
				return true;
			}
			if(temp.getValue().compareTo(value) > 0)
			{
				temp = temp.nextFromLeft();
			}
			else if(temp.getValue().compareTo(value) < 0)
			{
				temp = temp.nextFromRight();
			}
		}
		return false;
	}

	/**
	 * Looks for the minimal object in the tree, which is greater than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findGE(T val) {
		BinarySearchTree<T> temp = this;
		BinarySearchTree<T> prev = null;
		while(temp != null)
		{
			if(temp.getValue().compareTo(val) == 0)
			{
				return temp.getValue();
			}
			else{
				if(temp.getValue().compareTo(val) > 0)
				{
					prev = temp;
					temp = temp.nextFromLeft();
				}
				else if(temp.getValue().compareTo(val) < 0)
				{
					temp = temp.nextFromRight();
				}
			}
		}
		if(prev == null)
		{
			return null;
		}
		return prev.getValue();
	}

	/**
	 * Looks for the minimal object in the tree, which is smaller than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findLE(T val) {
		BinarySearchTree<T> temp = this;
		BinarySearchTree<T> prev = null;
		while(temp != null)
		{
			if(temp.getValue().compareTo(val) == 0)
			{
				return temp.getValue();
			}
			else{
				if(temp.getValue().compareTo(val) > 0)
				{
					temp = temp.nextFromLeft();
				}
				else if(temp.getValue().compareTo(val) < 0)
				{
					prev = temp;
					temp = temp.nextFromRight();
				}
			}
		}
		if(prev == null)
		{
			return null;
		}
		return prev.getValue();
	}

	/**
	 * Removes the object in the tree which is equal to the parameter. If the object
	 * was found but the tree contains only one element, it won't be removed and a
	 * run-time exception will be thrown. Nothing is done if node found
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return True, if the element was removed. Otherwise, false.
	 */
	public boolean remove(T val) {
		if(this.contains(val))
		{
			if(super.size() == 1)
			{
				throw new RuntimeException();
			}
			else{
				BinarySearchTree<T> temp = this;
				BinarySearchTree<T> prev = this;
				while(temp != null)
				{
					if((temp.nextFromLeft() != null) && (temp.getValue().compareTo(val) > 0))
					{
						prev = temp;
						temp = temp.nextFromLeft();
					}
					else if(temp.nextFromRight() != null && (temp.getValue().compareTo(val) < 0))
					{
						prev = temp;
						temp = temp.nextFromRight();
					}

					// the value found
					if(temp.getValue().compareTo(val) == 0)
					{
						// checking if we want to remove a leaf from the tree
						if(temp.isLeaf())
						{
							if(prev.getValue().compareTo(val) > 0)
							{
								prev.setLeft(null);
							}
							else if(prev.getValue().compareTo(val) < 0)
							{
								prev.setRight(null);
							}
						}

						// checking if we want to remove a node with only one son.
						if((temp.nextFromLeft() != null && temp.nextFromRight() == null) || (temp.nextFromLeft() == null && temp.nextFromRight() != null))
						{
							if(prev.getValue().compareTo(val) > 0)
							{ // means we turned left
								if(temp.nextFromLeft() != null && temp.nextFromRight() == null)
								{
									prev.setLeft(temp.nextFromLeft());
									temp.nextFromLeft().prevNode = prev;
								}
								else if(temp.nextFromLeft() == null && temp.nextFromRight() != null)
								{
									prev.setLeft(temp.nextFromRight());
									temp.nextFromRight().prevNode = prev;
								}
							}
							else if(prev.getValue().compareTo(val) < 0)
							{ // means we turned right
								if(temp.nextFromLeft() != null && temp.nextFromRight() == null)
								{
									prev.setRight(temp.nextFromLeft());
									temp.nextFromLeft().prevNode = prev;
								}
								else if(temp.nextFromLeft() == null && temp.nextFromRight() != null)
								{
									prev.setRight(temp.nextFromRight());
									temp.nextFromRight().prevNode = prev;
								}
							}
						}

						// checking if we want to remove a node with two sons.
						if(temp.nextFromLeft() != null && temp.nextFromRight() != null)
						{
							BinarySearchTree<T> temp2 = temp;
							T tracerValue = findGE(temp.nextFromRight().findGE(temp.getValue()));

							while(temp2.getValue().compareTo(tracerValue) != 0)
							{
								if (temp2.getValue().compareTo(tracerValue) > 0)
								{
									prev = temp2;
									temp2 = temp2.nextFromLeft();
								}
								else if (temp2.getValue().compareTo(tracerValue) < 0) {
									prev = temp2;
									temp2 = temp.nextFromRight();
								}
							}
							if(prev.getValue().compareTo(tracerValue) > 0)
							{
								prev.setLeft(temp2.nextFromRight());
							}
							else if(prev.getValue().compareTo(tracerValue) < 0)
							{
								prev.setRight(temp2.nextFromRight());
							}
							temp.setValue(temp2.getValue());
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public BinarySearchTree<T> getLeft() {
		BinarySearchTree<T> copyLeftTree = (BinarySearchTree<T>) super.getLeft();
		if(copyLeftTree == null)
		{
			return null;
		}
		BinarySearchTree<T> temp = new BinarySearchTree<T>(copyLeftTree.getValue());
		if(this.nextFromLeft() != null)
		{
			temp.setLeft(copyLeftTree.getLeft());
		}
		if(this.nextFromRight() != null)
		{
			temp.setRight(copyLeftTree.getRight());
		}
		return temp;
	}

	@Override
	public BinarySearchTree<T> getRight() {
		BinarySearchTree<T> copyRightTree = (BinarySearchTree<T>) super.getRight();
		if(copyRightTree == null)
		{
			return null;
		}
		BinarySearchTree<T> temp = new BinarySearchTree<T>(copyRightTree.getValue());
		if(this.nextFromLeft() != null)
		{
			temp.setLeft(copyRightTree.getLeft());
		}
		if(this.nextFromRight() != null)
		{
			temp.setRight(copyRightTree.getRight());
		}
		return temp;
	}


	@Override
	public void setLeft(BinaryTreeI<T> left){
		if(left == null)
		{
			super.setLeft(null);
			return;
		}
		else if((left.getClass() != this.getClass()) || (this.getValue().compareTo(((BinarySearchTree<T>)left).getValue()) < 0))
		{
			throw new RuntimeException();
		}
		super.setLeft((BinarySearchTree<T>)left);
	}

	@Override
	public void setRight(BinaryTreeI<T> right){
		if(right == null)
		{
			super.setRight(null);
			return;
		}
		else if((right.getClass() != this.getClass()) || (this.getValue().compareTo(((BinarySearchTree<T>)right).getValue()) > 0))
		{
			throw new RuntimeException();
		}
		super.setRight((BinarySearchTree<T>)right);
	}

	@Override
	public void setValue(T value){
		if(this.prevNode != null && this.prevNode.getValue().compareTo(this.getValue()) > 0 && this.prevNode.getValue().compareTo(value) < 0)
		{
			throw new RuntimeException();
		}
		else if(this.prevNode != null && this.prevNode.getValue().compareTo(this.getValue()) < 0 && this.prevNode.getValue().compareTo(value) > 0)
		{
			throw new RuntimeException();
		}
		else if(this.prevNode == null && this.nextFromLeft() != null && this.nextFromLeft().getValue().compareTo(value) > 0)
		{
			throw new RuntimeException();
		}
		else if(this.prevNode == null && this.nextFromRight() != null && this.nextFromRight().getValue().compareTo(value) < 0)
		{
			throw new RuntimeException();
		}
		super.setValue(value);
	}

	private BinarySearchTree<T> nextFromLeft(){
		return (BinarySearchTree<T>) super.getLeft();
	}
	private BinarySearchTree<T> nextFromRight(){
		return (BinarySearchTree<T>) super.getRight();
	}
}
