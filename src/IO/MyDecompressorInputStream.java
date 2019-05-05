package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream inputstream) {
        in = inputstream;
    }


    @Override
    public int read() throws IOException {

        return 0;
    }

    @Override
    public int read(byte[] b) throws IOException {
        int numOfRead = 0;
        int index = 12;
        byte oneORzero = 0;
        byte[] Compressed = new byte[in.available()];
        in.read(Compressed);//puts inside it the compressed array
        for (int i = 0; i < 12; i++) {//first, we put all the properties
            b[i] = Compressed[i];
            numOfRead++;
        }
        for (int i = 12; i <Compressed.length; i++) {//second, we put the maze
            for (int j = 0; j < Compressed[i]; j++) {
                b[index] = oneORzero;
                index++;
                numOfRead++;
            }
            if(oneORzero == 1)
                oneORzero = 0;
            else
                oneORzero = 1;
        }
        return numOfRead;
    }
}
