package pl.kl.weatherservice;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("PROD")
public class MailSenderImpl implements MailSender{

    public void senEmail(){
        // todo implementation
    }
}
