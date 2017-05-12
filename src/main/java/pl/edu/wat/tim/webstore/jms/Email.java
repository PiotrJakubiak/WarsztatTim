package pl.edu.wat.tim.webstore.jms;

import java.io.Serializable;

/**
 * Created by Piotr on 12.05.2017.
 */
public class Email implements Serializable{

    private static final long serialVersionUID = -295422703255886286L;

    private String to;
    private String body;

    public Email() {
    }

    public Email(String to, String body) {
        this.to = to;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("Email{to=%s, body=%s}", getTo(), getBody());
    }

}
