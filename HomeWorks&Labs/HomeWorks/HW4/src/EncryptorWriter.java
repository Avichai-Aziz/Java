import java.io.IOException;
import java.io.Writer;

public class EncryptorWriter extends Writer {
    private Writer writer;
    public EncryptorWriter (Writer w){
        this.writer = w;
    }
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len;i++)
        {
            char c = cbuf[i+off];
            sb.append(c);
            sb.append(c);
            sb.append(c);// multiplies char by 3 times (in:a out:aaa)
        }

        writer.write(sb.toString());
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
