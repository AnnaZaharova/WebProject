package kz.enu.epam.azimkhan.auth.command;

import kz.enu.epam.azimkhan.auth.entity.Role;
import kz.enu.epam.azimkhan.auth.entity.User;
import kz.enu.epam.azimkhan.auth.exception.CommandException;
import kz.enu.epam.azimkhan.auth.resource.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for managing tours
 */
public class ManagerCommand extends ActionCommand{

    /**
     * Only admin has access to this command
     * @param user can be null
     * @return
     */
    @Override
    public boolean checkAccess(User user) {
        if (user != null){
            Role role = user.getRole();
            if (role != null && Role.ROLE_ADMIN.equals(role.getRolename())){
                return true;
            }
        }

        return false;
    }

    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		PathManager pathManager = PathManager.INSTANCE;
		return pathManager.getString("path.page.admin.manager");
	}
}
