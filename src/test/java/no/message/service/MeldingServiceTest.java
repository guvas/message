package no.message.service;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.repository.MeldingRepository;
import no.message.testdata.TestData;
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
        when(meldingService.getAlleMineMeldinger(any(Bruker.class))).thenReturn(new TestData().testMeldinger());
        List<Meldinger> list = meldingService.getAlleMineMeldinger(new TestData().testBruker2());

        verify(meldingRepository, times(1)).findAllByTilbruker(any(Bruker.class));
        assertEquals(list.size(), new TestData().testMeldinger().size());
    }

    @Test
    public void test_sendMelding(){
        when(meldingService.sendMelding(any(Meldinger.class))).thenReturn(new TestData().testMelding());
        Meldinger result = meldingService.sendMelding(new TestData().testMelding());

        verify(meldingRepository, times(1)).save(any(Meldinger.class));

        assertEquals(result.getFrabruker().getName(), new TestData().testMelding().getFrabruker().getName());
        assertEquals(result.getMelding(), new TestData().testMelding().getMelding());
        assertEquals(result.getTilbruker().getName(), new TestData().testMelding().getTilbruker().getName());
        assertEquals(result.getMeldingId(), new TestData().testMelding().getMeldingId());
        assertEquals(result.toString(), new TestData().testMelding().toString());
    }


}
