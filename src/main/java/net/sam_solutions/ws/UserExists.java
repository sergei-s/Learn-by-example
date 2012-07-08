package net.sam_solutions.ws;

import javax.xml.bind.annotation.*;

/**
 * User: Sergei Savenko
 * Date: 11.06.12
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserExists {
    @XmlElement
    public boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
