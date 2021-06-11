package academy.pocu.comp3500.assignment2.app;

import academy.pocu.comp3500.assignment2.Indent;

public class LogBox {
    private Indent indent;
    private String log;

    public void setLog(String log) {
        this.log = log;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }

    public String getLog() {
        return log;
    }

    public Indent getIndent() {
        return indent;
    }
}
