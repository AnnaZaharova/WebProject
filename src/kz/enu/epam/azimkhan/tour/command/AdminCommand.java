package kz.enu.epam.azimkhan.tour.command;

import kz.enu.epam.azimkhan.tour.entity.Role;
import kz.enu.epam.azimkhan.tour.entity.User;

/**
 * Admin command that is allowed for execution for admins only
 */
public abstract class AdminCommand extends ActionCommand{

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

}
