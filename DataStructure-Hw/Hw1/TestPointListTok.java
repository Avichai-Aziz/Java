package lab01;

import java.awt.Point;
import java.io.*;


public class TestPointListTok {
	
	public static void main(String args[]) throws IOException {
		ArrayPointList apl = new ArrayPointList();
		InputStreamReader reader = new InputStreamReader(System.in);
		StreamTokenizer tokens = new StreamTokenizer(reader);
		
		while(tokens.nextToken() != StreamTokenizer.TT_EOF)
		{
			if(tokens.sval.equals("add")) 
			{
				tokens.nextToken();
				int x = (int)tokens.nval;
				tokens.nextToken();
				int y = (int)tokens.nval;
				apl.append(new Point(x, y));
			}
			else if(tokens.sval.equals("curr"))
			{
				System.out.println("(" + apl.getCursor().x + "," + apl.getCursor().y + ")");
			}
			else if(tokens.sval.equals("next"))
			{
				System.out.println(apl.goToNext());
			}
			else if(tokens.sval.equals("prev"))
			{
				System.out.println(apl.goToPrior());
			}
			else if(tokens.sval.equals("start"))
			{
				System.out.println(apl.goToBeginning());
			}
			else if(tokens.sval.equals("end"))
			{
				System.out.println(apl.goToEnd());
			}
			else if(tokens.sval.equals("empty"))
			{
				System.out.println(apl.isEmpty());
			}
			else if(tokens.sval.equals("full"))
			{
				System.out.println(apl.isFull());
			}
			else if(tokens.sval.equals("clear"))
			{
				apl.clear();
			}
			else if(tokens.sval.equals("quit"))
			{
				break;
			}
		}
	}
}


