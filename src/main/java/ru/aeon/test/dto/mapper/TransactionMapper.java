package ru.aeon.test.dto.mapper;



import ru.aeon.test.dto.TransactionDTO;
import ru.aeon.test.models.Transaction;

import java.util.List;
import java.util.stream.Collectors;

/** The Class to map Database Objects and Data Transfer Objects */
public class TransactionMapper {

	/** converts DTO to Database Object */
	public static Transaction dtoToDO(TransactionDTO w) {
		Transaction transaction = new Transaction.TransactionBuilder().setUser(w.getUserAccountId())
				.setAmount(w.getAmount()).setId(w.getId()).setDetails(w.getDetails())
				.setTransactionDate(w.getTransactionDate())
				.build();
		return transaction;
	}

	/** Converts Database Object to DTO */
	public static TransactionDTO doToDTO(Transaction w) {
		TransactionDTO transactionDTO = new TransactionDTO.TransactionDTOBuilder()
				.setUserAccountId(w.getUser().getId()).setAmount(w.getAmount()).setId(w.getId())
				.setDetails(w.getDetails()).setTransactionDate(w.getTransactionDate())
				.build();
		return transactionDTO;
	}

	/** Converts List of Database Object to List of DTO */
	public static List<TransactionDTO> doToDTOList(List<Transaction> txns) {
		return txns.stream().map(TransactionMapper::doToDTO).collect(Collectors.toList());
	}
}