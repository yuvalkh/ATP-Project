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
}
