package il.ac.telhai.ds.trees;

public class BinaryTree<T> implements BinaryTreeI<T>{
    private BinaryTreeI<T> leftT;
    private BinaryTreeI<T> rightT;
    private T element;

    public BinaryTree(T element){
        this(element, null, null);
    }
    public BinaryTree(T element, BinaryTreeI<T> leftT, BinaryTreeI<T> rightT){
        this.element = element;
        this.leftT = leftT;
        this.rightT = rightT;
    }

    @Override
    public BinaryTreeI<T> getLeft() {
        return this.leftT;
    }

    @Override
    public BinaryTreeI<T> getRight() {
        return this.rightT;
    }

    @Override
    public T getValue() {
        return this.element;
    }

    @Override
    public void setValue(T value) {
        this.element = value;
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.leftT = left;
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.rightT = right;
    }

    @Override
    public boolean isLeaf() {
        return (this.leftT == null && this.rightT == null);
    }

    @Override
    public int height() {
        if(isLeaf())
        {
            return 0;
        }
        int hright = 0, hleft = 0;
        if(this.rightT != null)
        {
            hright = this.rightT.height();

        }
        if(this.leftT != null)
        {
            hleft = this.leftT.height();
        }
        return Math.max(hleft, hright) + 1;
    }

    @Override
    public int size() {
        if(isLeaf())
        {
            return 1;
        }
        int sizeR = 0, sizeL = 0;
        if(this.rightT != null)
        {
            sizeR = this.rightT.size();
        }
        if(this.leftT != null)
        {
            sizeL = this.leftT.size();
        }
        return (sizeR + sizeL + 1);
    }

    @Override
    public void clear() {
        this.leftT = null;
        this.rightT = null;
    }

    @Override
    public String preOrder() {
        return preOrder(" ", " ");
    }

    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        String str = separationBeforeVal + this.element.toString() + separationAfterVal;
        if(isLeaf())
        {
            return str;
        }
        if(this.leftT != null)
        {
            str += this.leftT.preOrder(separationBeforeVal, separationAfterVal);
        }
        if(this.rightT != null)
        {
            str += this.rightT.preOrder(separationBeforeVal, separationAfterVal);
        }
        return str;
    }

    @Override
    public String inOrder() {
        return inOrder(" ", " ");
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        String str = "";
        if(isLeaf())
        {
            return separationBeforeVal + this.element.toString() + separationAfterVal;
        }
        if(this.leftT != null)
        {
            str += this.leftT.inOrder(separationBeforeVal, separationAfterVal);
        }
        str += separationBeforeVal + this.element.toString() + separationAfterVal;
        if(this.rightT != null)
        {
            str += this.rightT.inOrder(separationBeforeVal, separationAfterVal);
        }
        return str;
    }

    @Override
    public String postOrder() {
        return postOrder(" ", " ");
    }

    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        String str = "";
        if(isLeaf())
        {
            return separationBeforeVal + this.element.toString() + separationAfterVal;
        }
        if(this.leftT != null)
        {
            str += this.leftT.postOrder(separationBeforeVal, separationAfterVal);
        }
        if(this.rightT != null)
        {
            str += this.rightT.postOrder(separationBeforeVal, separationAfterVal);
        }
        str += separationBeforeVal + this.element.toString() + separationAfterVal;
        return str;
    }
}
