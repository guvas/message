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
        when(brukerService.hentBruker(anyString())).thenReturn(new TestData().testBruker1());
        when(meldingService.getAlleMineMeldinger(any(Bruker.class))).thenReturn(new TestData().testMeldinger());

        String result = loginController.login(new TestData().testBruker1().getName(), model);

        verify(brukerService, times(1)).hentBruker(new TestData().testBruker1().getName());
        verify(meldingService, times(1)).getAlleMineMeldinger(any(Bruker.class));

        Object list = model.get("meldinger");
        assertNotNull(list);
        assertThat(model, hasEntry("username", (Object) new TestData().testBruker1().getName()));
        assertEquals(result, "meldinger");

    }

    @Test
    public void test_login_opprett_bruker_hvis_ikke_finnes_i_db(){
        when(brukerService.hentBruker(anyString())).thenReturn(null);
        when(brukerService.createBruker(any(Bruker.class))).thenReturn(new TestData().testBruker2());

        String result = loginController.login(new TestData().testBruker1().getName(), model);

        verify(brukerService, times(1)).hentBruker(new TestData().testBruker1().getName());
        verify(brukerService, times(1)).createBruker(any(Bruker.class));

        assertEquals(result, "meldinger");
    }
}
