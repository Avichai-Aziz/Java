import java.io.IOException;
import java.io.Reader;

public class DecryptorReader extends Reader {
    Reader reader;
    public DecryptorReader (Reader r){
        this.reader = r;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        char[] temp_cbuf = new char[3];
        int numOfChars = 0;
        for(int i=off; i<len+off && reader.ready(); i++){
            reader.read(temp_cbuf,0,3);
            cbuf[i] = decodeChar(temp_cbuf); //needs to read 3 chars not 1
            numOfChars +=3;
        }
        return numOfChars;
    }
    private char decodeChar(char[] str){
        if((str[0]==str[1] )|| (str[0]==str[2]))
        	return str[0];
        else if(str[1]==str[2])
        	return str[1];
        else return (char) -1;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
