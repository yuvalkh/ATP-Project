package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;
    int lastb = -1;
    public MyCompressorOutputStream(OutputStream os) {
        out = os;
    }

    @Override
    public void write(int b) {

    }

    /**
     * need to compress the byte array into something
     * that is smaller
     * @param b the info of the maze in bytes
     */
    @Override
    public void write(byte[] b) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int zeroORone = 0;
        for (int i = 12; i < b.length; i++) {
            if(zeroORone == b[i] && list.get(list.size() - 1) < 255){//if it's the same as before and it can increment
                list.set(list.size() - 1,list.get(list.size()) + 1);
            }
            else if(zeroORone == b[i] && list.get(list.size() - 1) >= 255){//if it's the same as before but it cannot be incremented
                list.add(0);
                list.add(1);
            }
            else if(zeroORone == b[i] && list.get(list.size() - 1) < 255){//if it's not the same as before
                list.add(1);
                if(zeroORone == 1){
                    zeroORone = 0;
                }
                else{
                    zeroORone = 1;
                }
            }
        }
        byte[] finalByte = new byte[12+list.size()];
        for (int i = 0; i < 12 ; i++) {
            finalByte[i] = b[i];
        }
        for (int i = 0; i < list.size(); i++) {
            finalByte[12 + i] = list.get(i).byteValue();
        }
        out.write(finalByte);
        out.flush();
    }
}
