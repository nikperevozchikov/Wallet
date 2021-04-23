package ru.aeon.test.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aeon.test.models.Transaction;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

//    /**
//     * gets transaction by id
//     *
//     * @param transaction Ref id
//     * @return transaction
//     */
//    @Query(nativeQuery = true, value = "select * from transaction where transaction_reference = ?")
//    Optional<Transaction> getTransactionByRef(Long txnRef);

    /**
     * get balance in account
     *
     * @param accountId
     * @return balance
     */
    @Query(nativeQuery = true, value = "select coalesce(sum(amount),0.00) from transactions where user_id = ?")
    BigDecimal getBalance(Long accountId);

    /**
     * get list of transactions of particular account
     *
     * @param accountId
     * @return list of transactions
     */
    @Query(nativeQuery = true, value = "select * from transactions where user_id = ?")
    List<Transaction> getTransactionsForUser(Long accountId);
}

