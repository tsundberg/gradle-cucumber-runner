package se.thinkcode.stream;

import java.io.InputStream;
import java.io.OutputStream;

public class StreamConsumer implements Runnable {
    private static final int SLEEPING_TIME = 1;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private boolean stop;

    /**
     * Create a new stream consumer.
     *
     * @param inputStream  input stream to read data from
     * @param outputStream output stream to write data to.
     */
    public StreamConsumer(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.stop = false;
    }

    /**
     * Copies data from the input stream to the output stream. Terminates as
     * soon as the input stream is closed or an error occurs.
     */
    public void run() {
        try {
            while (!stop) {
                while (inputStream.available() > 0 && !stop) {
                    int read = inputStream.read();
                    outputStream.write(read);
                }
                outputStream.flush();
                Thread.sleep(SLEEPING_TIME);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stopProcessing() {
        stop = true;
    }
}
