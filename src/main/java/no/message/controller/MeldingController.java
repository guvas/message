package no.message.controller;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.service.BrukerService;
import no.message.service.MeldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/meldinger")
public class MeldingController {
    private MeldingService meldingService;
    private BrukerService brukerService;
    private final String HVISALLEMELDINGENEMINE ="/hvisMeldingeneMine";
    private final String SENDMELDING = "/sendMelding";

    @Autowired
    public MeldingController(MeldingService meldingService, BrukerService brukerService){
        this.meldingService = meldingService;
        this.brukerService = brukerService;
    }

    @RequestMapping(value = SENDMELDING, method = RequestMethod.GET)
    public ResponseEntity<?> sendMelding(@RequestParam(value = "tilbruker") String tilBruker, @RequestParam(value = "melding") String melding, @RequestParam(value = "username") String username, ModelMap model){
        Bruker tilBrukeren = brukerService.hentBruker(tilBruker);
        if(tilBrukeren == null){
            return ResponseEntity.badRequest().body("Bruker " + tilBruker + " finnes ikke i systemet");
        }
        Bruker fraBrukeren = brukerService.hentBruker(username);
        Meldinger opprettMelding = new Meldinger();
        opprettMelding.setMelding(melding);
        opprettMelding.setTilbruker(tilBrukeren);
        opprettMelding.setFrabruker(fraBrukeren);
        Meldinger lagredeMelding = meldingService.sendMelding(opprettMelding);

        return ResponseEntity.ok(lagredeMelding);
    }

    @RequestMapping(value = HVISALLEMELDINGENEMINE, method = RequestMethod.GET)
    public ResponseEntity<?> hvisAlleMeldingenemine(@RequestParam(value = "username") String brukernavn){
        Bruker brukeren = brukerService.hentBruker(brukernavn);
        List<Meldinger> meldingers = meldingService.getAlleMineMeldinger(brukeren);
        return ResponseEntity.ok(meldingers);
    }

}
