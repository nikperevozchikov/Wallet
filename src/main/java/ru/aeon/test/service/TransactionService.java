package ru.aeon.test.service;

import ru.aeon.test.dto.TransactionDTO;
import ru.aeon.test.exception.BalanceLowException;
import ru.aeon.test.exception.UserNotFoundException;
import ru.aeon.test.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

/** Service for Transaction */
public interface TransactionService {

    /**
     * Save a transaction
     *
     * @param t
     * @return
     * @throws Exception
     */
    Transaction save(Transaction t) throws Exception;

    /**
     * Update a transaction
     *
     * @param t
     * @param id
     * @return
     * @throws Exception
     */
    Transaction update(Transaction t, Long id) throws Exception;

    /**
     * @return list of transactions
     */
    List<Transaction> getList();

    /**
     * gets list of transactions by account id
     *
     * @param accountId
     * @return transaction
     * @throws Exception
     */
    List<Transaction> transactionsByUserAccountID(Long accountId) throws UserNotFoundException;

//    /**
//     * gets transaction by ref id
//     *
//     * @param transaction reference id
//     * @return transaction
//     * @throws Exception
//     */
//    Transaction transactionByRef(Long txnRef) throws Exception;

    /**
     * @param accountId
     * @return balance of user
     * @throws Exception
     */
    BigDecimal balanceByUserAccountID(Long accountId) throws UserNotFoundException;

    List<Transaction> transactions();

    Transaction createTransaction(Transaction txn) throws BalanceLowException;

    /**
     * Transfer Money from one user to other
     *
     * @param transaction       details
     * @param toUserAccountId
     * @param fromUserAccountId
     * @return list of parties involved if successful
     */
    List<Transaction> transfer(TransactionDTO walletDTO, Long toUserAccountId, Long fromUserAccountId) throws UserNotFoundException,BalanceLowException;
}
