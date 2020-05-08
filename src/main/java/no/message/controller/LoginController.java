package no.message.controller;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.service.BrukerService;
import no.message.service.MeldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private BrukerService brukerService;
    private MeldingService meldingService;

    @Autowired
    public LoginController(BrukerService brukerService, MeldingService meldingService){
        this.brukerService = brukerService;
        this.meldingService = meldingService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam("username") String brukernavn, ModelMap model) {
        Bruker brukeren = brukerService.hentBruker(brukernavn);
        if(brukeren != null){
            model.addAttribute("username", brukernavn);
            List<Meldinger> meldingers = meldingService.getAlleMineMeldinger(brukeren);
            if(!meldingers.isEmpty()){
                model.addAttribute("meldinger", meldingers);
            }
            return "inbox";
        }else {
            Bruker bruker = new Bruker();
            bruker.setName(brukernavn);
            brukerService.createBruker(bruker);
        }
        model.addAttribute("username", brukernavn);
        return "inbox";
    }



}
