package com.ash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.config.Jwtprovider;
import com.ash.exceptions.UserException;
import com.ash.models.Users;
import com.ash.reposetory.Userrepository;

@Service
public class UserServiceImplementation implements Userservice {

    @Autowired
    private Userrepository userrepository;

    @Override
    public Users registeruser(Users user) {

        Users newuser = new Users();

        newuser.setEmail(user.getEmail());
        newuser.setFirstName(user.getFirstName());
        newuser.setLastName(user.getLastName());
        newuser.setPassword(user.getPassword());

        return userrepository.save(newuser);
    }

    @Override
    public Users finduserbtid(Integer id) throws UserException {

        return userrepository.findById(id)
                .orElseThrow(() -> new UserException("User not present with id " + id));
    }

    @Override
    public Users finduserbyemail(String email) {
        return userrepository.findByEmail(email);
    }

    @Override
    public Users followuser(Integer requserid, Integer userid2) throws UserException {

        if (requserid.equals(userid2)) {
            throw new UserException("User cannot follow themselves");
        }

        Users requser = finduserbtid(requserid);
        Users user2 = finduserbtid(userid2);

        user2.getFollowers().add(requser.getId());
        requser.getFollowing().add(user2.getId());

        userrepository.save(requser);
        userrepository.save(user2);

        return requser;
    }

    @Override
    public Users updateuser(Users user, Integer userid) throws UserException {

        Users olduser = finduserbtid(userid);

        if (user.getFirstName() != null) {
            olduser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            olduser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            olduser.setEmail(user.getEmail());
        }

        if (user.getGender() != null) {
            olduser.setGender(user.getGender());
        }

        return userrepository.save(olduser);
    }

    @Override
    public List<Users> seachuser(String query) {
        return userrepository.searchUser(query);
    }

    @Override
    public Users finduserbyjwt(String jwt) {

        String email = Jwtprovider.getemailfromjwttoken(jwt);

        if (email == null) {
            return null;
        }

        return userrepository.findByEmail(email);
    }
}