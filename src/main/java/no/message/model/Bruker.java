package no.message.model;

import javax.persistence.*;

@Entity
public class Bruker {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long brukerid;

    private String name;

    public Bruker(Long brukerid, String name) {
        this.brukerid = brukerid;
        this.name = name;
    }

    public Bruker() {
    }

    public Long getBrukerid() {
        return brukerid;
    }

    public void setBrukerid(Long brukerid) {
        this.brukerid = brukerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bruker{" +
                "brukerid=" + brukerid +
                ", name='" + name + '\'' +
                '}';
    }
}
