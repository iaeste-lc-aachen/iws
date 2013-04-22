package net.iaeste.iws.fitnesse;

import net.iaeste.iws.api.Administration;
import net.iaeste.iws.api.requests.CreateUserRequest;
import net.iaeste.iws.api.util.Fallible;
import net.iaeste.iws.client.notifications.NotificationMessage;
import net.iaeste.iws.fitnesse.callers.AdministrationCaller;
import net.iaeste.iws.fitnesse.exceptions.StopTestException;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 4/14/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public final class CreateUser extends AbstractFixture<Fallible> {

    private static final Pattern STRING_PATTERN = Pattern.compile("=");
    private final Administration administration = new AdministrationCaller();
    private CreateUserRequest request = new CreateUserRequest();
    private NotificationMessage notificationMessage = null;

    public void setUsernameAndPassword(final String username, final String password) {
        setUsername(username);
        setPassword(password);
    }

    public void setNewFirstnameAndLastname(final String firstName, final String lastName) {
        request.setFirstname(firstName);
        request.setLastname(lastName);
    }

    public void setNewUsernameAndPassword(final String username, final String password) {
        request.setUsername(username);
        request.setPassword(password);
    }

    public void createUser() {
        execute();
    }

    public void readNotificationFromQueue() {
        notificationMessage = getNextNotification();
    }

    public String readNotificationMessage() {
        return notificationMessage != null ? notificationMessage.getMessage() : null;
    }

    public String getActivationCode()
    {
         return readActivationCode(notificationMessage.getMessage());
    }

    @Override
    public void execute() throws StopTestException {
        createSession();
        setResponse(administration.createUser(getToken(), request));
    }

    @Override
    public void reset() {
        // Resets the Response Object
        super.reset();

        request = null;
    }

    private static String readActivationCode(final String notificationMessage) {
        final String[] array = STRING_PATTERN.split(notificationMessage);

        return array[2].trim();
    }
}