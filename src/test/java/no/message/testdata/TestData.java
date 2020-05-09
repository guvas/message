package no.message.testdata;

import no.message.model.Bruker;
import no.message.model.Meldinger;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public Bruker testBruker1(){
        Bruker bruker = new Bruker();
        bruker.setBrukerid(1L);
        bruker.setName("Some Name1");
        return bruker;
    }
    public Bruker testBruker2(){
        Bruker bruker = new Bruker();
        bruker.setBrukerid(2L);
        bruker.setName("Some Name2");
        return bruker;
    }
    public Meldinger testMelding(){
        Meldinger meldinger = new Meldinger();
        meldinger.setFrabruker(testBruker1());
        meldinger.setTilbruker(testBruker2());
        meldinger.setMelding("some text");
        meldinger.setMldingId(1L);
        return meldinger;
    }
    public List<Meldinger> testMeldinger(){
        Meldinger meldinger = new Meldinger();
        meldinger.setFrabruker(testBruker1());
        meldinger.setTilbruker(testBruker2());
        meldinger.setMelding("some melding");
        meldinger.setMldingId(1L);
        List<Meldinger> list = new ArrayList<>();
        list.add(meldinger);
        return list;
    }
}
