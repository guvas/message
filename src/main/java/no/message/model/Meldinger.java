package no.message.model;

import javax.persistence.*;

@Entity
public class Meldinger {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long meldingId;

    private String melding;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
    @JoinColumn(name="frabrukerid")
    private Bruker frabruker;

    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
    @JoinColumn(name="tilbrukerid")
    private Bruker tilbruker;

    public Meldinger(Long meldingId, String melding, Bruker frabruker, Bruker tilbruker) {
        this.meldingId = meldingId;
        this.melding = melding;
        this.frabruker = frabruker;
        this.tilbruker = tilbruker;
    }

    public Meldinger() {
    }

    public Long getMeldingId() {
        return meldingId;
    }

    public void setMldingId(Long meldingId) {
        this.meldingId = meldingId;
    }

    public String getMelding() {
        return melding;
    }

    public void setMelding(String melding) {
        this.melding = melding;
    }

    public Bruker getFrabruker() {
        return frabruker;
    }

    public void setFrabruker(Bruker frabruker) {
        this.frabruker = frabruker;
    }

    public Bruker getTilbruker() {
        return tilbruker;
    }

    public void setTilbruker(Bruker tilbruker) {
        this.tilbruker = tilbruker;
    }

    @Override
    public String toString() {
        return "Meldinger{" +
                "meldingId=" + meldingId +
                ", melding='" + melding + '\'' +
                ", frabruker=" + frabruker +
                ", tilbruker=" + tilbruker +
                '}';
    }
}
