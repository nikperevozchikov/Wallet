package ru.aeon.test.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aeon.test.exception.UserNotFoundException;
import ru.aeon.test.models.User;
import ru.aeon.test.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
	@Autowired
	UserRepository userRepository;
	@Override
	public User userAccountByPK(Long userAccountId) throws UserNotFoundException {
		return userRepository.findById(userAccountId).orElseThrow(
				() -> new UserNotFoundException(String.format("userAccount with '%d' not found ", userAccountId)));
	}

	/**
	 * this operations registers a user and creates and userAccount for him/her with
	 * minimal details
	 */
	@Override
	@Transactional
	public User save(User userAccount) throws Exception {
		if (userAccount.getUsername() != null) {
			if (userAccount.getUsername().length() < 5) {
				throw new Exception("user name is should be 5 characters of more");
			}
			return userRepository.save(userAccount);
		}
		throw new Exception("user name is mandatory");
	}

	/**
	 * this operation updates a users userAccount information and checks for
	 * concurrent user modification
	 */
	@Override
	@Transactional
	public User update(User userAccount, Long userAccountId) throws Exception {
		if (userAccount.getUsername() != null) {
			userAccount.setId(userAccountId);
			try {
				return userRepository.save(userAccount);
			} catch (Exception e) {
				throw new Exception("Try again");
			}
		}
		throw new Exception("user name is mandatory");

	}

	/**
	 * this operation gets all userAccount lists and their respective transaction
	 * transactions
	 */
	@Override
	public List<User> getList() {
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);

	}

}
