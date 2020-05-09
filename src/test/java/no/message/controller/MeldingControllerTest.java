package no.message.controller;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.service.BrukerService;
import no.message.service.MeldingService;
import no.message.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MeldingControllerTest {
    @Mock
    private BrukerService brukerService;
    @Mock
    private MeldingService meldingService;

    private MeldingController meldingController;
    private ModelMap model;

    @Before
    public void setUp(){
        meldingController = new MeldingController(meldingService, brukerService);
        model = new ModelMap();
    }

    @Test
    public void test_sendMelding(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity <?> responseEntityTest = new ResponseEntity(new TestData().testMelding(), header, HttpStatus.OK);

        when(brukerService.hentBruker("test1")).thenReturn(new TestData().testBruker1());
        when(brukerService.hentBruker("test2")).thenReturn(new TestData().testBruker2());
        when(meldingService.sendMelding(any(Meldinger.class))).thenReturn(new TestData().testMelding());


        ResponseEntity<?> response = meldingController.sendMelding("test2", "sometext", "test1", model);

        verify(brukerService,times(2)).hentBruker(anyString());
        assertEquals(response.getStatusCode(), responseEntityTest.getStatusCode());
    }

    @Test
    public void test_test_sendMelding_som_bruker_erNull(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity <?> responseEntityTest = new ResponseEntity(new TestData().testMelding(), header, HttpStatus.BAD_REQUEST);

        ResponseEntity<?> response = meldingController.sendMelding("test2", "sometext", "test1", model);
        when(brukerService.hentBruker("test1")).thenReturn(null);
        verify(brukerService,times(1)).hentBruker(anyString());

        assertEquals(response.getStatusCode(), responseEntityTest.getStatusCode());
    }

    @Test
    public void test_hvisAlleMeldingenemine(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity <?> responseEntityTest = new ResponseEntity(new TestData().testMeldinger(), header, HttpStatus.OK);

        when(brukerService.hentBruker(anyString())).thenReturn(new TestData().testBruker1());
        when(meldingService.getAlleMineMeldinger(any(Bruker.class))).thenReturn(new TestData().testMeldinger());

        ResponseEntity<?> response = meldingController.hvisAlleMeldingenemine(new TestData().testBruker1().getName());

        verify(brukerService, times(1)).hentBruker(anyString());
        verify(meldingService, times(1)).getAlleMineMeldinger(any(Bruker.class));

        assertEquals(response.getStatusCode(), responseEntityTest.getStatusCode());
    }
}
