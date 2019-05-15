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
     * @param b the info of the maze in bytes
     */
    @Override
    public void write(byte[] b) throws IOException {
        ArrayList<Byte> list = new ArrayList<>();
        byte counter = 0;
        System.out.println("Maze Before compress:");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        byte sum = 0;
        byte[] finalByte;
        int lastIndex = 12;
        for (int i = 12; i < b.length; i++) {
            if (counter == 8) {
                list.add(sum);
                sum = 0;
                counter = 0;
                lastIndex = i;
            }
            if (b[i] == 1) {
                sum += Math.pow(2, 7 - counter);
            }
            counter++;
        }
        if (sum != 0) {
            sum = 0;
            counter = 0;
            for (int i = b.length - 1; i >= lastIndex; i--) {
                if(b[i] == 1){
                    sum += Math.pow(2, counter);
                }
                counter++;
            }
            list.add(sum);
        }
        if(b.length % 8 != 0 && sum == 0){
            list.add((byte)0);
        }
        finalByte = new byte[12 + list.size()];
        for (int i = 0; i < 12; i++) {//we copy the properties
            finalByte[i] = b[i];
        }
        for (int i = 0; i < list.size(); i++) {//we copy the compressed maze
            finalByte[i + 12] = list.get(i);
        }

        System.out.println();
        System.out.println("Maze After compress");
        for (int i = 0; i < finalByte.length; i++) {
            System.out.print((finalByte[i]) & 0xff);
            System.out.print(" ");
        }
        if (out instanceof ObjectOutputStream) {
            ((ObjectOutputStream) out).writeObject(finalByte);
        } else {
            out.write(finalByte);
        }
        out.flush();
    }
}
