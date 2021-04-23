package ru.aeon.test.service;

import ru.aeon.test.exception.UserNotFoundException;
import ru.aeon.test.models.User;

import java.util.List;

public interface UserService {

    /**
     * Save a user account
     *
     * @param t
     * @return
     * @throws Exception
     */
    User save(User t) throws Exception;

    /**
     * Update a user account
     *
     * @param user account
     * @param user id
     * @return
     * @throws Exception
     */
    User update(User t, Long id) throws Exception;

    /**
     * @return list of users
     */
    List<User> getList();

    /**
     * @param accountId
     * @return user account by account id
     * @throws Exception
     */
    User userAccountByPK(Long accountId) throws UserNotFoundException;
}
