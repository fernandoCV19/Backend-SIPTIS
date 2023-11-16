package backend.siptis.service.notifications;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WppServiceImpl implements WppService {
    private final String ACCOUNT_SID = "AC63a9e1f2f62cb06f952edf71abd34c64";
    private final String AUTH_TOKEN = "8a3fc48f638be3bb35259af456886396";

    @Override
    public void sendPersonalActivitiesWpp() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber("whatsapp:+59179795112"),
                        new PhoneNumber("whatsapp:+8155695561"),
                        "Hello, there!")
                .create();


        ArrayList<PhoneNumber> list = new ArrayList<>();
        list.add(new PhoneNumber("whatsapp:+59179795112"));
        list.add(new PhoneNumber("whatsapp:+59176461885"));
        list.add(new PhoneNumber("whatsapp:+59178333963"));
        list.add(new PhoneNumber("whatsapp:+59170540185"));
        list.add(new PhoneNumber("whatsapp:+59170795935"));

        for (PhoneNumber phoneNumber : list) {
            message = Message.creator(phoneNumber,
                    message.getFrom(),
                    "wpp").create();
        }

    }

}
