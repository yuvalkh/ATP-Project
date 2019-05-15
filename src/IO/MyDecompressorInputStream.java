package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream inputstream) {
        in = inputstream;
    }


    @Override
    public int read() throws IOException {

        return 0;
    }

    /**
     *
     * @param b the byte compressed by the compressor class.
     * @return
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        int numOfRead = 0;
        int LeftOver;
        int indexCounter = 12;
        int tempSize;
        LinkedList<Byte> current = new LinkedList<>();
        byte[] Compressed = new byte[in.available()];
        in.read(Compressed);//puts inside it the compressed array
        for (int i = 0; i < 12; i++) {//first, we put all the properties
            b[i] = Compressed[i];
            numOfRead++;
        }
        for (int i = 12; i < Compressed.length; i++) {
            LeftOver = (Compressed[i] & 0xff);
            if (i != Compressed.length - 1) {//if we're not in the end we just put it with the length 8
                for (int j = 0; j < 8 && LeftOver != 0; j++) {
                    current.addFirst((byte)(LeftOver % 2));
                    LeftOver /= 2;
                }
                tempSize = current.size();
                for (int j = 0; j < 8 - tempSize; j++) {
                    current.addFirst((byte)0);
                }
            }
            else{//we're not in the end therefore we need to see how much 0 we need to add
                byte howManyNumbers = (byte)((b.length - 12) % 8);
                if(howManyNumbers == 0){
                    howManyNumbers = 8;
                }
                for (int j = 0; j < 8 && LeftOver != 0; j++) {
                    current.addFirst((byte)(LeftOver % 2));
                    LeftOver /= 2;
                }
                for (int j = current.size(); j < howManyNumbers; j++) {
                    current.addFirst((byte)0);
                }
            }
            for (int j = 0; j < current.size(); j++) {
                b[indexCounter] = current.get(j);
                indexCounter++;
            }
            current = new LinkedList<>();
        }
        return numOfRead;
    }
}
