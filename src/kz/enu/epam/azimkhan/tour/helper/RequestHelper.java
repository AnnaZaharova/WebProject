package kz.enu.epam.azimkhan.tour.helper;

import kz.enu.epam.azimkhan.tour.command.*;
import kz.enu.epam.azimkhan.tour.command.admin.AddTourCommand;
import kz.enu.epam.azimkhan.tour.command.admin.DeleteTourCommand;
import kz.enu.epam.azimkhan.tour.command.admin.ManagerCommand;
import kz.enu.epam.azimkhan.tour.command.admin.UpdateTourCommand;
import kz.enu.epam.azimkhan.tour.command.client.AccountCommand;
import kz.enu.epam.azimkhan.tour.command.client.OrderCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import java.util.HashMap;

/**
 * Finds command
 */
public enum RequestHelper {

    INSTANCE;

    private final Logger logger = Logger.getRootLogger();
    /**
     * Request parameter name for command
     */
    public static final String COMMAND_PARAMETER = "c";

    /**
     * action commands
     */
    private HashMap<String, ActionCommand> commands = new HashMap<String, ActionCommand>();
    {
        //Everyone commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
		commands.put("tours", new ViewToursCommand());
        //Client commands
        commands.put("account", new AccountCommand());
        commands.put("order", new OrderCommand());
        //Admin commands
		commands.put("manager", new ManagerCommand());
		commands.put("add_tour", new AddTourCommand());
        commands.put("delete_tour", new DeleteTourCommand());
        commands.put("update_tour", new UpdateTourCommand());
    }

    /**
     * Find command from request
     * @param request
     * @return
     */
    public ActionCommand getCommand(ServletRequest request){
        String action = request.getParameter(COMMAND_PARAMETER);
        return getCommand(action);
    }

    /**
     *  Find command by name
     * @param action
     * @return
     */
    public ActionCommand getCommand(String action){

        ActionCommand command = commands.get(action);

        if (command == null){
            command = new EmptyCommand();
        }

        return command;
    }
}
