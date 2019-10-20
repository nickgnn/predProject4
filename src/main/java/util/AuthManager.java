package util;

import exception.DBException;
import model.User;
import org.apache.commons.lang3.StringUtils;
import service.Service;
import service.UserService;

public class AuthManager {
    private static Service service = UserService.getInstance();

    public static boolean isLogin(String name, String password) throws DBException {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }

        User user = service.getUserByName(name);

        if (user == null) {
            return false;
        }

        return password.equals(user.getPassword());
    }
}
