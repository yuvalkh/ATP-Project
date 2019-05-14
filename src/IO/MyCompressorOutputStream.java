package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    public MyCompressorOutputStream(OutputStream os) {
        out = os;
    }

    @Override
    public void write(int b) {

    }

    /**
     * The compresser will compress it to about 1/8 of its original length.
     * 00100101 10100101 01100100 01011011 11101000 011001
     *
     * @param b the info of the maze in bytes
     */
    @Override
    public void write(byte[] b) throws IOException {
        ArrayList<Byte> list = new ArrayList<>();
        System.out.println();
        byte counter = 0;
        byte sum = 0;
        byte[] finalByte;
        for (int i = 12; i < b.length; i++) {
            if (counter == 8) {
                list.add(sum);
                sum = 0;
                counter = 0;
            }
            if (b[i] == 1) {
                sum += Math.pow(2, 7 - counter);
            }
            counter++;
        }
        if (sum != 0) {
            list.add(sum);
        }
        finalByte = new byte[12 + list.size()];
        for (int i = 0; i < 12; i++) {//we copy the properties
            finalByte[i] = b[i];
        }
        for (int i = 0; i < list.size(); i++) {//we copy the compressed maze
            finalByte[i + 12] = list.get(i);
        }
        if (out instanceof ObjectOutputStream) {
            ((ObjectOutputStream) out).writeObject(finalByte);
        } else {
            out.write(finalByte);
        }
        out.flush();
    }
}
