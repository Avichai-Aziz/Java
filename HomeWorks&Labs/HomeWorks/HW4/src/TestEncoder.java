import java.io.*;

public class TestEncoder {

    public static void main(String[] args) throws IOException, IllegalArgumentException  {
    
		try {
			File file = new File(args[0]); //args[0] is a text file.
			if(!file.exists()) {
				throw new IllegalArgumentException("Error, text file must be entered as argument.");
			}
			FileReader fileReader = new FileReader(file);  
            FileWriter fileEncrypted = new FileWriter("encrypted_" + args[0]);
        	FileWriter fileDecrypted = new FileWriter("decrypted_" + args[0]);
            
        	char[] array = new char[9999];
            int size = ((fileReader.read(array)) * 3);

            StringWriter stringWriter = new StringWriter();
            EncryptorWriter encryptorWriter = new EncryptorWriter(stringWriter);
            encryptorWriter.write(array);
            
            for(int i=0; i<size; i++) {
            	fileEncrypted.write(stringWriter.toString().charAt(i));
            }

            StringReader stringReader = new StringReader(stringWriter.toString());
            DecryptorReader decryptorReader = new DecryptorReader(stringReader);
            
            char[] values = new char[9999];
            decryptorReader.read(values);
            size = (size / 3);
            
            for(int i=0; i<size; i++) {
                fileDecrypted.write(values[i]);
            }
            //flush and close files
            fileReader.close();
            fileEncrypted.flush();
            fileDecrypted.flush();
            fileEncrypted.close();
            fileDecrypted.close();
            encryptorWriter.flush();
            encryptorWriter.close();
            decryptorReader.close();
        }
        
		catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }
}
