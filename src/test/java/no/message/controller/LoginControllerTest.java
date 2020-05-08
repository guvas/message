package no.message.controller;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.service.BrukerService;
import no.message.service.MeldingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasEntry;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginControllerTest {
    private LoginController loginController;
    @Mock
    private BrukerService brukerService;
    @Mock
    private MeldingService meldingService;

    private ModelMap model;

    @Before
    public void setUp(){
        loginController = new LoginController(brukerService, meldingService);
        model = new ModelMap();
    }

    @Test
    public void test_login(){
        when(brukerService.hentBruker(anyString())).thenReturn(testBruker1());
        when(meldingService.getAlleMineMeldinger(any(Bruker.class))).thenReturn(testMeldinger());

        String result = loginController.login(testBruker1().getName(), model);

        verify(brukerService, times(1)).hentBruker(testBruker1().getName());
        verify(meldingService, times(1)).getAlleMineMeldinger(any(Bruker.class));

        Object list = model.get("meldinger");
        assertNotNull(list);
        assertThat(model, hasEntry("username", (Object) testBruker1().getName()));
        assertEquals(result, "inbox");

    }


    @Test
    public void test_login_opprett_bruker_hvis_ikke_finnes_i_db(){
        when(brukerService.hentBruker(anyString())).thenReturn(null);
        when(brukerService.createBruker(any(Bruker.class))).thenReturn(testBruker2());

        String result = loginController.login(testBruker1().getName(), model);

        verify(brukerService, times(1)).hentBruker(testBruker1().getName());
        verify(brukerService, times(1)).createBruker(any(Bruker.class));

        assertEquals(result, "inbox");
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

    private List<Meldinger> testMeldinger(){
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
