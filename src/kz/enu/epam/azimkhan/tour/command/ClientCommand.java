package kz.enu.epam.azimkhan.tour.command;

import kz.enu.epam.azimkhan.tour.entity.Role;
import kz.enu.epam.azimkhan.tour.entity.User;

/**
 * Client command
 */
public abstract class ClientCommand extends ActionCommand{
	@Override
	public boolean checkAccess(User user) {
		if (user != null){
			String rolename = user.getRole().getRolename();
			return rolename.equals(Role.ROLE_CLIENT);
		}

		return false;
	}
}
