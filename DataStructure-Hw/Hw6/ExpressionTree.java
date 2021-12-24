package il.ac.telhai.ds.trees;
import java.io.IOException;
import java.io.StreamTokenizer;

public class ExpressionTree extends FullBinaryTree<String>{
    public ExpressionTree(String element) {
        super(element);
    }
    public ExpressionTree(String element, ExpressionTree left, ExpressionTree right) {
        super(element, left, right);
    }
    /*
     * Read the stream tokenizer until EOF assuming and construct
     *  the expression tree corresponding to it.
     * The input contains a prefix expression.
     */
    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {

        tokenizer.nextToken();
        if(tokenizer.ttype == StreamTokenizer.TT_EOF)
        {
            return null;
        }

        if(tokenizer.ttype == (int)('+'))
        {
            ExpressionTree left = createTree(tokenizer);
            ExpressionTree right = createTree(tokenizer);

            return new ExpressionTree("+", left, right);
        }
        else if(tokenizer.ttype == (int)('-'))
        {
            ExpressionTree left = createTree(tokenizer);
            ExpressionTree right = createTree(tokenizer);

            return new ExpressionTree("-", left, right);
        }
        else if(tokenizer.ttype == (int)('*'))
        {
            ExpressionTree left = createTree(tokenizer);
            ExpressionTree right = createTree(tokenizer);

            return new ExpressionTree("*", left, right);
        }
        else if(tokenizer.ttype == (int)('/'))
        {
            ExpressionTree left = createTree(tokenizer);
            ExpressionTree right = createTree(tokenizer);

            return new ExpressionTree("/", left, right);
        }

        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER)
        {
            return new ExpressionTree(Integer.toString((int)tokenizer.nval));
        }

        throw new IOException("illegal input.");

    }

    /*
     * Returns the infix expression corresponding to the current tree (*)
     */
    public String infix() {
        String str = "";
        if(isLeaf())
        {
            return this.getValue().toString();
        }
        if(this.getLeft() != null)
        {
            str += '(' + ((ExpressionTree) this.getLeft()).infix();
        }
        str += " " + this.getValue().toString() + " ";
        if(this.getRight() != null)
        {
            str += ((ExpressionTree) this.getRight()).infix() + ')';
        }
        return str;
    }

    /*
     * Returns the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return preOrder();
    }

    /*
     * Evaluates the expression corresponding to the current tree
     * and returns its value
     */
    public double evaluate() {
        double equationLeft = 0;
        double equationRight = 0;

        if(isLeaf())
        {
            return Double.parseDouble(this.getValue());
        }
        equationLeft = ((ExpressionTree) this.getLeft()).evaluate();
        equationRight = ((ExpressionTree) this.getRight()).evaluate();

        if(this.getValue().equals("+"))
        {
            return equationLeft + equationRight;
        }
        else if(this.getValue().equals("-"))
        {
            return equationLeft - equationRight;

        }
        else if(this.getValue().equals("*"))
        {
            return equationLeft * equationRight;

        }
        return 0;
    }

}
