package pl.kl.weatherservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("TEST")
public class TestMailSender implements MailSender{

    public void senEmail(){
        // todo implementation
    }
}
