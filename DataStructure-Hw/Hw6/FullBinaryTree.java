package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T>{

    public FullBinaryTree(T element) {
        super(element);
    }
    public FullBinaryTree(T element, FullBinaryTree<T> left, FullBinaryTree<T> right) {
        super(element, left, right);
        if(((left == null) && (right != null)) || ((left != null) && (right == null)))
        {
            throw new RuntimeException();
        }
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        if(((left == null) && (getRight() != null)) || ((left != null) && (getRight() == null)))
        {
            throw new RuntimeException();
        }
        else if((left != null) && (!left.getClass().equals(this.getClass())))
        {
            throw new RuntimeException();
        }
        super.setLeft(left);
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if(((right == null) && (getLeft() != null)) || ((right != null) && (getLeft() == null)))
        {
            throw new RuntimeException();
        }
        else if((right != null) && (!right.getClass().equals(this.getClass())))
        {
            throw new RuntimeException();
        }
        super.setRight(right);
    }
}


