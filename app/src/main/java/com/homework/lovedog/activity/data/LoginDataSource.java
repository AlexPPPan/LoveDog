package com.homework.lovedog.activity.data;

import com.homework.lovedog.MyApp;
import com.homework.lovedog.activity.data.model.LoggedInUser;
import com.homework.lovedog.bean.User;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        User user = MyApp.getApplication().getHelper().userQueryUsername(username);
        if (user==null){
            MyApp.getApplication().getHelper().insertUser(username,password);

            User user2 = MyApp.getApplication().getHelper().userQueryUsername(username);
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            user2.getUserid()+"",
                            username);
            return new Result.Success<>(fakeUser);
        }else {

            if (user.getPassword().equals(password)){
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                user.getUserid()+"",
                                username);
                return new Result.Success<>(fakeUser);
            }else {
                return new Result.Error(new IOException());
            }

        }


    }

    public void logout() {
        // TODO: revoke authentication
    }
}