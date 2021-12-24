package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();

	private static void incorrectValues(){
		System.err.println(tokenizer);
		System.err.println(myStack);
		System.exit(1);
	}

	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');

		tokenizer.nextToken();
		while((tokenizer.ttype != StreamTokenizer.TT_EOF)){
			if(tokenizer.ttype == StreamTokenizer.TT_WORD)
			 {
				 if(tokenizer.sval.equals("quit")){
					 break;
				 }
				 else{
					 incorrectValues();
				 }
			 }
			 if(tokenizer.ttype == StreamTokenizer.TT_NUMBER){
				 myStack.push(tokenizer.nval);
			 }
			 else {
				 char c = (char) tokenizer.ttype;
				 double firstNum = 0, secondNum = 0, sum = 0;
				 try {
					 firstNum = myStack.pop();
					 secondNum = myStack.pop();
				 } catch (NullPointerException npe) {
					 incorrectValues();
				 }
				 switch (c) {
					 case '+':
						 sum = secondNum + firstNum;
						 myStack.push(sum);
						 break;
					 case '-':
						 sum = secondNum - firstNum;
						 myStack.push(sum);
						 break;
					 case '*':
						 sum = secondNum * firstNum;
						 myStack.push(sum);
						 break;
					 case '/':
						 sum = secondNum / firstNum;
						 myStack.push(sum);
						 break;
					 default:
						 incorrectValues();
						 break;
				 }
			 }
			 tokenizer.nextToken();
		}
		if(myStack.isEmpty())
		{
			incorrectValues();
		}
		else{
			double result = myStack.pop();
			if(!myStack.isEmpty())
			{
				incorrectValues();
			}
			System.out.println(result);
		}
	}

}
