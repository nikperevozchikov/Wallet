package ru.aeon.test.dto.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aeon.test.dto.UserDTO;
import ru.aeon.test.models.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/** The Class to map Database Objects and Data Transfer Objects */
public class UserMapper {

	@Autowired
	static
    PasswordEncoder encoder;

	/** converts DTO to Database Object */
	public static User dtoToDO(UserDTO a) {
		User account = new User.UserBuilder().setDateCreated(new Date()).setId(a.getId())
				.setUserName(a.getUserName()).setEmail(a.getEmail()).setPassword(encoder.encode(a.getPassword())).build();
		return account;

	}

	/** Converts Database Object to DTO */
	public static UserDTO doToDTO(User a) {
		double balance = a.getTransactions().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
		UserDTO dto = new UserDTO.UserDTOBuilder().setId(a.getId())
				.setDateCreated(a.getDateCreated()).setUserName(a.getUsername()).setId(a.getId()).setEmail(a.getEmail()).setPassword(a.getPassword())
				.setBalance(new BigDecimal(balance)).build();
		return dto;
	}

	/** Converts List of Database Object to List of DTO */
	public static List<UserDTO> doToDTOList(List<User> account) {
		return account.stream().map(UserMapper::doToDTO).collect(Collectors.toList());
	}

}
