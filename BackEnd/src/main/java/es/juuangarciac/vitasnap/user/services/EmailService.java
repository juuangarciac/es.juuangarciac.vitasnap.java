package es.juuangarciac.vitasnap.user.services;


import es.juuangarciac.vitasnap.user.domain.User;

public interface EmailService {

    boolean sendRegistrationEmail(User user);

}