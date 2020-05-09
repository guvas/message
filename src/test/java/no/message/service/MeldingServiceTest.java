package no.message.service;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.repository.MeldingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MeldingServiceTest {
    @Mock
    private MeldingRepository meldingRepository;

    private MeldingService meldingService;

    @Before
    public void setUp(){
        meldingService = new MeldingService();
        meldingService.setMeldingRepository(meldingRepository);
    }

    @Test
    public void test_getAlleMineMeldinger(){
        when(meldingService.getAlleMineMeldinger(any(Bruker.class))).thenReturn(testListMeldinger());
        List<Meldinger> list = meldingService.getAlleMineMeldinger(testBruker2());

        verify(meldingRepository, times(1)).findAllByTilbruker(any(Bruker.class));
        assertEquals(list.size(), testListMeldinger().size());
    }

    @Test
    public void test_sendMelding(){
        when(meldingService.sendMelding(any(Meldinger.class))).thenReturn(testMelding());
        Meldinger result = meldingService.sendMelding(testMelding());

        verify(meldingRepository, times(1)).save(any(Meldinger.class));

        assertEquals(result.getFrabruker().getName(), testMelding().getFrabruker().getName());
        assertEquals(result.getMelding(), testMelding().getMelding());
        assertEquals(result.getTilbruker().getName(), testMelding().getTilbruker().getName());
        assertEquals(result.getMeldingId(), testMelding().getMeldingId());
    }

    private Bruker testBruker1(){
        Bruker bruker = new Bruker();
        bruker.setBrukerid(1L);
        bruker.setName("Some Name1");
        return bruker;
    }
    private Bruker testBruker2(){
        Bruker bruker = new Bruker();
        bruker.setBrukerid(2L);
        bruker.setName("Some Name2");
        return bruker;
    }

    private Meldinger testMelding(){
        Meldinger meldinger = new Meldinger();
        meldinger.setFrabruker(testBruker1());
        meldinger.setTilbruker(testBruker2());
        meldinger.setMelding("some text");
        meldinger.setMldingId(1L);
        return meldinger;
    }

    private List<Meldinger> testListMeldinger(){
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
