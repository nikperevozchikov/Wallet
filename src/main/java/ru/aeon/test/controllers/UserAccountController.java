package ru.aeon.test.controllers;



import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aeon.test.dto.TransactionDTO;
import ru.aeon.test.dto.UserDTO;
import ru.aeon.test.dto.mapper.TransactionMapper;
import ru.aeon.test.dto.mapper.UserMapper;
import ru.aeon.test.exception.UserNotFoundException;
import ru.aeon.test.models.Transaction;
import ru.aeon.test.models.User;
import ru.aeon.test.service.TransactionService;
import ru.aeon.test.service.UserService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("v1/users")
public class UserAccountController {

	@Autowired
	private UserService userAccountService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	@ApiOperation(value = "Get All users ", response = List.class)
	public ResponseEntity getUsers() {
		List<User> userAccounts = userAccountService.getList();
		return new ResponseEntity<List<UserDTO>>(UserMapper.doToDTOList(userAccounts), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get User by id", response = UserDTO.class)
	public ResponseEntity getUser(@PathVariable("id") Long id) {
		User userAccount;
		try {
			userAccount = userAccountService.userAccountByPK(id);
		} catch (UserNotFoundException ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(UserMapper.doToDTO(userAccount), HttpStatus.OK);

	}

	@PostMapping
	@ApiOperation(value = "create User", response = UserDTO.class)
	public ResponseEntity createUser(@RequestBody UserDTO userAccountDTO) {
		User saved;
		try {
			saved = userAccountService.save(UserMapper.dtoToDO(userAccountDTO));
		} catch (Exception ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserDTO>(UserMapper.doToDTO(saved), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update User by id", response = UserDTO.class)
	public ResponseEntity updateUser(@PathVariable("id") Long userAccountId,
                                     @RequestBody UserDTO userAccountDTO) {
		User saved;
		try {
			saved = userAccountService.update(UserMapper.dtoToDO(userAccountDTO), userAccountId);
		} catch (Exception ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserDTO>(UserMapper.doToDTO(saved), HttpStatus.OK);
	}

	@GetMapping("/{id}/passbook")
	@ApiOperation(value = "get PassBook by UserId", response = List.class, tags = "getPassBook")
	public ResponseEntity getUserPassbook(@PathVariable("id") Long id) {
		List<Transaction> passbook;
		try {
			passbook = transactionService.transactionsByUserAccountID(id);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TransactionDTO>>(TransactionMapper.doToDTOList(passbook), HttpStatus.OK);
	}
}