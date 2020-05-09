package no.message.service;

import no.message.model.Bruker;
import no.message.repository.BrukerRepository;
import no.message.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BrukerServiceTest {
    @Mock
    private BrukerRepository brukerRepository;


    private BrukerService brukerService;

    @Before
    public void setUp(){
        brukerService = new BrukerService();
        brukerService.setBrukerRepository(brukerRepository);
    }

    @Test
    public void test_findByName(){
        when(brukerService.findByName(anyString())).thenReturn(new TestData().testBruker2());

        Bruker bruker = brukerService.findByName(new TestData().testBruker2().getName());

        verify(brukerRepository, times(1)).findByName(anyString());
        assertEquals(bruker.getBrukerid(), new TestData().testBruker2().getBrukerid());
        assertEquals(bruker.getName(), new TestData().testBruker2().getName());
    }

    @Test
    public void test_createBruker(){
        when(brukerService.createBruker(any(Bruker.class))).thenReturn(new TestData().testBruker1());

        Bruker bruker = brukerService.createBruker(new TestData().testBruker1());

        verify(brukerRepository, times(1)).save(any(Bruker.class));
        assertEquals(bruker.getBrukerid(), new TestData().testBruker1().getBrukerid());
        assertEquals(bruker.getName(), new TestData().testBruker1().getName());

    }

    @Test
    public void test_hetBruker(){
        when(brukerService.hentBruker(anyString())).thenReturn(new TestData().testBruker1());

        Bruker bruker = brukerService.hentBruker(new TestData().testBruker1().getName());

        verify(brukerRepository, times(1)).findByName(anyString());

        assertEquals(bruker.getBrukerid(), new TestData().testBruker1().getBrukerid());
        assertEquals(bruker.getName(), new TestData().testBruker1().getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_hentBruker_null(){
        Bruker bruker = null;

        when(brukerService.hentBruker(anyString())).thenReturn(bruker);
        when(brukerService.hentBruker(anyString())).thenThrow(new NullPointerException(new TestData().testBruker1().getName() + " finnes ikke i db"));

        Bruker bruker1 = brukerService.hentBruker(new TestData().testBruker1().getName());

        assertEquals(bruker1.getName(), new TestData().testBruker1().getName());
    }
}
