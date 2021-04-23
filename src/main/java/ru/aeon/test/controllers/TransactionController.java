package ru.aeon.test.controllers;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aeon.test.dto.TransactionDTO;
import ru.aeon.test.dto.mapper.TransactionMapper;
import ru.aeon.test.exception.BalanceLowException;
import ru.aeon.test.exception.UserNotFoundException;
import ru.aeon.test.models.Transaction;
import ru.aeon.test.service.TransactionService;
import ru.aeon.test.service.UserService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("v1/payment")
public class TransactionController {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@ApiOperation(value = "Fund Wallet ", response = TransactionDTO.class, tags = "transact")
	@PostMapping("/{id}")
	public ResponseEntity add(@PathVariable("id") Long userAccountId, @RequestBody TransactionDTO walletDTO) {
		Transaction saved;
		try {
			walletDTO.setUserAccountId(userAccountId);
			saved = transactionService.createTransaction(TransactionMapper.dtoToDO(walletDTO));
		} catch (BalanceLowException ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<TransactionDTO>(TransactionMapper.doToDTO(saved), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Debit wallet ", response = List.class, tags = "transact")
	@PostMapping("/{toUser}/from/{fromUser}")
	public ResponseEntity transfer(@PathVariable("toUser") Long toUserAccountId,
                                   @PathVariable("fromUser") Long fromUserAccountId, @RequestBody TransactionDTO walletDTO) {
		List<Transaction> both;

		try {
			both = transactionService.transfer(walletDTO, toUserAccountId, fromUserAccountId);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (BalanceLowException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<TransactionDTO>>(TransactionMapper.doToDTOList(both), HttpStatus.OK);
	}
}
